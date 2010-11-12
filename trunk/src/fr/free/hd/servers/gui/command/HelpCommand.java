package fr.free.hd.servers.gui.command;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.springframework.richclient.command.ActionCommand;

public class HelpCommand extends ActionCommand {


	/**
	 * @see org.springframework.richclient.command.ActionCommand#doExecuteCommand()
	 */
	@Override
	protected void doExecuteCommand() {

		try {
			
			//Depends of
			String filename = "Prise_en_main_logiciel.pdf";
			File file = new File(filename);
			if(!file.exists())
			{
				filename = ".." + File.separator  + filename;
				file = new File(filename);
				file = file.getCanonicalFile();
			}
			
			Desktop.getDesktop().open(file);
			
		} catch (IOException e) {
			showErrorMessage(e.getLocalizedMessage());
		}
	}
	
	protected static void showErrorMessage(String error)
	{
		JOptionPane
		.showMessageDialog(
				null,
				"Impossible d'ouvrir l'aide. Il est nécessaire d'avoir Acrobat Reader pour que l'aide s'affiche. \n"
						+ "Pour télécharger Acrobat Reader : http://get.adobe.com/fr/reader/\n"
						+ error,
				"Impossible d'ouvrir l'aide",
				JOptionPane.ERROR_MESSAGE);
	}
}
