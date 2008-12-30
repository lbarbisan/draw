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
	private int mouthYPercent = 680;
	private int mouthXPercent = 485;
	private int boucheRatio = 300;
	private int boucheX = 400;
	private int boucheY = 600;
	private int coteRatio = 300;
	private int coteX = 0;
	private int coteY = 400;
	private int couRatio = 300;
	private int couX = 400;
	private int couY = 800;
	private int mentonRatio = 300;
	private int mentonX = 500;
	private int mentonY = 700;
	private int paumetteRatio = 300;
	private int paumetteX = 300;
	private int paumetteY = 500;
	
	private int anchorX_2V;
	private int anchorX_3D;
	private int anchorX_1M;
	private int anchorX_2M;
	private int anchorX_4G;
	private int anchorX_5M;
	private int anchorX_3G;
	private int anchorX_2D;
	private int anchorY_2V;
	private int anchorY_3D;
	private int anchorY_1M;
	private int anchorY_2M;
	private int anchorY_4G;
	private int anchorY_5M;
	private int anchorY_3G;
	private int anchorY_2D;
	
	public int getAnchorX_2V() {
		return anchorX_2V;
	}
	public void setAnchorX_2V(int anchorX_2V) {
		this.anchorX_2V = anchorX_2V;
	}
	public int getAnchorX_3D() {
		return anchorX_3D;
	}
	public void setAnchorX_3D(int anchorX_3D) {
		this.anchorX_3D = anchorX_3D;
	}
	public int getAnchorX_1M() {
		return anchorX_1M;
	}
	public void setAnchorX_1M(int anchorX_1M) {
		this.anchorX_1M = anchorX_1M;
	}
	public int getAnchorX_2M() {
		return anchorX_2M;
	}
	public void setAnchorX_2M(int anchorX_2M) {
		this.anchorX_2M = anchorX_2M;
	}
	public int getAnchorX_4G() {
		return anchorX_4G;
	}
	public void setAnchorX_4G(int anchorX_4G) {
		this.anchorX_4G = anchorX_4G;
	}
	public int getAnchorX_5M() {
		return anchorX_5M;
	}
	public void setAnchorX_5M(int anchorX_5M) {
		this.anchorX_5M = anchorX_5M;
	}
	public int getAnchorX_3G() {
		return anchorX_3G;
	}
	public void setAnchorX_3G(int anchorX_3G) {
		this.anchorX_3G = anchorX_3G;
	}
	public int getAnchorX_2D() {
		return anchorX_2D;
	}
	public void setAnchorX_2D(int anchorX_2D) {
		this.anchorX_2D = anchorX_2D;
	}
	public int getAnchorY_2V() {
		return anchorY_2V;
	}
	public void setAnchorY_2V(int anchorY_2V) {
		this.anchorY_2V = anchorY_2V;
	}
	public int getAnchorY_3D() {
		return anchorY_3D;
	}
	public void setAnchorY_3D(int anchorY_3D) {
		this.anchorY_3D = anchorY_3D;
	}
	public int getAnchorY_1M() {
		return anchorY_1M;
	}
	public void setAnchorY_1M(int anchorY_1M) {
		this.anchorY_1M = anchorY_1M;
	}
	public int getAnchorY_2M() {
		return anchorY_2M;
	}
	public void setAnchorY_2M(int anchorY_2M) {
		this.anchorY_2M = anchorY_2M;
	}
	public int getAnchorY_4G() {
		return anchorY_4G;
	}
	public void setAnchorY_4G(int anchorY_4G) {
		this.anchorY_4G = anchorY_4G;
	}
	public int getAnchorY_5M() {
		return anchorY_5M;
	}
	public void setAnchorY_5M(int anchorY_5M) {
		this.anchorY_5M = anchorY_5M;
	}
	public int getAnchorY_3G() {
		return anchorY_3G;
	}
	public void setAnchorY_3G(int anchorY_3G) {
		this.anchorY_3G = anchorY_3G;
	}
	public int getAnchorY_2D() {
		return anchorY_2D;
	}
	public void setAnchorY_2D(int anchorY_2D) {
		this.anchorY_2D = anchorY_2D;
	}
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
	public int getBoucheRatio() {
		return boucheRatio;
	}
	public int getCoteRatio() {
		return coteRatio;
	}
	public void setCoteRatio(int coteRatio) {
		this.coteRatio = coteRatio;
	}
	public int getCouRatio() {
		return couRatio;
	}
	public void setCouRatio(int couRatio) {
		this.couRatio = couRatio;
	}
	public int getMentonRatio() {
		return mentonRatio;
	}
	public void setMentonRatio(int mentonRatio) {
		this.mentonRatio = mentonRatio;
	}
	public int getPaumetteRatio() {
		return paumetteRatio;
	}
	public void setPaumetteRatio(int paumetteRatio) {
		this.paumetteRatio = paumetteRatio;
	}
	public void setBoucheRatio(int boucheRatio) {
		this.boucheRatio = boucheRatio;
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
