/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.model.entity;

import com.sorena.write.utility.UtilMethod;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author sorena
 */
@Entity("account")
public class AccountEntity {

    @Id
    private ObjectId _id;
    @Version
    @Property("version")
    private long version;
    private String firstName;
    private String lastName;
    @Property("email")
    private String email;
    private String mobileNumber;
    private String token;
    private boolean active;
    private boolean ban;
    private Date registerDate;
    private String macAddress;
    private Date lastCreateStoryDate;
    private String username;
    private String password;
    @Embedded
    private List<DeviceEntity> devices;
    @Embedded
    private List<StoryEntity> stories;

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Date getLastCreateStoryDate() {
        return lastCreateStoryDate;
    }

    public void setLastCreateStoryDate(Date lastCreateStoryDate) {
        this.lastCreateStoryDate = lastCreateStoryDate;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public ObjectId getId() {
        return _id;
    }

    public long getVersion() {
        return version;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public List<DeviceEntity> getDevices() {
        return devices;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isBan() {
        return ban;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDevices(List<DeviceEntity> devices) {
        this.devices = devices;
    }

    public void setStories(List<StoryEntity> stories) {
        this.stories = stories;
    }

    public List<StoryEntity> getStories() {
        return stories;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = UtilMethod.hash(password);
    }

}
