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
@Table(name = "business_type")
public class BusinessType implements Serializable {

    /**
     *   业务受理类型主键
     *  businessTypeId      对应      business_type_id
     *  自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_type_id")
    private Integer businessTypeId;


    @Column(name = "business_type_name")
    private String businessTypeName;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "business_type_id")
    private Set<Card> Businesses = new HashSet<Card>();

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public Set<Card> getBusinesses() {
        return Businesses;
    }

    public void setBusinesses(Set<Card> businesses) {
        Businesses = businesses;
    }
}
