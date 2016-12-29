package com.hongqiunicom.crm.entity;



import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/17.
 * <p>
 * 号卡
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "card_company")
public class CardCompany implements Serializable {

    /**
     *   运营商表主键
     *  cardPackageId      对应      card_package_id
     *  自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_company_id")
    private Integer cardCompanyId;

    @Column(name = "card_company_name")
    private String cardCompanyName;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "card_company_id")
    private Set<Card> cards = new HashSet<Card>();

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "card_company_id")
    private Set<CardPackage> cardPackages = new HashSet<CardPackage>();


    public Integer getCardCompanyId() {
        return cardCompanyId;
    }

    public void setCardCompanyId(Integer cardCompanyId) {
        this.cardCompanyId = cardCompanyId;
    }

    public String getCardCompanyName() {
        return cardCompanyName;
    }

    public void setCardCompanyName(String cardCompanyName) {
        this.cardCompanyName = cardCompanyName;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<CardPackage> getCardPackages() {
        return cardPackages;
    }

    public void setCardPackages(Set<CardPackage> cardPackages) {
        this.cardPackages = cardPackages;
    }
}
