package com.task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.task.constant.DateFormatter;
import com.task.dao.EmployeeDao;
import com.task.entity.EmployeeFormBean;
import com.task.entity.EmployeeMasterBean;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	public boolean addorUpdateEmployee(EmployeeFormBean employeeFormBean) throws Exception {
		boolean status=false;
		EmployeeMasterBean	 employeeMasterBean=new EmployeeMasterBean();
		if(employeeFormBean.getEmployeeId()!=null) {
			employeeMasterBean.setEmployeeId(Integer.parseInt(employeeFormBean.getEmployeeId()));
		}
		employeeMasterBean.setAddress(employeeFormBean.getAddress());
		employeeMasterBean.setContactNumber(employeeFormBean.getContactNumber());
		employeeMasterBean.setDepartment(employeeFormBean.getDepartment());
		employeeMasterBean.setDateOfBirth(DateFormatter.stringToDate(employeeFormBean.getDateOfBirth()));
		employeeMasterBean.setFirstName(employeeFormBean.getFirstName());
		employeeMasterBean.setGender(employeeFormBean.getGender());
		employeeMasterBean.setJobTitle(employeeFormBean.getJobTitle());
		employeeMasterBean.setLastName(employeeFormBean.getLastName());
		employeeMasterBean.setNationality(employeeFormBean.getNationality());
		employeeMasterBean.setFlag("N");
		employeeMasterBean=employeeDao.save(employeeMasterBean);
		if(employeeMasterBean.getEmployeeId()!=null) {
			status=true;
		}
	    return status;
	}

	public List<EmployeeFormBean> listOfEmployees() throws Exception{
		 List<EmployeeFormBean> employeeFormBeans=new ArrayList<>();
		  List<EmployeeMasterBean> employeeMasterBeans=employeeDao.findAll();
		 for(EmployeeMasterBean employeeMasterBean : employeeMasterBeans) {
			 EmployeeFormBean employeeFormBean= new EmployeeFormBean();
			 employeeFormBean.setEmployeeId(employeeMasterBean.getEmployeeId().toString());
			 employeeFormBean.setFirstName(employeeMasterBean.getFirstName());
			 employeeFormBean.setLastName(employeeMasterBean.getLastName());
			 employeeFormBean.setGender(employeeMasterBean.getGender());
			 employeeFormBean.setNationality(employeeMasterBean.getNationality());
			 employeeFormBean.setAddress(employeeMasterBean.getAddress());
			 employeeFormBean.setContactNumber(employeeMasterBean.getContactNumber());
			 employeeFormBean.setDepartment(employeeMasterBean.getDepartment());
			 employeeFormBean.setJobTitle(employeeMasterBean.getJobTitle());
			 employeeFormBean.setDateOfBirth(DateFormatter.dateToString(employeeMasterBean.getDateOfBirth()));
			 employeeFormBean.setFlag(employeeMasterBean.getFlag());
			 employeeFormBeans.add(employeeFormBean);
		 }
		 return employeeFormBeans;
	}

	public EmployeeFormBean getById(String id) throws Exception{
    EmployeeMasterBean employeeMasterBean=employeeDao.findById(Integer.parseInt(id)).get();
	 EmployeeFormBean employeeFormBean= new EmployeeFormBean();
	 employeeFormBean.setEmployeeId(employeeMasterBean.getEmployeeId().toString());
	 employeeFormBean.setFirstName(employeeMasterBean.getFirstName());
	 employeeFormBean.setLastName(employeeMasterBean.getLastName());
	 employeeFormBean.setGender(employeeMasterBean.getGender());
	 employeeFormBean.setNationality(employeeMasterBean.getNationality());
	 employeeFormBean.setAddress(employeeMasterBean.getAddress());
	 employeeFormBean.setContactNumber(employeeMasterBean.getContactNumber());
	 employeeFormBean.setDepartment(employeeMasterBean.getDepartment());
	 employeeFormBean.setJobTitle(employeeMasterBean.getJobTitle());
	 employeeFormBean.setDateOfBirth(DateFormatter.dateToString(employeeMasterBean.getDateOfBirth()));
	 employeeFormBean.setFlag(employeeMasterBean.getFlag());
     return employeeFormBean;
	}

	public boolean deleteById(String employeeId)throws Exception {
		boolean status= false;
		 if (employeeDao.existsById(Integer.parseInt(employeeId))) {  // Check if the employee exists
	            employeeDao.deleteById(Integer.parseInt(employeeId));  // Delete the employee
	            status = true;  // Set status to true if successfully deleted
	        }
		return status;
		
	}

	private EmployeeMasterBean masterMapper(EmployeeFormBean employeeFormBean) {
		EmployeeMasterBean	 employeeMasterBean=new EmployeeMasterBean();
		employeeMasterBean.setEmployeeId(Integer.parseInt(employeeFormBean.getEmployeeId()));
		employeeMasterBean.setAddress(employeeFormBean.getAddress());
		employeeMasterBean.setContactNumber(employeeFormBean.getContactNumber());
		employeeMasterBean.setDepartment(employeeFormBean.getDepartment());
		employeeMasterBean.setDateOfBirth(DateFormatter.stringToDate(employeeFormBean.getDateOfBirth()));
		employeeMasterBean.setFirstName(employeeFormBean.getFirstName());
		employeeMasterBean.setGender(employeeFormBean.getGender());
		employeeMasterBean.setJobTitle(employeeFormBean.getJobTitle());
		employeeMasterBean.setLastName(employeeFormBean.getLastName());
		employeeMasterBean.setNationality(employeeFormBean.getNationality());
		return employeeMasterBean;
	}
	
	public boolean updateWelcomeFlag(String employeeId, String flag) throws Exception{
        EmployeeFormBean employee = getById(employeeId);
        boolean status=false;
        EmployeeMasterBean employeeMasterBean=masterMapper(employee);      
        if (employeeMasterBean != null) {
        	employeeMasterBean.setFlag("Y");
            employeeDao.save(employeeMasterBean);
            status=true;
        }
        return status;
    }
	

}
