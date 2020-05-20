package com.projectname.testcases;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.projectname.base.testbase;
import com.projectname.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class TC004_PUTRequest extends testbase {
	
	
	String name1= RestUtils.username();
	String job1= RestUtils.userjob();
	
	@BeforeClass
	void creatuser() throws InterruptedException
	
	{
		
		logger.info("********** started test case 004 ***********");
		RestAssured.baseURI= "https://reqres.in";
		
		httpRequest= RestAssured.given();
		
		JSONObject requestparam=new JSONObject();
		
		requestparam.put("name", name1);
		requestparam.put("job", job1);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestparam.toJSONString());
		response= httpRequest.request(Method.PUT,"/api/users/2");
		
		Thread.sleep(5000);
	}
	
	@Test
void checkResponseBody()

{
		String responsebody=response.getBody().asString();
		Assert.assertEquals(responsebody.contains(name1), true);
		Assert.assertEquals(responsebody.contains(job1), true);
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
		if(ResponseTime>2000)
		logger.warn("Response Time is Greater than 2000");
		Assert.assertTrue(ResponseTime<8000);
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
		
		logger.info("********** Finished TC004 ***********");
		
	}

}
