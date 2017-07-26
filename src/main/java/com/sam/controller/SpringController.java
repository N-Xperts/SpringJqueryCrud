package com.sam.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sam.bean.EmployeeBean;
import com.sam.modal.EmployeeModal;
import com.sam.service.EmployeeService;





@Controller
public class SpringController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping({"/","/index"})
	public ModelAndView login()
	{
		System.out.println("in");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/save",method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody String save(@RequestParam String employeeJson, @ModelAttribute EmployeeBean bean)
	{
		System.out.println("=================");
		System.out.println(bean.getEmployeeJson());
		System.out.println("=================");
		System.out.println("insave");
		System.out.println(employeeJson);
		Gson gson = new Gson();
		
		Type stringStringMap = new TypeToken<List<EmployeeBean>>(){}.getType();
		List<EmployeeBean> employeeBean = gson.fromJson(employeeJson, stringStringMap);
		
		employeeService.databaseOperations(employeeBean);
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("res", "Registered Suucessfully");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonArray.put(jsonObject);
		return jsonArray.toString();
	}
	
	@RequestMapping(value = "/getAll",method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody String getAll(@RequestParam int pageCount)
	{
		
		System.out.println("inview");
		
		List<EmployeeModal> employeeBean = employeeService.getAll(pageCount);
		
		JSONArray jsonArray = new JSONArray();
	
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject;
		try {
			
			jsonObject1.put("rowCount", employeeService.getRowCount());
			
			for(EmployeeModal list : employeeBean)
			{
				jsonObject = new JSONObject();
				
					jsonObject.put("id", list.getId());
					jsonObject.put("flag", "editData");
					jsonObject.put("name", list.getName());
					jsonObject.put("age", list.getAge());
					jsonObject.put("gender", list.getGender());
					jsonObject.put("designation", list.getDesignation());
					jsonObject.put("salary", list.getSalary());
					jsonArray.put(jsonObject);
			}
			jsonObject1.put("employees", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonArray.toString());
		return jsonObject1.toString();
	}
	
	
	
}