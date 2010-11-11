package fr.free.hd.servers.gui.command;

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
			String filename = "Prise_en_main_logiciel.pdf";
			
			boolean exists = (new File(filename)).exists();
			if (exists) {
			
			Process process = Runtime
					.getRuntime()
					.exec(
							"rundll32 url.dll,FileProtocolHandler " + filename);
			process.waitFor();
			if(process.exitValue()!=0)
				showErrorMessage("Process exit with return code greater than 0.");
			}
			else
			{
				showErrorMessage("File " + filename + " doesn't exist.");
			}
		} catch (IOException e) {
			showErrorMessage(e.getMessage());	
		} catch (InterruptedException e) {
			showErrorMessage(e.getMessage());	
		}

	}
	
	protected void showErrorMessage(String error)
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
