package com.te.test;

import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;
import java.util.Optional;

public class Test {
public static void main(String[] args) {
	NumberFormat instance = NumberFormat.getCompactNumberInstance(Locale.US,Style.SHORT);
      String format = instance.format(1000);
      System.out.println(format);
      
      
      String s="hi";
      String s1="hi"+"bye";
     
}
}
