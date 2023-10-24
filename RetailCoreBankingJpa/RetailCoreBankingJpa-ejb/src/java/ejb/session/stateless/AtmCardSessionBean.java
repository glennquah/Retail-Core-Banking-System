/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.Customer;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Stateless
public class AtmCardSessionBean implements AtmCardSessionBeanRemote, AtmCardSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createAtmCard(AtmCard newAtmCard, Long custID) throws UnknownPersistenceException {
        try {
            em.persist(newAtmCard);
            em.flush(); 
            
            Customer cust = em.find(Customer.class, custID);
            //to get list of deposit accounts (LAZY LOADING)
            Query query = em.createQuery("SELECT c FROM DepositAccount c WHERE c.customer = :customer");
            query.setParameter("customer", cust);
            List<DepositAccount> listOfDepositAccounts = (List<DepositAccount>)query.getResultList();
            
            //to replace old card
            if (cust.getAtmCard() != null) {
                em.remove(cust.getAtmCard());
            }
            
            //to set each dep accounts atm card
            for (int i = 0; i < listOfDepositAccounts.size(); i++) {
                listOfDepositAccounts.get(i).setAtmCard(newAtmCard);
            }
            
            //set customer to the new atm card
            cust.setAtmCard(newAtmCard);
            return newAtmCard.getAtmCardId();
        } catch (PersistenceException exception) {
            throw new UnknownPersistenceException(exception.getMessage());
        }
    }
    
}
