package componentes;


public class Registers {
	private final int SIZE_REG;
	private int[] registers;
	
	public Registers() {
		this.SIZE_REG = 16;
		this.registers = new int[SIZE_REG];
	}
	
	public void initRegisters(SegmentTable ST) {
		this.registers[0] = (ST.getSegmentTable()[0] >> 16) & 0xFFFF;
		this.registers[1] = (ST.getSegmentTable()[1] >> 16) & 0xFFFF;
		this.registers[5] = this.registers[0];
	}
	
	public int getRegister(int n) {
		return this.registers[n];	
	}
	
	public void add(int n,int cant) {
		this.registers[n] += cant;
	}
	
}
