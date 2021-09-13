package com.benz.dataProducer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;


public class AllTests {



	String createDataJson = "{\"name\":\"TT\",\"age\":24,\"salary\":2,\"dob\":\"12/09/2021\"}";

	String updateDataJson = "{\"name\":\"MMM\",\"age\":24,\"salary\":2,\"dob\":\"12/09/2021\"}";

	@Test
	public void whenUserInfoIsRetrieved_then200IsReceived()
			throws ClientProtocolException, IOException {

		// Given
		HttpUriRequest request = new HttpGet( "http://localhost:9000/read"  );

		// When
		HttpResponse httpResponse = (HttpResponse) HttpClientBuilder.create().build().execute( request );

		// Then
		assertThat(
				((org.apache.http.HttpResponse) httpResponse).getStatusLine().getStatusCode(),
				equalTo(HttpStatus.SC_OK));
	}


	//Please change the name passed in body everytime running this test case other wise it will return 500 status code
	@Test
	public void isCreatedthen200IsReceived()
			throws ClientProtocolException, IOException {

		// Given
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost( "http://localhost:9000/store"  );

		httpPost.addHeader("fileType", "xml");
		httpPost.setHeader("Content-type", "application/json");
		StringEntity stringEntity = new StringEntity(createDataJson);

		// When
		httpPost.getRequestLine();
		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);

		// Then
		assertThat(
				((org.apache.http.HttpResponse) response).getStatusLine().getStatusCode(),
				equalTo(HttpStatus.SC_OK));
	}

	@Test
	public void isUpdated_then200IsReceived()
			throws ClientProtocolException, IOException {

		// Given
		String name = "MM";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPost = new HttpPut( "http://localhost:9000/update?name="+name);

		httpPost.addHeader("fileType", "xml");
		httpPost.setHeader("Content-type", "application/json");
		StringEntity stringEntity = new StringEntity(updateDataJson);

		// When
		httpPost.getRequestLine();
		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);

		// Then
		assertThat(
				((org.apache.http.HttpResponse) response).getStatusLine().getStatusCode(),
				equalTo(HttpStatus.SC_OK));
	}




}
