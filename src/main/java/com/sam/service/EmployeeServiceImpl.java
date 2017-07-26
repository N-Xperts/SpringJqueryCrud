package com.sam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sam.bean.EmployeeBean;
import com.sam.dao.EmployeeDao;
import com.sam.modal.EmployeeModal;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class EmployeeServiceImpl implements EmployeeService{
	

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void databaseOperations(List<EmployeeBean> employeeBeans) {
		// TODO Auto-generated method stub
		System.out.println("hii"+employeeBeans.size());
		for(EmployeeBean bean : employeeBeans)
		{
			if(bean.getFlag().equals("addData" ))
			{
				employeeDao.saveEmployee(bean.beanToModal());
				
				System.out.println("2");
			}
			if(bean.getFlag().equals("editData"))
			{
				employeeDao.saveEmployee(bean.beanToModal());
			}
			if(bean.getFlag().equals("deleteData"))
			{
				employeeDao.deleteEmployee(bean.beanToModal());
			}
		}
		
	}

	@Override
	public List<EmployeeModal> getAll(int rowCount) {
		// TODO Auto-generated method stub
		
		return employeeDao.getAll(rowCount);
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		//System.out.println(employeeDao.getRowCount());
		return 0;//employeeDao.getRowCount();
	}
	
	

}
