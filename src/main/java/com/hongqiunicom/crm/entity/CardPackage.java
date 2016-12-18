package com.hongqiunicom.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Darkpower on 2016/11/17.
 * <p>
 * 号卡套餐
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "card_package")
public class CardPackage implements Serializable {

    /**
     *   套餐表主键
     *  cardPackageId      对应      card_package_id
     *  自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_package_id")
    private Integer cardPackageId;


    /**
     *  号卡卡品
     *  cardPackage 对应      card_package
     *  号卡套餐
     */
    @Column(name = "card_package_name")
    private String cardPackageName;

    /**
     *  卡费
     *  cardCost    对应      card_cost
     *  开卡所用费用
     */
    @Column(name = "card_cost")
    private Integer cardCost;

    /**
     *  运营商
     *  cardCompany 对应 card_company_id
     */
    @ManyToOne
    @JoinColumn(name = "card_company_id")
    private CardCompany cardCompany;

    public Integer getCardPackageId() {
        return cardPackageId;
    }

    public void setCardPackageId(Integer cardPackageId) {
        this.cardPackageId = cardPackageId;
    }

    public String getCardPackageName() {
        return cardPackageName;
    }

    public void setCardPackageName(String cardPackageName) {
        this.cardPackageName = cardPackageName;
    }

    public Integer getCardCost() {
        return cardCost;
    }

    public void setCardCost(Integer cardCost) {
        this.cardCost = cardCost;
    }

    public CardCompany getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(CardCompany cardCompany) {
        this.cardCompany = cardCompany;
    }
}
