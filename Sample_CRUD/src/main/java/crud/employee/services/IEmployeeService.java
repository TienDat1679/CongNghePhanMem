package crud.employee.services;

import java.util.List;

import crud.employee.models.EmployeeModel;

public interface IEmployeeService {
	
	void insertEmployee(EmployeeModel employee);
	
	EmployeeModel selectEmployee(int id);
	
	List<EmployeeModel> selectAllEmployees();
	
	void deleteEmployee(int id);
	
	void updateEmployee(EmployeeModel employee);
}
