package componentes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VirtualMachine {
	private VirtualMainMemory virtualMemory;
	private SegmentTable segTable;
	private Registers registers;
	
	public VirtualMachine() {
		this.virtualMemory = new VirtualMainMemory();
		this.segTable = new SegmentTable();
		this.registers = new Registers();
		
	}
	
	public void init(String vmx_path) throws Exception{
		try {
			
			byte[] allbytes = Files.readAllBytes(Paths.get(vmx_path));
			byte[] header = new byte[5];
			byte[] version = new byte[1];
			byte[] size = new byte[2];
			byte[] memory = new byte[allbytes.length - 8];
			
			
			System.arraycopy(allbytes,0,header,0, 5);
			
			validateHeader(header);
			
			System.arraycopy(allbytes, 5, version, 0, 1);
			
			validateVersion(version);
			
			System.arraycopy(allbytes,6,size,0,2);
			System.arraycopy(allbytes,8,memory,0,allbytes.length - 8);
			
			this.virtualMemory.setMemory(memory, size);
			this.segTable.setSegmentTable(size);
			
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void execute() throws Exception {
		int codop;
		int opA;
		int opB;
		
		this.registers.initRegisters(this.segTable);
		
		try {
		
			codop = this.getcodop(this.virtualMemory.readByte(this.registers.getRegister(5)));
			
			while(this.registers.getRegister(5) <= this.segTable.getSize(0) && codop != 0x0F) {
				opA = this.getopA(this.virtualMemory.readByte(this.registers.getRegister(5)));
				opB = this.getopB(this.virtualMemory.readByte(this.registers.getRegister(5)));
				this.opera(codop,opA,opB);
				this.incrementaIP(this.virtualMemory.readByte(this.registers.getRegister(5)));
				codop = this.getcodop(this.virtualMemory.readByte(this.registers.getRegister(5)));
			}
		}catch(Exception e) {
			throw e;
		}
	}

	private void incrementaIP(int byte1) {
		// TODO Auto-generated method stub
		
	}

	private void opera(int codop, int opA, int opB) {
		// TODO Auto-generated method stub
		
	}

	private int getopB(int register) {
		int tipo = (register >> 4) & 0x3;
		int opB= 0;
		
		for(int i = 1; i <= tipo; i++) {
			opB = (opB << 8) & this.virtualMemory.readByte(this.registers.getRegister(5) + i);
		}
		
		return opB;
	}

	private int getopA(int register) {
		int tipo = (register >> 4) & 0x3;
		int opA = 0;
		
		for(int i = 1; i <= tipo; i++) {
			opA = (opA << 8) & this.virtualMemory.readByte(this.registers.getRegister(5) + i + ((register >> 6) & 0x3));
		}
		
		return opA;
	}

	private int getcodop(int register) throws Exception {
		return (register & 0x1F);
	}

	public void validateExtension(String vmx_path) throws Exception {
		Path path = Paths.get(vmx_path);
		
		if(!vmx_path.toLowerCase().endsWith(".vmx")){
			throw new Exception("Invalid extension");
		}
		
		if(!Files.exists(path)) {
			throw new Exception("File not found");
		}
		
		
	}

	private void validateVersion(byte[] version) throws Exception{
		if(version[0] != -1) {
			throw new Exception("Invalid version");
		}
		
	}

	private void validateHeader (byte[] header) throws Exception{
		byte[] expected = {'V','M','X','2','5'};
		
		for(int i = 0;i < 5;i++) {
			if(header[i] != expected[i]) {
				throw new Exception("Invalid .vmx Header");
			}
				
		}
	}
	
}
