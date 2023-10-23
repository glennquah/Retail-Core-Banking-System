/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package ejb.session.stateless;

import entity.AtmCard;
import javax.ejb.Remote;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Lenovo
 */
@Remote
public interface AtmCardSessionBeanRemote {
    public Long createAtmCard(AtmCard newAtmCard, Long custID) throws UnknownPersistenceException;
}
