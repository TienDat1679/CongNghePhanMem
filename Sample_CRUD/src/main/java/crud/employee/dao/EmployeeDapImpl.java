package crud.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crud.employee.configs.DBConnectMySQL;
import crud.employee.models.EmployeeModel;

public class EmployeeDapImpl extends DBConnectMySQL implements IEmployeeDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public void insertEmployee(EmployeeModel employee) {
		String sql = "INSERT INTO employees(id, name, gender, email) VALUES (?, ?, ?, ?)";

		try {
			conn = super.getDatabaseConnection();

			ps = conn.prepareStatement(sql);

			ps.setInt(1, employee.getId());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getGender());
			ps.setString(4, employee.getEmail());
			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public EmployeeModel selectEmployee(int id) {
		String sql = "SELECT * FROM employees WHERE id = ? ";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			EmployeeModel employee = new EmployeeModel();
			while (rs.next()) {
				
				employee.setId(rs.getInt("id"));
				employee.setEmail(rs.getString("email"));
				employee.setGender(rs.getString("gender"));
				employee.setEmail(rs.getString("email"));
				
			}
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EmployeeModel> selectAllEmployees() {
		String sql = "SELECT * FROM employees";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<EmployeeModel> list = new ArrayList<>();
			while (rs.next()) {
				
				list.add(new EmployeeModel(rs.getInt("id"), rs.getString("name"), rs.getString("gender"),
						rs.getString("email")));

				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteEmployee(int id) {
		String sql = "Delete from employees where id = ?";
		
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
	        
	        ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateEmployee(EmployeeModel employee) {
		String sql = "Update employees set name = ?, gender = ? , email = ? where id = ?";
		
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, employee.getName());
	        ps.setString(2, employee.getGender());
	        ps.setString(3, employee.getEmail());
	        ps.setInt(4, employee.getId());
	        
	        ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
