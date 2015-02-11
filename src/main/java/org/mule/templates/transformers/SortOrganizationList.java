/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.google.common.collect.Lists;

/**
 * This transformer will sort a list of map defining a weight for each map base
 * on the value of its keys.
 * 
 * @author damian.sima
 */
public class SortOrganizationList extends AbstractMessageTransformer {
	private static final String IDENTITY_FIELD_KEY = "Name";

	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {

		List<Map<String, String>> sortedOrganizationList = Lists.newArrayList((Iterator<Map<String, String>>) message.getPayload());

		Collections.sort(sortedOrganizationList, organizationMapComparator);

		return sortedOrganizationList;
	}

	public static Comparator<Map<String, String>> organizationMapComparator = new Comparator<Map<String, String>>() {

		public int compare(Map<String, String> org1, Map<String, String> org2) {

			String key1 = buildKey(org1);
			String key2 = buildKey(org2);

			return key1.compareTo(key2);
		}

		private String buildKey(Map<String, String> org) {
			StringBuilder key = new StringBuilder();

			if (StringUtils.isNotBlank(org.get("IDInA")) && StringUtils.isNotBlank(org.get("IDInB"))) {
				key.append("~~~");
				key.append(org.get(IDENTITY_FIELD_KEY));
			}

			if (StringUtils.isNotBlank(org.get("IDInA")) && StringUtils.isBlank(org.get("IDInB"))) {
				key.append("~");
				key.append(org.get(IDENTITY_FIELD_KEY));
			}

			if (StringUtils.isBlank(org.get("IDInA")) && StringUtils.isNotBlank(org.get("IDInB"))) {
				key.append("~~");
				key.append(org.get(IDENTITY_FIELD_KEY));
			}

			return key.toString();
		}

	};

}
