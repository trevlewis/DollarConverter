package com.converter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Converter {
	
	private static Map<Integer, String> ones = new HashMap<Integer, String>(); 
	private static Map<Integer, String> tens = new HashMap<Integer, String>();

	static {
		ones.put(1, "one");
		ones.put(2, "two");
		ones.put(3, "three");
		ones.put(4, "four");
		ones.put(5, "five");
		ones.put(6, "six");
		ones.put(7, "seven");
		ones.put(8, "eight");
		ones.put(9, "nine");
		ones.put(10, "ten");
		ones.put(11, "eleven");
		ones.put(12, "twelve");
		ones.put(13, "thirteen");
		ones.put(14, "fourteen");
		ones.put(15, "fifteen");
		ones.put(16, "sixteen");
		ones.put(17, "seventeen");
		ones.put(18, "eighteen");
		ones.put(19, "nineteen");
	
		tens.put(2, "twenty");
		tens.put(3, "thirty");
		tens.put(4, "forty");
		tens.put(5, "fifty");
		tens.put(6, "sixty");
		tens.put(7, "seventy");
		tens.put(8, "eighty");
		tens.put(9, "ninety");
	}
	
	/**
	 * Converts an numeric input into it's String representation.
	 * Example: How you would write the amount out on a check.
	 * 
	 * @param numConvert - the amount as a double that will be converted
	 * 			into it's String representation.
	 * @return The String representation of the input.
	 */
	public String getConvertedString(double numConvert){
		
		if(numConvert == 0.0){ 
			return "zero";
		}

		String convertedStr = "";
		String startNum = "";
		String endNum = "";
		
		//Formats the double into an Integer so it doesn't 
		//use scientific notation when converting to String.
        DecimalFormat dfDouble = new DecimalFormat("#");
        dfDouble.setMaximumFractionDigits(25);

		String convertNum = String.valueOf(dfDouble.format(numConvert));
		System.out.println("Input: " + convertNum);
		
		int decimal = convertNum.indexOf(".");
		if(decimal != -1){
			startNum = convertNum.substring(0, decimal);
			endNum = convertNum.substring(decimal+1, convertNum.length());
			if(endNum.length() > 2){
				return "Sorry your decimal has too many digits. Please keep it to 2.";
			}
		}
		else{
			startNum = convertNum;
		}
		
		try{
			//Pad the number with 0's so it is easier to parse.
			DecimalFormat dfPadding = new DecimalFormat("000000000");
			startNum = dfPadding.format(Integer.parseInt(startNum));
			
			//Number cannot be handled at this time.
			if(startNum.length() > 9){
				throw new NumberFormatException();
			}
		}
		catch(NumberFormatException nfe){
			return "Sorry your number too large. Please keep it less than 1 billion.";
		}
		
		//Parse the Millions section of the Input String.
		int millions = Integer.parseInt(startNum.substring(0, 3));
		if(millions != 0){
			convertedStr += convert(millions) + " million ";
		}
		
		//Parse the Thousands section of the Input String.
		int thousands = Integer.parseInt(startNum.substring(3, 6));
		if(thousands != 0){
			convertedStr += convert(thousands) + " thousand ";
		}
		
		//Parse the Hundreds section of the Input String.
		int hundreds = Integer.parseInt(startNum.substring(6, 9));
		if(hundreds != 0){
			convertedStr += convert(hundreds);
		}
		
		//Handle the decimal of the input
		if(decimal != -1){
			if(endNum.length() < 2){
				convertedStr += " and " + endNum + "0/100 dollars";
			}
			else{
				convertedStr += " and " + endNum + "/100 dollars";
			}
		}
		else{
			convertedStr += " and 00/100 dollars";
		}

		
		return getFinalConvert(convertedStr);
	}	
	
	/**
	 * Converts each section of the Input Number 
	 * passed in to its String format. This is done
	 * for Hundreds, Tens, and Ones.
	 * 
	 * @param num - a section of the input to be converted 
	 * 			to it's String representation.
	 * @return String representation of the section of the input
	 * 			that was passed in.
	 */
	private String convert(int num){
		String resultStr = "";
		int onesNum = 0;
		int tensNum = 0;
		int hundredsNum = 0;
		
		if (num >= 100) {
			
			hundredsNum = num / 100;
			if (hundredsNum != 0){
				resultStr += ones.get(hundredsNum) + " hundred ";
			}
		}
		if (num % 100 < 20) {
			onesNum = num % 100;
			if(onesNum != 0){
				resultStr += ones.get(onesNum);
			}
		} 
		if (num % 100 >= 20 && num % 100 <= 99) {
				
			onesNum = num % 10;
			tensNum = (((num % 100) - onesNum) / 10);
			hundredsNum = num / 100;

			if (tensNum != 0){
				resultStr += tens.get(tensNum);
			}
			if (onesNum != 0){
				resultStr += "-" + ones.get(onesNum);
			}
		} 
		
		return resultStr;
	}
	
	/**
	 * Converts the First letter of the final String
	 * to uppercase.
	 * 
	 * @param convertStr - a String representation of the input.
	 * @return The String representation of the input with an uppercase letter
	 * 			at the beginning of the string.
	 */
	private String getFinalConvert(String convertStr){
		String firstLetter = String.valueOf(convertStr.charAt(0));
		
		return convertStr.replaceFirst(firstLetter, firstLetter.toUpperCase());
	}

	public static void main(String[] args) {
		Converter convert = new Converter();
		
		String result = convert.getConvertedString(123456789.99);

		System.out.println("Result: " + result);
	}

}
