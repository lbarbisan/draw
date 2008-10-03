package fr.free.hd.servers.entities;

/**
 * Repr�sente une phon�me.
 * Attention la repr�sentation interne est une r�pr�sentation en minuscule.
 * La phon�me n'est donc pas sensible � la casse.
 * @author Laurent BARBISAN
 */
public class Phonem extends BaseEntity {

	private String picture;
	private String phonem;
	
	/**
	 * For ORM Engine purpose, don't use it.
	 */
	public Phonem(){}
	
	public Phonem(String phonem, String picture) {
		super();
		this.picture = picture;
		this.phonem = phonem;
	}
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPhonem() {
		return this.phonem;
	}

	/**
	 * Attention la phon�me n'est pas sensible � la casse
	 * Tout est mis en minsucule
	 * @param phonem representation en cha�ne de caract�re de la phon�me
	 */
	public void setPhonem(String phonem) {
		this.phonem = phonem.toLowerCase();
	}

	@Override
	public String toString() {
		return "[" + 
			(this.isNew() ? "NoId]" : this.getId() + "]")
					+ this.getPhonem() + "(" + this.getPicture()  + ")";
	}
}
