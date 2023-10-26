/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

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
public class DepositAccSessionBean implements DepositAccSessionBeanRemote, DepositAccSessionBeanLocal {

    @PersistenceContext(unitName = "RetailCoreBankingJpa-ejbPU")
    private EntityManager em;

    @Override
    public Long createNewAccount(DepositAccount newDepAcc, Long custID) throws UnknownPersistenceException {
        try {
            em.persist(newDepAcc);
            Customer cust = em.find(Customer.class, custID);
            newDepAcc.setCustomer(cust);
            List<DepositAccount> depAccList = cust.getListOfDepositAccount();
            depAccList.add(newDepAcc);
            cust.setListOfDepAccount(depAccList);
            em.flush();
            return newDepAcc.getDepositAccountId();
        } catch (PersistenceException exception) {
            throw new UnknownPersistenceException(exception.getMessage());
        }
    }
}
