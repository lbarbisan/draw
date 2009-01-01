package fr.free.hd.servers.gui.command;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;

import org.springframework.richclient.command.ActionCommand;

import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.entities.Phonem;
import fr.free.hd.servers.gui.StatementListModel;
import fr.free.hd.servers.gui.tools.FaceGeneratorHelper;
import fr.free.hd.servers.gui.tools.ImageSelection;

public class CopyCommandPhonem extends ActionCommand {

	private Face face;
	private StatementListModel model;
	
	@Override
	protected void doExecuteCommand() {
		
		int size = model.getSize()* FaceGeneratorHelper.initialWidthSize;
		BufferedImage finalImage = null;
		Graphics2D g2 = null;
		
		for(int index = 0;index < model.getSize(); index++)
		{
			Phonem phonem = (Phonem) model.getElementAt(index);
			Image image = FaceGeneratorHelper.Create(phonem, face, null);
			if(finalImage==null)
			{
				finalImage = new BufferedImage(size, image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				g2 = (Graphics2D)finalImage.createGraphics();
			}
			g2.drawImage(image, index*FaceGeneratorHelper.initialWidthSize, 0, null);
		}
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		clipboard.setContents(new ImageSelection(finalImage), null);
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public void setModel(StatementListModel model) {
		this.model = model;
	}

}
