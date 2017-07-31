package com.nxsol.service;

import java.util.List;

import com.nxsol.bean.EmployeeBean;
import com.nxsol.modal.EmployeeModal;

public interface EmployeeService {

	public void databaseOperations(List<EmployeeBean> employeeBeans);
	
	public List<EmployeeModal> getAll(int rowCount);
	
	public int getRowCount();
	
}
