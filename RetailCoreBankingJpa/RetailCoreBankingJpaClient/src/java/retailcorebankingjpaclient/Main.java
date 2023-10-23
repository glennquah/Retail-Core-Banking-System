/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package retailcorebankingjpaclient;

import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.DepositAccSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.Customer;
import entity.DepositAccount;
import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import util.enumeration.DepositAccountType;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
public class Main {

    @EJB
    private static DepositAccSessionBeanRemote depositAccSessionBeanRemote;

    @EJB
    private static CustomerSessionBeanRemote customerSessionBeanRemote;

    @EJB
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;
    
    
    public static void main(String[] args) throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
        MainApp mainApp = new MainApp(depositAccSessionBeanRemote, customerSessionBeanRemote, employeeSessionBeanRemote);
        mainApp.runApp();
    }
}
