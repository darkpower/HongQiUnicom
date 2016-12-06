package com.hongqiunicom.crm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Darkpower on 2016/11/18.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "remark")
public class Remark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remark_id")
    private Integer remarkId;

    @ManyToOne
    @JoinColumn(name = "broadband_id")
    private Broadband broadband;

    @Column(name = "remark_date")
    @Temporal(TemporalType.DATE)
    private Date remarkDate;

    @Column(name = "remark_content")
    private String remarkContent;


    public Integer getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(Integer remarkId) {
        this.remarkId = remarkId;
    }

    public Broadband getBroadband() {
        return broadband;
    }

    public void setBroadband(Broadband broadband) {
        this.broadband = broadband;
    }

    public Date getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(Date remarkDate) {
        this.remarkDate = remarkDate;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }
}
