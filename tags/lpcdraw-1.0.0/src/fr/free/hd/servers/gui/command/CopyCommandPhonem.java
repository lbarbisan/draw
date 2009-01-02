package fr.free.hd.servers.gui.command;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.progress.ProgressMonitor;

import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;
import fr.free.hd.servers.gui.StatementListModel;
import fr.free.hd.servers.gui.tools.FaceGeneratorHelper;
import fr.free.hd.servers.gui.tools.ImageSelection;

public class CopyCommandPhonem extends ActionCommand {

	private Face face;
	private StatementListModel model;
	
	@Override
	protected void doExecuteCommand() {
		
		ApplicationWindow aw = Application.instance().getActiveWindow();  
		final ProgressMonitor pm = aw.getStatusBar().getProgressMonitor();
		pm.taskStarted("Copying...", -1);  
		SwingWorker sw = new SwingWorker() {  
		            //protected Object construct() in SpringRC's SwingWorker!  
		            protected Object doInBackground() {
		            	int total = 0;
		            	int size = model.getSize()* FaceGeneratorHelper.initialWidthSize;
		            	int diff = 90/model.getSize();
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
		        			total+=diff;
		        			pm.worked(total);
		        		}
		        		
		        		Toolkit toolkit = Toolkit.getDefaultToolkit();
		        		Clipboard clipboard = toolkit.getSystemClipboard();
		        		clipboard.setContents(new ImageSelection(finalImage), null);
		        		
		        		pm.worked(100);
		        		
		            	return null;
		           }  
		           //protected void finished()  
		           @Override  
		           protected void done() {  
		               // all of the following code will be called on the Event Dispatching Thread  
		               pm.done();  
		           }  
		       };  
		       //sw.start();  
		       //sw.clear(); // reuse would be possible with SpringRC's SwingWorker!  
		       sw.execute();
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public void setModel(StatementListModel model) {
		this.model = model;
	}

}
