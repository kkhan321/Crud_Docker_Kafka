package com.task;

import java.util.List;

import com.task.entity.EmployeeFormBean;

import lombok.Data;

@Data
public class ResponseDetails {

	private String responseCode;
	private String responseMessage;
	private String responseReson;
	private EmployeeFormBean employeeFormBean;
	private List<EmployeeFormBean> employeeFormBeans;
	
}
