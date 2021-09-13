package com.benz.dataProducer.Publisher;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.benz.dataProducer.Config.MQConfig;
import com.benz.dataProducer.Exception.DataAlreadyExistsException;
import com.benz.dataProducer.Exception.DataNotFoundException;
import com.benz.dataProducer.Exception.InvalidFileTypeException;
import com.benz.dataProducer.Model.UserData;
import com.benz.dataProducer.Model.UserDataResponse;
import com.benz.dataProducer.Service.DataService;

@RestController
public class MessagePublisher {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DataService service;

	@PostMapping("/store")
	public String createData(@RequestHeader String fileType,@RequestBody UserData data) {
		data.setFileType(fileType);
		if(data.getFileType().equalsIgnoreCase("xml")||data.getFileType().equalsIgnoreCase("csv")) {
			UserDataResponse[] existingData = service.getData();
			List<UserDataResponse> existingDataList = Arrays.asList(existingData);

			if(!service.containsName(existingDataList, data.getName())) {
				template.convertAndSend(MQConfig.EXCHANGE,
						MQConfig.ROUTING_KEY, data);
			}
			else {
				throw new DataAlreadyExistsException("File Already Exists.");
			}

		}
		else {
			throw new InvalidFileTypeException();
		}

		return "Data Stored Successfully";
	}


	@GetMapping("/read")
	public ResponseEntity<List<UserDataResponse>> getData() {
		return new ResponseEntity(service.getData(), HttpStatus.OK);	
	}
	
	@PutMapping("/update")	
	public String updateData(@RequestHeader String fileType,@RequestBody UserData data,
			@RequestParam String name) {
		data.setFileType(fileType);
		if(data.getFileType().equalsIgnoreCase("xml")||data.getFileType().equalsIgnoreCase("csv")) {

			UserDataResponse[] existingData = service.getData();
			List<UserDataResponse> existingDataList = Arrays.asList(existingData);

			if(service.containsName(existingDataList, name)) {
				template.convertAndSend(MQConfig.EXCHANGE,
						MQConfig.ROUTING_KEY, data);
			}
			else {
				throw new DataNotFoundException("File Not Found.Please create it first.");
			}

		}


		else {
			throw new InvalidFileTypeException("Invalid File Type. Accepted Value =(xml, cvs)");
		}
		return "Data Updated Successfully";

	}



}