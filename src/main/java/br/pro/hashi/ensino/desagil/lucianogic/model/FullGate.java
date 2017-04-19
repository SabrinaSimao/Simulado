package br.pro.hashi.ensino.desagil.lucianogic.model;

public class FullGate extends Gate {
	 
	 private OrGate orRight;
	 private XorGate xorLeft;
	 private XorGate xorRight;
	 private AndGate andUp;
	 private AndGate andDown;
	 
	 public FullGate() {
	 	super(3, 2);
	 
	 name = "FULL";
	 
	 xorLeft = new XorGate();
	 xorRight = new XorGate();
	 xorRight.connect(xorLeft, 0);
	 
	 andDown = new AndGate();
	 andUp = new AndGate();
	 andUp.connect(xorLeft, 0);
	 
	 orRight = new OrGate();
	 orRight.connect(andUp, 0);
	 orRight.connect(andDown, 1);
	 
	}
	
	 @Override
	 public boolean doRead(int index) {
	
	 if(index == 0){
		 return xorRight.read();
	 }else{
	 	 return orRight.read();
	 	}
	 }
	 
	 @Override
	 protected void doConnect(Emitter emitter, int index) {
	 	if(index==0){
	 		andDown.connect(emitter, 0);
	 		xorLeft.connect(emitter, 0);
	 
	 		}
	 	if(index==1){
	 		andDown.connect(emitter, 1);
	 		xorLeft.connect(emitter, 1);
	 		
	 }
	 	if(index==2){
	 		xorRight.connect(emitter, 1);
	 		andUp.connect(emitter, 1);
	 		}
	 }
	 
}