/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.dao;

import com.sai.erp.master.entity.CsiItemInstances;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author IT-HARSH
 */
public interface CsiItemInstancesDao extends JpaRepository<CsiItemInstances, Long> {

    public Optional<CsiItemInstances> findByInstanceNumber(String regNo);

    //query for getting new vehicle in ws details by regno from csi item instance table
//    @Query(value = " select cii.instance_number,cii.install_date registration_date,hca.account_number,\n" +
//"            case when hp.PARTY_TYPE='PERSON' then\n" +
//"            hp.PERSON_FIRST_NAME||' '||hp.PERSON_MIDDLE_NAME||' '||hp.PERSON_LAST_NAME \n" +
//"            ELSE PARTY_NAME\n" +
//"            end cust_name,\n" +
//"            hp.ADDRESS1||' '||hp.ADDRESS2||' '||hp.ADDRESS3||' '||hp.CITY||' '||hp.STATE||' '||hp.POSTAL_CODE address,\n" +
//"            NVL(hp.EMAIL_ADDRESS,'-') EMAIL_ADDRESS ,hp.PRIMARY_PHONE_NUMBER, msib.attribute6 CHASSIS_NO, msib.attribute12 ENGINE_NO\n" +
//"            from csi_item_instances cii,hz_parties hp, hz_cust_accounts hca , mtl_system_items_b msib\n" +
//"            where cii.instance_number=?1\n" +
//"            and hp.party_id=cii.OWNER_PARTY_ID\n" +
//"            and cii.OWNER_PARTY_ACCOUNT_ID=hca.cust_account_id\n" +
//"            and cii.INVENTORY_ITEM_ID=msib.INVENTORY_ITEM_ID\n" +
//"            AND msib.ORGANIZATION_ID=cii.INV_MASTER_ORGANIZATION_ID", nativeQuery = true)
//    public List<Map> getNewVehDetailsCsiByRegNo(String regNo);
    
    
    //query for getting new vehicle in ws details by regno from csi item instance table
    @Query(value = " select DISTINCT GFT.VIN, cii.instance_number,cii.install_date registration_date,hca.account_number,\n"
            + " case when hp.PARTY_TYPE='PERSON' then\n"
            + " hp.PERSON_FIRST_NAME||' '||hp.PERSON_MIDDLE_NAME||' '||hp.PERSON_LAST_NAME \n"
            + "ELSE PARTY_NAME\n"
            + "end cust_name,\n"
            + "hp.ADDRESS1||' '||hp.ADDRESS2||' '||hp.ADDRESS3||' '||hp.CITY||' '||hp.STATE||' '||hp.POSTAL_CODE address,\n"
            + "NVL(hp.EMAIL_ADDRESS,'-') EMAIL_ADDRESS ,hp.PRIMARY_PHONE_NUMBER, msib.attribute6 CHASSIS_NO, msib.attribute12 ENGINE_NO\n"
            + "from csi_item_instances cii,hz_parties hp, hz_cust_accounts hca , mtl_system_items_b msib, GD_FDI_TRANS GFT\n"
            + "where cii.instance_number=?1\n"
            + "and hp.party_id=cii.OWNER_PARTY_ID\n"
            + "and cii.OWNER_PARTY_ACCOUNT_ID=hca.cust_account_id\n"
            + "and cii.INVENTORY_ITEM_ID=msib.INVENTORY_ITEM_ID AND CII.INSTANCE_NUMBER=GFT.GE1\n"
            + "AND msib.ORGANIZATION_ID=cii.INV_MASTER_ORGANIZATION_ID", nativeQuery = true)
    public List<Map> getNewVehDetailsCsiByRegNo(String regNo);
    
    
    @Query(value = " SELECT cii.instance_number, msib.attribute6 chassis_no,\n"
            + "       msib.attribute12 engine_no, cii.install_date registration_date,\n"
            + "       hca.account_number,\n"
            + "       CASE\n"
            + "          WHEN hp.party_type = 'PERSON'\n"
            + "             THEN    hp.person_first_name\n"
            + "                  || ' '\n"
            + "                  || hp.person_middle_name\n"
            + "                  || ' '\n"
            + "                  || hp.person_last_name\n"
            + "          ELSE party_name\n"
            + "       END cust_name,\n"
            + "       msib.inventory_item_id,\n"
            + "       (SELECT MAX (variant_cd)\n"
            + "          FROM gd_fdi_trans\n"
            + "         WHERE ge1 = cii.instance_number AND trans_type = 'WI') variant_code,\n"
            + "       (SELECT fvl.meaning\n"
            + "          FROM (SELECT   gd.variant_cd\n"
            + "                    FROM gd_fdi_trans gd\n"
            + "                   WHERE gd.ge1 = cii.instance_number AND gd.trans_type = 'WI'\n"
            + "                ORDER BY gd.trans_date DESC) a,\n"
            + "               fnd_lookup_values_vl fvl\n"
            + "         WHERE ROWNUM = 1\n"
            + "           AND fvl.lookup_code = variant_cd\n"
            + "           AND attribute_category = 'SS_MSIL_SALES_CODE') vehicle_desc,\n"
            + "       (SELECT *\n"
            + "          FROM (SELECT   executive\n"
            + "                    FROM gd_fdi_trans\n"
            + "                   WHERE ge1 = cii.instance_number AND trans_type = 'WI'\n"
            + "                ORDER BY trans_date DESC) a\n"
            + "         WHERE ROWNUM = 1) service_advisor\n"
            + "  FROM csi_item_instances cii,\n"
            + "       mtl_system_items_b msib,\n"
            + "       hz_parties hp,\n"
            + "       hz_cust_accounts hca\n"
            + "WHERE cii.instance_number = ?1\n"
            + "   and cii.inventory_item_id = msib.inventory_item_id\n"
            + "   and hp.party_id = cii.owner_party_id\n"
            + "   and cii.owner_party_account_id = hca.cust_account_id\n"
            + "   and msib.organization_id = 119", nativeQuery = true)
    public List<Map> getVehDetailsCsiByRegNo(String regNo);
    
}
