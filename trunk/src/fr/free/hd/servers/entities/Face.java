package fr.free.hd.servers.entities;

/**
 * Represente la configuration d'un visage.
 * Fournit le visage à afficher ainsi que les coordonnées de chaque element.
 * Chaque coordonnée est configurée en unité de 1 à 1000
 * @author Laurent BARBISAN
 */
public class Face extends BaseEntity {

	private String picture = "Visage.jpg";
	private int mouthRatio = 200;
	private int handRatio = 300;
	private int mouthYPercent = 680;
	private int mouthXPercent = 485;
	private int boucheX = 400;
	private int boucheY = 600;
	private int coteX = 0;
	private int coteY = 400;
	private int couX = 400;
	private int couY = 800;
	private int mentonX = 500;
	private int mentonY = 700;
	private int paumetteX = 300;
	private int paumetteY = 500;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getMouthRatio() {
		return mouthRatio;
	}
	public void setMouthRatio(int mouthRatio) {
		this.mouthRatio = mouthRatio;
	}
	public int getHandRatio() {
		return handRatio;
	}
	public void setHandRatio(int handRatio) {
		this.handRatio = handRatio;
	}
	public int getMouthYPercent() {
		return mouthYPercent;
	}
	public void setMouthYPercent(int mouthYPercent) {
		this.mouthYPercent = mouthYPercent;
	}
	public int getMouthXPercent() {
		return mouthXPercent;
	}
	public void setMouthXPercent(int mouthXPercent) {
		this.mouthXPercent = mouthXPercent;
	}
	public int getBoucheX() {
		return boucheX;
	}
	public void setBoucheX(int boucheX) {
		this.boucheX = boucheX;
	}
	public int getBoucheY() {
		return boucheY;
	}
	public void setBoucheY(int boucheY) {
		this.boucheY = boucheY;
	}
	public int getCoteX() {
		return coteX;
	}
	public void setCoteX(int coteX) {
		this.coteX = coteX;
	}
	public int getCoteY() {
		return coteY;
	}
	public void setCoteY(int coteY) {
		this.coteY = coteY;
	}
	public int getCouX() {
		return couX;
	}
	public void setCouX(int couX) {
		this.couX = couX;
	}
	public int getCouY() {
		return couY;
	}
	public void setCouY(int couY) {
		this.couY = couY;
	}
	public int getMentonX() {
		return mentonX;
	}
	public void setMentonX(int mentonX) {
		this.mentonX = mentonX;
	}
	public int getMentonY() {
		return mentonY;
	}
	public void setMentonY(int mentonY) {
		this.mentonY = mentonY;
	}
	public int getPaumetteX() {
		return paumetteX;
	}
	public void setPaumetteX(int paumetteX) {
		this.paumetteX = paumetteX;
	}
	public int getPaumetteY() {
		return paumetteY;
	}
	public void setPaumetteY(int paumetteY) {
		this.paumetteY = paumetteY;
	}
}
