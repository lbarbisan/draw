package fr.free.hd.servers.entities;

/**
 * Représente une phonème.
 * Attention la représentation interne est une réprésentation en minuscule.
 * La phonème n'est donc pas sensible à la casse.
 * @author Laurent BARBISAN
 */
public class Phonem extends BaseEntity {

	private String phonem;
	private HandKeyEnum handKey;
	private HandPositionEnum handPosition;
	private MouthVowelEnum mouthVowel;
	
	public MouthVowelEnum getMouthVowel() {
		return mouthVowel;
	}

	public void setMouthVowel(MouthVowelEnum mouthVowel) {
		this.mouthVowel = mouthVowel;
	}

	/**
	 * For ORM Engine purpose, don't use it.
	 */
	public Phonem(){}
	
	public Phonem(String phonem, HandKeyEnum handKey, HandPositionEnum handPosition,
			MouthVowelEnum mouthVowel) {
		super();
		this.phonem = phonem;
		this.handKey = handKey;
		this.handPosition = handPosition;
		this.mouthVowel = mouthVowel;
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
					+ this.getPhonem() + "(" + this.handKey + "." + this.handPosition + "." + this.mouthVowel + ")";
	}

	public HandKeyEnum getHandKey() {
		return handKey;
	}

	public void setHandKey(HandKeyEnum handKey) {
		this.handKey = handKey;
	}

	public HandPositionEnum getHandPosition() {
		return handPosition;
	}

	public void setHandPosition(HandPositionEnum handPosition) {
		this.handPosition = handPosition;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Phonem)
		{
			return super.equals(obj);
		}
		
		Phonem phonem = (Phonem)obj;
		
		if(this.phonem.equals(phonem.getPhonem()))
		{
			return true;
		}
		return false;
	}
	
	
}
