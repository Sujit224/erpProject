package com.project.erp.setup.models;

import java.util.Vector;

import lombok.Data;

@Data
public class APIReturnModel {
	private String status = "Fail";
	private String message = "Something went Wrong";
	private Vector<?> data;
	private int count =0;

}
