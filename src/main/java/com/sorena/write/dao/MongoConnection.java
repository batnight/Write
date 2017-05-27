/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.dao;

import com.mongodb.MongoClient;
import com.sorena.write.utility.Keyword;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author sorena
 */
public class MongoConnection {

    private static MongoClient mongoClient = null;
    private static Datastore mainDatastore = null;
    private static Datastore imageDataStore = null;

    private MongoConnection() {
    }

    public static Datastore getImageDatastore() {
        if (imageDataStore == null) {
            Morphia morphia = new Morphia();
            morphia.mapPackage("com.sorena.write.model.entity");
            imageDataStore = morphia.createDatastore(getMongoConnection(), Keyword.IMAGE_DB_NAME);
        }
        return imageDataStore;
    }

    public static Datastore getMainDatastore() {

        if (mainDatastore == null) {
            Morphia morphia = new Morphia();

            //set entity package
            morphia.mapPackage("com.sorena.write.model.entity");
            mainDatastore = morphia.createDatastore(getMongoConnection(), Keyword.MAIN_DB_NAME);
        }

        return mainDatastore;
    }

    private static MongoClient getMongoConnection() {

        if (mongoClient == null) {
            mongoClient = new MongoClient(Keyword.MONGO_IP, Keyword.MONGO_PORT);
        }

        return mongoClient;

    }

}
