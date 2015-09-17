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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mule.api.MuleContext;
import org.mule.api.transformer.TransformerException;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationMergeTest {
	
	private static final Logger LOGGER = LogManager.getLogger(OrganizationMergeTest.class);
	
	@Mock
	private MuleContext muleContext;

	@Test
	public void testMerge() throws TransformerException {
		List<Map<String, String>> organizationsA = createOrganizationLists("A", 0, 1);
		List<Map<String, String>> organizationsB = createOrganizationLists("B", 1, 2);
		
		OrganizationMerge organizationMerge = new OrganizationMerge();
		List<Map<String, String>> mergedList = organizationMerge.mergeList(organizationsA, organizationsB);

		LOGGER.info(mergedList);
		Assert.assertEquals("The merged list obtained is not as expected", createExpectedList(), mergedList);

	}

	static List<Map<String, String>> createExpectedList() {
		Map<String, String> user0 = new HashMap<String, String>();
		user0.put("IdInWorkday", "0");
		user0.put("IdInSap", "");
		user0.put("Name", "SomeName_0");
		user0.put("AvailibilityDateInWorkday", "9999-12-31");
		user0.put("EndDateInSap", "");

		Map<String, String> user1 = new HashMap<String, String>();
		user1.put("IdInWorkday", "1");
		user1.put("IdInSap", "1");
		user1.put("Name", "SomeName_1");
		user1.put("AvailibilityDateInWorkday", "9999-12-31");
		user1.put("EndDateInSap", "9999-12-31");

		Map<String, String> user2 = new HashMap<String, String>();
		user2.put("IdInWorkday", "");
		user2.put("IdInSap", "2");
		user2.put("Name", "SomeName_2");
		user2.put("AvailibilityDateInWorkday", "");
		user2.put("EndDateInSap", "9999-12-31");

		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
		userList.add(user0);
		userList.add(user1);
		userList.add(user2);

		return userList;

	}

	static List<Map<String, String>> createOrganizationLists(String orgId, int start, int end) {
		List<Map<String, String>> orgList = new ArrayList<Map<String, String>>();
		for (int i = start; i <= end; i++) {
			orgList.add(createOrg(orgId, i));
		}
		return orgList;
	}

	static Map<String, String> createOrg(String orgId, int sequence) {
		Map<String, String> org = new HashMap<String, String>();

		org.put("Id", new Integer(sequence).toString());
		org.put("Name", "SomeName_" + sequence);
		org.put("EndDate", "9999-12-31");

		return org;
	}
}
