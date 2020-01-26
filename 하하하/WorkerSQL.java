package com.leebs.test.hc;

public class WorkerSQL {
	public static void main( String[] args ) throws Exception {
    	System.out.println("App");
    	/*
		The following data definition defines an organization's employee hierarchy.
		An employee is a manager if any other employee has their managerId set to the first employees id. An employee who is a manager may or may not also have a manager.

		TABLE employees
  			id INTEGER NOT NULL PRIMARY KEY
  			managerId INTEGER REFERENCES employees(id)
  			name VARCHAR(30) NOT NULL
		
		Write a query that selects the names of employees who are not managers.
    	 */
    	
    	// select * from employees as emp left join employees as mng on emp.managerId = mng.id;
    	// select emp.name from employees as emp left join employees as mng on emp.managerId = mng.id where mng.name is not null; 오답;
    	// select emp.name from employees as emp left join employees as mng on emp.id = mng.managerId where mng.name is null; 정답;
    }
}
