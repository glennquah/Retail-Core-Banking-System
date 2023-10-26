/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Remote;
import util.exception.CustomerNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface CustomerSessionBeanRemote {
    public Long createNewAccount(Customer newAccount) throws UnknownPersistenceException;
    public List<Customer> retrieveAllAccounts();
    public Long getCustomerAccount(String icNumber) throws CustomerNotFoundException;
    public Customer getListOfDepAccs(Long custID) throws CustomerNotFoundException;
}
