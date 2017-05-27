/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.business.internal;

import com.sorena.write.dao.imp.AccountDao;
import com.sorena.write.dao.imp.ImageDao;
import com.sorena.write.model.entity.AccountEntity;
import com.sorena.write.model.entity.ImageEntity;
import com.sorena.write.model.entity.SessionEntity;
import com.sorena.write.model.entity.StoryEntity;
import com.sorena.write.model.service.CreateSessionModel;
import com.sorena.write.model.service.CreateStoryModel;
import com.sorena.write.model.service.SelectMainActivityModel;
import com.sorena.write.model.service.SelectStoryModel;
import com.sorena.write.utility.AppUtil;
import com.sorena.write.utility.DBType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author sorena
 */
public class StoryBusiness {

    private AccountDao accountDao = null;
    private ImageDao imageDao = null;
    private AccountBusiness accountBusiness = null;

    public StoryBusiness() {
        accountDao = new AccountDao(AccountEntity.class, DBType.MAIN_DB);
        imageDao = new ImageDao(ImageEntity.class, DBType.IMAGE_DB);
        accountBusiness = new AccountBusiness();
    }

    public CreateStoryModel createStory(CreateStoryModel model, StoryEntity storyEntity, SessionEntity sessionEntity, String token, String macAddress, String image) throws Exception {
        AccountEntity entity;
        if ((entity = accountBusiness.selectAccountByTokenAndMac(token, macAddress)) != null) {
            sessionEntity.setCreateDate(new Date());
            sessionEntity.setCreator(entity);
            storyEntity.setCreateDate(new Date());
            storyEntity.setActive(true);
            if (entity.getStories() == null) {
                entity.setStories(new ArrayList<StoryEntity>());
            }
//            if (storyEntity.getSessions() == null) {
            storyEntity.setSessions(new ArrayList<SessionEntity>());
//            }
            storyEntity.getSessions().add(sessionEntity);

            HashMap<String, Object> param = new HashMap<>();

            String imageName = AppUtil.generateUUID();
            if (AppUtil.saveImage(AppUtil.decode64(image), "story", imageName)) {
                storyEntity.setImageName(imageName);
                // entity.getStories().add(storyEntity);
                // entity.setLastCreateStoryDate(new Date());
                entity.getStories().add(storyEntity);
                param.put("stories", entity.getStories());
                if (accountDao.merge(entity.getId(), param) != null) {
                    model.setMessage("داستان درست شد");
                    model.setMessageType("OK");
                } else {
                    model.setMessage("داستان خراب شد");
                    model.setMessageType("ERROR");
                }
            } else {
                model.setMessage("داستان خراب شد");
                model.setMessageType("ERROR");
            }

        } else {
            model.setMessage("احراز هویت خراب شد");
            model.setMessageType("ERROR");
        }

        return model;
    }

    public SelectMainActivityModel selectMainActivity(SelectMainActivityModel model, String token, String macAddress) {
        AccountEntity entity;
        if ((entity = accountBusiness.selectAccountByTokenAndMac(token, macAddress)) != null) {
            //fill last week and most liked Story
            List<AccountEntity> listAccount = accountDao.findList(null);
            for (AccountEntity a : listAccount) {
                if (a.getStories() != null) {
                    for (StoryEntity s : a.getStories()) {
                        SelectMainActivityModel.StoryThumbnailItem story = new SelectMainActivityModel.StoryThumbnailItem();
                        story.setId(a.getId().toHexString());
                        story.setStoryType(SelectMainActivityModel.StoryThumbnailItem.StoryType.LastStory);
                        story.setSummary(s.getSummary());
                        story.setTitle(s.getStoryName());
                        story.setImage("content/image/story/"
                                + s.getImageName() + ".jpg");
                        if (model.getItems() == null) {
                            model.setItems(new ArrayList<SelectMainActivityModel.StoryThumbnailItem>());
                        }
                        model.setMessage("اوکیه");
                        model.setMessageType("OK");
                        model.getItems().add(story);
                    }
                }
            }
        } else {
            model.setMessage("احراز هویت خراب شد");
            model.setMessageType("ERROR");
        }
        return model;
    }

    public SelectStoryModel selectStory(SelectStoryModel model, String token, String macAddress, ObjectId id, String storyTitle) {
        AccountEntity entity;
        if ((entity = accountBusiness.selectAccountByTokenAndMac(token, macAddress)) != null) {
            HashMap<String, Object> param = new HashMap<>();
            param.put("_id", id);
            AccountEntity accountEntity = accountDao.find(param);
            if (accountEntity != null) {
                for (StoryEntity s : accountEntity.getStories()) {
                    if (s.getStoryName().equals(storyTitle)) {
                        model.setId(accountEntity.getId().toHexString());
                        model.setImageUrl("content/image/story/"
                                + s.getImageName() + ".jpg");
                        model.setSummary(s.getSummary());
                        model.setTitle(s.getStoryName());
                        model.setNumberOfAuthors(s.getNumberAuthors());
                        model.setSessions(new ArrayList<SelectStoryModel.SessionModel>());
                        for (SessionEntity ss : s.getSessions()) {
                            SelectStoryModel.SessionModel sessionModel = new SelectStoryModel.SessionModel();
                            sessionModel.setContent(ss.getSessionText());
                            sessionModel.setTitle(ss.getSessionName());
                            model.getSessions().add(sessionModel);
                        }
                        model.setMessage("OK");
                        model.setMessageType("OK");
                        break;
                    }
                }
            } else {
                model.setMessage("داستان پیدا نشد شاید حذف شده باشد");
                model.setMessageType("ERROR");
            }
        } else {
            model.setMessage("احراز هویت نشد");
            model.setMessageType("ERROR");
        }

        return model;
    }

    public CreateSessionModel createSession(CreateSessionModel model, AccountEntity entity, String storyCreatorId, String storyName, SessionEntity sessionEntity) throws Exception {
        AccountEntity ac;
        if ((ac = accountBusiness.selectAccountByTokenAndMac(entity.getToken(), entity.getMacAddress())) != null) {
            AccountEntity creatorAccount = accountBusiness.selectAccountByID(storyCreatorId);
            HashMap<String, Object> updateParam, filterParam;
            updateParam = new HashMap<>();
            filterParam = new HashMap<>();
            int index = 0;
            for(StoryEntity se:creatorAccount.getStories()){
                if(se.getStoryName().equals(storyName)){
                    index = creatorAccount.getStories().indexOf(se);
                }
            }
            creatorAccount.getStories().get(index).getSessions().add(sessionEntity);
            filterParam.put("stories.storyName", storyName);
            updateParam.put("stories",creatorAccount.getStories());

            if (accountDao.merge(new ObjectId(storyCreatorId), updateParam, filterParam) != null) {
                model.setMessage("OK");
                model.setMessageType("OK");
            } else {
                model.setMessage("ERROR");
                model.setMessageType("ERROR");
            }
        } else {
            model.setMessage("احراز هویت نشد");
            model.setMessageType("ERROR");
        }
        return model;
    }
}
