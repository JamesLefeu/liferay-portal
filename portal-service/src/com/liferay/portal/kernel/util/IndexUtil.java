/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.util;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author James Lefeu
 */
public class IndexUtil {


	public static List<String> createSQLIndex(
		String table, List<String> columns, boolean unique)
		throws IOException {

		StringBundler sb = new StringBundler();

		sb.append(table + " (");

		for (int k = 0; k < columns.size(); k++) {
			String col = columns.get(k);

			sb.append(col);

			if ((k + 1) != columns.size()) {
				sb.append(", ");
			}
		}

		sb.append(");");

		String indexSpec = sb.toString();

		String indexName = createDBIndexId();

		sb.setIndex(0);

		sb.append("create ");

		if (unique) {
			sb.append("unique ");
		}

		sb.append("index " + indexName + " on ");
		sb.append(indexSpec);

		ArrayList<String> retVal = new ArrayList<String>();
		retVal.add(sb.toString());
		retVal.add(indexSpec);
		retVal.add(indexName);

		return retVal;
	}

	public static String createDBIndexId() {

		UUID id = UUID.randomUUID();
		String indexHash = StringUtil.toHexString(id.hashCode()).toUpperCase();

		String indexName = "IX_" + indexHash;

		return indexName;
	}

	public static final int FULL_STRING = 0;
	public static final int INDEX_SPEC = 1;
	public static final int INDEX_NAME = 2;

}