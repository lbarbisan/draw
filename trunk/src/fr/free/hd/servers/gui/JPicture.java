package fr.free.hd.servers.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.JPanel;

public class JPicture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911569452984025191L;
	private URL path = null;
	private Image image = null;
	
	public void paint(Graphics g)
		{
			super.paintComponent(g);
			if(image != null) // Si l'image existe, ...
				g.drawImage(image, 100, 100, this); // ... on la dessine
		}

	public URL getPath() {
		return path;
	}

	public void setPath(URL path) {
		this.path = path;
		image = this.getToolkit().getImage(path);
	}

}
