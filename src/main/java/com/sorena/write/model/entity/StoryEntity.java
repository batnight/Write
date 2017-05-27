/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.model.entity;

import java.util.Date;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author sorena
 */
@Embedded
public class StoryEntity {

    private String storyName;
    private String summary;
    private String tips;
    private boolean active;
    private Date createDate;
    private Date lastChange;
    private int numberAuthors;
    private String imageName;
    @Embedded
    private List<SessionEntity> sessions;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setNumberAuthors(int numberAuthors) {
        this.numberAuthors = numberAuthors;
    }

    public int getNumberAuthors() {
        return numberAuthors;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public List<SessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionEntity> sessions) {
        this.sessions = sessions;
    }

}
