/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.business.internal;

import com.sorena.write.dao.imp.AccountDao;
import com.sorena.write.model.entity.AccountEntity;
import com.sorena.write.model.service.InitializedModel;
import com.sorena.write.model.service.LoginModel;
import com.sorena.write.model.service.RegisterModel;
import com.sorena.write.utility.DBType;
import com.sorena.write.utility.UtilMethod;
import java.util.HashMap;
import org.bson.types.ObjectId;

/**
 *
 * @author sorena
 */
public class AccountBusiness {

    private AccountDao accountDao = null;

    public AccountBusiness() {
        accountDao = new AccountDao(AccountEntity.class, DBType.MAIN_DB);
    }

    public AccountEntity selectAccountByUsername(String username) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("username", username);

        return accountDao.find(param);
    }
    public AccountEntity selectAccountByID(String id) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("_id", new ObjectId(id));

        return accountDao.find(param);
    }

    public AccountEntity selectAccountByEmail(String email) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("email", email);

        return accountDao.find(param);
    }

    public AccountEntity selectAccountByMobileNumber(String mobileNumber) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("mobileNumber", mobileNumber);

        return accountDao.find(param);
    }

    public AccountEntity selectAccountByUsernameAndPassword(String username, String password) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password", UtilMethod.hash(password));

        return accountDao.find(param);
    }

    public AccountEntity selectAccountByTokenAndMac(String token, String mac) {
        HashMap<String, Object> param = new HashMap<>();
//        DeviceEntity de = new DeviceEntity();
//        de.setMacAddress(mac);
        param.put("token", token);
        param.put("macAddress", mac);

        return accountDao.find(param);
    }

    public RegisterModel register(AccountEntity entity, RegisterModel model) throws Exception {
        if (selectAccountByEmail(entity.getEmail()) != null) {
            model.setMessage("ایمیل وارد شده تکراری است");
            model.setMessageType("ERROR");
            return model;
        }
        if (selectAccountByMobileNumber(entity.getMobileNumber()) != null) {
            model.setMessage("شماره همراه وارد شده تکراری است");
            model.setMessageType("ERROR");
            return model;
        }
        if (selectAccountByUsername(entity.getUsername()) != null) {
            model.setMessage("نام کاربری وارد شده تکراری است");
            model.setMessageType("ERROR");
            return model;
        }
        try {

            AccountEntity a = accountDao.insert(entity);
            if (a != null) {

                model.setMessage("ثبت نام اوکی شد.");
                model.setMessageType("OK");
                model.setToken(a.getToken());
                model.setEmail(a.getEmail());
                model.setMobileNumber(a.getMobileNumber());
                model.setFirstName(a.getFirstName());
                model.setLastName(a.getLastName());
                model.setUsername(a.getUsername());
                model.setId(a.getId().toHexString());
                return model;
            } else {
                model.setMessage("ثبت نام نشد");
                model.setMessageType("ERROR");
                return model;
            }
        } catch (Exception e) {
            model.setMessage("ثبت نام نشد");
            model.setMessageType("ERROR");
            return model;
        }
    }

    public InitializedModel initialize(String token, String macAddress, InitializedModel model) {
        AccountEntity accountEntity;
        if ((accountEntity = selectAccountByTokenAndMac(token, macAddress)) != null) {
            model.setMessage("احراز هویت اوکیه");
            model.setMessageType("OK");
            model.setEmail(accountEntity.getEmail());
            model.setFirstName(accountEntity.getFirstName());
            model.setLastName(accountEntity.getLastName());
            model.setMobileNumber(accountEntity.getMobileNumber());
            model.setUsername(accountEntity.getUsername());

            return model;
        } else {
            model.setMessage("احراز هویت اوکی نیست");
            model.setMessageType("ERROR");
            return model;
        }
    }

    public LoginModel login(String username, String password, String macAddress, LoginModel model) throws Exception {
        AccountEntity entity;
        if (selectAccountByUsername(username) == null) {
            model.setMessage("نام کاربری یافت نشد");
            model.setMessageType("ERROR");
            throw new Exception();
        }
        if ((entity = selectAccountByUsernameAndPassword(username, password)) != null) {
//                if (entity.getMacAddress().equals(macAddress)) {
            HashMap<String, Object> param = new HashMap<>();
            param.put("macAddress", macAddress);
            if (accountDao.merge(entity.getId(), param) != null) {
                model.setEmail(entity.getEmail());
                model.setMobileNumber(entity.getMobileNumber());
                model.setFirstName(entity.getFirstName());
                model.setLastName(entity.getLastName());
                model.setUsername(entity.getUsername());
                model.setToken(entity.getToken());
                model.setMessage("ورود اوکی شد");
                model.setMessageType("OK");
                model.setId(entity.getId().toHexString());

                return model;
            }
//                }

            model.setMessage("ورود با خطا همراه بود");
            model.setMessageType("ERROR");
            throw new Exception();
        } else {
            model.setMessage("ورود با خطا همراه بود");
            model.setMessageType("ERROR");
            throw new Exception();
        }

    }
}
