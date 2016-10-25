package org.mobiletrain.food.bean;

/**
 * Created by 王松 on 2016/8/1.
 */
public class ClassfyBean {
    public ClassfyBean() {
    }

    public ClassfyBean(String description, int foodclass, int id, String keywords, String name, int seq, String title) {
        this.description = description;
        this.foodclass = foodclass;
        this.id = id;
        this.keywords = keywords;
        this.name = name;
        this.seq = seq;
        this.title = title;
    }

    /**
     * description : 养生保健
     * foodclass : 0
     * id : 1
     * keywords : 养生保健
     * name : 养生保健
     * seq : 0
     * title : 养生保健
     */

    private String description;
    private int foodclass;
    private int id;
    private String keywords;
    private String name;
    private int seq;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoodclass() {
        return foodclass;
    }

    public void setFoodclass(int foodclass) {
        this.foodclass = foodclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
