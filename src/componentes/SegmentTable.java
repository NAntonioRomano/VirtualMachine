package componentes;

public class SegmentTable {
	private final int TABLE_SIZE;
	private int[] segmentTable;
	
	public SegmentTable() {
		TABLE_SIZE = 8;
		this.segmentTable = new int[TABLE_SIZE];
	}
	
	int[] getSegmentTable() {
		return this.segmentTable;
	}
	
	int getBase(int n) {
		return (this.segmentTable[n] >> 16) & 0xFFFF;
	}
	
	int getSize(int n) {
		return (this.getBase(n) + (this.segmentTable[n] & 0xFFFF));
	}
	
	public void setSegmentTable(byte[] size) {
		
	}
	
	public int LogicToFisic(int logic_address) {
		int Mask = 0x0000FFFF;
		int segment = (logic_address >> 16) & Mask;
		int offset = logic_address & Mask;
		
		int base_address = (this.segmentTable[segment] >> 16) & Mask;
		int segment_size = this.segmentTable[segment] & Mask;
		int limit_segment = base_address + segment_size;
		int fisic_address = base_address + offset;
		
		if(fisic_address <= limit_segment) 
			return fisic_address;
		return 0;//POR AHORA!!!
	}
}
