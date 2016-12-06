package com.hongqiunicom.crm.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/17.
 *
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "broadband")
public class Broadband implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadband_id")
    private Integer broadbandId;

    @Column(name = "broadband_account", length = 255, nullable = false)
    private String broadbandAccount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "broadband_expire_date")
    @Temporal(TemporalType.DATE)
    private Date broadbandExpireDate;

    @Column(name = "broadband_renewal_date")
    @Temporal(TemporalType.DATE)
    private Date broadbandRenewalDate;

    @Column(name = "broadband_price")
    private String broadbandPrice;

    @Column(name = "broadband_system_type")
    private String broadbandSystemType;

    @Column(name = "broadband_state")
    private String broadbandState;

    @Column(name = "broadband_xufei_state")
    private String broadbandXuFeiState;

    @OneToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "broadband_id")
    private Set<Remark> remasks = new HashSet<Remark>();

    public Integer getBroadbandId() {
        return broadbandId;
    }

    public void setBroadbandId(Integer broadbandId) {
        this.broadbandId = broadbandId;
    }

    public String getBroadbandAccount() {
        return broadbandAccount;
    }

    public void setBroadbandAccount(String broadbandAccount) {
        this.broadbandAccount = broadbandAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getBroadbandExpireDate() {
        return broadbandExpireDate;
    }

    public void setBroadbandExpireDate(Date broadbandExpireDate) {
        this.broadbandExpireDate = broadbandExpireDate;
    }

    public Date getBroadbandRenewalDate() {
        return broadbandRenewalDate;
    }

    public void setBroadbandRenewalDate(Date broadbandRenewalDate) {
        this.broadbandRenewalDate = broadbandRenewalDate;
    }

    public String getBroadbandPrice() {
        return broadbandPrice;
    }

    public void setBroadbandPrice(String broadbandPrice) {
        this.broadbandPrice = broadbandPrice;
    }

    public String getBroadbandSystemType() {
        return broadbandSystemType;
    }

    public void setBroadbandSystemType(String broadbandSystemType) {
        this.broadbandSystemType = broadbandSystemType;
    }

    public String getBroadbandState() {
        return broadbandState;
    }

    public void setBroadbandState(String broadbandState) {
        this.broadbandState = broadbandState;
    }

    public String getBroadbandXuFeiState() {
        return broadbandXuFeiState;
    }

    public void setBroadbandXuFeiState(String broadbandXuFeiState) {
        this.broadbandXuFeiState = broadbandXuFeiState;
    }

    public Set<Remark> getRemasks() {
        return remasks;
    }

    public void setRemasks(Set<Remark> remasks) {
        this.remasks = remasks;
    }

    public void toPrint(){
        System.out.println("宽带序号：" + this.broadbandId);
        System.out.println("宽带账号：" + this.broadbandAccount);
        System.out.println("宽带到期时间：" + this.broadbandExpireDate);
        System.out.println("宽带续费时间：" + this.broadbandRenewalDate);
        System.out.println("宽带价格：" + this.broadbandPrice);
        System.out.println("宽带系统标识：" + this.broadbandSystemType);
        System.out.println("宽带状态：" + this.broadbandState);
        System.out.println("宽带续费状态：" + this.broadbandXuFeiState);
    }

}
