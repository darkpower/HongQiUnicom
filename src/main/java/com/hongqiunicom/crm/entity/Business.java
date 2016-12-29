package com.hongqiunicom.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Darkpower on 2016/11/17.
 * <p>
 * 号卡
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "business")
public class Business implements Serializable {


    /**
     *  号卡表主键
     *  businessItemId      对应      business_item_id
     *  自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Integer businessItemId;

    /**
     *  business_item
     *  cardIccid   对应      card_iccid
     *  18位数字
     */
    @Column(name = "card_iccid")
    private String cardIccid;

    /**
     *  号卡类型
     *  cardType    对应      card_type
     *  白卡 or 橙卡
     */
    @Column(name = "card_type")
    private String cardType;

    /**
     *  卡号
     *  cardTelphoneNumber  对应      card_telphone_number
     *  号卡卡号
     */
    @Column(name ="card_telphone_number")
    private String cardTelphoneNumber;

    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @ManyToOne
    @JoinColumn(name = "card_company_id")
    private CardCompany cardCompany;


    public String getCardIccid() {
        return cardIccid;
    }

    public void setCardIccid(String cardIccid) {
        this.cardIccid = cardIccid;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardTelphoneNumber() {
        return cardTelphoneNumber;
    }

    public void setCardTelphoneNumber(String cardTelphoneNumber) {
        this.cardTelphoneNumber = cardTelphoneNumber;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public CardCompany getCardCompany() {
        return cardCompany;
    }

    public void setCardCompany(CardCompany cardCompany) {
        this.cardCompany = cardCompany;
    }
}
