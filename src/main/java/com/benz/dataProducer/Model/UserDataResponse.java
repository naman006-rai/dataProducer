package com.benz.dataProducer.Model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.benz.dataProducer.Util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {

	@NotEmpty(message = "Name can't be null or Empty")
	private String name;
	
	@NotNull(message = "Invalid Dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date dob;
	
	@NotNull(message = "Salary can't be null or 0")
	@Min(value=1)
	private Double Salary;
	
	@NotNull(message = "Age can't be null or 0")
	@Min(value=1)
	private Integer age;
}
