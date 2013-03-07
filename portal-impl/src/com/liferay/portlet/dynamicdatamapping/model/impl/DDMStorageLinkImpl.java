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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import java.util.Date;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DDMStorageLinkImpl extends DDMStorageLinkBaseImpl {

	public DDMStorageLinkImpl() {
	}

	public String getStorageType() throws PortalException, SystemException {
		DDMStructure structure = getStructure();

		return structure.getStorageType();
	}

	public DDMStructure getStructure() throws PortalException, SystemException {
		return DDMStructureLocalServiceUtil.getStructure(getStructureId());
	}

	
	public long getGroupId() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setGroupId(long groupId) {
		// TODO Auto-generated method stub
		
	}

	
	public long getCompanyId() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getUserUuid() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setCompanyId(long companyId) {
		// TODO Auto-generated method stub
		
	}

	
	public void setCreateDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	
	public void setModifiedDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	
	public void setUserId(long userId) {
		// TODO Auto-generated method stub
		
	}

	
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

	
	public void setUserUuid(String userUuid) {
		// TODO Auto-generated method stub
		
	}

}