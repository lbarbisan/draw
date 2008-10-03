package fr.free.hd.servers.entities;

/**
 * Représente une phonème.
 * Attention la représentation interne est une réprésentation en minuscule.
 * La phonème n'est donc pas sensible à la casse.
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
	 * Attention la phonème n'est pas sensible à la casse
	 * Tout est mis en minsucule
	 * @param phonem representation en chaîne de caractère de la phonème
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
