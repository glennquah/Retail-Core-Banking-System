/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Customer;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Local;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Local
public interface DepositAccSessionBeanLocal {
    public Long createNewAccount(DepositAccount newDepAcc, Long custID) throws UnknownPersistenceException;
}
