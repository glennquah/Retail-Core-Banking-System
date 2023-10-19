/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package retailcorebankingjpaclient;

import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.Employee;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Lenovo
 */
public class Main {

    @EJB(name = "EmployeeSessionBeanRemote")
    private static EmployeeSessionBeanRemote employeeSessionBeanRemote;

    
    public static void main(String[] args) throws InvalidLoginCredentialException {
        runApp(); 
    }
    
    public static void runApp() throws InvalidLoginCredentialException {
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
    
    public static void login(Scanner sc) throws InvalidLoginCredentialException {
        System.out.println("*** Retail Core Banking System :: Login ***\n");
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
    
    public static void loginPage(Scanner sc, Employee emp) {
        String welcomeMessage = String.format("*** Welcome %s to Teller Terminal ***\n\n", emp.getFirstName());
        System.out.println(welcomeMessage);
        System.out.println("1: Create a Customer");
        System.out.println("2: Open Deposit Account");
        System.out.println("3: Issue ATM Card");
        System.out.println("4: Issue Replacement ATM Card");
        System.out.println("5: Insert ATM Card");
        System.out.println("6: Change PIN");
        System.out.println("7: Enquire Available Balance");
        System.out.println("8: Log Out\n");
        
        Integer response;
        while(true) {
            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                createACustomer(sc);
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
                System.out.println("7");
            } else if (response == 8) {
                break;
            } else {
                System.out.println("Invalid option, please try again!\n");
            }
        }
    }
    
    public static void createACustomer(Scanner sc) {
        System.out.println("*** Create A Customer ***");
        System.out.println("*** Input Customer Details ***\n\n");
        
        System.out.print("Enter Customer First Name> ");
        String username = sc.nextLine().trim();
        System.out.print("Enter Customer Last Name> ");
        String password = sc.nextLine().trim();
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
        
    }
}
