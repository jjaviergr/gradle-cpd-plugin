package de.aaschmid.gradle.plugins.cpd.internal;

import de.aaschmid.gradle.plugins.cpd.Cpd;
import de.aaschmid.gradle.plugins.cpd.CpdCsvFileReport;
import de.aaschmid.gradle.plugins.cpd.CpdReports;
import de.aaschmid.gradle.plugins.cpd.CpdTextFileReport;
import de.aaschmid.gradle.plugins.cpd.CpdVsFileReport;
import de.aaschmid.gradle.plugins.cpd.CpdXmlFileReport;
import org.gradle.api.internal.CollectionCallbackActionDecorator;
import org.gradle.api.reporting.SingleFileReport;
import org.gradle.api.reporting.internal.TaskReportContainer;

public class CpdReportsImpl extends TaskReportContainer<SingleFileReport> implements CpdReports {

    public CpdReportsImpl(Cpd task, CollectionCallbackActionDecorator callbackActionDecorator) {
        super(SingleFileReport.class, task, callbackActionDecorator);

        add(CpdCsvFileReportImpl.class, "csv", task);
        add(CpdTextFileReportImpl.class, "text", task);
        add(CpdVsFileReportImpl.class, "vs", task);
        add(CpdXmlFileReportImpl.class, "xml", task);
    }

    @Override
    public CpdCsvFileReport getCsv() {
        return (CpdCsvFileReport) getByName("csv");
    }

    @Override
    public CpdTextFileReport getText() {
        return (CpdTextFileReport) getByName("text");
    }

    @Override
    public CpdVsFileReport getVs() {
        return (CpdVsFileReport) getByName("vs");
    }

    @Override
    public CpdXmlFileReport getXml() {
        return (CpdXmlFileReport) getByName("xml");
    }
}
