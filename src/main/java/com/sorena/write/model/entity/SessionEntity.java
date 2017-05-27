/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.model.entity;

import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author sorena
 */
@Embedded
public class SessionEntity {

    private String sessionName;
    private String sessionText;
    private Date createDate;
    @Reference
    private AccountEntity creator;
    @Embedded
    private List<RateEntity> rates;

    public List<RateEntity> getRates() {
        return rates;
    }

    public void setRates(List<RateEntity> rates) {
        this.rates = rates;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionText() {
        return sessionText;
    }

    public void setSessionText(String sessionText) {
        this.sessionText = sessionText;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public AccountEntity getCreator() {
        return creator;
    }

    public void setCreator(AccountEntity creator) {
        this.creator = creator;
    }

}
