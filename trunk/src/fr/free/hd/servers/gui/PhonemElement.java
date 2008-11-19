package fr.free.hd.servers.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;

import fr.free.hd.servers.entities.Word;

public class PhonemElement implements Element {

	private Document document; 
	private SimpleAttributeSet attributes = new SimpleAttributeSet();
	private int startOffset;
	private Word word;
	public Word getWord() {
		return word;
	}
	private PhonemRootElement root;

	public PhonemElement(Document document, PhonemRootElement root, int startOffset, Word word)
	{
		this.document = document;
		this.startOffset = startOffset;
		this.word = word;
		this.root = root;
	}
	
	@Override
	public AttributeSet getAttributes() {
		return attributes;
	}
	@Override
	public Document getDocument() {
		return document;
	}
	@Override
	public Element getElement(int index) {
		return null;
	}
	@Override
	public int getElementCount() {
		return 0;
	}
	@Override
	public int getElementIndex(int offset) {
		return 0;
	}
	@Override
	public int getEndOffset() {
		return startOffset + word.getWord().length();
	}
	@Override
	public String getName() {
		return word.getWord();
	}
	@Override
	public Element getParentElement() {
		return root;
	}

	@Override
	public int getStartOffset() {
		return startOffset;
	}
	@Override
	public boolean isLeaf() {
		return true;
	}

}
