package fr.free.hd.servers.gui;

import fr.free.hd.servers.entities.Phonem;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PhonemDataSource implements JRDataSource {

	private StatementListModel model;
	int index=-1;
	
	public PhonemDataSource(StatementListModel model)
	{
		this.model = model;
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
	
		if(field.getName().equals("phonem"))
		{
			return ((Phonem) model.getElementAt(index)).getPhonem();
		}
		if(field.getName().equals("picture"))
		{
			return FaceGenerator.Create((Phonem) model.getElementAt(index)) ;
		}
		return null;
	}

	@Override
	public boolean next() throws JRException {
		
		return (index++ < (model.getSize()-1));
	}

}
