/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.dao.imp;

import com.sorena.write.dao.AbstractFacade;
import com.sorena.write.model.entity.AccountEntity;
import com.sorena.write.utility.DBType;

/**
 *
 * @author sorena
 */
public class AccountDao extends AbstractFacade<AccountEntity>{
    
    public AccountDao(Class<AccountEntity> entityClass, DBType type) {
        super(entityClass, type);
    }
    
}
