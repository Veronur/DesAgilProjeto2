package br.pro.hashi.ensino.desagil.rafaelogic.model;

public abstract class Gate implements Emitter, Receiver {
	private int size;
	private String name;
	
	protected Gate(String name, int size) {
		this.name = name;
		this.size = size;
	}
	public abstract void connect(int pinIndex, Emitter emitter);
	public int getSize() {
		return size;

	}
	public String toString() {
		return name;
	}
}
