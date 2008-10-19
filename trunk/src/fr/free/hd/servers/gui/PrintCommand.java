package fr.free.hd.servers.gui;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.richclient.command.ActionCommand;

public class PrintCommand extends ActionCommand {

	private PhonenListModel model;
	
	public PrintCommand()
	{
		  super("printCommand");
	}
	
	@Override
	protected void doExecuteCommand() {
		  // - Chargement et compilation du rapport
        JasperDesign jasperDesign = JRXmlLoader.load("F:\\projects\\workspace\\LPCDraw\\rsc\\fr\\free\\hd\\servers\\ui\\Simple.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("Titre", "Titre");

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        // - Création du rapport au format PDF
        JasperViewer.viewReport(jasperPrint);
        //JasperExportManager.
        //exportReportToPdfFile(jasperPrint, "D:\\iReport-1.2.1\\classic.pdf");

	}

	public void setModel(PhonenListModel model) {
		this.model = model;
	}

}
