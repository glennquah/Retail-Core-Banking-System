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
        System.out.println("Please select the following options \n");
        System.out.println("1: Change PIN");
        System.out.println("2: Enquire Available Balance");
    }
}
