package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class XorGate extends Gate{
	private NandGate nand1;
	private NandGate nand2;	
	private NandGate nand3;
	private NandGate nand4;
	
	public XorGate(){
		super("XorGate",2);
		nand1 = new NandGate();
		nand2 = new NandGate();
		nand3 = new NandGate();
		nand4 = new NandGate();
		nand4.connect(0, nand2);
		nand4.connect(1, nand3);
		nand2.connect(1, nand1);
		nand3.connect(0, nand1);

	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		if (pinIndex==0) {
			nand1.connect(0, emitter);
			nand2.connect(0, emitter);
		}
		else{
			nand1.connect(1, emitter);
			nand3.connect(1, emitter);
		}
		
	}
	
	
	@Override
	public boolean read() {
		return nand4.read();
	}







}
