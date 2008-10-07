package fr.free.hd.servers.entities;

/**
 * Repr�sente une phon�me.
 * Attention la repr�sentation interne est une r�pr�sentation en minuscule.
 * La phon�me n'est donc pas sensible � la casse.
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
