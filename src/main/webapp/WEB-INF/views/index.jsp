<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Spring MVC</title>
 	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/index.css">
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/resources/css/bootstrap-theme.css">
 	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">
 	
 	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 	<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
 	<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
 	<script type="application/javascript" src="${pageContext.request.contextPath}/resources/js/employee.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
 	
</head>

<body>
	<div >
		<div class="col-md-12 w3-teal">
			<center>
				<h2>SPRING MVC DEMO</h2>
			</center>
		</div>
		
		<div class="col-md-3">
			<br />
			<center>
				<a href="#content"> Insert Employee Details</a>
			</center>

		</div>
		<div class="col-md-9 w3-card-4" style=" position: absolute; left: 25%; top: 8%;  bottom: 6%;overflow:auto; ">
			<div id="formContainer">
			<div class="w3-margin w3-card-4" style="padding-bottom: 5px;" id="addDiv">
				<div class="w3-container w3-blue" style="margin-bottom: 5px;">
					<h3>Add Employee Detail</h3>
				</div>
				<form class="w3-margin" name="form" id="addForm">
				<input id="gridId" style="display: none;" value="">
				<input id="empId" style="display: none;" value="">
				<input id="flag" style="display: none;" value="">
				<div class="row">
					<div class="form-group col-md-8">
						<label for="empName">Employee Name</label> 
						<span id="empNameErr" class="error" style="font-size:8pt; color: red;visibility: hidden;">please enter your name</span>
						<input type="text" class="form-control" id="empName" placeholder="Employee Name" onblur="employee.validate('addForm',this.id);" onkeypress="employee.removeError('addForm',this.id);">
					</div>
					<div class="form-group col-md-4">
								<label for="empAge">Age</label> 
								<span id="empAgeErr" class="error" style="font-size:8pt; color: red;visibility: hidden;">Age should not be empty</span>
								<input type="text" class="form-control" id="empAge" placeholder="Age" onblur="employee.validate('addForm',this.id);" onkeypress="employee.removeError('addForm',this.id);">
							</div>
					</div>
					<div class="row">
						
							<div class="form-group col-md-4">
								<label for="empGender">Gender</label>
								<div class="radio">
									<label class="radio-inline"> 
									<input type="radio" name="empGender" id="male" value="male" checked>
										Male
									</label> <label class="radio-inline"> 
									<input type="radio" name="empGender" id="female" value="female">
										Female
									</label>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label for="empDesignation">Designation</label> <select
									class="form-control" id="empDesignation">
									<option>Coder</option>
									<option>Team Leader</option>
									<option>Project Manager</option>
								</select>
							</div>
							<div class="form-group col-md-4">
								<label for="empSalary">Salary</label> 
								<span id="empSalaryErr" class="error" style="font-size:8pt; color: red;visibility: hidden;">Salary should not be empty</span>
								<input type="text" class="form-control" id="empSalary" placeholder="Salary" onblur="employee.validate('addForm',this.id);" onkeypress="employee.removeError('addForm',this.id);">
							</div>
						
					</div>

					
					


					<button type="reset" class="w3-blue w3-btn"  onclick="employee.add('addDiv');" >Add</button>
				</form>

			</div>
			
			
			
			<!-- testt -->
			
			<div id="editt">
			
			</div>
			
			<!-- test finish -->	
		
		</div>
		
		<div class="w3-container" data-example-id="hoverable-table">
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="hide">GRID ID</th>
						<th class="hide">EMP ID</th>
						<th class="hide">Flag</th>
						<th></th>
						<th>Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Designation</th>
						<th>Salary</th>
						<th>Action</th>
					</tr>
				</thead>
				
				
				
				<tbody class="hide" id="dataBody">
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					
				</tbody>
			</table>
		</div>
		
<nav aria-label="Page navigation">
  <ul class="pagination" id="page">
  

  
  </ul>
</nav>
		
		<div class="row col-md-12" style="margin-bottom: 10px;">
		<button style="margin-right: 5px;" type="button" class="w3-button w3-blue" onclick="employee.persist();">Save</button>
		<button style="margin-right: 5px;" type="button" class="w3-button w3-green" onclick="employee.multipleEdit();">Edit Selected</button>
		<button style="margin-right: 5px;" type="button" class="w3-button w3-red" onclick="employee.multipleDelete();">Delete Selected</button>
		</div>
		
		

	</div>
	<div class="col-md-12 w3-teal" style="position: absolute; top: 94%;">
			<center>
				<h5>Copyright by Samir Patel</h5>
			</center>
		</div>
	</div>
</body>