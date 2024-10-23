package com.transporte.app.service.impl;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public byte[] generateTicketReport() throws Exception {
        InputStream reportStream = getClass().getResourceAsStream("/ReportePasajes.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();
        // Agrega parámetros si es necesario

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generateBusReport() throws Exception {
        InputStream reportStream = getClass().getResourceAsStream("/ReporteBuses.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();
        // Agrega parámetros si es necesario

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}