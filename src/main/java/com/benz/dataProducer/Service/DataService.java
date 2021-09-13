package com.benz.dataProducer.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.benz.dataProducer.Model.UserDataResponse;

@Service
public class DataService {
	
	
	public UserDataResponse[] getData() {
		String uri = "http://localhost:9001/read";
	    RestTemplate restTemplate = new RestTemplate();
	    UserDataResponse[] getResponse = restTemplate.getForObject(uri,UserDataResponse[].class );
		return getResponse;
	} 
	
	public boolean containsName(final List<UserDataResponse> list, final String name){
	    return list.stream().anyMatch(o -> o.getName().equals(name));
	}
}
