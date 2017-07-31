package com.nxsol.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nxsol.modal.EmployeeModal;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveEmployee(EmployeeModal employeeModal) {
		// TODO Auto-generated method stub
		System.out.println("34");
		sessionFactory.getCurrentSession().merge(employeeModal);
	}

	@Override
	public void deleteEmployee(EmployeeModal employeeModal) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(employeeModal);
	}

	@Override
	public List<EmployeeModal> getAll(int rowCount) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmployeeModal.class);
		criteria.setFirstResult(((rowCount-1)*5));
		criteria.setMaxResults(5);
		return criteria.list();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		Number totalRow = (Number) sessionFactory.getCurrentSession().createCriteria(EmployeeModal.class).setProjection(Projections.rowCount()).uniqueResult(); 
		return totalRow.intValue();
	}

}
