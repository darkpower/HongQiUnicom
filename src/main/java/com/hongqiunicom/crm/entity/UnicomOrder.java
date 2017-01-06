package com.hongqiunicom.crm.entity;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/17.
 * <p>
 * 号卡
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "unicom_order")
public class UnicomOrder implements Serializable {

    /**
     * 业务受理类型主键
     * businessTypeId      对应      business_type_id
     * 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unicom_order_id")
    private Integer unicomOrderId;


    @Column(name = "unicom_order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unicomOrderDate;

    /**
     * 业务受理状态
     * <p>
     * 1 = 未完工  2 = 已完工 3 = 有问题
     */
    @Column(name = "unicom_order_state")
    private Integer unicomOrderState;

    /**
     * 业务验收状态
     */
    @Column(name = "unicom_order_verify")
    private Integer unicomOrderVerify;

    /**
     * 业务受理续费标识
     */
    @ManyToOne
    @JoinColumn(name = "unicom_order_tag_id")
    private UnicomOrderTag unicomOrderTag;

    @ManyToOne
    @JoinColumn(name = "unicom_order_type_id")
    private UnicomOrderType unicomOrderType;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "unicom_order_id")
    private Set<Business> businesses = new HashSet<>();


    public Date getUnicomOrderDate() {
        return unicomOrderDate;
    }

    public void setUnicomOrderDate(Date unicomOrderDate) {
        this.unicomOrderDate = unicomOrderDate;
    }


    public UnicomOrderType getUnicomOrderType() {
        return unicomOrderType;
    }

    public void setUnicomOrderType(UnicomOrderType unicomOrderType) {
        this.unicomOrderType = unicomOrderType;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Set<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        this.businesses = businesses;
    }

    public Integer getUnicomOrderId() {
        return unicomOrderId;
    }

    public void setUnicomOrderId(Integer unicomOrderId) {
        this.unicomOrderId = unicomOrderId;
    }

    public Integer getUnicomOrderState() {
        return unicomOrderState;
    }

    public void setUnicomOrderState(Integer unicomOrderState) {
        this.unicomOrderState = unicomOrderState;
    }


    public UnicomOrderTag getUnicomOrderTag() {
        return unicomOrderTag;
    }

    public void setUnicomOrderTag(UnicomOrderTag unicomOrderTag) {
        this.unicomOrderTag = unicomOrderTag;
    }

    public Integer getUnicomOrderVerify() {
        return unicomOrderVerify;
    }

    public void setUnicomOrderVerify(Integer unicomOrderVerify) {
        this.unicomOrderVerify = unicomOrderVerify;
    }
}
