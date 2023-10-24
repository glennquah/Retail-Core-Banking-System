/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import entity.DepositAccount;
import java.util.List;
import javax.ejb.Local;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Local
public interface AtmCardSessionBeanLocal {
    public Long createAtmCard(AtmCard newAtmCard, Long custID) throws UnknownPersistenceException;
    public AtmCard getAtmCard(Long custID) throws UnknownPersistenceException;
    public AtmCard retrieveAtmCardByAtmNum(String atmNum);
    public AtmCard insertCard(String atmNum, String pinNum) throws InvalidLoginCredentialException;
}
