/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.Model.Employee;
/**
 *
 * @author saifu
 */
public class EmployeeDAO {
    Connection conn = null;
    private String jdbcURL = "jdbc:mysql://localhost:3306/company";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    
    private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO employees (name, email, position) VALUES " +
            "(?, ?, ?)";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT id,name,email,position FROM employees where id=?";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String DELETE_EMPLOYEES_SQL = "DELETE FROM employees WHERE id=?";
    private static final String UPDATE_EMPLOYEES_SQL = "UPDATE employees SET name=?,email=?,position=? WHERE id=?";
    
    public EmployeeDAO() {}
    
    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public void insertEmployee(Employee employee) throws SQLException {
        System.out.println(INSERT_EMPLOYEES_SQL);
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Employee selectEmployee(int id) throws SQLException {
        System.out.println(SELECT_EMPLOYEE_BY_ID);
        Employee employee = null;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            ps.setInt(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String position = rs.getString("position");
                employee = new Employee(id, name, email, position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employee;
    }
    
    public List<Employee> selectAllEmployee() throws SQLException {
        System.out.println(SELECT_ALL_EMPLOYEES);
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String position = rs.getString("position");
                employees.add(new Employee(id, name, email, position));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employees;
    }
    
    public boolean deleteEmployee(int id) throws SQLException {
        System.out.println(DELETE_EMPLOYEES_SQL);
        boolean rowDeleted = false;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_EMPLOYEES_SQL)) {
            ps.setInt(1, id);
            System.out.println(ps);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
    
    public boolean updateEmployee(Employee employee) throws SQLException {
        System.out.println(UPDATE_EMPLOYEES_SQL);
        boolean rowUpdated = false;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLOYEES_SQL)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPosition());
            ps.setInt(4, employee.getId());
            System.out.println(ps);
            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rowUpdated;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
