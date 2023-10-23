/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import util.enumeration.DepositAccountType;

/**
 *
 * @author Lenovo
 */
@Entity
public class DepositAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositAccountId;
    @Column(unique = true)
    private String accountNumber;
    private DepositAccountType accountType;
    @Column(precision = 11, scale = 2)
    private BigDecimal availableBalance;
    @Column(precision = 11, scale = 2)
    private BigDecimal holdBalance;
    @Column(precision = 11, scale = 2)
    private BigDecimal ledgerBalance;
    private boolean enabled;
    
    @ManyToOne
    private Customer customer;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private AtmCard atmCard;
    @OneToMany(mappedBy="DepositAccount")
    private List<DepositAccountTransaction> listOfTransactions;

    public DepositAccount() {
    }

    public DepositAccount(String accountNumber, DepositAccountType accountType, BigDecimal availableBalance, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.availableBalance = availableBalance;
        this.holdBalance = availableBalance;
        this.ledgerBalance = availableBalance;
        this.enabled = true;
        this.atmCard = null;
        this.customer = customer;
        this.listOfTransactions = null;
    }
    
    public void setCustomer(Customer cust) {
        this.customer = cust;
    }
    
    public Long getDepositAccountId() {
        return depositAccountId;
    }

    public void setDepositAccountId(Long depositAccountId) {
        this.depositAccountId = depositAccountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depositAccountId != null ? depositAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the depositAccountId fields are not set
        if (!(object instanceof DepositAccount)) {
            return false;
        }
        DepositAccount other = (DepositAccount) object;
        if ((this.depositAccountId == null && other.depositAccountId != null) || (this.depositAccountId != null && !this.depositAccountId.equals(other.depositAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DepositAccount[ id=" + depositAccountId + " ]";
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the accountType
     */
    public DepositAccountType getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(DepositAccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the availableBalance
     */
    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    /**
     * @param availableBalance the availableBalance to set
     */
    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    /**
     * @return the holdBalance
     */
    public BigDecimal getHoldBalance() {
        return holdBalance;
    }

    /**
     * @param holdBalance the holdBalance to set
     */
    public void setHoldBalance(BigDecimal holdBalance) {
        this.holdBalance = holdBalance;
    }

    /**
     * @return the ledgerBalance
     */
    public BigDecimal getLedgerBalance() {
        return ledgerBalance;
    }

    /**
     * @param ledgerBalance the ledgerBalance to set
     */
    public void setLedgerBalance(BigDecimal ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
}
