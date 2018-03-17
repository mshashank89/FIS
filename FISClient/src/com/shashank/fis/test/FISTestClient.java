package com.shashank.fis.test;

import java.util.Scanner;

import com.shashank.fis.service.impl.FileOperationsServiceImpl;
import com.shashank.fis.service.util.FISClientConstants;

public class FISTestClient {
	
	private FileOperationsServiceImpl fileOpService;
	
	public FISTestClient() {
		fileOpService = new FileOperationsServiceImpl();
	}
	
	public static void main(String[] args) {
		FISTestClient test = new FISTestClient();
		
		String pingResponse = test.fileOpService.pingServer();
		if (null == pingResponse) {
			System.out.println("500. Cannot Connect to server. Please make check the configurations");
		}
		else 
		{
			test.process();
		}
		
	}
	
	private void process() {

		//establish connection with the server
		int status = 200;
		System.out.println(status + " Welcome to File Information System");
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		while (!exit) {
			String input = sc.nextLine(); 
			String command[] = parseCommand(input);
			String cmd = command[0].toUpperCase();
			String args = command[1];
			String response = "";
			switch(cmd) {
			case "DIR" :
				response = fileOpService.handleDirReq(args);
				break;
			case "INFO":
				response = fileOpService.handleInfoReq(args);
				break;
			case "CD" :
				response = fileOpService.handleCdReq(args);
				break;
			case "PWD" :
				response = fileOpService.handlePwdReq(args);
				break;
			case "QUIT" :
				response = fileOpService.handleExit(args);
				if (FISClientConstants.GOODBYE_MESSAGE.equals(response)) {					
					exit = true;
					sc.close();
				}
				break;
			default :
				System.out.println("503 Unknown command");
				break;
			}
			System.out.println(response);
		}
	
	}

	
	
	private static String[] parseCommand(String cmd) {
		String s[] = cmd.split(" ");
		String command[] = new String[2];
		
		command[0] = s[0];
		
		String arg = "";
		System.out.println(s[0]);
		for (int i=1;i<s.length;i++) {
			arg = arg + " " + s[i];
		}
		
		System.out.println(arg.trim());
		
		command[1] = arg.trim();
		
		return command;
	}

}
