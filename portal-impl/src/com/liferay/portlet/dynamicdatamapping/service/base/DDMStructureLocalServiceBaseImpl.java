/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.dynamicdatamapping.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.SystemEventPersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryTypeFinder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryTypePersistence;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMContentPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStorageLinkPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructureFinder;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructureLinkPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructurePersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMTemplateFinder;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMTemplatePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the d d m structure local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.dynamicdatamapping.service.impl.DDMStructureLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.dynamicdatamapping.service.impl.DDMStructureLocalServiceImpl
 * @see com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil
 * @generated
 */
public abstract class DDMStructureLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DDMStructureLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil} to access the d d m structure local service.
	 */

	/**
	 * Adds the d d m structure to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmStructure the d d m structure
	 * @return the d d m structure that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMStructure addDDMStructure(DDMStructure ddmStructure)
		throws SystemException {
		ddmStructure.setNew(true);

		return ddmStructurePersistence.update(ddmStructure);
	}

	/**
	 * Creates a new d d m structure with the primary key. Does not add the d d m structure to the database.
	 *
	 * @param structureId the primary key for the new d d m structure
	 * @return the new d d m structure
	 */
	@Override
	public DDMStructure createDDMStructure(long structureId) {
		return ddmStructurePersistence.create(structureId);
	}

	/**
	 * Deletes the d d m structure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param structureId the primary key of the d d m structure
	 * @return the d d m structure that was removed
	 * @throws PortalException if a d d m structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMStructure deleteDDMStructure(long structureId)
		throws PortalException, SystemException {
		return ddmStructurePersistence.remove(structureId);
	}

	/**
	 * Deletes the d d m structure from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmStructure the d d m structure
	 * @return the d d m structure that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMStructure deleteDDMStructure(DDMStructure ddmStructure)
		throws SystemException {
		return ddmStructurePersistence.remove(ddmStructure);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DDMStructure.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return ddmStructurePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return ddmStructurePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return ddmStructurePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return ddmStructurePersistence.countWithDynamicQuery(dynamicQuery);
	}

	@Override
	public DDMStructure fetchDDMStructure(long structureId)
		throws SystemException {
		return ddmStructurePersistence.fetchByPrimaryKey(structureId);
	}

	/**
	 * Returns the d d m structure with the primary key.
	 *
	 * @param structureId the primary key of the d d m structure
	 * @return the d d m structure
	 * @throws PortalException if a d d m structure with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMStructure getDDMStructure(long structureId)
		throws PortalException, SystemException {
		return ddmStructurePersistence.findByPrimaryKey(structureId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return ddmStructurePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the d d m structure matching the UUID and group.
	 *
	 * @param uuid the d d m structure's UUID
	 * @param groupId the primary key of the group
	 * @return the matching d d m structure
	 * @throws PortalException if a matching d d m structure could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DDMStructure getDDMStructureByUuidAndGroupId(String uuid,
		long groupId) throws PortalException, SystemException {
		return ddmStructurePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the d d m structures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.dynamicdatamapping.model.impl.DDMStructureModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m structures
	 * @param end the upper bound of the range of d d m structures (not inclusive)
	 * @return the range of d d m structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMStructure> getDDMStructures(int start, int end)
		throws SystemException {
		return ddmStructurePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d d m structures.
	 *
	 * @return the number of d d m structures
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getDDMStructuresCount() throws SystemException {
		return ddmStructurePersistence.countAll();
	}

	/**
	 * Updates the d d m structure in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmStructure the d d m structure
	 * @return the d d m structure that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMStructure updateDDMStructure(DDMStructure ddmStructure)
		throws SystemException {
		return ddmStructurePersistence.update(ddmStructure);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addDLFileEntryTypeDDMStructure(long fileEntryTypeId,
		long structureId) throws SystemException {
		dlFileEntryTypePersistence.addDDMStructure(fileEntryTypeId, structureId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addDLFileEntryTypeDDMStructure(long fileEntryTypeId,
		DDMStructure ddmStructure) throws SystemException {
		dlFileEntryTypePersistence.addDDMStructure(fileEntryTypeId, ddmStructure);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addDLFileEntryTypeDDMStructures(long fileEntryTypeId,
		long[] structureIds) throws SystemException {
		dlFileEntryTypePersistence.addDDMStructures(fileEntryTypeId,
			structureIds);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addDLFileEntryTypeDDMStructures(long fileEntryTypeId,
		List<DDMStructure> DDMStructures) throws SystemException {
		dlFileEntryTypePersistence.addDDMStructures(fileEntryTypeId,
			DDMStructures);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearDLFileEntryTypeDDMStructures(long fileEntryTypeId)
		throws SystemException {
		dlFileEntryTypePersistence.clearDDMStructures(fileEntryTypeId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteDLFileEntryTypeDDMStructure(long fileEntryTypeId,
		long structureId) throws SystemException {
		dlFileEntryTypePersistence.removeDDMStructure(fileEntryTypeId,
			structureId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteDLFileEntryTypeDDMStructure(long fileEntryTypeId,
		DDMStructure ddmStructure) throws SystemException {
		dlFileEntryTypePersistence.removeDDMStructure(fileEntryTypeId,
			ddmStructure);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteDLFileEntryTypeDDMStructures(long fileEntryTypeId,
		long[] structureIds) throws SystemException {
		dlFileEntryTypePersistence.removeDDMStructures(fileEntryTypeId,
			structureIds);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteDLFileEntryTypeDDMStructures(long fileEntryTypeId,
		List<DDMStructure> DDMStructures) throws SystemException {
		dlFileEntryTypePersistence.removeDDMStructures(fileEntryTypeId,
			DDMStructures);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMStructure> getDLFileEntryTypeDDMStructures(
		long fileEntryTypeId) throws SystemException {
		return dlFileEntryTypePersistence.getDDMStructures(fileEntryTypeId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMStructure> getDLFileEntryTypeDDMStructures(
		long fileEntryTypeId, int start, int end) throws SystemException {
		return dlFileEntryTypePersistence.getDDMStructures(fileEntryTypeId,
			start, end);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DDMStructure> getDLFileEntryTypeDDMStructures(
		long fileEntryTypeId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return dlFileEntryTypePersistence.getDDMStructures(fileEntryTypeId,
			start, end, orderByComparator);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getDLFileEntryTypeDDMStructuresCount(long fileEntryTypeId)
		throws SystemException {
		return dlFileEntryTypePersistence.getDDMStructuresSize(fileEntryTypeId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean hasDLFileEntryTypeDDMStructure(long fileEntryTypeId,
		long structureId) throws SystemException {
		return dlFileEntryTypePersistence.containsDDMStructure(fileEntryTypeId,
			structureId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean hasDLFileEntryTypeDDMStructures(long fileEntryTypeId)
		throws SystemException {
		return dlFileEntryTypePersistence.containsDDMStructures(fileEntryTypeId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setDLFileEntryTypeDDMStructures(long fileEntryTypeId,
		long[] structureIds) throws SystemException {
		dlFileEntryTypePersistence.setDDMStructures(fileEntryTypeId,
			structureIds);
	}

	/**
	 * Returns the d d m content local service.
	 *
	 * @return the d d m content local service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMContentLocalService getDDMContentLocalService() {
		return ddmContentLocalService;
	}

	/**
	 * Sets the d d m content local service.
	 *
	 * @param ddmContentLocalService the d d m content local service
	 */
	public void setDDMContentLocalService(
		com.liferay.portlet.dynamicdatamapping.service.DDMContentLocalService ddmContentLocalService) {
		this.ddmContentLocalService = ddmContentLocalService;
	}

	/**
	 * Returns the d d m content persistence.
	 *
	 * @return the d d m content persistence
	 */
	public DDMContentPersistence getDDMContentPersistence() {
		return ddmContentPersistence;
	}

	/**
	 * Sets the d d m content persistence.
	 *
	 * @param ddmContentPersistence the d d m content persistence
	 */
	public void setDDMContentPersistence(
		DDMContentPersistence ddmContentPersistence) {
		this.ddmContentPersistence = ddmContentPersistence;
	}

	/**
	 * Returns the d d m storage link local service.
	 *
	 * @return the d d m storage link local service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalService getDDMStorageLinkLocalService() {
		return ddmStorageLinkLocalService;
	}

	/**
	 * Sets the d d m storage link local service.
	 *
	 * @param ddmStorageLinkLocalService the d d m storage link local service
	 */
	public void setDDMStorageLinkLocalService(
		com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalService ddmStorageLinkLocalService) {
		this.ddmStorageLinkLocalService = ddmStorageLinkLocalService;
	}

	/**
	 * Returns the d d m storage link persistence.
	 *
	 * @return the d d m storage link persistence
	 */
	public DDMStorageLinkPersistence getDDMStorageLinkPersistence() {
		return ddmStorageLinkPersistence;
	}

	/**
	 * Sets the d d m storage link persistence.
	 *
	 * @param ddmStorageLinkPersistence the d d m storage link persistence
	 */
	public void setDDMStorageLinkPersistence(
		DDMStorageLinkPersistence ddmStorageLinkPersistence) {
		this.ddmStorageLinkPersistence = ddmStorageLinkPersistence;
	}

	/**
	 * Returns the d d m structure local service.
	 *
	 * @return the d d m structure local service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService getDDMStructureLocalService() {
		return ddmStructureLocalService;
	}

	/**
	 * Sets the d d m structure local service.
	 *
	 * @param ddmStructureLocalService the d d m structure local service
	 */
	public void setDDMStructureLocalService(
		com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService ddmStructureLocalService) {
		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	/**
	 * Returns the d d m structure remote service.
	 *
	 * @return the d d m structure remote service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMStructureService getDDMStructureService() {
		return ddmStructureService;
	}

	/**
	 * Sets the d d m structure remote service.
	 *
	 * @param ddmStructureService the d d m structure remote service
	 */
	public void setDDMStructureService(
		com.liferay.portlet.dynamicdatamapping.service.DDMStructureService ddmStructureService) {
		this.ddmStructureService = ddmStructureService;
	}

	/**
	 * Returns the d d m structure persistence.
	 *
	 * @return the d d m structure persistence
	 */
	public DDMStructurePersistence getDDMStructurePersistence() {
		return ddmStructurePersistence;
	}

	/**
	 * Sets the d d m structure persistence.
	 *
	 * @param ddmStructurePersistence the d d m structure persistence
	 */
	public void setDDMStructurePersistence(
		DDMStructurePersistence ddmStructurePersistence) {
		this.ddmStructurePersistence = ddmStructurePersistence;
	}

	/**
	 * Returns the d d m structure finder.
	 *
	 * @return the d d m structure finder
	 */
	public DDMStructureFinder getDDMStructureFinder() {
		return ddmStructureFinder;
	}

	/**
	 * Sets the d d m structure finder.
	 *
	 * @param ddmStructureFinder the d d m structure finder
	 */
	public void setDDMStructureFinder(DDMStructureFinder ddmStructureFinder) {
		this.ddmStructureFinder = ddmStructureFinder;
	}

	/**
	 * Returns the d d m structure link local service.
	 *
	 * @return the d d m structure link local service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkLocalService getDDMStructureLinkLocalService() {
		return ddmStructureLinkLocalService;
	}

	/**
	 * Sets the d d m structure link local service.
	 *
	 * @param ddmStructureLinkLocalService the d d m structure link local service
	 */
	public void setDDMStructureLinkLocalService(
		com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkLocalService ddmStructureLinkLocalService) {
		this.ddmStructureLinkLocalService = ddmStructureLinkLocalService;
	}

	/**
	 * Returns the d d m structure link persistence.
	 *
	 * @return the d d m structure link persistence
	 */
	public DDMStructureLinkPersistence getDDMStructureLinkPersistence() {
		return ddmStructureLinkPersistence;
	}

	/**
	 * Sets the d d m structure link persistence.
	 *
	 * @param ddmStructureLinkPersistence the d d m structure link persistence
	 */
	public void setDDMStructureLinkPersistence(
		DDMStructureLinkPersistence ddmStructureLinkPersistence) {
		this.ddmStructureLinkPersistence = ddmStructureLinkPersistence;
	}

	/**
	 * Returns the d d m template local service.
	 *
	 * @return the d d m template local service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalService getDDMTemplateLocalService() {
		return ddmTemplateLocalService;
	}

	/**
	 * Sets the d d m template local service.
	 *
	 * @param ddmTemplateLocalService the d d m template local service
	 */
	public void setDDMTemplateLocalService(
		com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalService ddmTemplateLocalService) {
		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	/**
	 * Returns the d d m template remote service.
	 *
	 * @return the d d m template remote service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService getDDMTemplateService() {
		return ddmTemplateService;
	}

	/**
	 * Sets the d d m template remote service.
	 *
	 * @param ddmTemplateService the d d m template remote service
	 */
	public void setDDMTemplateService(
		com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService ddmTemplateService) {
		this.ddmTemplateService = ddmTemplateService;
	}

	/**
	 * Returns the d d m template persistence.
	 *
	 * @return the d d m template persistence
	 */
	public DDMTemplatePersistence getDDMTemplatePersistence() {
		return ddmTemplatePersistence;
	}

	/**
	 * Sets the d d m template persistence.
	 *
	 * @param ddmTemplatePersistence the d d m template persistence
	 */
	public void setDDMTemplatePersistence(
		DDMTemplatePersistence ddmTemplatePersistence) {
		this.ddmTemplatePersistence = ddmTemplatePersistence;
	}

	/**
	 * Returns the d d m template finder.
	 *
	 * @return the d d m template finder
	 */
	public DDMTemplateFinder getDDMTemplateFinder() {
		return ddmTemplateFinder;
	}

	/**
	 * Sets the d d m template finder.
	 *
	 * @param ddmTemplateFinder the d d m template finder
	 */
	public void setDDMTemplateFinder(DDMTemplateFinder ddmTemplateFinder) {
		this.ddmTemplateFinder = ddmTemplateFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group remote service.
	 *
	 * @return the group remote service
	 */
	public com.liferay.portal.service.GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(
		com.liferay.portal.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the system event local service.
	 *
	 * @return the system event local service
	 */
	public com.liferay.portal.service.SystemEventLocalService getSystemEventLocalService() {
		return systemEventLocalService;
	}

	/**
	 * Sets the system event local service.
	 *
	 * @param systemEventLocalService the system event local service
	 */
	public void setSystemEventLocalService(
		com.liferay.portal.service.SystemEventLocalService systemEventLocalService) {
		this.systemEventLocalService = systemEventLocalService;
	}

	/**
	 * Returns the system event persistence.
	 *
	 * @return the system event persistence
	 */
	public SystemEventPersistence getSystemEventPersistence() {
		return systemEventPersistence;
	}

	/**
	 * Sets the system event persistence.
	 *
	 * @param systemEventPersistence the system event persistence
	 */
	public void setSystemEventPersistence(
		SystemEventPersistence systemEventPersistence) {
		this.systemEventPersistence = systemEventPersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the document library file entry type local service.
	 *
	 * @return the document library file entry type local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService getDLFileEntryTypeLocalService() {
		return dlFileEntryTypeLocalService;
	}

	/**
	 * Sets the document library file entry type local service.
	 *
	 * @param dlFileEntryTypeLocalService the document library file entry type local service
	 */
	public void setDLFileEntryTypeLocalService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService dlFileEntryTypeLocalService) {
		this.dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
	}

	/**
	 * Returns the document library file entry type remote service.
	 *
	 * @return the document library file entry type remote service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService getDLFileEntryTypeService() {
		return dlFileEntryTypeService;
	}

	/**
	 * Sets the document library file entry type remote service.
	 *
	 * @param dlFileEntryTypeService the document library file entry type remote service
	 */
	public void setDLFileEntryTypeService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService dlFileEntryTypeService) {
		this.dlFileEntryTypeService = dlFileEntryTypeService;
	}

	/**
	 * Returns the document library file entry type persistence.
	 *
	 * @return the document library file entry type persistence
	 */
	public DLFileEntryTypePersistence getDLFileEntryTypePersistence() {
		return dlFileEntryTypePersistence;
	}

	/**
	 * Sets the document library file entry type persistence.
	 *
	 * @param dlFileEntryTypePersistence the document library file entry type persistence
	 */
	public void setDLFileEntryTypePersistence(
		DLFileEntryTypePersistence dlFileEntryTypePersistence) {
		this.dlFileEntryTypePersistence = dlFileEntryTypePersistence;
	}

	/**
	 * Returns the document library file entry type finder.
	 *
	 * @return the document library file entry type finder
	 */
	public DLFileEntryTypeFinder getDLFileEntryTypeFinder() {
		return dlFileEntryTypeFinder;
	}

	/**
	 * Sets the document library file entry type finder.
	 *
	 * @param dlFileEntryTypeFinder the document library file entry type finder
	 */
	public void setDLFileEntryTypeFinder(
		DLFileEntryTypeFinder dlFileEntryTypeFinder) {
		this.dlFileEntryTypeFinder = dlFileEntryTypeFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.dynamicdatamapping.model.DDMStructure",
			ddmStructureLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.dynamicdatamapping.model.DDMStructure");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return DDMStructure.class;
	}

	protected String getModelClassName() {
		return DDMStructure.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = ddmStructurePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMContentLocalService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMContentLocalService ddmContentLocalService;
	@BeanReference(type = DDMContentPersistence.class)
	protected DDMContentPersistence ddmContentPersistence;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMStorageLinkLocalService ddmStorageLinkLocalService;
	@BeanReference(type = DDMStorageLinkPersistence.class)
	protected DDMStorageLinkPersistence ddmStorageLinkPersistence;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService ddmStructureLocalService;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMStructureService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMStructureService ddmStructureService;
	@BeanReference(type = DDMStructurePersistence.class)
	protected DDMStructurePersistence ddmStructurePersistence;
	@BeanReference(type = DDMStructureFinder.class)
	protected DDMStructureFinder ddmStructureFinder;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkLocalService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMStructureLinkLocalService ddmStructureLinkLocalService;
	@BeanReference(type = DDMStructureLinkPersistence.class)
	protected DDMStructureLinkPersistence ddmStructureLinkPersistence;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalService ddmTemplateLocalService;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMTemplateService ddmTemplateService;
	@BeanReference(type = DDMTemplatePersistence.class)
	protected DDMTemplatePersistence ddmTemplatePersistence;
	@BeanReference(type = DDMTemplateFinder.class)
	protected DDMTemplateFinder ddmTemplateFinder;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.GroupLocalService.class)
	protected com.liferay.portal.service.GroupLocalService groupLocalService;
	@BeanReference(type = com.liferay.portal.service.GroupService.class)
	protected com.liferay.portal.service.GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.SystemEventLocalService.class)
	protected com.liferay.portal.service.SystemEventLocalService systemEventLocalService;
	@BeanReference(type = SystemEventPersistence.class)
	protected SystemEventPersistence systemEventPersistence;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService dlFileEntryTypeLocalService;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService dlFileEntryTypeService;
	@BeanReference(type = DLFileEntryTypePersistence.class)
	protected DLFileEntryTypePersistence dlFileEntryTypePersistence;
	@BeanReference(type = DLFileEntryTypeFinder.class)
	protected DLFileEntryTypeFinder dlFileEntryTypeFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}