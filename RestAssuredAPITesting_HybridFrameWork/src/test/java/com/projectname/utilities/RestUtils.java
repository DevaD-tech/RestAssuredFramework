package com.projectname.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String username()
	{
		String generatedString= RandomStringUtils.randomAlphabetic(1);
		return("Devesh"+generatedString);
		
	}	
	public static String userjob()
	
	{
		String generatedString= RandomStringUtils.randomAlphabetic(1);
		return("Engineering"+generatedString);
	}
	
}
