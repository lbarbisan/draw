package fr.free.hd.servers.gui;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import fr.free.hd.servers.entities.Phonem;

public class PhonemListRenderer extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7912817506483642111L;
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Phonem phonem = (Phonem)value;
		
		this.setVerticalTextPosition(JLabel.BOTTOM);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setText(phonem.getPhonem());
		this.setIcon(new ImageIcon(FaceGenerator.Create(phonem)));
		
		return this;
	}

}
