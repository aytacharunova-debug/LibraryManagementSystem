package com.library.daoInterface;

import java.util.List;

import com.library.model.Employee;

public interface IEmployee {
	void add(Employee employee);
	void update(Employee employee);
	void delete(int id);
	Employee getById( int id);
	List<Employee> getAll();
	  

}
