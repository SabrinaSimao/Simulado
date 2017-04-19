package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {

	private AndGate andDown;
	private XorGate xorUp;

	public HalfGate() {
		super(2, 2);

		name = "HALF";

		xorUp = new XorGate();
		andDown = new AndGate();
	}

	@Override
	 public boolean doRead(int index) {
	 	if(index == 0){
	 		return xorUp.read();
	 	}else{
	 		return andDown.read();
	 	}
	 }
	@Override
	protected void doConnect(Emitter emitter, int index) {
	 	if(index==0){
	 		andDown.connect(emitter, 0);
			xorUp.connect(emitter, 0);
	 	}else{
			andDown.connect(emitter, 1);
	 		xorUp.connect(emitter, 1);
	 		}
	 	}
}