package com.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConvertedStringTest {
	
	Converter converter;

	@Before
	public void setUp() throws Exception {
		converter = new Converter();
	}

	@Test
	public void test1() {
		String convertedStr = converter.getConvertedString(999999999.99);
		assertEquals("Nine hundred ninety-nine million nine hundred ninety-nine thousand "
				+ "nine hundred ninety-nine and 99/100 dollars", convertedStr);
	}
	
	@Test
	public void test2() {
		String convertedStr = converter.getConvertedString(2523.04);
		assertEquals("Two thousand five hundred twenty-three and 04/100 dollars", convertedStr);
	}
	
	@Test
	public void test3() {
		String convertedStr = converter.getConvertedString(13579.9);
		assertEquals("Thirteen thousand five hundred seventy-nine and 90/100 dollars", convertedStr);
	}
	
	@Test
	public void test4() {
		String convertedStr = converter.getConvertedString(246589.00);
		assertEquals("Two hundred forty-six thousand five hundred eighty-nine and 00/100 dollars", convertedStr);
	}
	
	@Test
	public void test5() {
		String convertedStr = converter.getConvertedString(5);
		assertEquals("Five and 00/100 dollars", convertedStr);
	}
	
	@Test
	public void test6() {
		String convertedStr = converter.getConvertedString(99999999.99);
		assertEquals("Ninety-nine million nine hundred ninety-nine thousand nine hundred ninety-nine and 99/100 dollars", convertedStr);
	}

	@Test
	public void test7() {
		String convertedStr = converter.getConvertedString(1123456789.99);
		assertEquals("Sorry your number too large. Please keep it less than 1 billion.", convertedStr);
	}
	
	@Test
	public void test8() {
		String convertedStr = converter.getConvertedString(9123456789.99);
		assertEquals("Sorry your number too large. Please keep it less than 1 billion.", convertedStr);
	}
	
	@Test
	public void test9() {
		String convertedStr = converter.getConvertedString(123456789.994);
		assertEquals("Sorry your decimal has too many digits. Please keep it to 2.", convertedStr);
	}
}
