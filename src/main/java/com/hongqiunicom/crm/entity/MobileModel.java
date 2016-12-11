package com.hongqiunicom.crm.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/17.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mobile_model")
public class MobileModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobile_model_id")
    private Integer mobileModelId;

    @Column(name = "mobile_model_name")
    private String mobileModelName;

    @ManyToOne
    @JoinColumn(name = "mobile_brand_id")
    private MobileBrand mobileBrand;

    @OneToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "mobile_model_id")
    private Set<Mobile> mobiles = new HashSet<Mobile>();


    public Integer getMobileModelId() {
        return mobileModelId;
    }

    public void setMobileModelId(Integer mobileModelId) {
        this.mobileModelId = mobileModelId;
    }

    public String getMobileModelName() {
        return mobileModelName;
    }

    public void setMobileModelName(String mobileModelName) {
        this.mobileModelName = mobileModelName;
    }


    public MobileBrand getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(MobileBrand mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public Set<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
    }
}
