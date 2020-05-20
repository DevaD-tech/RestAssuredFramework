package com.projectname.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.projectname.base.testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GETUSER_LIST extends testbase
{
	@BeforeClass
	void getAllList()
	{
		logger.info("********** started test case 001 ***********");
		RestAssured.baseURI= "https://reqres.in";
		httpRequest= RestAssured.given();
		response=httpRequest.request(Method.GET,"/api/users?page=2");
	}	
	
	@Test
	void checkResponseBody()
	{
		logger.info("********** checking response body ***********");
		String responseBody=response.getBody().asString();
		logger.info("Response Body >>> "+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void CheckstatusCode()
	{
		logger.info("********** checking status code ***********");
		int statuscode=response.getStatusCode();
		logger.info("Status Code is >>> "+ statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	
	@Test
	void CheckResponseTime()
	{
		logger.info("********** checking response time ***********");
		long ResponseTime= response.getTime();
		logger.info("Response Time is >>  "+ResponseTime);
		if(ResponseTime>200)
		logger.warn("Response Time is Greater than 2000");
		Assert.assertTrue(ResponseTime<10000);
	}
	
	@Test
	void ContentType()
	{
		logger.info("********** checking content type ***********");
		String contenttype= response.header("Content-Type");
		logger.info("Content Type is >>"+contenttype);
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
	}
	
	@AfterClass
	
	void tearDown()
	{
		
		logger.info("********** Finished TC001 ***********");
		
	}
	
}
