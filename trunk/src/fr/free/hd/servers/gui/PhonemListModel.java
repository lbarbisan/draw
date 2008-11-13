package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import fr.free.hd.servers.entities.Phonem;

public class PhonemListModel extends AbstractListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2852839549377279707L;
	private List<Phonem> list = new ArrayList<Phonem>();
	
	public PhonemListModel(List<Phonem> list)
	{
		if(list==null)
		{
			throw new NullPointerException("List must be set");
		}
		this.list = list;
	}
	
	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
