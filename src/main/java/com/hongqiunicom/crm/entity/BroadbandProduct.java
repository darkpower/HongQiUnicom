package com.hongqiunicom.crm.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/12/20.
 *
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "broadband_product")
public class BroadbandProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadband_product_id")
    private Integer broadbandProductId;

    @Column(name = "broadband_product_name")
    private String broadbandProductName;

    @Column(name = "broadband_product_length")
    private String broadbandProductLength;

    @Column(name = "broadband_product_deposit")
    private Integer broadbandProductDeposit;

    @Column(name = "broadband_product_monthly")
    private Double broadbandProductMonthly;

    @OneToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "broadband_product_id")
    private Set<Broadband> broadbands = new HashSet<Broadband>();

    public Integer getBroadbandProductId() {
        return broadbandProductId;
    }

    public void setBroadbandProductId(Integer broadbandProductId) {
        this.broadbandProductId = broadbandProductId;
    }

    public String getBroadbandProductName() {
        return broadbandProductName;
    }

    public void setBroadbandProductName(String broadbandProductName) {
        this.broadbandProductName = broadbandProductName;
    }

    public String getBroadbandProductLength() {
        return broadbandProductLength;
    }

    public void setBroadbandProductLength(String broadbandProductLength) {
        this.broadbandProductLength = broadbandProductLength;
    }

    public Integer getBroadbandProductDeposit() {
        return broadbandProductDeposit;
    }

    public void setBroadbandProductDeposit(Integer broadbandProductDeposit) {
        this.broadbandProductDeposit = broadbandProductDeposit;
    }

    public Double getBroadbandProductMonthly() {
        return broadbandProductMonthly;
    }

    public void setBroadbandProductMonthly(Double broadbandProductMonthly) {
        this.broadbandProductMonthly = broadbandProductMonthly;
    }

    public Set<Broadband> getBroadbands() {
        return broadbands;
    }

    public void setBroadbands(Set<Broadband> broadbands) {
        this.broadbands = broadbands;
    }


}
