package com.nxsol.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//import org.hibernate.validator.constraints.NotEmpty;


import com.nxsol.modal.EmployeeModal;

@SuppressWarnings("serial")
public class EmployeeBean {
	
	
	private int id;
	
	private String flag;
	
	//@NotEmpty
	@Pattern(regexp = "^[A-Za-z]*$")
	private String name;
	
	//@NotEmpty
	@Pattern(regexp = "^[0-9]*$")
	private String age;

	@NotNull
	//@NotEmpty
	private String gender;
	
	//@NotEmpty
	@Pattern(regexp = "^[A-Za-z]*$")
	private String designation;
	
	//@NotEmpty
	@Pattern(regexp = "^[0-9]*$")
	private String salary;
	
	private String employeeJson;

	

	

	public String getEmployeeJson() {
		return employeeJson;
	}

	public void setEmployeeJson(String employeeJson) {
		this.employeeJson = employeeJson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
    public EmployeeModal beanToModal ()
    {
    	return new EmployeeModal(id, name, age, gender, designation, salary);
    			
    			
    }
	
	
	

}
