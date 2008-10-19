package fr.free.hd.servers.gui;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.richclient.command.ActionCommand;

import com.sun.corba.se.impl.interceptors.PICurrent;

public class PrintCommand extends ActionCommand {

	private PhonenListModel model;
	private JPicture picture;
	
	public PrintCommand()
	{
		  super("printCommand");
	}
	
	@Override
	protected void doExecuteCommand() {

		try {
			// - Chargement et compilation du rapport
			JasperDesign jasperDesign = JRXmlLoader.load("D:\\Projects\\workspace-springrcp\\LPCDraw\\rsc\\fr\\free\\hd\\servers\\ui\\Simple.jrxml");
	
	        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	
	        // - Paramètres à envoyer au rapport
	        Map parameters = new HashMap();
	        parameters.put("Titre", "Titre");
	
	        // - Execution du rapport
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new PhonemDataSource(model, picture));
	        // - Création du rapport au format PDF
	        JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setModel(PhonenListModel model) {
		this.model = model;
	}
	
	public void setPicture(JPicture picture) {
		this.picture = picture;
	}

}
