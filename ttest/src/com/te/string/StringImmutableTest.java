package com.te.string;

public class StringImmutableTest {
	public static void main(String[] args) {
		String s = "hello12";
		String s2 = "hello";
		int compareTo = s.compareTo(s2);
		System.out.println(compareTo);
		System.out.println(s.hashCode());
		//s = s+"java";
		String concat = s.concat("java");
		System.out.println(concat);
	}
}
