package componentes;



public class VirtualMainMemory {
	private final int MEMORY_SIZE;
	private byte[] memory;
	
	public VirtualMainMemory() {
		this.MEMORY_SIZE = 16384;
		this.memory = new byte[MEMORY_SIZE];
	}
	
	public int readByte(int fisic_address) {
		return (this.memory[fisic_address]) & 0xFF;
	}
	
	public void writeByte(int fisic_address, int data) {
		byte newdata = (byte) data;
		this.memory[fisic_address] = newdata;
	}
	
	public void setMemory(byte[] memory, byte[] size) throws Exception {
		int s = ((int)(size[0] << 8)|(int)(size[1]) & 0xFF);
		if(s > this.MEMORY_SIZE) {
			throw new Exception("Exceeded virtual memory size");
		}else {
			System.arraycopy(memory, 0, this.memory, 0, memory.length);
		}
	}
	
} 
