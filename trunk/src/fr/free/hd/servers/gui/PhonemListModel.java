package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;

import fr.free.hd.servers.entities.Phonem;

public class PhonemListModel extends AbstractListModel implements ListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7924816720292256274L;
	
	protected Map<String, Phonem> phonemsCaches  = new HashMap<String, Phonem>();
	protected List<Phonem> phonemList = new ArrayList<Phonem>();
	protected String phonems = "";
	
	public PhonemListModel(Map<String, Phonem> phonemsCaches)
	{
		this.phonemsCaches = phonemsCaches;
	}
	
	//TODO : Mettre en place un meilleur algorithme
	public void setPhonem(String phonems)
	{
		if(phonems.equals(this.phonems))
		{
			return;
		}
		
		if(phonems==null || phonems =="")
		{
			phonemList.clear();
			fireContentsChanged(this, 0, 0);
		}
		else if(phonems.startsWith(this.phonems) && this.phonems !="")
		{
			int start = this.phonems.length();
			this.phonems = phonems;
			decompile(start);
			fireIntervalAdded(this, start, phonems.length());
		}
		else
		{
			this.phonems =phonems;
			phonemList.clear();
			decompile(0);
			fireContentsChanged(this, 0, phonemList.size()-1);
		}
		if(phonems==null)
		{
			return;
		}	
	}
	
	private void decompile(int start)
	{
		int windowMaxSize=5;
		int windowSize=1;
		int windowInitialStart=start;
		int windowStart=start;
		while(windowStart<phonems.length())
		{
			//TODO: Regexp
			String potentialPhonem = phonems.substring(
					windowStart, 
					(
						(windowStart + windowSize > phonems.length())? 
						phonems.length() - 1 : (windowStart + windowSize)
					)
			);
			
			if(phonemsCaches.containsKey(potentialPhonem))
			{
				phonemList.add(phonemsCaches.get(potentialPhonem));
				windowInitialStart = windowStart = windowStart+windowSize;
				windowSize = 1;
			}
			//Move windows from + 1
			else if((windowSize)>=windowMaxSize)
			{
				windowInitialStart++;
				windowStart = windowInitialStart;
				windowSize = 1;
			}
			//
			else
			{
				windowSize++;
			}
		}
	}
	
	@Override
	public Object getElementAt(int index) {
		return phonemList.get(index);
	}

	@Override
	public int getSize() {
		return phonemList.size();
	}

}
