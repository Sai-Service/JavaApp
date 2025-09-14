/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.saivehicledelivery.dao;

import com.sai.saivehicledelivery.entity.SsVehDelvTrans;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Harsh Gawde
 */
public interface SsVehDelvTransDao extends CrudRepository<SsVehDelvTrans, Integer> {

    public Optional<SsVehDelvTrans> findFirstByAttribute1OrderByCreationDateDesc(String attribute1);

//    //query for getting trans details by invoice no
//    @Query(value = " select DISTINCT GDF.GE1  VEHICLE_NO,hp.PARTY_NAME NAME,(hp.ADDRESS1 ||','|| hp.ADDRESS2||','||hp.ADDRESS3||','||hp.ADDRESS4||','||hp.ADDRESS4||','||hp.city||','||hp.postal_code ||','||hp.state) ADDRESS,\n"
//            + "hp.PRIMARY_PHONE_NUMBER CONTACT_NO, rcta.ct_reference INVOICE_NO, rcta.trx_number TRANSACTION_NO,rcta.trx_date TRANSACTION_DATE,apsa.amount_due_remaining, rcta.org_Id\n"
//            + "       from \n"
//            + "    ra_customer_trx_all rcta,\n"
//            + "    AR_PAYMENT_SCHEDULES_ALL apsa,GD_FDI_TRANS GDF,hz_cust_accounts hca,hz_parties hp\n"
//            + "    where rcta.ct_reference =?1\n"
//            + "    and rcta.customer_trx_id=apsa.customer_trx_id\n"
//            + "    AND rcta.ct_reference=GDF.TRANS_ID\n"
//            + "    and rcta.BILL_TO_CUSTOMER_ID=hca.CUST_ACCOUNT_ID\n"
//            + "    AND hca.party_id=hp.party_id", nativeQuery = true)
//    public List<Map> getDetailsByInvoiceNo(String invoiceNo);
    //query for getting trans details by invoice no
    @Query(value = " SELECT DISTINCT sgps.gate_pass_id, gdf.executive, gdf.trans_ref_num,\n"
            + "                gdf.ge1 vehicle_no, rcta.ct_reference invoice_no,\n"
            + "                apsa.amount_due_remaining, rcta.org_id,\n"
            + "                sgps.customer_id party_id, sgps.date_of_delivery,\n"
            + "                sgps.customer_name, sgps.customer_address, sgps.contact_no,\n"
            + "                sgps.service_location, sgps.meaning model\n"
            + "           FROM ra_customer_trx_all rcta,\n"
            + "                ss_gate_pass_service sgps,\n"
            + "                ar_payment_schedules_all apsa,\n"
            + "                gd_fdi_trans gdf\n"
            + "          WHERE rcta.customer_trx_id = apsa.customer_trx_id\n"
            + "            AND rcta.attribute4 = gdf.trans_id\n"
            + "            AND sgps.service_request_number = gdf.trans_ref_num\n"
            + "            AND sgps.vehicle_no = gdf.ge1\n"
            + "            AND sgps.gate_pass_id = ?1\n"
            + "            AND sgps.service_location =?2\n"
            + "            and gdf.ge10='CUS'", nativeQuery = true)
    public List<Map> getDetailsByGatePassIdAndServiceLocation(String attribute1, String location);
    //new query for fetching trans details with total amount
//    @Query(value = "  SELECT DISTINCT sgps.gate_pass_id, gdf.executive, gdf.trans_ref_num,\n"
//            + "gdf.ge1 vehicle_no, rcta.ct_reference invoice_no,\n"
//            + "nvl(ssgpid.TOT_AMT,0) amount_due_remaining, rcta.org_id,\n"
//            + "sgps.customer_id party_id, sgps.date_of_delivery,\n"
//            + "sgps.customer_name, sgps.customer_address, sgps.contact_no,\n"
//            + " sgps.service_location, sgps.meaning model\n"
//            + "FROM ra_customer_trx_all rcta,\n"
//            + "SS_SERVICE_GATE_PASS_INFO_DMS ssgpid,\n"
//            + "ss_gate_pass_service sgps,\n"
//            + "ar_payment_schedules_all apsa,\n"
//            + "gd_fdi_trans gdf\n"
//            + "WHERE rcta.customer_trx_id = apsa.customer_trx_id\n"
//            + "and ssgpid.gate_pass_id= sgps.gate_pass_id\n"
//            + "AND rcta.attribute4 = gdf.trans_id\n"
//            + "AND sgps.service_request_number = gdf.trans_ref_num\n"
//            + "AND sgps.vehicle_no = gdf.ge1\n"
//            + "AND sgps.gate_pass_id = ?1\n"
//            + "AND sgps.service_location =?2\n"
//            + "and gdf.ge10='CUS'", nativeQuery = true)
//    public List<Map> getDetailsByGatePassIdAndServiceLocation(String attribute1, String location);

