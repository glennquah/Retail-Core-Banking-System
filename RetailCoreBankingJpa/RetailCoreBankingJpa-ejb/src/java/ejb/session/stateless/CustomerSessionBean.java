/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lenovo
 */
@Stateless
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAccount(Customer newAccount) {
        em.persist(newAccount);
        em.flush(); 
        return newAccount.getCustomerId();
    }

    @Override
    public List<Customer> retrieveAllAccounts() {
        //Whatever JPQL Statement u want
        Query query = em.createQuery("SELECT c FROM Customer c");
        return query.getResultList();
    }
}
