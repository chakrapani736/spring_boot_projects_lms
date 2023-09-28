package com.te.string;

public class ExceptionTest {

	int n =10;
	public static void main(String[] args) {
		
	}
	public void m1() throws BijanException{
		System.out.println("calling bijan exception");
		if(n==10) {
			throw new ArithmeticException("arithmetic exception");
		}
	}
}
