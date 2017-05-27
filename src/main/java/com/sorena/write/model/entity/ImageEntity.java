/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.model.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author sorena
 */
@Entity("image")
public class ImageEntity {

    @Id
    private ObjectId _id;
    @Version
    @Property("version")
    private long version;
    private String imageName;
    private String imageBase64;

    public long getVersion() {
        return version;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

}
