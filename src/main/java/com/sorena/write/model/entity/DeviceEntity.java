/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.model.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author sorena
 */
@Embedded
public class DeviceEntity {

    private String apiVersion;
    private String macAddress;

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getMacAddress() {
        return macAddress;
    }

}
