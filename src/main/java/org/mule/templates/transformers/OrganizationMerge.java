/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This transformer will take two lists as input and create a third one that
 * will be the merge of the previous two. The identity of list's element is
 * defined by its Name.
 * 
 * @author damian.sima
 */
public class OrganizationMerge {

	/**
	 * The method will merge the organizations from the two lists creating a new one.
	 * 
	 * @param organizationsFromOrgA
	 *            organizations from organization A
	 * @param organizationsFromOrgB
	 *            organizations from organization B
	 * @return a list with the merged content of the to input lists
	 */
	List<Map<String, String>> mergeList(List<Map<String, String>> organizationsFromOrgA, List<Map<String, String>> organizationsFromOrgB) {
		List<Map<String, String>> mergedOrganizationList = new ArrayList<Map<String, String>>();

		// Put all organizations from A in the merged List
		for (Map<String, String> organizationFromA : organizationsFromOrgA) {
			Map<String, String> mergedOrganization = createMergedOrganization(organizationFromA);

			if(mergedOrganization == null) {
				continue;
			}
			
			mergedOrganization.put("IdInWorkday", organizationFromA.get("Id"));
			mergedOrganization.put("AvailibilityDateInWorkday", organizationFromA.get("EndDate"));
			mergedOrganizationList.add(mergedOrganization);
		}

		// Add the new organizations from B and update the exiting ones
		for (Map<String, String> organizationFromB : organizationsFromOrgB) {
			Map<String, String> organizationFromA = findOrganizationInList(organizationFromB.get("Name"), mergedOrganizationList);
			if (organizationFromA != null) {
				organizationFromA.put("IdInSap", organizationFromB.get("Id"));
				organizationFromA.put("EndDateInSap", organizationFromB.get("EndDate"));
			} else {
				Map<String, String> mergedOrganization = createMergedOrganization(organizationFromB);

				if(mergedOrganization == null) {
					continue;
				}
				
				mergedOrganization.put("IdInSap", organizationFromB.get("Id"));
				mergedOrganization.put("EndDateInSap", organizationFromB.get("EndDate"));
				mergedOrganizationList.add(mergedOrganization);
			}

		}
		return mergedOrganizationList;
	}

	private Map<String, String> createMergedOrganization(Map<String, String> organization) {
		// don't create a merged organization if it doesn't have a name
		if(organization.get("Name") == null || "".equals(organization.get("Name"))) {
			return null;
		}
		
		Map<String, String> mergedOrganization = new HashMap<String, String>();
		mergedOrganization.put("Name", organization.get("Name"));
		mergedOrganization.put("IdInWorkday", "");
		mergedOrganization.put("AvailibilityDateInWorkday", "");
		mergedOrganization.put("IdInSap", "");
		mergedOrganization.put("EndDateInSap", "");
		return mergedOrganization;
	}

	private Map<String, String> findOrganizationInList(String organizationName, List<Map<String, String>> orgList) {
		if(organizationName == null) {
			return null;
		}
		
		for (Map<String, String> org : orgList) {
			String orgName = org.get("Name");
			if(orgName == null) {
				return null;
			} else if (orgName.equals(organizationName)) {
				return org;
			}
		}
		return null;
	}

}