    //query without total amount 
    @Query(value = " SELECT DISTINCT sgps.gate_pass_id, gdf.executive, gdf.trans_ref_num,\n"
            + "gdf.ge1 vehicle_no, rcta.ct_reference invoice_no,\n"
            + "apsa.amount_due_remaining, rcta.org_id,\n"
            + "sgps.customer_id party_id, sgps.date_of_delivery,\n"
            + " sgps.customer_name, sgps.customer_address, sgps.contact_no,\n"
            + " sgps.service_location, sgps.meaning model\n"
            + " FROM ra_customer_trx_all rcta,\n"
            + "ss_gate_pass_service sgps,\n"
            + " ar_payment_schedules_all apsa,\n"
            + "  gd_fdi_trans gdf\n"
            + " WHERE rcta.customer_trx_id = apsa.customer_trx_id\n"
            + " AND rcta.attribute4 = gdf.trans_id\n"
            + " AND sgps.service_request_number = gdf.trans_ref_num\n"
            + " AND sgps.vehicle_no = gdf.ge1\n"
            + " AND sgps.vehicle_no = ?1\n"
            + " AND sgps.service_location =?2\n"
            + " and gdf.ge10='CUS' AND ROWNUM=1\n"
            + " ORDER BY SGPS.DATE_OF_DELIVERY DESC", nativeQuery = true)
    public List<Map> getGpDetailsByVehicleNoAndServiceLocation(String vehicleNo, String location);
    //query with total amount 
//    @Query(value = " SELECT DISTINCT sgps.gate_pass_id, gdf.executive, gdf.trans_ref_num,\n"
//            + "gdf.ge1 vehicle_no, rcta.ct_reference invoice_no,\n"
//            + "nvl(ssgpid.TOT_AMT,0) amount_due_remaining, rcta.org_id,\n"
//            + "sgps.customer_id party_id, sgps.date_of_delivery,\n"
//            + " sgps.customer_name, sgps.customer_address, sgps.contact_no,\n"
//            + " sgps.service_location, sgps.meaning model\n"
//            + " FROM ra_customer_trx_all rcta,\n"
//            + "ss_gate_pass_service sgps,\n"
//            + "SS_SERVICE_GATE_PASS_INFO_DMS ssgpid,\n"
//            + " ar_payment_schedules_all apsa,\n"
//            + "  gd_fdi_trans gdf\n"
//            + " WHERE rcta.customer_trx_id = apsa.customer_trx_id\n"
//            + " and ssgpid.gate_pass_id= sgps.gate_pass_id\n"
//            + " AND rcta.attribute4 = gdf.trans_id\n"
//            + " AND sgps.service_request_number = gdf.trans_ref_num\n"
//            + " AND sgps.vehicle_no = gdf.ge1\n"
//            + " AND sgps.vehicle_no = ?1\n"
//            + " AND sgps.service_location =?2\n"
//            + " and gdf.ge10='CUS' AND ROWNUM=1\n"
//            + " ORDER BY SGPS.DATE_OF_DELIVERY DESC", nativeQuery = true)
//    public List<Map> getGpDetailsByVehicleNoAndServiceLocation(String vehicleNo, String location);

