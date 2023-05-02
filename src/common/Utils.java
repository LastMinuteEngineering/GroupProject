package common;

import java.util.Arrays;

public class Utils {
	public static String listToString(String[] values){
		// return array vals as string : "val1, val2, ..., valN"
		return String.join(", ", values);
	}
	
	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
		// return true if inputStr matches one of the strings in items array.
	    return Arrays.stream(items).anyMatch(inputStr::contains);
	}
}
