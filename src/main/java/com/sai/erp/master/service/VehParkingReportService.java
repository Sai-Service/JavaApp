/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author HarshG
 */
@Service
public class VehParkingReportService {

    @Autowired
    protected DataSource localDataSource;

    public byte[] getParkingReport(Map<String, Object> parameters, String fileNme) throws Exception {

        File file = ResourceUtils.getFile("classpath:ParkingInOut.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        java.sql.Connection con = localDataSource.getConnection();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        JRXlsExporter exporter = new JRXlsExporter();

        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setDetectCellType(true);
        // configuration.setCollapseRowSpan(false);
        configuration.setWhitePageBackground(false);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setWhitePageBackground(false);
        configuration.setCollapseRowSpan(true);
        configuration.setIgnoreGraphics(false);
        configuration.setWrapText(false);
        configuration.setPrintHeaderMargin(10);

        File outputFile = new File(fileNme);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArray));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        con.close();
//       return new ByteArrayInputStream(byteArray.toByteArray());
        return byteArray.toByteArray();

    }

}
