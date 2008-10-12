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
		int length=0;
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
				length = index;
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
			length = cursor +1;
		}
		
		
			subString = e.getDocument().getText(
					model.getAllPositions().get(start).getStart()
					, 	model.getAllPositions().get(length).getStart() 
						- model.getAllPositions().get(start).getStart()
						+ model.getAllPositions().get(length).getString().length()
						+ e.getLength());
			
		List<Position> tmpPosition = decompile(model.getAllPositions().get(start).getStart(), subString);
		model.updateCollection(start, length, tmpPosition);
		
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		int cursor=0;
		int start=0;
		int charStart = 0;
		int length=0;
		
		try 
		{
			String subString = null;
			charStart = e.getOffset();
			
			if(model.getAllPositions().size()==0)
			{
					subString = e.getDocument().getText(charStart, e.getLength());
			}
			else if(e.getOffset()==model.getAllPositions().size())
			{
					subString = e.getDocument().getText(charStart, e.getLength());
					start = charStart;
					length = 0;
			}
			//Si la liste est vide inutile de chercher à faire quqoie ce soit
			else
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
				start = cursor;
				length = 0;
					//Il est possible de prendre -1 ?
					if(cursor>0)
					{
						start = cursor -1;
						length++;
					}
					//Il est possible de prendre +1 ?
					if(cursor< (model.getAllPositions().size()-1))
					{
						length++;
					}
				
				charStart = model.getAllPositions().get(start).getStart();
				subString = e.getDocument().getText(charStart,model.getAllPositions().get(length).getStart() - charStart + e.getLength());
			}
		
			List<Position> tmpPosition = decompile(charStart, subString);
			
			model.updateCollection(start, length, tmpPosition);
		
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		int start=0;
		int length=0;
		int cursor=0;
		
		String subString;
	try
	{
		for(int index = 0;index <model.getAllPositions().size();index++)
		{
			if(model.getAllPositions().get(index).getStart()<= e.getOffset()
			&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())>= e.getOffset())
			{
				start = index;
			}
			if(model.getAllPositions().get(index).getStart()<= (e.getOffset()+e.getLength())
			&& (model.getAllPositions().get(index).getStart() + model.getAllPositions().get(index).getString().length())>= (e.getOffset()+e.getLength()))
			{
				length = start-index;
			}
		}
		
		//2. Prend un interval de +1, -1 la position, si l'insertion concerne une phonem d'avant ou d'après
		//Il est possible de prendre -1 ?
		length=0;
		if(cursor>0)
		{
			start = cursor -1;
			length++;
		}
		//Il est possible de prendre +1 ?
		if(cursor< (model.getAllPositions().size()-1))
		{
			length++;
		}
		
		
			subString = e.getDocument().getText(
					model.getAllPositions().get(start).getStart()
					, 	model.getAllPositions().get(length).getStart() 
						- model.getAllPositions().get(start).getStart()
						+ e.getLength());
			
		List<Position> tmpPosition = decompile(model.getAllPositions().get(start).getStart(), subString);
		model.updateCollection(start, length, tmpPosition);
		
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private List<Position> decompile(int refstart, String string)
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
				position.setStart(refstart + windowStart);
				position.setString(potentialPhonem);

				phonemList.add(position);
				
				windowInitialStart = windowStart = windowStart+windowSize;
				windowSize = 1;
				
				blankPosition = new Position();
				blankPosition.setStart(refstart + windowStart);
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
		if(phonemList.size()>0 && (
				phonemList.get(phonemList.size()-1).getStart()
				+ phonemList.get(phonemList.size()-1).getString().length() != string.length()))
		{
			
			blankPosition.setStart(phonemList.get(phonemList.size()-1).getStart()+phonemList.get(phonemList.size()-1).getString().length());
			blankPosition.setString(string.substring(blankPosition.getStart() - refstart,string.length()));	
			phonemList.add(blankPosition);
		}

		return phonemList;
	}

	
	public PhonemDocumentListener(PhonenListModel model)
	{
		this.model = model;
	}
}
