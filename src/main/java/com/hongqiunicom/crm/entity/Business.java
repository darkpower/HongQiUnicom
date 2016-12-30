package com.hongqiunicom.crm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
     *  businessId      对应      business_id
     *  自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_id")
    private Integer businessId;

    /**
     *  业务受理时间
     *  businessDate   对应      business_date
     *  受理时间
     */
    @Column(name = "business_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date businessDate;

    /**
     *  业务类型
     *  businessType    对应      business_type
     *  宽带 or 号卡
     */
    @ManyToOne
    @JoinColumn(name = "business_type_id")
    private BusinessType businessType;

    /**
     *  业务受理账号
     *  businessAccount  对应      business_account
     *  宽带账号 or 号卡卡号
     */
    @Column(name ="business_account")
    private String businessAccount;

    /**
     *  业务受理费用
     *  businessPrice   对应      business_price
     */
    @Column(name = "business_cost")
    private Double businessCost;

    /**
     *  业务受理状态
     *  businessState   对应      business_state
     *  1 = 系统抽取，   2 = 核实
     */
    @Column(name = "business_state")
    private Integer businessState;


    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBusinessAccount() {
        return businessAccount;
    }

    public void setBusinessAccount(String businessAccount) {
        this.businessAccount = businessAccount;
    }

    public Double getBusinessCost() {
        return businessCost;
    }

    public void setBusinessCost(Double businessCost) {
        this.businessCost = businessCost;
    }

    public Integer getBusinessState() {
        return businessState;
    }

    public void setBusinessState(Integer businessState) {
        this.businessState = businessState;
    }
}
