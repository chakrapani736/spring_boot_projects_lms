package com.te.string;

public class StringBufferTest {

	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer();
		System.out.println(buffer.hashCode());
		buffer.append("hi");
		System.out.println(buffer.length());
		buffer.append("hi");
		System.out.println(buffer.length());
		buffer.append("hi");
		System.out.println(buffer.length());
		System.out.println(buffer.hashCode());
		System.out.println(buffer.toString());
		
		
		
		StringBuffer buffer2 = new StringBuffer("java");
		buffer2.insert(0,"p");
		System.out.println(buffer2.toString());
	
	}
}
