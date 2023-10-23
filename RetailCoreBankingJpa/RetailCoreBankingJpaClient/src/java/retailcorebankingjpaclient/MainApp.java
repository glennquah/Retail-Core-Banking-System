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
public class MainApp { 

    private DepositAccSessionBeanRemote depositAccSessionBeanRemote;
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private EmployeeSessionBeanRemote employeeSessionBeanRemote;
    private CustomerOperationalModule COModule;
    private Employee currentEmployee;
    private Customer currentCustomer;
    
    public MainApp() {
    }
    
    public MainApp(DepositAccSessionBeanRemote depositAccSessionBeanRemote, CustomerSessionBeanRemote customerSessionBeanRemote, EmployeeSessionBeanRemote employeeSessionBeanRemote) {
        this.depositAccSessionBeanRemote = depositAccSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.employeeSessionBeanRemote = employeeSessionBeanRemote;
    }
        
    public void runApp() throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
        Scanner sc = new Scanner(System.in);
        Integer response;

        System.out.println("*** Welcome to Retail Core Banking System ***\n");            
        System.out.println("1: Login");
        System.out.println("2: Exit\n");
        while(true) {
            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                login(sc);
            } else if (response == 2) {
                System.out.println("Exited");
                break;
            } else {
                System.out.print("Invalid option, please try again!\n");                
            }
        }
    }
    
    public void login(Scanner sc) throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
        System.out.println("\n*** Retail Core Banking System :: Login ***\n");
        String username = "";
        String password = "";
        System.out.print("Enter username> ");
        username = sc.nextLine().trim();
        System.out.print("Enter password> ");
        password = sc.nextLine().trim();
        
        if(username.length() > 0 && password.length() > 0) {
            currentEmployee = employeeSessionBeanRemote.employeeLogin(username, password);
            System.out.println("*** You have Successfully Login! ***");
            loginPage(sc, currentEmployee);
        } else {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    public void loginPage(Scanner sc, Employee emp) throws UnknownPersistenceException, InvalidLoginCredentialException, CustomerNotFoundException {
        String welcomeMessage = String.format("\n*** Welcome %s to Teller Terminal ***\n", emp.getFirstName());
        Integer response;
        while(true) {
            System.out.println(welcomeMessage);
            System.out.println("1: New Customer");
            System.out.println("2: Recurring Customer");
            System.out.println("3: Log Out\n");
        
            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                currentCustomer = createACustomer(sc);
                String createCustMsg = String.format("\n*** %s's Account Created***\n", currentCustomer.getFirstName());
                System.out.println(createCustMsg);                  
                COModule = new CustomerOperationalModule(depositAccSessionBeanRemote, customerSessionBeanRemote, currentCustomer);
                COModule.customerLoginPage();
            } else if (response == 2) {
                currentCustomer = recurringCust(sc);
                String foundCustMsg = String.format("\n*** %s's Account found***\n", currentCustomer.getFirstName());
                System.out.println(foundCustMsg);
                COModule = new CustomerOperationalModule(depositAccSessionBeanRemote, customerSessionBeanRemote, currentCustomer);
                COModule.customerLoginPage();
            } else if (response == 3) {
                runApp();
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public Customer createACustomer(Scanner sc) throws UnknownPersistenceException {
        System.out.println("\n*** Create A Customer ***");
        System.out.println("*** Input Customer Details ***\n\n");
        
        System.out.print("Enter Customer First Name> ");
        String firstName = sc.nextLine().trim();
        System.out.print("Enter Customer Mid Name> ");
        String lastName = sc.nextLine().trim();
        System.out.print("Enter Customer Identification Number> ");
        String ICNumber = sc.nextLine().trim();
        System.out.print("Enter Customer Contact Number> ");
        String contactNumber = sc.nextLine().trim();
        System.out.print("Enter Customer First Address> ");
        String address1 = sc.nextLine().trim();
        System.out.print("Enter Customer Second Address> ");
        String address2 = sc.nextLine().trim();
        System.out.print("Postal Code> ");
        String postalCode = sc.nextLine().trim();
        
        Customer newCustomer = new Customer(firstName, lastName, ICNumber, contactNumber, address1, address2, postalCode);
        
        long id = customerSessionBeanRemote.createNewAccount(newCustomer);
        System.out.println("\nCustomer Created!");
        System.out.println("Customer ID = " + id);
        
        return newCustomer;
    }
    
    public Customer recurringCust(Scanner sc) throws CustomerNotFoundException {
        System.out.println("\n*** Recurring Customer ***");
        System.out.print("Enter Customer Identification Number> ");
        String ICNumber = sc.nextLine().trim();
        
        Customer cust = customerSessionBeanRemote.getCustomerAccount(ICNumber);
        return cust;
    }
}
