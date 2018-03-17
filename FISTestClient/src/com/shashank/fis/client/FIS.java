package com.shashank.fis.client;

import java.util.Scanner;

public class FIS {

	public static void main(String[] args) {
		//establish connection with the server
		int status = 200;
		System.out.println(status + " Welcome to File Information System");
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		while (!exit) {
			String input = sc.nextLine(); 
			String[] inputArr = input.split(" ");
			String command = inputArr[0];
			switch(command) {
			case "DIR" :
				System.out.println("DIR command entered");
				break;
			case "INFO":
				System.out.println("INFO command entered");
				break;
			case "cd" :
				System.out.println("CD command entered");
				break;
			case "QUIT" :
				System.out.println("Goodbye");
				exit = true;
				break;
			default :
				System.out.println("Invalid command");
				break;
			}
		}
	}
}
