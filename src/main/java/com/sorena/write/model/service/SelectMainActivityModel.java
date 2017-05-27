/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.model.service;

import java.util.List;

/**
 *
 * @author sorena
 */
public class SelectMainActivityModel extends BaseServiceModel {

    private List<StoryThumbnailItem> items;

    public List<StoryThumbnailItem> getItems() {
        return items;
    }

    public void setItems(List<StoryThumbnailItem> items) {
        this.items = items;
    }

    public static class StoryThumbnailItem {

        private String id;
        private String title;
        private String summary;
        private String image;
        private StoryType storyType;

        public StoryThumbnailItem() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public StoryType getStoryType() {
            return storyType;
        }

        public void setStoryType(StoryType storyType) {
            this.storyType = storyType;
        }

        public enum StoryType {
            LastStory, PopularStory, TopRomanStory;
        }
    }
}
