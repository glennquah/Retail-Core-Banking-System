/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.CustomerNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAccount(Customer newAccount) throws UnknownPersistenceException {
        try {
            em.persist(newAccount);
            em.flush(); 
            return newAccount.getCustomerId();
        } catch (PersistenceException exception) {
            throw new UnknownPersistenceException(exception.getMessage());
        }
    }

    @Override
    public List<Customer> retrieveAllAccounts() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }
    
    public Customer getCustomerAccount(String icNumber) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.identificationNumber = :icNum");
        query.setParameter("icNum", icNumber);
        
        try {
            return (Customer)query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new CustomerNotFoundException("Customer ID " + icNumber + "does not exist!");
        }
    }
}
