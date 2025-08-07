/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IT-HARSH
 */
@Entity
@Table(name = "csi_item_instances")
public class CsiItemInstances {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INSTANCE_ID")
    private Integer instanceId;
    @Column(name = "INSTANCE_NUMBER")
    private String instanceNumber;

    @Column(name = "EXTERNAL_REFERENCE")
    private String externalReference;

    @Column(name = "INVENTORY_ITEM_ID")
    private Integer inventoryItemId;

    @Column(name = "INVENTORY_REVISION")
    private String inventoryRevision;

    @Column(name = "INV_MASTER_ORGANIZATION_ID")
    private Integer invMasterOrganizationId;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @Column(name = "MFG_SERIAL_NUMBER_FLAG")
    private String mfgSerialNumberFlag;

    @Column(name = "LOT_NUMBER")
    private String lotNumber;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "UNIT_OF_MEASURE")
    private String unitOfMeasure;

    @Column(name = "ACCOUNTING_CLASS_CODE")
    private String accountingClassCode;

    @Column(name = "INSTANCE_CONDITION_ID")
    private Integer instanceConditionId;

    @Column(name = "INSTANCE_STATUS_ID")
    private Integer instanceStatusId;

    @Column(name = "CUSTOMER_VIEW_FLAG")
    private String customerViewFlag;

    @Column(name = "MERCHANT_VIEW_FLAG")
    private String merchantViewFlag;

    @Column(name = "SELLABLE_FLAG")
    private String sellableFlag;

    @Column(name = "SYSTEM_ID")
    private Integer systemId;

    @Column(name = "INSTANCE_TYPE_CODE")
    private String instanceTypeCode;

    @Column(name = "ACTIVE_START_DATE")
    private Date activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    private Date activeEndDate;

    @Column(name = "LOCATION_TYPE_CODE")
    private String locationTypeCode;

    @Column(name = "LOCATION_ID")
    private Integer locationId;

    @Column(name = "INV_ORGANIZATION_ID")
    private Integer invOrganizationId;

    @Column(name = "INV_SUBINVENTORY_NAME")
    private String invSubinventoryName;

    @Column(name = "INV_LOCATOR_ID")
    private Integer invLocatorId;

    @Column(name = "PA_PROJECT_ID")
    private Integer paProjectId;

    @Column(name = "PA_PROJECT_TASK_ID")
    private Integer paProjectTaskId;

    @Column(name = "IN_TRANSIT_ORDER_LINE_ID")
    private Integer inTransitOrderLineId;

    @Column(name = "WIP_JOB_ID")
    private Integer wipJobId;

    @Column(name = "PO_ORDER_LINE_ID")
    private Integer poOrderLineId;

    @Column(name = "LAST_OE_ORDER_LINE_ID")
    private Integer lastOeOrderLineId;

    @Column(name = "LAST_OE_RMA_LINE_ID")
    private Integer lastOeRmaLineId;

    @Column(name = "LAST_PO_PO_LINE_ID")
    private Integer lastPoPoLineId;

    @Column(name = "LAST_OE_PO_NUMBER")
    private String lastOePoNumber;

    @Column(name = "LAST_WIP_JOB_ID")
    private Integer lastWipJobId;

    @Column(name = "LAST_PA_PROJECT_ID")
    private Integer lastPaProjectId;

    @Column(name = "LAST_PA_TASK_ID")
    private Integer lastPaTaskId;

    @Column(name = "LAST_OE_AGREEMENT_ID")
    private Integer lastOeAgreementId;

    @Column(name = "INSTALL_DATE")
    private Date installDate;

    @Column(name = "MANUALLY_CREATED_FLAG")
    private String manuallyCreatedFlag;

    @Column(name = "RETURN_BY_DATE")
    private Date returnByDate;

    @Column(name = "ACTUAL_RETURN_DATE")
    private Date actualReturnDate;

    @Column(name = "CREATION_COMPLETE_FLAG")
    private String creationCompleteFlag;

    @Column(name = "COMPLETENESS_FLAG")
    private String completenessFlag;

    @Column(name = "CONTEXT")
    private String context;

    @Column(name = "ATTRIBUTE1")
    private String attribute1;

    @Column(name = "ATTRIBUTE2")
    private String attribute2;

    @Column(name = "ATTRIBUTE3")
    private String attribute3;

    @Column(name = "ATTRIBUTE4")
    private String attribute4;

    @Column(name = "ATTRIBUTE5")
    private String attribute5;

    @Column(name = "ATTRIBUTE6")
    private String attribute6;

    @Column(name = "ATTRIBUTE7")
    private String attribute7;

    @Column(name = "ATTRIBUTE8")
    private String attribute8;

    @Column(name = "ATTRIBUTE9")
    private String attribute9;

    @Column(name = "ATTRIBUTE10")
    private String attribute10;

    @Column(name = "ATTRIBUTE11")
    private String attribute11;

    @Column(name = "ATTRIBUTE12")
    private String attribute12;

    @Column(name = "ATTRIBUTE13")
    private String attribute13;

    @Column(name = "ATTRIBUTE14")
    private String attribute14;

    @Column(name = "ATTRIBUTE15")
    private String attribute15;

    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "LAST_UPDATED_BY")
    private Integer lastUpdatedBy;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "LAST_UPDATE_LOGIN")
    private Integer lastUpdateLogin;

    @Column(name = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;

    @Column(name = "SECURITY_GROUP_ID")
    private Integer securityGroupId;

    @Column(name = "LAST_TXN_LINE_DETAIL_ID")
    private Integer lastTxnLineDetailId;

    @Column(name = "INSTALL_LOCATION_TYPE_CODE")
    private String installLocationTypeCode;

    @Column(name = "INSTALL_LOCATION_ID")
    private Integer installLocationId;

    @Column(name = "INSTANCE_USAGE_CODE")
    private String instanceUsageCode;

    @Column(name = "OWNER_PARTY_SOURCE_TABLE")
    private String ownerPartySourceTable;

    @Column(name = "OWNER_PARTY_ID")
    private Integer ownerPartyId;

    @Column(name = "OWNER_PARTY_ACCOUNT_ID")
    private Integer ownerPartyAccountId;

    @Column(name = "LAST_VLD_ORGANIZATION_ID")
    private Integer lastVldOrganizationId;

    @Column(name = "MIGRATED_FLAG")
    private String migratedFlag;

    @Column(name = "REQUEST_ID")
    private Integer requestId;

    @Column(name = "PROGRAM_APPLICATION_ID")
    private Integer programApplicationId;

    @Column(name = "PROGRAM_ID")
    private Integer programId;

    @Column(name = "PROGRAM_UPDATE_DATE")
    private Date programUpdateDate;

    @Column(name = "CONFIG_INST_HDR_ID")
    private Integer configInstHdrId;

    @Column(name = "CONFIG_INST_REV_NUM")
    private Integer configInstRevNum;

    @Column(name = "CONFIG_INST_ITEM_ID")
    private Integer configInstItemId;

    @Column(name = "CONFIG_VALID_STATUS")
    private String configValidStatus;

    @Column(name = "INSTANCE_DESCRIPTION")
    private String instanceDescription;

    @Column(name = "LAST_PURGE_DATE")
    private Date lastPurgeDate;

    @Column(name = "ATTRIBUTE16")
    private String attribute16;

    @Column(name = "ATTRIBUTE17")
    private String attribute17;

    @Column(name = "ATTRIBUTE18")
    private String attribute18;

    @Column(name = "ATTRIBUTE19")
    private String attribute19;

    @Column(name = "ATTRIBUTE20")
    private String attribute20;

    @Column(name = "ATTRIBUTE21")
    private String attribute21;

    @Column(name = "ATTRIBUTE22")
    private String attribute22;

    @Column(name = "ATTRIBUTE23")
    private String attribute23;

    @Column(name = "ATTRIBUTE24")
    private String attribute24;

    @Column(name = "ATTRIBUTE25")
    private String attribute25;

    @Column(name = "ATTRIBUTE26")
    private String attribute26;

    @Column(name = "ATTRIBUTE27")
    private String attribute27;

    @Column(name = "ATTRIBUTE28")
    private String attribute28;

    @Column(name = "ATTRIBUTE29")
    private String attribute29;

    @Column(name = "ATTRIBUTE30")
    private String attribute30;

    @Column(name = "NETWORK_ASSET_FLAG")
    private String networkAssetFlag;

    @Column(name = "MAINTAINABLE_FLAG")
    private String maintainableFlag;

    @Column(name = "PN_LOCATION_ID")
    private Integer pnLocationId;

    @Column(name = "ASSET_CRITICALITY_CODE")
    private String assetCriticalityCode;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "EQUIPMENT_GEN_OBJECT_ID")
    private Integer equipmentGenObjectId;

    @Column(name = "INSTANTIATION_FLAG")
    private String instantiationFlag;

    @Column(name = "LINEAR_LOCATION_ID")
    private Integer linearLocationId;

    @Column(name = "OPERATIONAL_LOG_FLAG")
    private String operationalLogFlag;

    @Column(name = "CHECKIN_STATUS")
    private Integer checkinStatus;

    @Column(name = "SUPPLIER_WARRANTY_EXP_DATE")
    private Date supplierWarrantyExpDate;

    @Column(name = "PURCHASE_UNIT_PRICE")
    private Integer purchaseUnitPrice;

    @Column(name = "PURCHASE_CURRENCY_CODE")
    private String purchaseCurrencyCode;

    @Column(name = "PAYABLES_UNIT_PRICE")
    private Integer payablesUnitPrice;

    @Column(name = "PAYABLES_CURRENCY_CODE")
    private String payablesCurrencyCode;

    @Column(name = "SALES_UNIT_PRICE")
    private Integer salesUnitPrice;

    @Column(name = "SALES_CURRENCY_CODE")
    private String salesCurrencyCode;

    @Column(name = "OPERATIONAL_STATUS_CODE")
    private String operationalStatusCode;

    @Column(name = "SOURCE_CODE")
    private String sourceCode;

    public CsiItemInstances() {

    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(String instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public Integer getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Integer inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryRevision() {
        return inventoryRevision;
    }

    public void setInventoryRevision(String inventoryRevision) {
        this.inventoryRevision = inventoryRevision;
    }

    public Integer getInvMasterOrganizationId() {
        return invMasterOrganizationId;
    }

    public void setInvMasterOrganizationId(Integer invMasterOrganizationId) {
        this.invMasterOrganizationId = invMasterOrganizationId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMfgSerialNumberFlag() {
        return mfgSerialNumberFlag;
    }

    public void setMfgSerialNumberFlag(String mfgSerialNumberFlag) {
        this.mfgSerialNumberFlag = mfgSerialNumberFlag;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getAccountingClassCode() {
        return accountingClassCode;
    }

    public void setAccountingClassCode(String accountingClassCode) {
        this.accountingClassCode = accountingClassCode;
    }

    public Integer getInstanceConditionId() {
        return instanceConditionId;
    }

    public void setInstanceConditionId(Integer instanceConditionId) {
        this.instanceConditionId = instanceConditionId;
    }

    public Integer getInstanceStatusId() {
        return instanceStatusId;
    }

    public void setInstanceStatusId(Integer instanceStatusId) {
        this.instanceStatusId = instanceStatusId;
    }

    public String getCustomerViewFlag() {
        return customerViewFlag;
    }

    public void setCustomerViewFlag(String customerViewFlag) {
        this.customerViewFlag = customerViewFlag;
    }

    public String getMerchantViewFlag() {
        return merchantViewFlag;
    }

    public void setMerchantViewFlag(String merchantViewFlag) {
        this.merchantViewFlag = merchantViewFlag;
    }

    public String getSellableFlag() {
        return sellableFlag;
    }

    public void setSellableFlag(String sellableFlag) {
        this.sellableFlag = sellableFlag;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getInstanceTypeCode() {
        return instanceTypeCode;
    }

    public void setInstanceTypeCode(String instanceTypeCode) {
        this.instanceTypeCode = instanceTypeCode;
    }

    public Date getActiveStartDate() {
        return activeStartDate;
    }

    public void setActiveStartDate(Date activeStartDate) {
        this.activeStartDate = activeStartDate;
    }

    public Date getActiveEndDate() {
        return activeEndDate;
    }

    public void setActiveEndDate(Date activeEndDate) {
        this.activeEndDate = activeEndDate;
    }

    public String getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setLocationTypeCode(String locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getInvOrganizationId() {
        return invOrganizationId;
    }

    public void setInvOrganizationId(Integer invOrganizationId) {
        this.invOrganizationId = invOrganizationId;
    }

    public String getInvSubinventoryName() {
        return invSubinventoryName;
    }

    public void setInvSubinventoryName(String invSubinventoryName) {
        this.invSubinventoryName = invSubinventoryName;
    }

    public Integer getInvLocatorId() {
        return invLocatorId;
    }

    public void setInvLocatorId(Integer invLocatorId) {
        this.invLocatorId = invLocatorId;
    }

    public Integer getPaProjectId() {
        return paProjectId;
    }

    public void setPaProjectId(Integer paProjectId) {
        this.paProjectId = paProjectId;
    }

    public Integer getPaProjectTaskId() {
        return paProjectTaskId;
    }

    public void setPaProjectTaskId(Integer paProjectTaskId) {
        this.paProjectTaskId = paProjectTaskId;
    }

    public Integer getInTransitOrderLineId() {
        return inTransitOrderLineId;
    }

    public void setInTransitOrderLineId(Integer inTransitOrderLineId) {
        this.inTransitOrderLineId = inTransitOrderLineId;
    }

    public Integer getWipJobId() {
        return wipJobId;
    }

    public void setWipJobId(Integer wipJobId) {
        this.wipJobId = wipJobId;
    }

    public Integer getPoOrderLineId() {
        return poOrderLineId;
    }

    public void setPoOrderLineId(Integer poOrderLineId) {
        this.poOrderLineId = poOrderLineId;
    }

    public Integer getLastOeOrderLineId() {
        return lastOeOrderLineId;
    }

    public void setLastOeOrderLineId(Integer lastOeOrderLineId) {
        this.lastOeOrderLineId = lastOeOrderLineId;
    }

    public Integer getLastOeRmaLineId() {
        return lastOeRmaLineId;
    }

    public void setLastOeRmaLineId(Integer lastOeRmaLineId) {
        this.lastOeRmaLineId = lastOeRmaLineId;
    }

    public Integer getLastPoPoLineId() {
        return lastPoPoLineId;
    }

    public void setLastPoPoLineId(Integer lastPoPoLineId) {
        this.lastPoPoLineId = lastPoPoLineId;
    }

    public String getLastOePoNumber() {
        return lastOePoNumber;
    }

    public void setLastOePoNumber(String lastOePoNumber) {
        this.lastOePoNumber = lastOePoNumber;
    }

    public Integer getLastWipJobId() {
        return lastWipJobId;
    }

    public void setLastWipJobId(Integer lastWipJobId) {
        this.lastWipJobId = lastWipJobId;
    }

    public Integer getLastPaProjectId() {
        return lastPaProjectId;
    }

    public void setLastPaProjectId(Integer lastPaProjectId) {
        this.lastPaProjectId = lastPaProjectId;
    }

    public Integer getLastPaTaskId() {
        return lastPaTaskId;
    }

    public void setLastPaTaskId(Integer lastPaTaskId) {
        this.lastPaTaskId = lastPaTaskId;
    }

    public Integer getLastOeAgreementId() {
        return lastOeAgreementId;
    }

    public void setLastOeAgreementId(Integer lastOeAgreementId) {
        this.lastOeAgreementId = lastOeAgreementId;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getManuallyCreatedFlag() {
        return manuallyCreatedFlag;
    }

    public void setManuallyCreatedFlag(String manuallyCreatedFlag) {
        this.manuallyCreatedFlag = manuallyCreatedFlag;
    }

    public Date getReturnByDate() {
        return returnByDate;
    }

    public void setReturnByDate(Date returnByDate) {
        this.returnByDate = returnByDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getCreationCompleteFlag() {
        return creationCompleteFlag;
    }

    public void setCreationCompleteFlag(String creationCompleteFlag) {
        this.creationCompleteFlag = creationCompleteFlag;
    }

    public String getCompletenessFlag() {
        return completenessFlag;
    }

    public void setCompletenessFlag(String completenessFlag) {
        this.completenessFlag = completenessFlag;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Integer getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(Integer securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public Integer getLastTxnLineDetailId() {
        return lastTxnLineDetailId;
    }

    public void setLastTxnLineDetailId(Integer lastTxnLineDetailId) {
        this.lastTxnLineDetailId = lastTxnLineDetailId;
    }

    public String getInstallLocationTypeCode() {
        return installLocationTypeCode;
    }

    public void setInstallLocationTypeCode(String installLocationTypeCode) {
        this.installLocationTypeCode = installLocationTypeCode;
    }

    public Integer getInstallLocationId() {
        return installLocationId;
    }

    public void setInstallLocationId(Integer installLocationId) {
        this.installLocationId = installLocationId;
    }

    public String getInstanceUsageCode() {
        return instanceUsageCode;
    }

    public void setInstanceUsageCode(String instanceUsageCode) {
        this.instanceUsageCode = instanceUsageCode;
    }

    public String getOwnerPartySourceTable() {
        return ownerPartySourceTable;
    }

    public void setOwnerPartySourceTable(String ownerPartySourceTable) {
        this.ownerPartySourceTable = ownerPartySourceTable;
    }

    public Integer getOwnerPartyId() {
        return ownerPartyId;
    }

    public void setOwnerPartyId(Integer ownerPartyId) {
        this.ownerPartyId = ownerPartyId;
    }

    public Integer getOwnerPartyAccountId() {
        return ownerPartyAccountId;
    }

    public void setOwnerPartyAccountId(Integer ownerPartyAccountId) {
        this.ownerPartyAccountId = ownerPartyAccountId;
    }

    public Integer getLastVldOrganizationId() {
        return lastVldOrganizationId;
    }

    public void setLastVldOrganizationId(Integer lastVldOrganizationId) {
        this.lastVldOrganizationId = lastVldOrganizationId;
    }

    public String getMigratedFlag() {
        return migratedFlag;
    }

    public void setMigratedFlag(String migratedFlag) {
        this.migratedFlag = migratedFlag;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getProgramApplicationId() {
        return programApplicationId;
    }

    public void setProgramApplicationId(Integer programApplicationId) {
        this.programApplicationId = programApplicationId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Date getProgramUpdateDate() {
        return programUpdateDate;
    }

    public void setProgramUpdateDate(Date programUpdateDate) {
        this.programUpdateDate = programUpdateDate;
    }

    public Integer getConfigInstHdrId() {
        return configInstHdrId;
    }

    public void setConfigInstHdrId(Integer configInstHdrId) {
        this.configInstHdrId = configInstHdrId;
    }

    public Integer getConfigInstRevNum() {
        return configInstRevNum;
    }

    public void setConfigInstRevNum(Integer configInstRevNum) {
        this.configInstRevNum = configInstRevNum;
    }

    public Integer getConfigInstItemId() {
        return configInstItemId;
    }

    public void setConfigInstItemId(Integer configInstItemId) {
        this.configInstItemId = configInstItemId;
    }

    public String getConfigValidStatus() {
        return configValidStatus;
    }

    public void setConfigValidStatus(String configValidStatus) {
        this.configValidStatus = configValidStatus;
    }

    public String getInstanceDescription() {
        return instanceDescription;
    }

    public void setInstanceDescription(String instanceDescription) {
        this.instanceDescription = instanceDescription;
    }

    public Date getLastPurgeDate() {
        return lastPurgeDate;
    }

    public void setLastPurgeDate(Date lastPurgeDate) {
        this.lastPurgeDate = lastPurgeDate;
    }

    public String getAttribute16() {
        return attribute16;
    }

    public void setAttribute16(String attribute16) {
        this.attribute16 = attribute16;
    }

    public String getAttribute17() {
        return attribute17;
    }

    public void setAttribute17(String attribute17) {
        this.attribute17 = attribute17;
    }

    public String getAttribute18() {
        return attribute18;
    }

    public void setAttribute18(String attribute18) {
        this.attribute18 = attribute18;
    }

    public String getAttribute19() {
        return attribute19;
    }

    public void setAttribute19(String attribute19) {
        this.attribute19 = attribute19;
    }

    public String getAttribute20() {
        return attribute20;
    }

    public void setAttribute20(String attribute20) {
        this.attribute20 = attribute20;
    }

    public String getAttribute21() {
        return attribute21;
    }

    public void setAttribute21(String attribute21) {
        this.attribute21 = attribute21;
    }

    public String getAttribute22() {
        return attribute22;
    }

    public void setAttribute22(String attribute22) {
        this.attribute22 = attribute22;
    }

    public String getAttribute23() {
        return attribute23;
    }

    public void setAttribute23(String attribute23) {
        this.attribute23 = attribute23;
    }

    public String getAttribute24() {
        return attribute24;
    }

    public void setAttribute24(String attribute24) {
        this.attribute24 = attribute24;
    }

    public String getAttribute25() {
        return attribute25;
    }

    public void setAttribute25(String attribute25) {
        this.attribute25 = attribute25;
    }

    public String getAttribute26() {
        return attribute26;
    }

    public void setAttribute26(String attribute26) {
        this.attribute26 = attribute26;
    }

    public String getAttribute27() {
        return attribute27;
    }

    public void setAttribute27(String attribute27) {
        this.attribute27 = attribute27;
    }

    public String getAttribute28() {
        return attribute28;
    }

    public void setAttribute28(String attribute28) {
        this.attribute28 = attribute28;
    }

    public String getAttribute29() {
        return attribute29;
    }

    public void setAttribute29(String attribute29) {
        this.attribute29 = attribute29;
    }

    public String getAttribute30() {
        return attribute30;
    }

    public void setAttribute30(String attribute30) {
        this.attribute30 = attribute30;
    }

    public String getNetworkAssetFlag() {
        return networkAssetFlag;
    }

    public void setNetworkAssetFlag(String networkAssetFlag) {
        this.networkAssetFlag = networkAssetFlag;
    }

    public String getMaintainableFlag() {
        return maintainableFlag;
    }

    public void setMaintainableFlag(String maintainableFlag) {
        this.maintainableFlag = maintainableFlag;
    }

    public Integer getPnLocationId() {
        return pnLocationId;
    }

    public void setPnLocationId(Integer pnLocationId) {
        this.pnLocationId = pnLocationId;
    }

    public String getAssetCriticalityCode() {
        return assetCriticalityCode;
    }

    public void setAssetCriticalityCode(String assetCriticalityCode) {
        this.assetCriticalityCode = assetCriticalityCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getEquipmentGenObjectId() {
        return equipmentGenObjectId;
    }

    public void setEquipmentGenObjectId(Integer equipmentGenObjectId) {
        this.equipmentGenObjectId = equipmentGenObjectId;
    }

    public String getInstantiationFlag() {
        return instantiationFlag;
    }

    public void setInstantiationFlag(String instantiationFlag) {
        this.instantiationFlag = instantiationFlag;
    }

    public Integer getLinearLocationId() {
        return linearLocationId;
    }

    public void setLinearLocationId(Integer linearLocationId) {
        this.linearLocationId = linearLocationId;
    }

    public String getOperationalLogFlag() {
        return operationalLogFlag;
    }

    public void setOperationalLogFlag(String operationalLogFlag) {
        this.operationalLogFlag = operationalLogFlag;
    }

    public Integer getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(Integer checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public Date getSupplierWarrantyExpDate() {
        return supplierWarrantyExpDate;
    }

    public void setSupplierWarrantyExpDate(Date supplierWarrantyExpDate) {
        this.supplierWarrantyExpDate = supplierWarrantyExpDate;
    }

    public Integer getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public void setPurchaseUnitPrice(Integer purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public String getPurchaseCurrencyCode() {
        return purchaseCurrencyCode;
    }

    public void setPurchaseCurrencyCode(String purchaseCurrencyCode) {
        this.purchaseCurrencyCode = purchaseCurrencyCode;
    }

    public Integer getPayablesUnitPrice() {
        return payablesUnitPrice;
    }

    public void setPayablesUnitPrice(Integer payablesUnitPrice) {
        this.payablesUnitPrice = payablesUnitPrice;
    }

    public String getPayablesCurrencyCode() {
        return payablesCurrencyCode;
    }

    public void setPayablesCurrencyCode(String payablesCurrencyCode) {
        this.payablesCurrencyCode = payablesCurrencyCode;
    }

    public Integer getSalesUnitPrice() {
        return salesUnitPrice;
    }

    public void setSalesUnitPrice(Integer salesUnitPrice) {
        this.salesUnitPrice = salesUnitPrice;
    }

    public String getSalesCurrencyCode() {
        return salesCurrencyCode;
    }

    public void setSalesCurrencyCode(String salesCurrencyCode) {
        this.salesCurrencyCode = salesCurrencyCode;
    }

    public String getOperationalStatusCode() {
        return operationalStatusCode;
    }

    public void setOperationalStatusCode(String operationalStatusCode) {
        this.operationalStatusCode = operationalStatusCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }



}
