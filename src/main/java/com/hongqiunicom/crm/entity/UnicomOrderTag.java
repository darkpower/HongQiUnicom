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
@Table(name = "unicom_order_tag")
public class UnicomOrderTag implements Serializable  {

    /**
     * 业务受理类型主键
     * businessTypeId      对应      business_type_id
     * 自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unicom_order_tag_id")
    private Integer unicomOrderTagId;


    @Column(name = "unicom_order_type_name")
    private String unicomOrderTagName;

    @Column(name = "unicom_order_type_state")
    private Integer unicomOrderTagState;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "unicom_order_tag_id")
    @JSONField(serialize=false)
    private Set<UnicomOrder> unicomOrders = new HashSet<UnicomOrder>();


    public Integer getUnicomOrderTagId() {
        return unicomOrderTagId;
    }

    public void setUnicomOrderTagId(Integer unicomOrderTagId) {
        this.unicomOrderTagId = unicomOrderTagId;
    }

    public String getUnicomOrderTagName() {
        return unicomOrderTagName;
    }

    public void setUnicomOrderTagName(String unicomOrderTagName) {
        this.unicomOrderTagName = unicomOrderTagName;
    }

    public Integer getUnicomOrderTagState() {
        return unicomOrderTagState;
    }

    public void setUnicomOrderTagState(Integer unicomOrderTagState) {
        this.unicomOrderTagState = unicomOrderTagState;
    }

    public Set<UnicomOrder> getUnicomOrders() {
        return unicomOrders;
    }

    public void setUnicomOrders(Set<UnicomOrder> unicomOrders) {
        this.unicomOrders = unicomOrders;
    }
}
