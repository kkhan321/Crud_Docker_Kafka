package com.task.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.RequestDetails;
import com.task.ResponseDetails;
import com.task.constant.RestConstant;
import com.task.entity.EmployeeFormBean;
import com.task.entity.EmployeeMasterBean;
import com.task.service.EmployeeService;
import com.task.service.KafkaProducerService;

@Service
public class EmployeeWrapper {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    private KafkaProducerService kafkaProducerService;

	public ResponseDetails addEmployee(RequestDetails requestDetails) {
	    ResponseDetails responseDetails = new ResponseDetails();
	    EmployeeFormBean employeeFormBean = requestDetails.getEmployeeFormBean();
	    boolean status = false;

	    try {
	        if (employeeFormBean.getEmployeeId() == null) {
	            // Adding new employee
	            status = employeeService.addorUpdateEmployee(employeeFormBean);
	            if (status) {
	                responseDetails.setResponseCode(RestConstant.SUCCESS_CODE);
	                responseDetails.setResponseMessage(RestConstant.SUCCESS_MSG);
	                responseDetails.setResponseReson(RestConstant.ADD_SUCCESS);
	            } else {
	                responseDetails.setResponseCode(RestConstant.EXCEPTION_OCCURED);
	                responseDetails.setResponseMessage(RestConstant.EXCEPTION_OCCURED_MESSAGE);
	            }
	        } else {
	            // Updating existing employee
	            status = employeeService.addorUpdateEmployee(employeeFormBean); // Make sure to implement this method
	            if (status) {
	                responseDetails.setResponseCode(RestConstant.SUCCESS_CODE);
	                responseDetails.setResponseMessage(RestConstant.SUCCESS_MSG);
	                responseDetails.setResponseReson(RestConstant.UPDATE_SUCCESS); // Define this constant
	            } else {
	                responseDetails.setResponseCode(RestConstant.ERROR_CODE);
	                responseDetails.setResponseMessage(RestConstant.ERROR_MSG);
	                responseDetails.setResponseReson(RestConstant.UPDATE_FAILURE); // Define this constant
	            }
	        }
	    } catch (Exception e) {
	    	responseDetails.setResponseCode(RestConstant.EXCEPTION_OCCURED);
	        responseDetails.setResponseMessage(RestConstant.EXCEPTION_OCCURED_MESSAGE);
	        responseDetails.setResponseReson(e.getMessage()); // Optionally include the exception message
	    }
	    
	    return responseDetails;
	}

	public ResponseDetails listOfEmployees(RequestDetails requestDetails) {
		ResponseDetails responseDetails= new ResponseDetails();
		try {
			List<EmployeeFormBean> employeeFormBeans= employeeService.listOfEmployees();
			if(!employeeFormBeans.isEmpty()) {
				responseDetails.setResponseCode(RestConstant.SUCCESS_CODE);
				responseDetails.setResponseMessage(RestConstant.SUCCESS_MSG);
				responseDetails.setEmployeeFormBeans(employeeFormBeans);
			} else {
				responseDetails.setResponseCode(RestConstant.ERROR_CODE);
				responseDetails.setResponseMessage(RestConstant.ERROR_MSG);
			}
		} catch (Exception e) {
			responseDetails.setResponseCode(RestConstant.EXCEPTION_OCCURED);
	        responseDetails.setResponseMessage(RestConstant.EXCEPTION_OCCURED_MESSAGE);
	        responseDetails.setResponseReson(e.getMessage()); // Optionally include the exception message
	 		}
		return responseDetails;
	}

	public ResponseDetails getById(RequestDetails requestDetails) {
		ResponseDetails responseDetails= new ResponseDetails();
		try {
		EmployeeFormBean employeeFormBean=employeeService.getById(requestDetails.getEmployeeFormBean().getEmployeeId());
		if(employeeFormBean!=null) {
			responseDetails.setResponseCode(RestConstant.SUCCESS_CODE);
			responseDetails.setResponseMessage(RestConstant.SUCCESS_MSG);
			responseDetails.setEmployeeFormBean(employeeFormBean);

		}else {
			responseDetails.setResponseCode(RestConstant.ERROR_CODE);
			responseDetails.setResponseMessage(RestConstant.ERROR_MSG);
			responseDetails.setResponseReson(RestConstant.NOT_FOUND);
		}
		} catch (Exception e) {
			responseDetails.setResponseCode(RestConstant.EXCEPTION_OCCURED);
	        responseDetails.setResponseMessage(RestConstant.EXCEPTION_OCCURED_MESSAGE);
	        responseDetails.setResponseReson(e.getMessage()); // Optionally include the exception message
	 				}
		return responseDetails;
	}

	public ResponseDetails deleteById(RequestDetails requestDetails) {
		ResponseDetails responseDetails = new ResponseDetails();
	    EmployeeFormBean employeeFormBean = requestDetails.getEmployeeFormBean();
	    boolean status = false;

	    try {
	       
	            
	            status = employeeService.deleteById(employeeFormBean.getEmployeeId());
	            if (status) {
	                responseDetails.setResponseCode(RestConstant.SUCCESS_CODE);
	                responseDetails.setResponseMessage(RestConstant.SUCCESS_MSG);
	                responseDetails.setResponseReson(RestConstant.DELETE_SUCCESS);
	            } else {
	            	responseDetails.setResponseCode(RestConstant.ERROR_CODE);
	    			responseDetails.setResponseMessage(RestConstant.ERROR_MSG);
	    			responseDetails.setResponseReson(RestConstant.NOT_FOUND);
	            }
	        
	        } catch (Exception e) {
				responseDetails.setResponseCode(RestConstant.EXCEPTION_OCCURED);
		        responseDetails.setResponseMessage(RestConstant.EXCEPTION_OCCURED_MESSAGE);
		        responseDetails.setResponseReson(e.getMessage()); // Optionally include the exception message
		 				}
			return responseDetails;
		
	}

	public ResponseDetails sendWelcomeMessage(String employeeId) {
		ResponseDetails responseDetails = new ResponseDetails();

		try {
			EmployeeFormBean employee = employeeService.getById(employeeId);
			if (employee != null) {
	            String firstName = employee.getFirstName();
	            String message = "Hi " + firstName + ". Welcome to Technodrome Solutions Pvt Ltd.";
	            kafkaProducerService.sendMessage("WELCOME_MSG", message);
	            boolean status  =  employeeService.updateWelcomeFlag(employeeId, "Y");
                if(status) {
                	responseDetails.setResponseCode(RestConstant.SUCCESS_CODE);
	                responseDetails.setResponseMessage(RestConstant.SUCCESS_MSG);
	                responseDetails.setResponseReson(RestConstant.KAFKA_MESSAGE);
	         
                }
			}else {
				responseDetails.setResponseCode(RestConstant.ERROR_CODE);
    			responseDetails.setResponseMessage(RestConstant.ERROR_MSG);
    			responseDetails.setResponseReson(RestConstant.NOT_FOUND);
			}
		} catch (Exception e) {
			responseDetails.setResponseCode(RestConstant.EXCEPTION_OCCURED);
	        responseDetails.setResponseMessage(RestConstant.EXCEPTION_OCCURED_MESSAGE);
	        responseDetails.setResponseReson(e.getMessage()); // Optionally include the exception message
	 					}

		return responseDetails;
	}

}