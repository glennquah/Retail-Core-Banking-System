/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.DepositAccount;
import javax.ejb.Remote;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface DepositAccSessionBeanRemote {
    public Long createNewAccount(DepositAccount newDepAcc) throws UnknownPersistenceException;
}
