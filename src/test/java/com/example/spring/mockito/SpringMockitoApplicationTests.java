package com.example.spring.mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.spring.mockito.model.Employee;
import com.example.spring.mockito.model.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SpringMockitoApplicationTests {

	private MockMvc mockMvc; 
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om = new ObjectMapper();
	
	/*
	 * @Before(value = "add") public void setUp() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(context).build(); }
	 */
	
	@Test
	public void addEmployeeTest() throws Exception
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		Employee employee = new Employee();
		employee.setName("Basant");
		employee.setDept("IT");
		
		String jsonRequest = om.writeValueAsString(employee);
		
		MvcResult result = mockMvc.perform(post("/EmployeeService/addEmployee").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}
	
	@Test
	public void getEmployeesTest() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		MvcResult result = mockMvc
				.perform(get("/EmployeeService/getEmployees").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);

	}
}
