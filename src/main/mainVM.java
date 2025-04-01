package main;
import java.io.*;

import componentes.*;

public class mainVM {

	public static void main(String[] args) {
		VirtualMachine vm = new VirtualMachine();
		
		try {
			vm.validateExtension(args[0]);
			vm.init(args[0]);
		}
		catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
