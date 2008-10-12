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
	
	private List<Integer> links = new ArrayList<Integer>();
	private List<Position> allPositions = new ArrayList<Position>();
	private List<Position> positions = new ArrayList<Position>();
	private Map<String, Phonem> phonemCaches = new HashMap<String, Phonem>();
	
	public PhonenListModel(Map<String, Phonem> caches)
	{
		this.phonemCaches = caches;
	}
	
	public void updateCollection(int start, int length, List<Position> tmpPosition) {
			int linksValueIndex = 0 ;
			int linksIndex = linksValueIndex;
			int initialStartlinksValueIndex = linksValueIndex;
			int initialFinallinksValueIndex = linksValueIndex;
			
			if(links.size()>0 && length!=0)
			{
				initialFinallinksValueIndex = links.get(start+(length-1));
				
				for(int index = start;index < (start+length);index++)
				{
					if(links.get(start)>= 0)
					{					
						positions.remove((int)(links.get(start)));
						for(int index2 = start;index2 < links.size();index2++)
						{
							links.set(index2,links.get(index2) - 1);
						}
						
					}
					allPositions.remove((int)start);
					links.remove((int)start);
					
				}
			}
			
			for(int index =0;index < tmpPosition.size();index++)
			{	
				Position position = tmpPosition.get(index);
				
				if(position.getPhonem()!=null)
				{
					for(int index2 = start+index;index2 < links.size();index2++)
					{
						if(links.get(index2)>=0)
						{
							links.set(index2,links.get(index2)+1);
						}
					}
					links.add(linksIndex,linksValueIndex);
					positions.add(linksValueIndex, position);
					linksValueIndex++;
					
				}
				else
				{
					links.add(linksIndex,-1);
				}
				allPositions.add(linksIndex,position);
				linksIndex++;
			}
			
			
			if(initialFinallinksValueIndex > linksValueIndex)
			{
				if(initialStartlinksValueIndex!=linksValueIndex)
				{
					fireContentsChanged(this,initialStartlinksValueIndex , linksValueIndex);
				}
				fireIntervalRemoved(this, linksValueIndex, initialFinallinksValueIndex);
			}
			else if(initialFinallinksValueIndex < linksValueIndex)
			{
				if(initialStartlinksValueIndex!=initialFinallinksValueIndex)
				{
					fireContentsChanged(this,initialStartlinksValueIndex , initialFinallinksValueIndex);
				}
				fireIntervalRemoved(this, initialFinallinksValueIndex ,linksValueIndex);
			}
			else
			{
				fireContentsChanged(this,initialStartlinksValueIndex , initialFinallinksValueIndex);
			}
	}

	public List<Position> getAllPositions() {
		return allPositions;
	}

	public Map<String, Phonem> getPhonemCaches() {
		return phonemCaches;
	}

	
	@Override
	public Object getElementAt(int index) {
		return positions.get(index).getPhonem();
	}

	@Override
	public int getSize() {
		return positions.size();
	}

}
