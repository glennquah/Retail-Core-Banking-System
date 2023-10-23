/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.DepositAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class DepositAccSessionBean implements DepositAccSessionBeanRemote, DepositAccSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAccount(DepositAccount newDepAcc, Customer cust) throws UnknownPersistenceException {
        try {
            newDepAcc.setCustomer(cust);
            //cust.getListOfDepositAccount().add(newDepAcc);
            em.persist(newDepAcc);
            em.flush(); 
            return newDepAcc.getDepositAccountId();
        } catch (PersistenceException exception) {
            throw new UnknownPersistenceException(exception.getMessage());
        }
    }
    
}
