package com.hongqiunicom.crm.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/18.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_card_id")
    private String customerCardId = "";

    @Column(name = "customer_name")
    private String customerName = "";

    @Column(name = "customer_telphone")
    private String customerTelphone = "";

    @Column(name = "customer_quality_voice")
    private Integer customerQualityVoice = 0;

    @Column(name = "customer_quality_data")
    private Integer customerQualityData = 0;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private Set<Broadband> broadbands = new HashSet<Broadband>();

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCardId() {
        return customerCardId;
    }

    public void setCustomerCardId(String customerCardId) {
        this.customerCardId = customerCardId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTelphone() {
        return customerTelphone;
    }

    public void setCustomerTelphone(String customerTelphone) {
        this.customerTelphone = customerTelphone;
    }

    public Set<Broadband> getBroadbands() {
        return broadbands;
    }

    public void setBroadbands(Set<Broadband> broadbands) {
        this.broadbands = broadbands;
    }


    public Integer getCustomerQualityVoice() {
        return customerQualityVoice;
    }

    public void setCustomerQualityVoice(Integer customerQualityVoice) {
        this.customerQualityVoice = customerQualityVoice;
    }

    public Integer getCustomerQualityData() {
        return customerQualityData;
    }

    public void setCustomerQualityData(Integer customerQualityData) {
        this.customerQualityData = customerQualityData;
    }

    public void toPrint(){
        System.out.println("客户序号：" + this.customerId);
        System.out.println("身份证号：" + this.customerCardId);
        System.out.println("客户姓名：" + this.customerName);
        System.out.println("客户电话：" + this.customerTelphone);
        System.out.println("客户分钟使用量：" + this.customerQualityVoice);
        System.out.println("客户数据使用量：" + this.customerQualityData);
    }
}
