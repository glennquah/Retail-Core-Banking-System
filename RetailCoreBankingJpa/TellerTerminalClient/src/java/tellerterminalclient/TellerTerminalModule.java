/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tellerterminalclient;

import ejb.session.stateless.AtmCardSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.DepositAccSessionBeanRemote;
import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class TellerTerminalModule {

    private DepositAccSessionBeanRemote depositAccSessionBeanRemote;
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private AtmCardSessionBeanRemote atmCardSessionBeanRemote;
    private long custId;
    
    public TellerTerminalModule() {
    }
    
    public TellerTerminalModule(DepositAccSessionBeanRemote depositAccSessionBeanRemote, CustomerSessionBeanRemote customerSessionBeanRemote, AtmCardSessionBeanRemote atmCardSessionBeanRemote, long custId) {
        this.depositAccSessionBeanRemote = depositAccSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.atmCardSessionBeanRemote = atmCardSessionBeanRemote;
        this.custId = custId;
    }

    public void customerLoginPage() throws UnknownPersistenceException, CustomerNotFoundException, InvalidLoginCredentialException {
            Scanner sc = new Scanner(System.in);
            Integer response;
            while(true) {
                System.out.println("Please select the following options \n");
                System.out.println("1: Open Deposit Account");
                System.out.println("2: Issue ATM Card");
                System.out.println("3: Issue Replacement ATM Card");
                System.out.println("4: back\n");

                System.out.print("> ");
                response = sc.nextInt();
                sc.nextLine();
                if(response == 1) {
                    openDepositAccount(sc);
                } else if (response == 2) {
                    issueAtmCard(sc);
                } else if (response == 3) {
                    issueReplacementCard(sc);
                } else if (response == 4) {
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
        }

    public void openDepositAccount(Scanner sc) throws UnknownPersistenceException {
        String welcomeMessage = String.format("\n*** Create a new Deposit Account for %s ***", custId);
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
        DepositAccount depAccount = new DepositAccount(accNum, DepositAccountType.SAVINGS, cashDeposit);
        System.out.println("cust ID = " + custId);
        Long depId = depositAccSessionBeanRemote.createNewAccount(depAccount, custId);
        System.out.print("\nDeposit Account Created");
        System.out.println("\nDeposit Account ID = " + depId + "\n");
    }
    
    public void issueAtmCard(Scanner sc) throws UnknownPersistenceException, CustomerNotFoundException {
        System.out.println("*** Issue new Atm Card ***");
        System.out.println("*** Input Atm Card Details ***\n");
        
        System.out.print("Enter your preffered 8 digit ATM Number> ");
        String atmNum = sc.nextLine().trim();
        System.out.print("Enter preffered Name On Card> ");
        String nameOnCard = sc.nextLine().trim();
        System.out.print("Enter 6 Digit Pin> ");
        String pin = sc.nextLine().trim();
        System.out.println("\nAccounts to be linked:\n");
        Customer cust = customerSessionBeanRemote.getListOfDepAccs(custId);
        List<DepositAccount> listOfDepositAccount = cust.getListOfDepositAccount();
        for (int i = 0; i < listOfDepositAccount.size(); i++) {
            String eachAccDetails = String.format("%s: AccountNumber: %s \n   Available Balance: %s", i+1, listOfDepositAccount.get(i).getAccountNumber(), listOfDepositAccount.get(i).getAvailableBalance());
            System.out.println(eachAccDetails);
        }
        
        System.out.print("Press Y to confirm Linking and N to restart> ");
        if (sc.nextLine().equals("Y")) {
            AtmCard atmCard = new AtmCard(atmNum, nameOnCard, true, pin);
            //add stop when they selected all
            //come up with error statement if they picked a deposit account that already has an ATM
            //System.out.println("CUST ID= " + custId);
            Long atmId = atmCardSessionBeanRemote.createAtmCard(atmCard, custId);
            System.out.println("\nLinked Successfully!");
            System.out.println("ATM Card ID = " + atmId);
            System.out.println("\n");
        } else {
            issueAtmCard(sc);
        }
    }
    
    public void issueReplacementCard(Scanner sc) throws UnknownPersistenceException, CustomerNotFoundException, InvalidLoginCredentialException {
        System.out.println("\n*** Issue Replacement Atm Card ***");
        AtmCard prevAtmC = atmCardSessionBeanRemote.getAtmCard(custId);
        System.out.print("Replace ATM Card: ");
        System.out.println(prevAtmC.getCardNumber());
        System.out.print("Name: ");
        System.out.println(prevAtmC.getNameOnCard());
        System.out.println("Press Y to confirm Replacement and N to go back> ");
        if (sc.nextLine().equals("Y")) {               
            System.out.println("*** Input New Atm Card Details ***\n");
            System.out.print("Enter your preffered 8 digit ATM Number> ");
            String atmNum = sc.nextLine().trim();
            System.out.print("Enter preffered Name On Card> ");
            String nameOnCard = sc.nextLine().trim();
            System.out.print("Enter 6 Digit Pin> ");
            String pin = sc.nextLine().trim();
            System.out.println("\nAccounts to be linked:\n");
            Customer cust = customerSessionBeanRemote.getListOfDepAccs(custId);
            List<DepositAccount> listOfDepositAccount = cust.getListOfDepositAccount();
            for (int i = 0; i < listOfDepositAccount.size(); i++) {
                String eachAccDetails = String.format("%s: AccountNumber: %s \n   Available Balance: %s", i+1, listOfDepositAccount.get(i).getAccountNumber(), listOfDepositAccount.get(i).getAvailableBalance());
                System.out.println(eachAccDetails);
            }

            System.out.print("Press Y to confirm Linking and N to restart> ");
            if (sc.nextLine().equals("Y")) {
                AtmCard atmCard = new AtmCard(atmNum, nameOnCard, true, pin);
                //add stop when they selected all
                //come up with error statement if they picked a deposit account that already has an ATM
                Long atmId = atmCardSessionBeanRemote.createAtmCard(atmCard, custId);
                System.out.println("\nLinked Successfully!");
                System.out.println("ATM Card ID = " + atmId);
                System.out.println("\n");
            } else {
                issueReplacementCard(sc);
            }
        } else {
            customerLoginPage();
        }
    }
}