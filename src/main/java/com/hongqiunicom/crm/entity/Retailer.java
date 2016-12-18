package com.hongqiunicom.crm.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/12/16.
 * <p>
 * 代理商
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "retailer")
public class Retailer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retailer_id")
    private Integer retailerId;

    @Column(name = "retailer_name")
    private String retailerName;

    @Column(name = "retailer_address")
    private String retailerAddress;

    @Column(name = "retailer_account")
    private String retailerAccount;

    @Column(name = "retailer_code")
    private String retailerCode;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "retailer_id")
    private Set<Card> cards = new HashSet<Card>();

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getRetailerAddress() {
        return retailerAddress;
    }

    public void setRetailerAddress(String retailerAddress) {
        this.retailerAddress = retailerAddress;
    }

    public String getRetailerAccount() {
        return retailerAccount;
    }

    public void setRetailerAccount(String retailerAccount) {
        this.retailerAccount = retailerAccount;
    }

    public String getRetailerCode() {
        return retailerCode;
    }

    public void setRetailerCode(String retailerCode) {
        this.retailerCode = retailerCode;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}
