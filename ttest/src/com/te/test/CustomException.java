package com.te.test;

public class CustomException {

	
	public static void main(String[] args) throws ArithmeticException{
		
			int n = 3/10;
			if(n==0) {
				System.out.println("remaining");
			}else {
				throw new ArithmeticException();
			}
	}
}
