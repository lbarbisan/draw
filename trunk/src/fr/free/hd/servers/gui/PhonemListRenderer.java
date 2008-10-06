package fr.free.hd.servers.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import fr.free.hd.servers.LPCDraw;
import fr.free.hd.servers.entities.Phonem;

public class PhonemListRenderer implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		Phonem phonem = (Phonem)value;
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		
		
		JLabel label = new JLabel();
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		label.setText(phonem.getPhonem());
		mainPanel.add(label, BorderLayout.SOUTH);
		
		try
		{
			JPicture picture = new JPicture(phonem);
			picture.setVisible(true);
			mainPanel.add(picture, BorderLayout.CENTER);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return mainPanel;
	}

}
