package com.hongqiunicom.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Darkpower on 2016/11/17.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mobile")
public class Mobile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobile_id")
    private Integer mobileId;

    @ManyToOne
    @JoinColumn(name = "mobile_model_id")
    private MobileModel mobileModel;


    public Integer getMobileId() {
        return mobileId;
    }

    public void setMobileId(Integer mobileId) {
        this.mobileId = mobileId;
    }

    public MobileModel getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(MobileModel mobileModel) {
        this.mobileModel = mobileModel;
    }
}
