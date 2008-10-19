package fr.free.hd.servers.gui;

import fr.free.hd.servers.entities.Phonem;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PhonemDataSource implements JRDataSource {

	private PhonenListModel model;
	private JPicture picture;
	int index=0;
	
	public PhonemDataSource(PhonenListModel model, JPicture picture)
	{
		this.model = model;
		this.picture = picture;
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
	
		if(field.getName().equals("phonem"))
		{
			return ((Phonem) model.getElementAt(index++)).getPhonem();
		}
		if(field.getName().equals("picture"))
		{
			return picture.getComponent(index);
		}
		return null;
	}

	@Override
	public boolean next() throws JRException {
		
		return (index < model.getSize());
	}

}
