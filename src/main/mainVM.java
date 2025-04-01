package main;

import java.nio.file.Files;
import java.nio.file.Paths;

import componentes.*;

public class mainVM {

	public static void main(String[] args) {
		VirtualMachine vm = new VirtualMachine();
		
		try {
			byte[] allbytes = Files.readAllBytes(Paths.get(args[0]));
			
			vm.validateExtension(args[0]);
			vm.init(allbytes);
			vm.startMemory(allbytes);
			vm.execute();
			
		}
		catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
