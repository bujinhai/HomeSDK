package com.jinshu.homelibrary.entity;

/**
 * Create on 2019-11-02 16:57 by bll
 */

public class FocusInfo {

    private String focusPictureID;
    private String name;
    private String fileName;
    private int orderSeq;
    private String objectDefineID;
    private String objectDefineName;
    private String objectID;
    private String objectName;
    private String picturePath;
    private Object showURL;
    private Object pageName;

    public String getFocusPictureID() {
        return focusPictureID;
    }

    public void setFocusPictureID(String focusPictureID) {
        this.focusPictureID = focusPictureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getObjectDefineID() {
        return objectDefineID;
    }

    public void setObjectDefineID(String objectDefineID) {
        this.objectDefineID = objectDefineID;
    }

    public String getObjectDefineName() {
        return objectDefineName;
    }

    public void setObjectDefineName(String objectDefineName) {
        this.objectDefineName = objectDefineName;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Object getShowURL() {
        return showURL;
    }

    public void setShowURL(Object showURL) {
        this.showURL = showURL;
    }

    public Object getPageName() {
        return pageName;
    }

    public void setPageName(Object pageName) {
        this.pageName = pageName;
    }
}
