package com.atguiu.mybili.bean;

/**
 * Created by 进击的程序猿 on 2017/3/20.
 */

public class ContentModel {
    private int image;
    private String content;

    public ContentModel() {
    }

    public ContentModel(int image,String content) {
        this.image = image;
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentModel{" +
                "image=" + image +
                ", content='" + content + '\'' +
                '}';
    }
}
