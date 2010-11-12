package fr.free.hd.servers.gui.command;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import org.springframework.richclient.command.ActionCommand;

import fr.free.hd.servers.LPCDraw;

public class HelpCommand extends ActionCommand {

	/**
	 * @see org.springframework.richclient.command.ActionCommand#doExecuteCommand()
	 */
	@Override
	protected void doExecuteCommand() {

		try {

			File temp = File.createTempFile("Prise_en_main_logiciel", ".pdf");
			temp.deleteOnExit();

			FileOutputStream writer = new FileOutputStream(temp);
			InputStream stream = LPCDraw.class
					.getResourceAsStream("help/Prise_en_main_logiciel.pdf");

			copy(stream, writer);

			Desktop.getDesktop().open(temp);

		} catch (IOException e) {
			showErrorMessage(e.getLocalizedMessage());
		}
	}

	protected static void copy(InputStream inFile, OutputStream outFile)
			throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(inFile);
			out = new BufferedOutputStream(outFile);
			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
				out.write(data);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	protected static void showErrorMessage(String error) {
		JOptionPane
				.showMessageDialog(
						null,
						"Impossible d'ouvrir l'aide. Il est nécessaire d'avoir Acrobat Reader pour que l'aide s'affiche. \n"
								+ "Pour télécharger Acrobat Reader : http://get.adobe.com/fr/reader/\n"
								+ error, "Impossible d'ouvrir l'aide",
						JOptionPane.ERROR_MESSAGE);
	}
}
