package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;

public class PhonemRootElement implements Element {

	private List<PhonemElement> child = new ArrayList<PhonemElement>();
	public List<PhonemElement> getChild() {
		return child;
	}
	private Document document; 
	private SimpleAttributeSet attributes = new SimpleAttributeSet();
	
	public PhonemRootElement(Document document)
	{
		this.document = document;
	}
	@Override
	public Document getDocument() {
		return document;
	}
	@Override
	public Element getElement(int index) {
		return child.get(index);
	}
	@Override
	public int getElementCount() {
		return child.size();
	}
	@Override
	public boolean isLeaf() {
		return false;
	}
	@Override
	public int getEndOffset() {
		if(child.size()>0)
		{
			return 0;
		}
		else
		{
			return child.get(child.size()-1).getEndOffset();
		}
	}
	@Override
	public String getName() {
		return "Root";
	}
	@Override
	public Element getParentElement() {
		return null;
	}
	@Override
	public int getStartOffset() {
		return 0;
	}
	@Override
	public AttributeSet getAttributes() {
		return attributes;
	}	
	@Override
	public int getElementIndex(int offset) {
		for (int index = 0; index < child.size(); index++) {
			PhonemElement element = child.get(index);
			if(element.getStartOffset() <= offset && element.getEndOffset() >= offset)
			{
				return index;
			}
		}
		return 0;
	}	
}
