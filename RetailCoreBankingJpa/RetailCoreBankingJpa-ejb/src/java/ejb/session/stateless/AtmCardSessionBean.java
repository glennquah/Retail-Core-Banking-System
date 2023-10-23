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
            List<DepositAccount> depAccs = cust.getListOfDepositAccount();
            cust.setAtmCard(newAtmCard);      
            return newAtmCard.getAtmCardId();
        } catch (PersistenceException exception) {
            throw new UnknownPersistenceException(exception.getMessage());
        }
    }
    
}
