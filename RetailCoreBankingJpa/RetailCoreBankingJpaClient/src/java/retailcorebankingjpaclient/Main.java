/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package retailcorebankingjpaclient;

import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.Employee;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Lenovo
 */
public class Main {

    @EJB(name = "EmployeeSessionBeanRemote")
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;

    
    public static void main(String[] args) {
        List<Employee> retrieveAllAccounts = employeeSessionBeanRemote.retrieveAllAccounts();
        for (Employee emp: retrieveAllAccounts) {
            System.out.println("Account ID: " + emp.getEmployeeId());
            System.out.println("Account UserName: " + emp.getFirstName());
            System.out.println("Account Password: " + emp.getLastName()+ "\n\n");
        }
    }
}