    @Query(value = " select DISTINCT GDF.GE1  \"vehicleNo\" ,hp.PARTY_NAME \"custName\" ,(hp.ADDRESS1 ||','|| hp.ADDRESS2||','||hp.ADDRESS3||','||hp.ADDRESS4||','||hp.ADDRESS4||','||hp.city||','||hp.postal_code ||','||hp.state) \"address\" ,\n"
            + "hp.PRIMARY_PHONE_NUMBER  \"contactNo\" , rcta.ct_reference \"invoiceNo\" , rcta.trx_number \"transactionNo\" ,rcta.trx_date \"transactionDate\" ,apsa.amount_due_remaining \"pendingAmount\" , rcta.org_Id \"orgId\" \n"
            + "       from \n"
            + "    ra_customer_trx_all rcta,\n"
            + "    AR_PAYMENT_SCHEDULES_ALL apsa,GD_FDI_TRANS GDF,hz_cust_accounts hca,hz_parties hp\n"
            + "    where rcta.ct_reference =?1\n"
            + "    and rcta.customer_trx_id=apsa.customer_trx_id\n"
            + "    AND rcta.ct_reference=GDF.TRANS_ID\n"
            + "    and rcta.BILL_TO_CUSTOMER_ID=hca.CUST_ACCOUNT_ID\n"
            + "    AND hca.party_id=hp.party_id", nativeQuery = true)
    public Map getDetailsByInvoiceNoNew(String invoiceNo);

    Optional<SsVehDelvTrans> findFirstByInvoiceNoOrderByCreationDateDesc(String invoiceNo);

//     Optional<SsVehDelvTrans>findByCt_Reference(String invoiceNo);
    @Query(value = "SELECT MAX(trans_id) FROM SS_VEH_DELV_TRANS", nativeQuery = true)
    Integer findLastInsertedTransId();

    @Query(value = "select ct_reference from ra_customer_trx_all where ct_reference  = ?1", nativeQuery = true)
    public String findByCt_Reference(String invoiceNo);

    @Query(value = " select lookup_code PAYMENT_TYPE,description \n"
            + "           from\n"
            + "           fnd_lookup_values flv\n"
            + "          where lookup_type = ?1 and enabled_flag='Y' and lookup_code in ('CASH','UPI','CREDIT_CARD')", nativeQuery = true)
    public List<Map> getDetailsByPaymentType(String paymentType);

    //OLD QUERY FOR RECEIPT METHOD AS PER LOCATION, OU , AND PAYMENT TYPE AND DEPT
//    @Query(value = " select am.name,RECEIPT_METHOD_ID,am.attribute1,am.attribute3\n"
//            + " from ar_receipt_methods am,\n"
//            + " hr_operating_units ho,\n"
//            + " HR_ALL_ORGANIZATION_UNITS hrou\n"
//            + " where am.name like '%'||ho.SHORT_CODE||'%'\n"
//            + " and am.attribute1=:department\n"
//            + " and decode(:paymentType,'CASH',am.attribute3,'UPI',am.attribute3)=decode(:paymentType,'CASH','Cash','UPI','Bank')\n"
//            + " and am.name like '%'||hrou.name\n"
//            + " and hrou.name=:location\n"
//            + " and ho.organization_id=:ouId and am.END_DATE is null\n"
//            + " union\n"
//            + " select am.name,RECEIPT_METHOD_ID,am.attribute1,am.attribute3\n"
//            + " from ar_receipt_methods am,\n"
//            + " hr_operating_units ho,\n"
//            + " HR_ALL_ORGANIZATION_UNITS hrou\n"
//            + " where am.name like '%'||ho.SHORT_CODE||'%'\n"
//            + " and am.attribute1='COMMON'--:department\n"
//            + "--and decode(:paymentType,'CASH',am.attribute3,'UPI',am.attribute3,'CREDIT_CARD',am.attribute3)=decode(:paymentType,'CASH','Cash','UPI','Bank','CREDIT_CARD','Credit Card')\n"
//            + "and decode(:paymentType,'UPI',am.attribute3,'CASH',am.attribute3,'UPI',am.attribute3,'CREDIT_CARD',am.attribute3)=decode(:paymentType,'UPI','UPI','CASH','Cash','UPI','Bank','CREDIT_CARD','Credit Card')\n"
//            + "and am.name like '%'||hrou.name\n"
//            + "and hrou.name=:location\n"
//            + "and ho.organization_id=:ouId and am.END_DATE is null ", nativeQuery = true)
//    public List<Map> getPaymentDetailsByDepartment(String department, String paymentType, String location, Integer ouId);
    
