/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Lenovo
 */
@Entity
public class AtmCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmCardId;
    @Column(unique = true)
    private String cardNumber;
    private String nameOnCard;
    private Boolean enabled;
    private String pin;
    
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Customer customer;
    
    @OneToMany(mappedBy="AtmCard")
    private List<DepositAccount> listOfDepositAccount;

    public AtmCard() {
    }

    public AtmCard(String cardNumber, String nameOnCard, Boolean enabled, String pin, Customer customer) {
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.enabled = enabled;
        this.pin = pin;
        this.customer = customer;
        this.listOfDepositAccount = null;
    }
    
    public void setListOfDepositAccount(List<DepositAccount> depAccs) {
        this.listOfDepositAccount = depAccs;
    }

    public List<DepositAccount> getListOfDepositAccount() {
        return listOfDepositAccount;
    }
    
    public Long getAtmCardId() {
        return atmCardId;
    }

    public void setAtmCardId(Long atmCardId) {
        this.atmCardId = atmCardId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atmCardId != null ? atmCardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the atmCardId fields are not set
        if (!(object instanceof AtmCard)) {
            return false;
        }
        AtmCard other = (AtmCard) object;
        if ((this.atmCardId == null && other.atmCardId != null) || (this.atmCardId != null && !this.atmCardId.equals(other.atmCardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AtmCard[ id=" + atmCardId + " ]";
    }

    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the nameOnCard
     */
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
     * @param nameOnCard the nameOnCard to set
     */
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}
