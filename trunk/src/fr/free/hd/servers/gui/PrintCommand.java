package fr.free.hd.servers.gui;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.richclient.command.ActionCommand;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import fr.free.hd.servers.entities.Phonem;

public class PrintCommand extends ActionCommand {

	private PhonenListModel model;
	
	public PrintCommand()
	{
		  super("printCommand");
	}
	

	/**
	 * @see org.springframework.richclient.command.ActionCommand#doExecuteCommand()
	 */
	@Override
	protected void doExecuteCommand() {

		// etape 1: creation du document
        Document document = new Document(PageSize.A4.rotate());
        
        // Create temp file.
        File temp;
		try {
			temp = File.createTempFile("lpcdraw", ".pdf");
			// Delete temp file when program exits.
			temp.deleteOnExit();
            // etape 2:
            // creation du writer -> PDF ou HTML 
            PdfWriter.getInstance(document, new FileOutputStream(temp));
            // etape 3: ouverture du document
            document.open();
            // etape 4: Creation d'un tableau  
            PdfPTable table = createTable(model);	            
            document.add(table);
        
        	document.close();
            
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + temp.getAbsolutePath());
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setModel(PhonenListModel model) {
		this.model = model;
	}
	
	
	/*Création du tableau de notre emploi du temps*/	
	public PdfPTable createTable(PhonenListModel model) throws BadElementException {
		
		PdfPTable table = new PdfPTable(model.getSize() > 6 ? 6 : model.getSize());
		table.setSplitRows(false);
		table.setWidthPercentage(model.getSize() > 6 ? 100 : model.getSize()*16);
		table.getDefaultCell().setFixedHeight(160);
		
		for(int index = 0;index < model.getSize();index++)
		{
			PdfPTable innertable = new PdfPTable(1);
			innertable.getDefaultCell().setFixedHeight(140);
			Phonem phonem = (Phonem)model.getElementAt(index);
			try {
				innertable.addCell(com.lowagie.text.Image.getInstance( FaceGenerator.Create(phonem), Color.WHITE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PdfPCell cell = new PdfPCell(new Paragraph(phonem.getPhonem()));
			cell.setFixedHeight(10);
			innertable.addCell(cell);
			table.addCell(innertable);
		}
		table.completeRow();
		return table;
	}
}
