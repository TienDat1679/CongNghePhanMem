package crud.employee.services;

import java.util.List;

import crud.employee.dao.EmployeeDapImpl;
import crud.employee.dao.IEmployeeDao;
import crud.employee.models.EmployeeModel;

public class EmployeeServiceImpl implements IEmployeeService {
	
	IEmployeeDao employeeDao = new EmployeeDapImpl();
	
	@Override
	public void insertEmployee(EmployeeModel employee) {
		employeeDao.insertEmployee(employee);
		
	}

	@Override
	public EmployeeModel selectEmployee(int id) {
		return employeeDao.selectEmployee(id);
	}

	@Override
	public List<EmployeeModel> selectAllEmployees() {
		return employeeDao.selectAllEmployees();
	}

	@Override
	public void deleteEmployee(int id) {
		employeeDao.deleteEmployee(id);
		
	}

	@Override
	public void updateEmployee(EmployeeModel employee) {
		employeeDao.updateEmployee(employee);
		
	}
	
	
}
