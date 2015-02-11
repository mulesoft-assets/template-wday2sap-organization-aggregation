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
			mergedOrganization.put("IDInA", organizationFromA.get("Id"));
			mergedOrganization.put("IndustryInA", organizationFromA.get("Industry"));
			mergedOrganization.put("NumberOfEmployeesInA", organizationFromA.get("NumberOfEmployees"));
			mergedOrganizationList.add(mergedOrganization);
		}

		// Add the new organizations from B and update the exiting ones
		for (Map<String, String> organizationFromB : organizationsFromOrgB) {
			Map<String, String> organizationFromA = findOrganizationInList(organizationFromB.get("Name"), mergedOrganizationList);
			if (organizationFromA != null) {
				organizationFromA.put("IDInB", organizationFromB.get("Id"));
				organizationFromA.put("IndustryInB", organizationFromB.get("Industry"));
				organizationFromA.put("NumberOfEmployeesInB", organizationFromB.get("NumberOfEmployees"));
			} else {
				Map<String, String> mergedOrganization = createMergedOrganization(organizationFromB);
				mergedOrganization.put("IDInB", organizationFromB.get("Id"));
				mergedOrganization.put("IndustryInB", organizationFromB.get("Industry"));
				mergedOrganization.put("NumberOfEmployeesInB", organizationFromB.get("NumberOfEmployees"));
				mergedOrganizationList.add(mergedOrganization);
			}

		}
		return mergedOrganizationList;
	}

	private Map<String, String> createMergedOrganization(Map<String, String> organization) {
		Map<String, String> mergedOrganization = new HashMap<String, String>();
		mergedOrganization.put("Name", organization.get("Name"));
		mergedOrganization.put("IDInA", "");
		mergedOrganization.put("IndustryInA", "");
		mergedOrganization.put("NumberOfEmployeesInA", "");
		mergedOrganization.put("IDInB", "");
		mergedOrganization.put("IndustryInB", "");
		mergedOrganization.put("NumberOfEmployeesInB", "");
		return mergedOrganization;
	}

	private Map<String, String> findOrganizationInList(String organizationName, List<Map<String, String>> orgList) {
		for (Map<String, String> org : orgList) {
			if (org.get("Name").equals(organizationName)) {
				return org;
			}
		}
		return null;
	}

}
