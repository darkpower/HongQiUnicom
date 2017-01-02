package com.hongqiunicom.crm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Darkpower on 2016/11/18.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "staff")
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "staff_name")
    private String staffName;

    /**
     * 1 = 正常,  2 = 离职
     */
    @Column(name = "staff_state")
    private Integer staffState;

    @OneToMany
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name = "staff_id")
    @JSONField(serialize=false)
    private Set<UnicomOrder> unicomOrders = new HashSet<UnicomOrder>();



    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


    public Integer getStaffState() {
        return staffState;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }

    public Set<UnicomOrder> getUnicomOrders() {
        return unicomOrders;
    }

    public void setUnicomOrders(Set<UnicomOrder> unicomOrders) {
        this.unicomOrders = unicomOrders;
    }
}
