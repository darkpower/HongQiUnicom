package com.hongqiunicom.crm.entity;


import com.alibaba.fastjson.annotation.JSONField;
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
@Table(name = "unicom_order_type")
public class UnicomOrderType  implements Serializable  {

    /**
     * 业务受理类型主键
     * businessTypeId      对应      business_type_id
     * 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unicom_order_type_id")
    private Integer unicomOrderTypeId;


    @Column(name = "unicom_order_type_name")
    private String unicomOrderTypeName;

    @Column(name = "unicom_order_type_state")
    private Integer unicomOrderTypeState;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "unicom_order_type_id")
    @JSONField(serialize=false)
    private Set<UnicomOrder> unicomOrders = new HashSet<UnicomOrder>();


    public Integer getUnicomOrderTypeId() {
        return unicomOrderTypeId;
    }

    public void setUnicomOrderTypeId(Integer unicomOrderTypeId) {
        this.unicomOrderTypeId = unicomOrderTypeId;
    }


    public Set<UnicomOrder> getUnicomOrders() {
        return unicomOrders;
    }

    public void setUnicomOrders(Set<UnicomOrder> unicomOrders) {
        this.unicomOrders = unicomOrders;
    }

    public String getUnicomOrderTypeName() {
        return unicomOrderTypeName;
    }

    public void setUnicomOrderTypeName(String unicomOrderTypeName) {
        this.unicomOrderTypeName = unicomOrderTypeName;
    }

    public Integer getUnicomOrderTypeState() {
        return unicomOrderTypeState;
    }

    public void setUnicomOrderTypeState(Integer unicomOrderTypeState) {
        this.unicomOrderTypeState = unicomOrderTypeState;
    }
}
