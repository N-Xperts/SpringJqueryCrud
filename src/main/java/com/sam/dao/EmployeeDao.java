package com.sam.dao;

import java.util.List;

import com.sam.modal.EmployeeModal;

public interface EmployeeDao {
	
	public void saveEmployee(EmployeeModal employeeModal);
	
	public void deleteEmployee(EmployeeModal employeeModal);
	
	public List<EmployeeModal> getAll(int rowCount);
	
	public int getRowCount();
	
	

}
