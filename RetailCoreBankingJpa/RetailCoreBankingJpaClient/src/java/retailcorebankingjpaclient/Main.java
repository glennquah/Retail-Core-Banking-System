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

    @EJB(name = "DepositAccSessionBeanRemote")
    private static DepositAccSessionBeanRemote depositAccSessionBeanRemote;

    @EJB(name = "CustomerSessionBeanRemote")
    private static CustomerSessionBeanRemote customerSessionBeanRemote;

    @EJB(name = "EmployeeSessionBeanRemote")
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;
    
    

    
    public static void main(String[] args) throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
        runApp(); 
    }
    
    public static void runApp() throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
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
    
    public static void login(Scanner sc) throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
        System.out.println("\n*** Retail Core Banking System :: Login ***\n");
        String username = "";
        String password = "";
        System.out.print("Enter username> ");
        username = sc.nextLine().trim();
        System.out.print("Enter password> ");
        password = sc.nextLine().trim();
        
        if(username.length() > 0 && password.length() > 0) {
            Employee employeeEntity = employeeSessionBeanRemote.employeeLogin(username, password);
            System.out.println("*** You have Successfully Login! ***");
            loginPage(sc, employeeEntity);
        } else {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
    
    public static void loginPage(Scanner sc, Employee emp) throws UnknownPersistenceException, InvalidLoginCredentialException, CustomerNotFoundException {
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
                Customer cust = createACustomer(sc);
                customerLoginPage(sc, cust);
            } else if (response == 2) {
                Customer cust = recurringCust(sc);
                customerLoginPage(sc, cust);
            } else if (response == 3) {
                runApp();
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public static void customerLoginPage(Scanner sc, Customer cust) throws UnknownPersistenceException {
        String welcomeMessage = String.format("\n*** %s Account found, Please select the following options ***\n", cust.getFirstName());
        Integer response;
        while(true) {
            System.out.println("1: Open Deposit Account");
            System.out.println("2: Issue ATM Card");
            System.out.println("3: Issue Replacement ATM Card");
            System.out.println("4: Insert ATM Card");
            System.out.println("5: Change PIN");
            System.out.println("6: Enquire Available Balance");
            System.out.println("7: back\n");
            
            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                openDepositAccount(cust, sc);
            } else if (response == 2) {
                System.out.println("2");
            } else if (response == 3) {
                System.out.println("3");
            } else if (response == 4) {
                System.out.println("4");
            } else if (response == 5) {
                System.out.println("5");
            } else if (response == 6) {
                System.out.println("6");
            } else if (response == 7) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public static Customer createACustomer(Scanner sc) throws UnknownPersistenceException {
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
    
    public static Customer recurringCust(Scanner sc) throws CustomerNotFoundException {
        System.out.println("\n*** Recurring Customer ***");
        System.out.print("Enter Customer Identification Number> ");
        String ICNumber = sc.nextLine().trim();
        
        Customer cust = customerSessionBeanRemote.getCustomerAccount(ICNumber);
        return cust;
    }
    
    public static void openDepositAccount(Customer cust, Scanner sc) throws UnknownPersistenceException {
        String welcomeMessage = String.format("\n*** Create a new Deposit Account for %s ***\n", cust.getFirstName());
        System.out.println("*** Input Deposit Account Details ***\n\n");
        
        System.out.print("Enter your preffered 8 digit Account Number> ");
        String accNum = sc.nextLine().trim();
        System.out.println("Pick Account Type: ");
        System.out.println("1: SAVINGS");
        System.out.println("2: CURRENT");
        System.out.print("> ");
        int accType = sc.nextInt();
        System.out.print("Provide Cash Deposit> ");
        BigDecimal cashDeposit = sc.nextBigDecimal();
        DepositAccount depAccount = new DepositAccount(accNum, DepositAccountType.SAVINGS, cashDeposit, cust);
        long id = depositAccSessionBeanRemote.createNewAccount(depAccount);
        System.out.print("\nDeposit Account Created");
        System.out.print("\nDeposit Account ID = " + id);
        
    }
}
