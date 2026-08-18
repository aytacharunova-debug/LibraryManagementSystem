package com.library.daoImpl;

import com.library.daoInterface.IEmployee;
import com.library.model.Employee;
import com.library.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IEmployee {

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employee (name, surname, phone, email, username, password, position, hire_date, salary, age) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getSurname());
            pstmt.setString(3, employee.getPhone());
            pstmt.setString(4, employee.getEmail());
            pstmt.setString(5, employee.getUsername());
            pstmt.setString(6, employee.getPassword());
            pstmt.setString(7, employee.getPosition());
            
            // LocalDate-i java.sql.Date-ə çeviririk
            if (employee.getHireDate() != null) {
                pstmt.setDate(8, Date.valueOf(employee.getHireDate()));
            } else {
                pstmt.setNull(8, Types.DATE);
            }
            
            pstmt.setDouble(9, employee.getSalary());
            pstmt.setInt(10, employee.getAge());
            
            pstmt.executeUpdate();
            System.out.println("İşçi uğurla əlavə edildi!");
            
        } catch (SQLException e) {
            System.out.println("İşçi əlavə edilərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, surname = ?, phone = ?, email = ?, username = ?, " +
                     "password = ?, position = ?, hire_date = ?, salary = ?, age = ? WHERE employee_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getSurname());
            pstmt.setString(3, employee.getPhone());
            pstmt.setString(4, employee.getEmail());
            pstmt.setString(5, employee.getUsername());
            pstmt.setString(6, employee.getPassword());
            pstmt.setString(7, employee.getPosition());
            pstmt.setDate(8, Date.valueOf(employee.getHireDate()));
            pstmt.setDouble(9, employee.getSalary());
            pstmt.setInt(10, employee.getAge());
            pstmt.setInt(11, employee.getEmployeeId());
            
            pstmt.executeUpdate();
            System.out.println("İşçi məlumatları yeniləndi!");
            
        } catch (SQLException e) {
            System.out.println("İşçi yenilənərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("İşçi silindi!");
            
        } catch (SQLException e) {
            System.out.println("İşçi silinərkən xəta: " + e.getMessage());
        }
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        Employee employee = null;
        
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = mapResultSetToEmployee(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("İşçi axtarışında xəta: " + e.getMessage());
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                employeeList.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            System.out.println("Siyahı alınarkən xəta: " + e.getMessage());
        }
        return employeeList;
    }

    // ResultSet-dən gələn məlumatları Employee obyektinə doldurmaq üçün köməkçi metod
    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setName(rs.getString("name"));
        employee.setSurname(rs.getString("surname"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setUsername(rs.getString("username"));
        employee.setPassword(rs.getString("password"));
        employee.setPosition(rs.getString("position"));
        
        // SQL Date-i LocalDate-ə çeviririk
        Date sqlDate = rs.getDate("hire_date");
        if (sqlDate != null) {
            employee.setHireDate(sqlDate.toLocalDate());
        }
        
        employee.setSalary(rs.getDouble("salary"));
        employee.setAge(rs.getInt("age"));
        return employee;
    }
}