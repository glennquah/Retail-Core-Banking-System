/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Employee;
import java.util.List;
import javax.ejb.Remote;
import util.exception.EmployeeNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface EmployeeSessionBeanRemote {
    public Long createNewAccount(Employee newAccount);
    public List<Employee> retrieveAllAccounts();
    public Employee retrieveEmployeeByUsername(String username) throws EmployeeNotFoundException;
    public Employee employeeLogin(String username, String password) throws InvalidLoginCredentialException;
}
