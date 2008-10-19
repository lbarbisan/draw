package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;

import fr.free.hd.servers.entities.Phonem;

public class PhonenListModel extends AbstractListModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6273561806978963558L;
	
	private List<Phonem> positions = new ArrayList<Phonem>();
	private Map<String, Phonem> phonemCaches = new HashMap<String, Phonem>();
	
	public PhonenListModel(Map<String, Phonem> caches)
	{
		this.phonemCaches = caches;
	}
	
	int start=0;
	int end=0;
	public void setString(String string)
	{
		List<Phonem> newPhonems = decompile(string);
		int newIndex=0;
		int oldIndex=0;
		boolean different = false;
		
		if(positions.size()==0)
		{
			start = 0;
			end=newPhonems.size()-1;
		}
		else
		{
			//1. Comparaison
			while(newIndex< newPhonems.size()&& oldIndex < positions.size())
			{
				Phonem newPhonem = newPhonems.get(newIndex);
				Phonem oldPhonem = positions.get(oldIndex);
				if(!newPhonem.getPhonem().equals(oldPhonem.getPhonem()))
				{
					if(different==false)
					{
						different = true;
						start = newIndex;
					}
					//Ajout d'element
					if(newPhonems.size()>positions.size())
					{
						newIndex++;
					}
					//Suppression d'element
					else if(newPhonems.size()<positions.size())
					{
						oldIndex++;
					}
					//Mise à jour d'element
					else
					{
						start = oldIndex;
						oldIndex++;
						newIndex++;
					}
					
					
				}
				else if(newPhonem.getPhonem().equals(oldPhonem.getPhonem()) && different==true)
				{
					different=false;
					//Ajout d'element
					if(newPhonems.size()>positions.size())
					{
						end = newIndex-1;
					}
					//Suppression d'element
					else if(newPhonems.size()<positions.size())
					{
						end = oldIndex-1;
					}
					//Mise à jour d'element
					else
					{
						end = newIndex-1;	
					}
					newIndex++;
					oldIndex++;
				}
				else
				{
					newIndex++;
					oldIndex++;
				}
			}
		}
		//2. Notifiaction des nouveaux elements 
		//Ajout d'elements
		if(newPhonems.size()>positions.size())
		{
			fireIntervalAdded(this, start, end);
		}
		//Suppression d'elements
		else if(newPhonems.size()<positions.size())
		{
			fireIntervalRemoved(this, start, end);
		}
		//Mise à jour d'elements
		else
		{
			fireContentsChanged(this, start, end);	
		}
		
		positions = newPhonems;
	}

	
	private List<Phonem> decompile(String string)
	{
		List<Phonem> phonemList =  new ArrayList<Phonem>();
		Phonem current = null;
		
		int windowMaxSize=5;
		int windowSize=1;
		int windowInitialStart=0;
		int windowStart=0;
		
		while(windowStart<string.length())
		{
			//TODO: Regexp
			String potentialPhonem = string.substring(windowStart, 
					((windowStart + windowSize > string.length())? string.length() : (windowStart + windowSize)));
			
			if(phonemCaches.containsKey(potentialPhonem))
			{
				current = phonemCaches.get(potentialPhonem);
			}
			
			//Move windows from + 1
			if((windowSize)>=windowMaxSize)
			{
				if(current!=null)
				{
					phonemList.add(current);
				    windowInitialStart = windowStart = windowStart+current.getPhonem().length();
					windowSize = 1;
				}
				else
				{
					windowInitialStart++;
					windowStart = windowInitialStart;
					windowSize = 1;
				}
				current = null;
			}
			//
			else
			{
				windowSize++;
			}
		}
		
		return phonemList;
	}

	
	@Override
	public Object getElementAt(int index) {
		return positions.get(index);
	}

	@Override
	public int getSize() {
		return positions.size();
	}

}
