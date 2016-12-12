package com.hongqiunicom.crm.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/17.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mobile_brand")
public class MobileBrand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobile_brand_id")
    private Integer mobileBrandId;

    @Column(name = "mobile_brand_name")
    private String mobileBrandName;

    @OneToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "mobile_brand_id")
    private Set<MobileModel> mobileModels = new HashSet<MobileModel>();


    public Integer getMobileBrandId() {
        return mobileBrandId;
    }

    public void setMobileBrandId(Integer mobileBrandId) {
        this.mobileBrandId = mobileBrandId;
    }

    public String getMobileBrandName() {
        return mobileBrandName;
    }

    public void setMobileBrandName(String mobileBrandName) {
        this.mobileBrandName = mobileBrandName;
    }

    public Set<MobileModel> getMobileModels() {
        return mobileModels;
    }

    public void setMobileModels(Set<MobileModel> mobileModels) {
        this.mobileModels = mobileModels;
    }
}
