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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;
import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLinkModel;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DDMStorageLink service. Represents a row in the &quot;DDMStorageLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.dynamicdatamapping.model.DDMStorageLinkModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMStorageLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStorageLinkImpl
 * @see com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink
 * @see com.liferay.portlet.dynamicdatamapping.model.DDMStorageLinkModel
 * @generated
 */
public class DDMStorageLinkModelImpl extends BaseModelImpl<DDMStorageLink>
	implements DDMStorageLinkModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a d d m storage link model instance should use the {@link com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink} interface instead.
	 */
	public static final String TABLE_NAME = "DDMStorageLink";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "storageLinkId", Types.BIGINT },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "structureId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table DDMStorageLink (uuid_ VARCHAR(75) null,storageLinkId LONG not null primary key,classNameId LONG,classPK LONG,structureId LONG)";
	public static final String TABLE_SQL_DROP = "drop table DDMStorageLink";
	public static final String ORDER_BY_JPQL = " ORDER BY ddmStorageLink.storageLinkId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DDMStorageLink.storageLinkId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink"),
			true);
	public static long CLASSPK_COLUMN_BITMASK = 1L;
	public static long STRUCTUREID_COLUMN_BITMASK = 2L;
	public static long UUID_COLUMN_BITMASK = 4L;
	public static long STORAGELINKID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink"));

	public DDMStorageLinkModelImpl() {
	}

	public long getPrimaryKey() {
		return _storageLinkId;
	}

	public void setPrimaryKey(long primaryKey) {
		setStorageLinkId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _storageLinkId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return DDMStorageLink.class;
	}

	public String getModelClassName() {
		return DDMStorageLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("storageLinkId", getStorageLinkId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("structureId", getStructureId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long storageLinkId = (Long)attributes.get("storageLinkId");

		if (storageLinkId != null) {
			setStorageLinkId(storageLinkId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long structureId = (Long)attributes.get("structureId");

		if (structureId != null) {
			setStructureId(structureId);
		}
	}

	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	public long getStorageLinkId() {
		return _storageLinkId;
	}

	public void setStorageLinkId(long storageLinkId) {
		_storageLinkId = storageLinkId;
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	public long getStructureId() {
		return _structureId;
	}

	public void setStructureId(long structureId) {
		_columnBitmask |= STRUCTUREID_COLUMN_BITMASK;

		if (!_setOriginalStructureId) {
			_setOriginalStructureId = true;

			_originalStructureId = _structureId;
		}

		_structureId = structureId;
	}

	public long getOriginalStructureId() {
		return _originalStructureId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			DDMStorageLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMStorageLink toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DDMStorageLink)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DDMStorageLinkImpl ddmStorageLinkImpl = new DDMStorageLinkImpl();

		ddmStorageLinkImpl.setUuid(getUuid());
		ddmStorageLinkImpl.setStorageLinkId(getStorageLinkId());
		ddmStorageLinkImpl.setClassNameId(getClassNameId());
		ddmStorageLinkImpl.setClassPK(getClassPK());
		ddmStorageLinkImpl.setStructureId(getStructureId());

		ddmStorageLinkImpl.resetOriginalValues();

		return ddmStorageLinkImpl;
	}

	public int compareTo(DDMStorageLink ddmStorageLink) {
		long primaryKey = ddmStorageLink.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		DDMStorageLink ddmStorageLink = null;

		try {
			ddmStorageLink = (DDMStorageLink)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = ddmStorageLink.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		DDMStorageLinkModelImpl ddmStorageLinkModelImpl = this;

		ddmStorageLinkModelImpl._originalUuid = ddmStorageLinkModelImpl._uuid;

		ddmStorageLinkModelImpl._originalClassPK = ddmStorageLinkModelImpl._classPK;

		ddmStorageLinkModelImpl._setOriginalClassPK = false;

		ddmStorageLinkModelImpl._originalStructureId = ddmStorageLinkModelImpl._structureId;

		ddmStorageLinkModelImpl._setOriginalStructureId = false;

		ddmStorageLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMStorageLink> toCacheModel() {
		DDMStorageLinkCacheModel ddmStorageLinkCacheModel = new DDMStorageLinkCacheModel();

		ddmStorageLinkCacheModel.uuid = getUuid();

		String uuid = ddmStorageLinkCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmStorageLinkCacheModel.uuid = null;
		}

		ddmStorageLinkCacheModel.storageLinkId = getStorageLinkId();

		ddmStorageLinkCacheModel.classNameId = getClassNameId();

		ddmStorageLinkCacheModel.classPK = getClassPK();

		ddmStorageLinkCacheModel.structureId = getStructureId();

		return ddmStorageLinkCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", storageLinkId=");
		sb.append(getStorageLinkId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", structureId=");
		sb.append(getStructureId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>storageLinkId</column-name><column-value><![CDATA[");
		sb.append(getStorageLinkId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>structureId</column-name><column-value><![CDATA[");
		sb.append(getStructureId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = DDMStorageLink.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			DDMStorageLink.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _storageLinkId;
	private long _classNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _structureId;
	private long _originalStructureId;
	private boolean _setOriginalStructureId;
	private long _columnBitmask;
	private DDMStorageLink _escapedModel;

	
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