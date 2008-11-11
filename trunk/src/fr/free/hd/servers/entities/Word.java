package fr.free.hd.servers.entities;

import java.util.List;

/**
 * Represente un mot.
 * On entend par mot les compositions de mot dont il est necessaire de faire
 * les liaisons
 * @author Laurent BARBISAN
 */
public class Word extends BaseEntity {

	private String word;
	private List<Phonem> phonems;
	
	/**
	 * For ORM Engine purpose, don't use it.
	 */
	public Word(){}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Word))
		{
			return super.equals(obj);
		}
		
		Word word = (Word)obj;
		
		if(this.word.equals(word.getWord()))
		{
			return true;
		}
		return false;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Phonem> getPhonems() {
		return phonems;
	}

	public void setPhonems(List<Phonem> phonems) {
		this.phonems = phonems;
	}
	
}
