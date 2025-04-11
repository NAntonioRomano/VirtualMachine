package mnemonic;

public class MOV extends Mnemonic {

	@Override
	public void opera(int tipoA, int tipoB, int opA, int opB){
		int dato;
		dato = getData(tipoB, opB);
		setData(tipoA, opA, dato);
		ModificaCC(dato);
	}
		
		
}


