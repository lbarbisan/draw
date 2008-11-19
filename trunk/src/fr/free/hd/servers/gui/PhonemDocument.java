package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import fr.free.hd.servers.dao.WordDAO;
import fr.free.hd.servers.entities.Word;

public class PhonemDocument implements Document {

	/**
	 * 
	 */
	private static final long serialVersionUID = 949853974904787327L;

	private Map<Object, Object> properties = new HashMap<Object, Object>();
	private PhonemRootElement root = new PhonemRootElement(this);
	private WordDAO wordDAO;
	private List<DocumentListener> documentListener = new ArrayList<DocumentListener>();
	private List<UndoableEditListener> undoListener = new ArrayList<UndoableEditListener>();
	
	public PhonemDocument(WordDAO wordDAO)
	{
		this.wordDAO = wordDAO;
	}
	
	@Override
	public void addDocumentListener(DocumentListener listener) {
		documentListener.add(listener);
	}
	@Override
	public void addUndoableEditListener(UndoableEditListener listener) {
		undoListener.add(listener);
	}
	@Override
	public void removeDocumentListener(DocumentListener listener) {
		documentListener.remove(listener);
		
	}
	@Override
	public void removeUndoableEditListener(UndoableEditListener listener) {
		undoListener.remove(listener);
	}
	
	@Override
	public Element getDefaultRootElement() {
		return root;
	}
	
	@Override
	public int getLength() {
		return root.getEndOffset()+1;
	}
	
	@Override
	public Element[] getRootElements() {
		Element[] roots = new Element[1];
		roots[0] = root;
		return roots;
	}
	
	@Override
	public String getText(int offset, int length) throws BadLocationException {
		//Get first and last element that surround the needing text
		int startIndex = root.getElementIndex(offset);
		int endIndex = root.getElementIndex(offset+length);
		
		StringBuilder builder = new StringBuilder();
		
		//If needed remove begin and end charater
		 for (int index = startIndex; index <= endIndex; index++) {
			PhonemElement element = (PhonemElement) root.getElement(index);
			if(index == startIndex)
			{
				builder.append(element.getWord().getWord().substring(offset - element.getStartOffset()));
			}
			else if(index==endIndex)
			{
				builder.append(element.getWord().getWord().substring(0,offset + length - element.getStartOffset())); 
			}
			else
			{
				builder.append(element.getWord().getWord());
			}
		}
		
		return builder.toString();
	}
	
	@Override
	public Position createPosition(int offs) throws BadLocationException {
		return new WordPosition(this, offs);
	}
	@Override
	public Position getStartPosition() {
		return new WordPosition(0);
	}
	@Override
	public Position getEndPosition() {
		return new WordPosition(this.root.getEndOffset());
	}
	@Override
	public Object getProperty(Object key) {
		return properties.get(key);
	}
	@Override
	public void putProperty(Object key, Object value) {
		properties.put(key, value);
	}
	@Override
	public void render(Runnable r) {}
	@Override
	public void getText(int offset, int length, Segment txt)
			throws BadLocationException {
		String str =  getText(offset, length);
		txt = new Segment(str.toCharArray(), offset, length);
	}

	@Override
	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		 
		StringBuilder builder = new StringBuilder(str);
		int index = 0;
		int startOffset = 0;
		
		if(this.root.getElementCount()>0)
		{
			//1. Retrieve Word
			index = this.root.getElementIndex(offset);
			PhonemElement element = (PhonemElement) this.root.getElement(index);
			startOffset = element.getStartOffset();
			builder = new StringBuilder(element.getWord().getWord());
			builder.insert( offset - element.getStartOffset(), str);
			root.getChild().remove(index);
		}	
		
		List<Word> words = findWord(builder.toString());
		
		for (int i = 0; i < words.size(); i++) {
			PhonemElement innerElement = new PhonemElement(this, this.root,startOffset,words.get(i));
			root.getChild().add(index, innerElement);
		}
	}
	@Override
	public void remove(int offset, int length) throws BadLocationException {
		//Get first and last element that surround the needing text
		int startElementIndex = root.getElementIndex(offset);
		int endElementIndex = root.getElementIndex(offset+length);
		PhonemElement startElement = (PhonemElement) root.getElement(startElementIndex);
		PhonemElement endElement = (PhonemElement) root.getElement(endElementIndex);
		
		//Get index to recreate word if needed
		int startIndex = startElement.getStartOffset();
		int endIndex = endElement.getEndOffset();
		
		//Get Relative index
		int relativeStartIndex = offset - startIndex;
		int relativeEndIndex = offset + length - endIndex;
		
		//GetWord
		String newWord = startElement.getWord().getWord().substring(relativeStartIndex);
		newWord += endElement.getWord().getWord().substring(0, relativeEndIndex);
		
		//Remove element needed
		 for (int index = startElementIndex; index <= endElementIndex; index++) {
			this.root.getChild().remove(index);
		}
		 
		 insertString(startIndex, newWord, null);
	}

	private List<Word> findWord(String string)
	{
		List<Word> phonemList =  new ArrayList<Word>();
		Word current = null;
		
		int windowMaxSize=string.length();
		int windowSize=1;
		int windowInitialStart=0;
		int windowStart=0;
		
		while(windowStart<string.length())
		{
			//TODO: Regexp
			String potentialPhonem = string.substring(windowStart, 
					((windowStart + windowSize > string.length())? string.length() : (windowStart + windowSize)));
			
			current = wordDAO.findWord(potentialPhonem);
			
			//Move windows from + 1
			if((windowSize)>=windowMaxSize)
			{
				if(current!=null)
				{
					phonemList.add(current);
				    windowInitialStart = windowStart = windowStart+current.getWord().length();
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
	
	public class WordPosition implements Position
	{
		protected int offset;

		private Document document;
		
		public WordPosition(Document document, int offset)
		{
			this.offset = offset;
			this.document = document;
			this.document.addDocumentListener(new DocumentListener()
			{

				@Override
				public void changedUpdate(DocumentEvent e) {
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					if(PhonemDocument.WordPosition.this.offset  >= e.getOffset())
					{
						 PhonemDocument.WordPosition.this.offset += e.getLength();
					}
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					if(PhonemDocument.WordPosition.this.offset  >= e.getOffset())
					{
						 PhonemDocument.WordPosition.this.offset -= e.getLength();
					}
				}
				
			});
		}
		
		public WordPosition(int offset)
		{
			this.offset = offset;
		}

		@Override
		public int getOffset() {
			return offset;
		}
		
	}
}
