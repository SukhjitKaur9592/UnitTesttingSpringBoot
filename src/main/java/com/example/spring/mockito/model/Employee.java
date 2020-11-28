package com.example.spring.mockito.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity	
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String dept;

	
}
