package com.mvit.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table ( name="roles")
public class Role {
	
	@Id
	//@GeneratedValue(strategy= GenerationType.IDENTITY)
	@GeneratedValue(generator="increment") //soportado por oracle
	@GenericGenerator(name="increment", strategy = "increment") //soportado por oracle 
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
