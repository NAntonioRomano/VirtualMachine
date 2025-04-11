package mnemonic;

abstract public class Mnemonic{

	private VirtualMachine vm;

	public Mnemonic(VirtualMachine vm){
		this.vm = vm;
	}

	abstract public void opera(int tipoA, int tipoB, int opA, int opB);
	public void ModificaCC(int resultado){
	}

	public int getData(int tipo,int op){
		
	}

	public void setData(int tipo, int op, int data){

	}
}