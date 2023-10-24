/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retailcorebankingjpaclient;

import ejb.session.stateless.AtmCardSessionBeanRemote;
import ejb.session.stateless.CustomerSessionBeanRemote;
import ejb.session.stateless.DepositAccSessionBeanRemote;
import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class AutomatedTellerModule {
    
    private DepositAccSessionBeanRemote depositAccSessionBeanRemote;
    private CustomerSessionBeanRemote customerSessionBeanRemote;
    private AtmCardSessionBeanRemote atmCardSessionBeanRemote;
    private AtmCard atmCard;
    
    public AutomatedTellerModule() {
    }
    
    public AutomatedTellerModule(DepositAccSessionBeanRemote depositAccSessionBeanRemote, CustomerSessionBeanRemote customerSessionBeanRemote, AtmCardSessionBeanRemote atmCardSessionBeanRemote, AtmCard atmCard) {
        this.depositAccSessionBeanRemote = depositAccSessionBeanRemote;
        this.customerSessionBeanRemote = customerSessionBeanRemote;
        this.atmCardSessionBeanRemote = atmCardSessionBeanRemote;
        this.atmCard = atmCard;
    }
    
    public void insertCardPage(Scanner sc) {
        System.out.println("\nPlease select the following options \n");
        System.out.println("1: Change PIN");
        System.out.println("2: Enquire Available Balance");
        System.out.println("3: Log Out");
        Integer response;
        while(true) {
            System.out.print("> ");
            response = sc.nextInt();
            sc.nextLine();
            if(response == 1) {
                System.out.println("Change Pin");
            } else if (response == 2) {
                enquireBalance(sc);
            } else if (response == 3) {
                System.out.println("Exited");
                break;
            } else {
                System.out.print("Invalid option, please try again!\n");                
            }
        }
    }
    
    public void enquireBalance(Scanner sc) {
        String welcomeMessage = String.format("\n*** Hello %s, here is your Balance Breakdown ***\n", this.atmCard.getNameOnCard());
        System.out.println(welcomeMessage);
        List<DepositAccount> listOfDepositAccounts = atmCardSessionBeanRemote.getListOfDepositAccounts(this.atmCard);
        BigDecimal totalAvailBal = BigDecimal.ZERO;
        BigDecimal totalHoldBal = BigDecimal.ZERO;
        BigDecimal totalLedgerBal = BigDecimal.ZERO;
        
        for (int i = 0; i < listOfDepositAccounts.size(); i++) {
            System.out.println("Account Numer: " + listOfDepositAccounts.get(i).getAccountNumber());
            System.out.println("Available Balance= $" + listOfDepositAccounts.get(i).getAvailableBalance());
            totalAvailBal = totalAvailBal.add(listOfDepositAccounts.get(i).getAvailableBalance());
            System.out.println("Hold      Balance= $" + listOfDepositAccounts.get(i).getHoldBalance());
            totalHoldBal = totalHoldBal.add(listOfDepositAccounts.get(i).getHoldBalance());
            System.out.println("Ledger    Balance= $" + listOfDepositAccounts.get(i).getLedgerBalance());
            totalLedgerBal = totalLedgerBal.add(listOfDepositAccounts.get(i).getLedgerBalance());
            System.out.println("");
        }
        
        System.out.println("Total Available Balance= $" + totalAvailBal);
        System.out.println("Total Hold      Balance= $" + totalHoldBal);
        System.out.println("Total Ledger    Balance= $" + totalLedgerBal);
    }
}
