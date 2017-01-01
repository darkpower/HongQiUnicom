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
     *   业务受理类型主键
     *  businessTypeId      对应      business_type_id
     *  自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unicom_order_id")
    private Integer unicomOrderId;


    @Column(name = "unicom_order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unicomOrderDate;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "unicom_order_id")
    private Set<Business> Businesses = new HashSet<Business>();


    public Integer getUnicomOrderId() {
        return unicomOrderId;
    }

    public void setUnicomOrderId(Integer unicomOrderId) {
        this.unicomOrderId = unicomOrderId;
    }

    public Date getUnicomOrderDate() {
        return unicomOrderDate;
    }

    public void setUnicomOrderDate(Date unicomOrderDate) {
        this.unicomOrderDate = unicomOrderDate;
    }

    public Set<Business> getBusinesses() {
        return Businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        Businesses = businesses;
    }
}
