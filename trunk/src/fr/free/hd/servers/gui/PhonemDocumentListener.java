package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class PhonemDocumentListener implements DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7924816720292256274L;
	
	protected PhonenListModel model;
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		int cursor=0;
		int start=0;
		int end=0;
		String subString; 

		try {
			
		for(int index = 0;index <model.getAllPositions().size();index++)
		{
			if(model.getAllPositions().get(index).getStart()<= e.getOffset()
			&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())> e.getOffset())
			{
				start = index;
			}
			
			if(model.getAllPositions().get(index).getStart()<= (e.getOffset()+e.getLength())
			&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())> (e.getOffset()+e.getLength()))
			{
				end = index;
			}
		}
		
		//2. Prend un interval de +1, -1 la position, si l'insertion concerne une phonem d'avant ou d'après
		//Il est possible de prendre -1 ?
		if(cursor>0)
		{
			start = cursor -1;
		}
		//Il est possible de prendre +1 ?
		if(cursor< (model.getAllPositions().size()-1))
		{
			end = cursor +1;
		}
		
		
			subString = e.getDocument().getText(
					model.getAllPositions().get(start).getStart()
					, 	model.getAllPositions().get(end).getStart() 
						- model.getAllPositions().get(start).getStart()
						+ model.getAllPositions().get(end).getString().length()
						+ e.getLength());
			
		List<Position> tmpPosition = decompile(subString);
		model.updateCollection(start, end, tmpPosition);
		
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		int cursor=0;
		int start=0;
		int end=0;
		
		try 
		{
			String subString = null;
			//Si la liste est vide inutile de chercher à faire quqoie ce soit
			if(model.getAllPositions().size()>0)
			{
				//1. Retrouve la position concernée par l'insertion
				for(int index = 0;index <model.getAllPositions().size();index++)
				{
					if(model.getAllPositions().get(index).getStart()<= e.getOffset()
					&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())> e.getOffset())
					{
						cursor = index;
						break;
					}
				}
				
				//2. Prend un interval de +1, -1 la position, si l'insertion concerne une phonem d'avant ou d'après
				start = end = cursor;		
					//Il est possible de prendre -1 ?
					if(cursor>0)
					{
						start = cursor -1;
					}
					//Il est possible de prendre +1 ?
					if(cursor< (model.getAllPositions().size()-1))
					{
						end = cursor +1;
					}
				
				subString = e.getDocument().getText(
						model.getAllPositions().get(start).getStart()
						, 	model.getAllPositions().get(end).getStart() 
							- model.getAllPositions().get(start).getStart()
							+ e.getLength());
				
			}
			else
			{
				
					subString = e.getDocument().getText(e.getOffset(), e.getLength());
			}
		
		
			List<Position> tmpPosition = decompile(subString);
			model.updateCollection(start, end, tmpPosition);
		
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		int start=0;
		int end=0;
		int cursor=0;
		String subString; 
	try
	{
		for(int index = 0;index <model.getAllPositions().size();index++)
		{
			if(model.getAllPositions().get(index).getStart()<= e.getOffset()
			&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())> e.getOffset())
			{
				start = index;
			}
			if(model.getAllPositions().get(index).getStart()<= (e.getOffset()+e.getLength())
			&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())> (e.getOffset()+e.getLength()))
			{
				end = index;
			}
		}
		
		//2. Prend un interval de +1, -1 la position, si l'insertion concerne une phonem d'avant ou d'après
		//Il est possible de prendre -1 ?
		if(cursor>0)
		{
			start = cursor -1;
		}
		//Il est possible de prendre +1 ?
		if(cursor< (model.getAllPositions().size()-1))
		{
			end = cursor +1;
		}
		
		
			subString = e.getDocument().getText(
					model.getAllPositions().get(start).getStart()
					, 	model.getAllPositions().get(end).getStart() 
						- model.getAllPositions().get(start).getStart()
						+ model.getAllPositions().get(end).getString().length()
						+ e.getLength());
			
		List<Position> tmpPosition = decompile(subString);
		model.updateCollection(start, end, tmpPosition);
		
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private List<Position> decompile(String string)
	{
		List<Position> phonemList =  new ArrayList<Position>();
		
		int windowMaxSize=5;
		int windowSize=1;
		int windowInitialStart=0;
		int windowStart=0;
		
		Position blankPosition = new Position();
		blankPosition.setStart(windowStart);
		
		while(windowStart<string.length())
		{
			//TODO: Regexp
			String potentialPhonem = string.substring(windowStart, ((windowStart + windowSize > string.length())? string.length() - 1 : (windowStart + windowSize)));
			
			if(model.getPhonemCaches().containsKey(potentialPhonem))
			{
				
				if(blankPosition.getStart()<windowStart)
				{
					 blankPosition.setString(string.substring(
							 blankPosition.getStart(),
							 windowStart));
					 phonemList.add(blankPosition);
				}
				
				Position position = new Position();
				position.setPhonem(model.getPhonemCaches().get(potentialPhonem));
				position.setStart(windowStart);
				position.setString(potentialPhonem);

				phonemList.add(position);
				
				windowInitialStart = windowStart = windowStart+windowSize;
				windowSize = 1;
				
				blankPosition = new Position();
				blankPosition.setStart(windowStart);
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
		
		//Fin de la chaîne de caractère, il reste des caractères non utilise ?
		if(phonemList.size()>0 && (phonemList.get(phonemList.size()-1).getStart()+phonemList.get(phonemList.size()-1).getString().length() != string.length()))
		{
			
			blankPosition.setStart(phonemList.get(phonemList.size()-1).getStart()+phonemList.get(phonemList.size()-1).getString().length());
			blankPosition.setString(string.substring(blankPosition.getStart(),string.length()));	
			phonemList.add(blankPosition);
		}

		return phonemList;
	}

	
	public PhonemDocumentListener(PhonenListModel model)
	{
		this.model = model;
	}
}
