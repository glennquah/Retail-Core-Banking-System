/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retailcorebankingjpaclient;

import ejb.session.stateless.DepositAccSessionBeanRemote;
import entity.Customer;
import entity.DepositAccount;
import java.math.BigDecimal;
import java.util.Scanner;
import javax.ejb.EJB;
import util.enumeration.DepositAccountType;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
public class CustomerOperationalModule {

    private DepositAccSessionBeanRemote depositAccSessionBeanRemote;
    private Customer currentCustomer;
    
    public CustomerOperationalModule() {
    }
    
    public CustomerOperationalModule(DepositAccSessionBeanRemote depositAccSessionBeanRemote, Customer currCustomer) {
        this.depositAccSessionBeanRemote = depositAccSessionBeanRemote;
        this.currentCustomer = currentCustomer;
    }

    public void customerLoginPage() throws UnknownPersistenceException {
            Scanner sc = new Scanner(System.in);
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
                    openDepositAccount(currentCustomer, sc);
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

    public void openDepositAccount(Customer cust, Scanner sc) throws UnknownPersistenceException {
        String welcomeMessage = String.format("\n*** Create a new Deposit Account for %s ***", cust.getFirstName());
        System.out.println(welcomeMessage);
        System.out.println("*** Input Deposit Account Details ***\n");
        
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
        if (depAccount == null) {
            System.out.println("depacc is null");
        }
        if (cust == null) {
            System.out.println("cust is unll");
        }
        long id = depositAccSessionBeanRemote.createNewAccount(depAccount, cust);
        System.out.print("\nDeposit Account Created");
        System.out.print("\nDeposit Account ID = " + id);
        
    }
}