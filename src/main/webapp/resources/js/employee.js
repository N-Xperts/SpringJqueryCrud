var employees=new Array();
var errFlag = 0;
var employee = {
		
		
		add: function(id){
			/*addForm=$('#addForm');
			alert(addForm.innerHtml);*/
			 var empDetail = new Object();
			 
			 $("#"+id+" #flag").val("addData");
			 
			 if($("#"+id+" #gridId").val()=="")
				 {
				 	empDetail.gridId=$(".w3-container .table-hover #dataRow").length;
				 }
			 else {
				 empDetail.gridId=$("#"+id+" #gridId").val();
			}
			 var blank="";
			 if($("#"+id+" #empId").val()!=blank){
				 empDetail.id = $("#"+id+" #empId").val()
			 }
			 
			 empDetail.flag = $("#"+id+" #flag").val()
			 empDetail.name = $("#"+id+" #empName").val()
			 empDetail.age = $("#"+id+" #empAge").val()
			 empDetail.gender= $("#"+id+" input[name=empGender]:checked").val()
			 empDetail.designation = $("#"+id+" #empDesignation").val()
			 empDetail.salary = $("#"+id+" #empSalary").val()
			 
			
			
			 employees[empDetail.gridId]=empDetail;
			 employee.populate();
			 //employee.clear();
			 //alert(empDetail.flag + empDetail.name + empDetail.age +  empDetail.gender + empDetail.designation +  empDetail.salary);
			
		},
		
		validate: function(formId,tagId){
//			alert(tagId)
//			alert($("#"+formId+" #"+tagId).val())
//			alert($("#"+tagId).closest('form').attr('value'));
			var onlyLetters = /^[a-zA-Z\s]*$/;
			var onlyNumers= /^[0-9\s]*$/;
//			alert(onlyLetters.test($("#"+tagId).val()))
			if(tagId == "empName")
				{
					if ($("#"+formId+" #"+tagId).val() == "" || $("#"+formId+" #"+tagId).val() == null) {
						$("#"+formId+" #empNameErr").css('visibility','visible');
						$("#"+formId+" #empNameErr").html("please enter your name");
						$("#"+formId+" .w3-btn").attr('disabled',true);
						
					} else if (!onlyLetters.test($("#"+formId+" #"+tagId).val())) {
						$("#"+formId+" #empNameErr").css('visibility','visible');
						$("#"+formId+" #empNameErr").html("Only alphabets are allowed");
						$("#"+formId+" .w3-btn").attr('disabled',true);
						
				      }
					else{
						errFlag++;
					}
				}
			else if(tagId == "empAge")
			{
				if ($("#"+formId+" #"+tagId).val() == "" || $("#"+formId+" #"+tagId).val() == null) {
					$("#"+formId+" #empAgeErr").css('visibility','visible');
					$("#"+formId+" #empAgeErr").html("Age should not be empty");
					$("#"+formId+" .w3-btn").attr('disabled',true);
					
				} else if (!onlyNumers.test($("#"+formId+" #"+tagId).val())) {
					$("#"+formId+" #empAgeErr").css('visibility','visible');
					$("#"+formId+" #empAgeErr").html("Only numerics are allowed");
					$("#"+formId+" .w3-btn").attr('disabled',true);
					
			    }
				else{
					errFlag++;
				}
			}
			else if(tagId == "empSalary")
			{
				if ($("#"+formId+" #"+tagId).val() == "" || $("#"+formId+" #"+tagId).val() == null) {
					$("#"+formId+" #empSalaryErr").css('visibility','visible');
					$("#"+formId+" #empSalaryErr").html("salary should not e empty");
					$("#"+formId+" .w3-btn").attr('disabled',true);
				} else if (!onlyNumers.test($("#"+formId+" #"+tagId).val())) {
					$("#"+formId+" #empSalaryErr").css('visibility','visible');
					$("#"+formId+" #empSalaryErr").html("Only numerics are allowed");
					$("#"+formId+" .w3-btn").attr('disabled',true);
			      }else {
			    	  errFlag++;
				}
			}
		
			
		},
		
		removeError: function(formId,tagId){
			$("#"+formId+" #"+tagId+"Err").css('visibility','hidden');
			//
			var error=$(".error[style*='visible']").length;
			
			if(error==0){
				$("#"+formId+" .w3-btn").attr('disabled',false);
			}
			//
		},
		
		
		getEmployee: function(i){
			$.ajax({
		         type: 'GET',
		         url:'/SpringDemo/getAll',
		         dataType: "json",
		         contentType:'application/json',
		         data: {
		        	 'pageCount': i
		         },
		         success: function(result) {
		        	    
		        	 	employees = JSON.parse(JSON.stringify(result.employees));
		        	 	employee.populate();
		         }
		     });
		},

		populate: function(){
			$('.table #dataBody tr').remove();
			
			
			$("#dataBody").removeClass("hide");
			var counter=0;
			for (var i = 0; i < employees.length; i++) {
                
				var empObj = employees[i];
				empObj.gridId=i;
				var flagValue="deleteData";
                if(empObj.flag!=flagValue){
                		
                
                var row = "";
    			
    			row = "<tr id='dataRow'>";
    			row += "<td class='hide'>" + empObj.gridId +"</td>";
    			row += "<td class='hide'>" + empObj.id +"</td>";
    			row += "<td class='hide'>" + empObj.flag +"</td>";
    			row += "<td scope='row'><input type='checkbox' id='checkbox' value='"+empObj.gridId+"'></td>";	
    			row += "<td>" + empObj.name + "</td>"
    			row += "<td>" + empObj.age + "</td>"
    			row += "<td>" + empObj.gender + "</td>"
    			row += "<td>" + empObj.designation + "</td>"
    			row += "<td>" + empObj.salary + "</td>"
    			
    			row += "<td><button style='margin-right: 5px;' type='button' class='w3-button w3-tiny w3-green w3-padding-small' onclick='employee.edit("+empObj.gridId+");'>" +'Edit'+ "</button>";
    			row += "<button type='button' class='w3-button w3-tiny w3-red w3-padding-small' onclick='employee.deleteRow("+empObj.gridId+");'>" +'Delete'+ "</button></td>";
    			$("#dataBody").append(row);
    			counter++;
                }
    			employees[i]=empObj;
            }
			
			/*$('.table #dataBody tr').eq(empObj.gridId).remove();*/
			
			
			
			//$('#example').DataTable();
			
		},
		
		
		
		edit: function(pos)
		{
			var form = $("#formContainer #addDiv").html;
			var num = pos+1;
			//alert(employees.length +" "+ pos)
			var empObj=employees[pos];
			$("#addDiv").clone().prop('id', 'editDiv'+num ).appendTo($("#formContainer"));
			$("#editDiv"+num+" h3").html("Edit Employee Detail");
			$("#editDiv"+num+" #gridId").val(empObj.gridId);
			$("#editDiv"+num+" #empId").val(empObj.id);
			$("#editDiv"+num+" #flag").val(empObj.flag);
			 $("#editDiv"+num+" #empName").val(empObj.name);
			 $("#editDiv"+num+" #empName").attr("onblur","employee.validate('editDiv"+num+"',this.id)")
			 $("#editDiv"+num+" #empAge").val(empObj.age);
			 $("#editDiv"+num+" #empAge").attr("onblur","employee.validate('editDiv"+num+"',this.id)")
			 $("#editDiv"+num+" #"+empObj.gender).prop("checked", true);
			 $("#editDiv"+num+" #empDesignation").val(empObj.designation);
			 $("#editDiv"+num+" #empSalary").val(empObj.salary);
			 $("#editDiv"+num+" #empSalary").attr("onblur","employee.validate('editDiv"+num+"',this.id)")
			 $("#editDiv"+num+" button").html('Edit');
			 $("#editDiv"+num+" button").attr('onclick','employee.add("editDiv'+num+'"),employee.remove("editDiv'+num+'")');
		},
		
		multipleEdit : function(){
			$(".table input:checkbox:checked").map(function(){
			    employee.edit($(this).val());  
				
			    });
			
		},
		multipleDelete : function(){
			$(".table input:checkbox:checked").map(function(){
				
			    employee.deleteRow($(this).val());  
				
			    });
			
		},
		
		remove: function(id)
		{
			 $('#'+id).remove();
		},
		
		deleteRow: function(pos)
		{
			var empObj=employees[pos];
			var flagValue = "addData";
			if(empObj.flag==flagValue){
				employees.splice(pos,1);
			} else{
				empObj.flag="deleteData";
				employees[pos]=empObj;
			}
			employee.populate();
		},
		
		persist: function()
		{
			//alert(JSON.stringify(employees))
			  $.ajax({
	                type: 'GET',
	                url:'/SpringDemo/save',
	                dataType: "json",
	                contentType:'application/json',
	                data: {
	                	'employeeJson': JSON.stringify(employees)
	                },
	                success: function(result) {
	                	location.reload();
	                	
	    				alert("success:  "+data[0].res);
	                }
	            });
			
		},
		
		page: function(rowCount)
		{
			var totalPage = rowCount / 5;
			for(var i=0 ; i< totalPage ; i++ )
				{
				
					if(i==0)
						{
				$("#page").append("<li><a class='active' onclick='employee.getEmployee("+(i+1)+")' >"+(i+1)+"</a></li>");
				
				}
					else
						{
						$("#page").append("<li><a onclick='employee.getEmployee("+(i+1)+")' >"+(i+1)+"</a></li>");	
						}
						}
			
			employee.getEmployee(1);
			
			
		}
		
		
		
		
		
};

$( document ).ready(function() {
	
	 $.ajax({
         type: 'GET',
        
         url:'/SpringDemo/getAll',
         dataType: "json",
         contentType:'application/json',
         data: {
        	 'pageCount': 1
         },
         success: function(result) {
        	 	
        	    employee.page(result.rowCount);
/*        	 	employees = JSON.parse(JSON.stringify(result.employees));
        	 	employee.populate();*/
         }
     });
	 
	 
    
});