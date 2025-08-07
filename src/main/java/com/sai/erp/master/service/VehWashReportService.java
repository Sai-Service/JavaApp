/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.erp.master.service;

import com.sai.erp.master.dao.VehWashingReportDao;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author HarshG
 */
@Service
public class VehWashReportService {

    @Autowired
    private VehWashingReportDao washReportRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    protected DataSource localDataSource;

//    public void generateAndSendFilteredReport(VehWashingReportMailDto request) throws IOException, MessagingException {
//
//        List<Object[]> results = washReportRepo.getVehWashFilteredReport(request.getOuId(), request.getLocId(), request.getFromDate(), request.getToDate());
//
//        byte[] excelData = generateExcel(results);
//        sendEmailWithAttachment(request.getEmailList(), "Vehicle Wash Report", "Attached is your filtered report.", excelData);
//    }
    public byte[] generateExcel(List<Object[]> results) throws IOException {
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Filtered Report");

            // Header row setup
            String[] headers = {
                "SR NO", "VEHICLE REGISTRATION NO", "MODEL", "SERVICE ADVISOR", "WASHING SUPERVISOR", "1ST STAGE IN TIME", "AIR BLOW-1ST STAGE",
                "UNDER BODY-1ST STAGE", "ENGINE ROOM-1ST STAGE", "1ST STAGE OUT TIME", "2ND STAGE IN TIME", "LOOSE ITEM-2ND STAGE",
                "VEH INTERIOR-2ND STAGE", "2ND STAGE OUT TIME", "3RD STAGE IN TIME", "VEH EXTERIOR-3RD STAGE",
                "GLASS POLISH-3RD STAGE", "3RD STAGE OUT TIME", "CREATION DATE"
            };

            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Data rows
            int rowIdx = 1;
            for (Object[] row : results) {
                Row dataRow = sheet.createRow(rowIdx++);

                for (int i = 0; i < headers.length; i++) {
                    Object value = (i < row.length) ? row[i] : null;
                    String cellValue = (value instanceof Timestamp || value instanceof Date)
                            ? formatDateTime(value)
                            : (value != null ? value.toString() : "");
                    dataRow.createCell(i).setCellValue(cellValue);
                }
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }

    private String formatDateTime(Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Timestamp) {
            return ((Timestamp) value).toLocalDateTime().toString();
        }
        return value.toString();
    }

//    private void sendEmailWithAttachment(List<String> recipients, String subject, String body, byte[] fileBytes) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        String senderEmail = "edpborivali@saiservice.com";
//        helper.setFrom(senderEmail);
//
//        // Convert List<String> to String array
//        helper.setTo(recipients.toArray(new String[0]));
//
//        helper.setSubject(subject);
//        helper.setText(body);
//
//        InputStreamSource attachment = new ByteArrayResource(fileBytes);
//        helper.addAttachment("VehicleWashReport.xlsx", attachment);
//
//        mailSender.send(message);
//    }
    public byte[] getVehWashMainReport(Map<String, Object> parameters, String fileNme) throws Exception {

        File file = ResourceUtils.getFile("classpath:Veh_InOut.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        java.sql.Connection con = localDataSource.getConnection();
//        //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(gpList);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
//        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
//        con.close();
//        return new ByteArrayInputStream(bytes);
        //  File file = ResourceUtils.getFile("classpath://AccountsReports//DebtorsAgingOuWise.jrxml");  //Linux

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
