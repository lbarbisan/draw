package fr.free.hd.servers.gui;

import fr.free.hd.servers.entities.Phonem;


class Position {

	private String string;
	
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	private Phonem phonem;
	public Phonem getPhonem() {
		return phonem;
	}
	public void setPhonem(Phonem phonem) {
		this.phonem = phonem;
	}
	private int start;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
