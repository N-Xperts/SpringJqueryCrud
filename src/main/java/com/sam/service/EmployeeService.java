package com.sam.service;

import java.util.List;

import com.sam.bean.EmployeeBean;
import com.sam.modal.EmployeeModal;

public interface EmployeeService {

	public void databaseOperations(List<EmployeeBean> employeeBeans);
	
	public List<EmployeeModal> getAll(int rowCount);
	
	public int getRowCount();
	
}
