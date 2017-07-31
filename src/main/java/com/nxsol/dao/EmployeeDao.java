package com.nxsol.dao;

import java.util.List;

import com.nxsol.modal.EmployeeModal;

public interface EmployeeDao {
	
	public void saveEmployee(EmployeeModal employeeModal);
	
	public void deleteEmployee(EmployeeModal employeeModal);
	
	public List<EmployeeModal> getAll(int rowCount);
	
	public int getRowCount();
	
	

}
