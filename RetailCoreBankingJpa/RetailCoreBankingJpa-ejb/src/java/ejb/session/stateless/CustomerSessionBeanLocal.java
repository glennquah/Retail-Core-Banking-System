/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Local;
import util.exception.CustomerNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Local
public interface CustomerSessionBeanLocal {
    public Long createNewAccount(Customer newAccount) throws UnknownPersistenceException;
    public List<Customer> retrieveAllAccounts();
    public Customer getCustomerAccount(String icNumber) throws CustomerNotFoundException;
    public Customer getListOfDepAccs(Long custID) throws CustomerNotFoundException;
}
