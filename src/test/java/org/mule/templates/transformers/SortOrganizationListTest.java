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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class SortOrganizationListTest {
	@Mock
	private MuleContext muleContext;

	@Test
	public void testSort() throws TransformerException {

		MuleMessage message = new DefaultMuleMessage(OrganizationMergeTest.createExpectedList(), muleContext);

		SortOrganizationList transformer = new SortOrganizationList();
		List<Map<String, String>> sortedList = (List<Map<String, String>>) transformer.transform(message, "UTF-8");

		System.out.println(sortedList);
		Assert.assertEquals("The merged list obtained is not as expected", createOriginalList(), sortedList);

	}

	private List<Map<String, String>> createOriginalList() {
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
		userList.add(user2);
		userList.add(user1);

		return userList;

	}

}
