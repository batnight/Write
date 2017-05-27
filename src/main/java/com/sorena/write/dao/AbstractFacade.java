/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.dao;

import com.mongodb.WriteResult;
import com.sorena.write.utility.DBType;
import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 *
 * @author sorena
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    private Datastore datastore = null;

    public AbstractFacade(Class<T> entityClass, DBType type) {
        this.entityClass = entityClass;
        if (type == DBType.MAIN_DB) {
            this.datastore = MongoConnection.getMainDatastore();
        } else {
            this.datastore = MongoConnection.getImageDatastore();
        }
    }

    public T insert(T entity) {
        try {
            Query<T> query = datastore.createQuery(entityClass);
            Key<T> t = datastore.save(entity);
            ObjectId id = (ObjectId) t.getId();
            query.filter("_id =", id);

            return query.get();

        } catch (Exception e) {
            return null;
        }
    }

    public T merge(ObjectId id, HashMap<String, Object> parameters, HashMap<String, Object> filter) throws Exception {
        try {
            Query<T> query = datastore.createQuery(entityClass)
                    .filter("_id =", id);
            if (filter != null) {
                for (String key : filter.keySet()) {
                    query.filter(key + " =", filter.get(key));
                }
            }
            UpdateOperations<T> update = datastore.createUpdateOperations(entityClass);
            for (String key : parameters.keySet()) {
                update.set(key, parameters.get(key));
            }
            UpdateResults res = datastore.update(query, update);
            if (res.getUpdatedExisting()) {
                return query.filter("_id =", id)
                        .get();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public T merge(ObjectId id, HashMap<String, Object> parameters) throws Exception {
        try {
            Query<T> query = datastore.createQuery(entityClass)
                    .filter("_id =", id);
            UpdateOperations<T> update = datastore.createUpdateOperations(entityClass);
            for (String key : parameters.keySet()) {
                update.set(key, parameters.get(key));
            }
            UpdateResults res = datastore.update(query, update);
            if (res.getUpdatedExisting()) {
                return query.filter("_id =", id)
                        .get();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public boolean delete(T t) throws Exception {
        try {
            WriteResult result = datastore.delete(t);
            return (result.getN() != 0);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public List<T> findList(HashMap<String, Object> parameters) {
        Query<T> query = datastore.createQuery(entityClass);
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                query.filter(key + " =", parameters.get(key));
            }
        }
        return (query.asList() != null) ? query.asList() : null;
    }

    public T find(HashMap<String, Object> parameters) {
        Query<T> query = datastore.createQuery(entityClass);
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                query.filter(key + " =", parameters.get(key));
            }
        }
        return (query.get() != null) ? query.get() : null;
    }

}