    //NEW QUERY FOR RECEIPT METHOD AS PER LOCATION, OU , AND PAYMENT TYPE AND DEPT
    //CHANGES DONE BY HARSH ON 14 SEP 2025 FOR COCHIN LOCATION AS IN ARRECEIPTMETHOD THE LOC IS 11CO-501 AND NOT 11CO.501
    @Query(value = " SELECT am.NAME,\n"
            + "       am.receipt_method_id,\n"
            + "       am.attribute1,\n"
            + "       am.attribute3\n"
            + "  FROM ar_receipt_methods am\n"
            + "       JOIN hr_operating_units ho\n"
            + "         ON am.NAME LIKE '%' || ho.short_code || '%'\n"
            + "       JOIN hr_all_organization_units hrou\n"
            + "         ON hrou.NAME = :location\n"
            + " WHERE am.attribute1 = :department\n"
            + "   AND DECODE(:paymentType,\n"
            + "              'UPI', am.attribute3,\n"
            + "              'CASH', am.attribute3,\n"
            + "              'Cash', am.attribute3,\n"
            + "              'Bank') =\n"
            + "       DECODE(:paymentType,\n"
            + "              'UPI', 'UPI',\n"
            + "              'CASH', 'Cash',\n"
            + "              'Cash', 'Cash',\n"
            + "              'Bank')\n"
            + "   -- normalize dash to dot before comparing\n"
            + "   AND REPLACE(am.NAME, '-', '.') LIKE '%' || :location\n"
            + "   AND ho.organization_id = :ouId\n"
            + "   AND am.end_date IS NULL\n"
            + "UNION\n"
            + "SELECT am.NAME,\n"
            + "       am.receipt_method_id,\n"
            + "       am.attribute1,\n"
            + "       am.attribute3\n"
            + "  FROM ar_receipt_methods am\n"
            + "       JOIN hr_operating_units ho\n"
            + "         ON am.NAME LIKE '%' || ho.short_code || '%'\n"
            + "       JOIN hr_all_organization_units hrou\n"
            + "         ON hrou.NAME = :location\n"
            + " WHERE am.attribute1 = 'COMMON'\n"
            + "   AND DECODE(:paymentType,\n"
            + "              'UPI', am.attribute3,\n"
            + "              'CASH', am.attribute3,\n"
            + "              'UPI', am.attribute3,\n"
            + "              'CREDIT_CARD', am.attribute3) =\n"
            + "       DECODE(:paymentType,\n"
            + "              'UPI', 'UPI',\n"
            + "              'CASH', 'Cash',\n"
            + "              'UPI', 'Bank',\n"
            + "              'CREDIT_CARD', 'Credit Card')\n"
            + "   -- normalize dash to dot before comparing\n"
            + "   AND REPLACE(am.NAME, '-', '.') LIKE '%' || :location\n"
            + "   AND ho.organization_id = :ouId\n"
            + "   AND am.end_date IS NULL", nativeQuery = true)
    public List<Map> getPaymentDetailsByDepartment(String department, String paymentType, String location, Integer ouId);

    @Query(value = "  SELECT ROW_NUMBER() OVER (ORDER BY CREATION_DATE asc) AS sr_no,\n"
            + "NVL(VEHICLE_NO,'-') VEHICLE_NO, NVL(CUST_NAME,'-') CUST_NAME, NVL(CUST_ADDRESS,'-') CUST_ADDRESS,\n"
            + "NVL('******'||substr(CUST_CONTACT_NO,7,10),'-')CUST_CONTACT_NO, NVL(INVOICE_NO,'-') INVOICE_NO, NVL(TRANSACTION_NO,'-') TRANSACTION_NO,\n"
            + "NVL(TO_CHAR(AMOUNT_DUE_REMAINING),'-') AMOUNT_DUE_REMAINING,  NVL(TO_CHAR(AMOUNT_PAID),'-') AMOUNT_PAID, \n"
            + "NVL('******' || SUBSTR(ATTRIBUTE2, 7, 10), '-') OTP_SEND_NUMBER,\n"
            + "NVL(TO_CHAR(AMOUNT_PENDING),'-') AMOUNT_PENDING, NVL(PAYMENT_TYPE,'-') PAYMENT_TYPE, NVL(RECEIPT_METHOD_ID,'-') RECEIPT_METHOD_ID,\n"
            + "NVL(PAYMENT_TRANS_NO,'-') PAYMENT_TRANS_NO, NVL(PAYMENT_IMAGE,'-') PAYMENT_IMAGE, NVL(TO_CHAR(CREATION_DATE,'DD-MM-YYYY HH24:MI:SS'),'-') CREATION_DATE,\n"
            + "NVL(DEPARTMENT,'-') DEPARTMENT, NVL(DRIVER_NAME,'-') DRIVER_NAME, NVL('******'||substr(DRIVER_CONTACT_NO,7,10),'-') DRIVER_CONTACT_NO,\n"
            + "NVL(TO_CHAR(DRIVER_LOC_ID),0) DRIVER_LOC_ID, NVL(DRIVER_LOCATION_NAME,'-') DRIVER_LOCATION_NAME,\n"
            + "NVL(TO_CHAR(PARTYID),'-') PARTY_ID, NVL(TO_CHAR(OU_ID),0) OU_ID\n"
            + "FROM SS_VEH_DELV_TRANS WHERE DRIVER_LOC_ID=:driverLocId AND CREATION_DATE BETWEEN :fromDate AND :toDate", nativeQuery = true)
    public List<Map> getTransactionDetailsByLocation(Integer driverLocId, Date fromDate, Date toDate);

