/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package automatedtellermachineclient;

import ejb.session.stateless.AtmCardSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.DepositAccSessionBeanRemote;
import ejb.session.stateless.EmployeeSessionBeanRemote;
import entity.AtmCard;
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
    private AtmCardSessionBeanRemote atmCardSessionBeanRemote;
    private AutomatedTellerModule AutModule;
    
    private Employee currentEmployee;
    private Customer currentCustomer;
    
    public MainApp() {
    }
    
    public MainApp(DepositAccSessionBeanRemote depositAccSessionBeanRemote, CustomerSessionBeanRemote customerSessionBeanRemote, EmployeeSessionBeanRemote employeeSessionBeanRemote, AtmCardSessionBeanRemote atmCardSessionBeanRemote) {
        this.depositAccSessionBeanRemote = depositAccSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.employeeSessionBeanRemote = employeeSessionBeanRemote;
        this.atmCardSessionBeanRemote = atmCardSessionBeanRemote;
    }
        
    public void runApp() throws InvalidLoginCredentialException, UnknownPersistenceException, CustomerNotFoundException {
        Scanner sc = new Scanner(System.in);
        Integer response;

        System.out.println("*** Welcome to the Automated Teller Machine System ***\n");            
        System.out.println("1: Insert Card");
        System.out.println("2: Exit\n");
        while(true) {
            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if (response == 1) {
                insertCard(sc);
            } else if (response == 2) {
                System.out.println("Exited");
                break;
            } else {
                System.out.print("Invalid option, please try again!\n");                
            }
        }
    }
    
        
    public void insertCard(Scanner sc) throws InvalidLoginCredentialException {
        System.out.println("\n*** Please Insert your Card ***");
        System.out.println("*** Input ATM card Details ***\n");
        System.out.print("Enter ATM Card Number> ");
        String atmNum = sc.nextLine().trim();
        System.out.print("Enter PIN> ");
        String pinNum = sc.nextLine().trim();
        
        if(atmNum.length() > 0 && pinNum.length() > 0) {
            AtmCard atm = atmCardSessionBeanRemote.insertCard(atmNum, pinNum);
            AutModule = new AutomatedTellerModule(depositAccSessionBeanRemote, customerSessionBeanRemote, atmCardSessionBeanRemote, atm);
            System.out.println("*** ATM Card Credential Correct! ***");
            AutModule.insertCardPage(sc);
        } else {
            throw new InvalidLoginCredentialException("Missing login credential!");
        }
    }
}
