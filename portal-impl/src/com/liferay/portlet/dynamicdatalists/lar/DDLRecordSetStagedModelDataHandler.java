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

package com.liferay.portlet.dynamicdatalists.lar;

import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelPathUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.persistence.DDLRecordSetUtil;
import com.liferay.portlet.dynamicdatalists.service.persistence.DDLRecordVersionUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMContent;
import com.liferay.portlet.dynamicdatamapping.model.DDMStorageLink;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMContentUtil;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStorageLinkUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author James Lefeu
 */
public class DDLRecordSetStagedModelDataHandler
	extends BaseStagedModelDataHandler<DDLRecordSet> {

	@Override
	public String getClassName() {
		return DDLRecordSet.class.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Element[] elements,
			DDLRecordSet recordSet)
		throws Exception {

		Element recordSetsElement = elements[0];

		Element recordSetElement = recordSetsElement.addElement("record-set");

		portletDataContext.addClassedModel(
			recordSetElement, StagedModelPathUtil.getPath(recordSet), recordSet,
			DDLPortletDataHandler.NAMESPACE);

		Element ddmStructuresElement = recordSetElement.addElement(
			"ddm-structures");

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, ddmStructuresElement, ddmStructure);

		Element ddmTemplatesElement = recordSetElement.addElement(
			"ddm-templates");

		List<DDMTemplate> ddmTemplates = ddmStructure.getTemplates();

		for (DDMTemplate ddmTemplate : ddmTemplates) {
			StagedModelDataHandlerUtil.exportStagedModel(
				portletDataContext, ddmTemplatesElement, ddmTemplate);
		}

		Element recordsElement = recordSetElement.addElement(
				"records");

		List<DDLRecord> ddlRecords = recordSet.getRecords();
		for (DDLRecord ddlRecord : ddlRecords) {
			Element recordElement = recordsElement.addElement(
					"record");

			Element ddlRecordElement = recordElement.addElement(
					"ddl-record");

			portletDataContext.addClassedModel(
					ddlRecordElement, StagedModelPathUtil.getPath(ddlRecord), ddlRecord,
					DDLPortletDataHandler.NAMESPACE);

			/* Do we want to export all versions or just the latest/current version? 
			 * Or, do we want the user to have this as an option to choose?
			 */
			Element ddlRecordVersionElement = recordElement.addElement(
					"ddl-recordVersion");

			DDLRecordVersion ddlRecordVersion = DDLRecordVersionUtil.fetchByR_V(
					ddlRecord.getRecordId(), ddlRecord.getVersion());

			portletDataContext.addClassedModel(
					ddlRecordVersionElement, 
					StagedModelPathUtil.getPath(ddlRecordVersion), 
					ddlRecordVersion,
					DDLPortletDataHandler.NAMESPACE);

			Element ddmStorageLinkElement = recordElement.addElement(
					"ddm-storageLink");

			DDMStorageLink ddmStorageLink = DDMStorageLinkUtil.fetchByClassPK(
					ddlRecordVersion.getDDMStorageId());

			portletDataContext.addClassedModel(
					ddmStorageLinkElement, 
					StagedModelPathUtil.getPath(ddmStorageLink), 
					ddmStorageLink,
					DDLPortletDataHandler.NAMESPACE);

			Element ddmContentElement = recordElement.addElement(
					"ddm-content");

			DDMContent ddmContent = DDMContentUtil.fetchByPrimaryKey(
					ddlRecordVersion.getDDMStorageId());

			portletDataContext.addClassedModel(
					ddmContentElement, 
					StagedModelPathUtil.getPath(ddmContent), 
					ddmContent,
					DDLPortletDataHandler.NAMESPACE);
		}

	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Element element, String path,
			DDLRecordSet recordSet)
		throws Exception {

		Element ddmStructuresElement = element.element("ddm-structures");

		if (ddmStructuresElement != null) {
			importDDMStructures(portletDataContext, ddmStructuresElement);
		}

		Element ddmTemplatesElement = element.element("ddm-templates");

		if (ddmTemplatesElement != null) {
			importDDMTemplates(portletDataContext, ddmTemplatesElement);
		}
		
		Element recordsElement = element.element("records");
		
		if (recordsElement != null) {
			importRecords(portletDataContext, recordsElement);
		}

		long userId = portletDataContext.getUserId(recordSet.getUserUuid());

		Map<Long, Long> ddmStructureIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				DDMStructure.class);

		long ddmStructureId = MapUtil.getLong(
			ddmStructureIds, recordSet.getDDMStructureId(),
			recordSet.getDDMStructureId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			path, recordSet, DDLPortletDataHandler.NAMESPACE);

		DDLRecordSet importedRecordSet = null;

		if (portletDataContext.isDataStrategyMirror()) {
			DDLRecordSet existingRecordSet = DDLRecordSetUtil.fetchByUUID_G(
				recordSet.getUuid(), portletDataContext.getScopeGroupId());

			if (existingRecordSet == null) {
				serviceContext.setUuid(recordSet.getUuid());

				importedRecordSet = DDLRecordSetLocalServiceUtil.addRecordSet(
					userId, portletDataContext.getScopeGroupId(),
					ddmStructureId, recordSet.getRecordSetKey(),
					recordSet.getNameMap(), recordSet.getDescriptionMap(),
					recordSet.getMinDisplayRows(), recordSet.getScope(),
					serviceContext);
			}
			else {
				importedRecordSet =
					DDLRecordSetLocalServiceUtil.updateRecordSet(
						existingRecordSet.getRecordSetId(), ddmStructureId,
						recordSet.getNameMap(), recordSet.getDescriptionMap(),
						recordSet.getMinDisplayRows(), serviceContext);
			}
		}
		else {
			importedRecordSet = DDLRecordSetLocalServiceUtil.addRecordSet(
				userId, portletDataContext.getScopeGroupId(), ddmStructureId,
				recordSet.getRecordSetKey(), recordSet.getNameMap(),
				recordSet.getDescriptionMap(), recordSet.getMinDisplayRows(),
				recordSet.getScope(), serviceContext);
		}

		portletDataContext.importClassedModel(
			recordSet, importedRecordSet, DDLPortletDataHandler.NAMESPACE);
	}

	protected void importDDMStructures(
			PortletDataContext portletDataContext,
			Element ddmStructureReferencesElement)
		throws Exception {

		List<Element> ddmStructureElements =
			ddmStructureReferencesElement.elements("structure");

		for (Element ddmStructureElement : ddmStructureElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, ddmStructureElement);
		}
	}
	
	protected void importRecords(
			PortletDataContext portletDataContext,
			Element records)
		throws Exception {

		List<Element> recordElements =
				records.elements("record");

		for (Element recordElement : recordElements) {
			
			Element ddlRecordElement = recordElement.element("ddl-record");
			
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, ddlRecordElement);
			
			Element ddlRecordVersionElement = recordElement.element("ddl-recordVersion");
			
			StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, ddlRecordVersionElement);
			
			Element ddmStorageLinkElement = recordElement.element("ddm-storageLink");
			
			StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, ddmStorageLinkElement);
			
			Element ddmContentElement = recordElement.element("ddm-content");
			
			StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, ddmContentElement);
		}
	}

	protected void importDDMTemplates(
			PortletDataContext portletDataContext,
			Element ddmTemplateReferencesElement)
		throws Exception {

		List<Element> ddmTemplateElements =
			ddmTemplateReferencesElement.elements("template");

		for (Element ddmTemplateElement : ddmTemplateElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, ddmTemplateElement);
		}
	}

}