    @Query(value = " select * from( SELECT distinct SVDT.ATTRIBUTE1 GATE_PASS_ID, SVDT.VEHICLE_NO,SVDT.CUST_NAME CUSTOMER_NAME, SVDT.CUST_ADDRESS CUSTOMER_ADDRESS, SVDT.CUST_CONTACT_NO CONTACT_NO,\n"
            + "SVDT.INVOICE_NO, SVDT.TRANSACTION_NO TRANS_REF_NUM, SVDT.TRANSACTION_DATE DATE_OF_DELIVERY, SVDT.AMOUNT_PENDING AMOUNT_DUE_REMAINING,\n"
            + "SVDT.CREATION_DATE , SVDT.PARTYID PARTY_ID, SVDT.OU_ID ORG_ID,\n"
            + "sgps.service_location, sgps.meaning model, gdf.executive  FROM SS_VEH_DELV_TRANS SVDT, gd_fdi_trans GDF, ss_gate_pass_service sgps,\n"
            + "ra_customer_trx_all rcta\n"
            + " WHERE  SVDT.TRANSACTION_NO = gdf.trans_ref_num  AND sgps.gate_pass_id=SVDT.ATTRIBUTE1\n"
            + "AND sgps.vehicle_no = gdf.ge1\n"
            + "AND sgps.service_request_number = gdf.trans_ref_num and  rcta.attribute4 = gdf.trans_id\n"
            + "AND svdt.ATTRIBUTE1=?1 and gdf.ge10='CUS' AND sgps.service_location =?2\n"
            + "order by svdt.creation_date desc)\n"
            + "where rownum=1", nativeQuery = true)
    public List<Map> getDetailsByAttribute1AndServiceLocation(String attribute1, String location);

    @Query(value = " select * from( SELECT distinct SVDT.ATTRIBUTE1 GATE_PASS_ID, SVDT.VEHICLE_NO,SVDT.CUST_NAME CUSTOMER_NAME, SVDT.CUST_ADDRESS CUSTOMER_ADDRESS, SVDT.CUST_CONTACT_NO CONTACT_NO,\n"
            + "SVDT.INVOICE_NO, SVDT.TRANSACTION_NO TRANS_REF_NUM, SVDT.TRANSACTION_DATE DATE_OF_DELIVERY, SVDT.AMOUNT_PENDING AMOUNT_DUE_REMAINING,\n"
            + "SVDT.CREATION_DATE , SVDT.PARTYID PARTY_ID, SVDT.OU_ID ORG_ID,\n"
            + " sgps.service_location, sgps.meaning model, gdf.executive  FROM SS_VEH_DELV_TRANS SVDT, gd_fdi_trans GDF, ss_gate_pass_service sgps,\n"
            + "ra_customer_trx_all rcta\n"
            + "WHERE  SVDT.TRANSACTION_NO = gdf.trans_ref_num  AND sgps.gate_pass_id=SVDT.ATTRIBUTE1\n"
            + "AND sgps.vehicle_no = gdf.ge1\n"
            + "AND sgps.service_request_number = gdf.trans_ref_num and  rcta.attribute4 = gdf.trans_id\n"
            + "AND svdt.vehicle_no=?1 and gdf.ge10='CUS' AND sgps.service_location =?2\n"
            + "order by svdt.creation_date desc)\n"
            + "where rownum=1", nativeQuery = true)
    public List<Map> getDetailsByVehicleNoAndServiceLocation(String vehicleNo, String location);

    public Optional<SsVehDelvTrans> findFirstByVehicleNoOrderByCreationDateDesc(String regNo);
}
