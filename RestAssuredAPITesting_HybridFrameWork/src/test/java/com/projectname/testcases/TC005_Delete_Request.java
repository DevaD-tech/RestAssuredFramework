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
import io.restassured.response.Response;


public class TC005_Delete_Request extends testbase {
	
@BeforeClass	
public void deleterequest() throws InterruptedException
{
	RestAssured.baseURI= "https://reqres.in";
	httpRequest= RestAssured.given();
	
	 response =httpRequest.request(Method.DELETE,"/api/users/2");
	Thread.sleep(3000);
}

@Test
void CheckstatusCode()
{
	logger.info("********** checking status code ***********");
	int statuscode=response.getStatusCode();
	logger.info("Status Code is >>> "+ statuscode);
	Assert.assertEquals(statuscode, 204);
}

@Test
void CheckResponseTime()
{
	logger.info("********** checking response time ***********");
	long ResponseTime= response.getTime();
	logger.info("Response Time is >>  "+ResponseTime);
	if(ResponseTime>2000)
	logger.warn("Response Time is Greater than 2000");
	Assert.assertTrue(ResponseTime<20000);
}
@AfterClass

void tearDown()
{
	
	logger.info("********** Finished TC005 ***********");
	
}


}

