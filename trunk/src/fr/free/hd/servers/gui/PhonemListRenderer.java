package fr.free.hd.servers.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
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
		
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		this.setText(phonem.getPhonem());
		
		return this;
	}

}
