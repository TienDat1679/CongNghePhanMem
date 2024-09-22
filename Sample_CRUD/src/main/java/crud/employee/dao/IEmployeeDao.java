package crud.employee.dao;

import java.util.List;

import crud.employee.models.EmployeeModel;

public interface IEmployeeDao {
	
	void insertEmployee(EmployeeModel employee);
	
	EmployeeModel selectEmployee(int id);
	
	List<EmployeeModel> selectAllEmployees();
	
	void deleteEmployee(int id);
	
	void updateEmployee(EmployeeModel employee);
}
