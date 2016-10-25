package org.mobiletrain.food.bean;

/**
 * Created by 王松 on 2016/8/1.
 */
public class FoodListBean {
    public FoodListBean() {
    }

    @Override
    public String toString() {
        return "FoodListBean{" +
                "count=" + count +
                ", description='" + description + '\'' +
                ", disease='" + disease + '\'' +
                ", fcount=" + fcount +
                ", food='" + food + '\'' +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", keywords='" + keywords + '\'' +
                ", name='" + name + '\'' +
                ", rcount=" + rcount +
                ", summary='" + summary + '\'' +
                ", symptom='" + symptom + '\'' +
                '}';
    }

    public FoodListBean(int count, String description, String disease, int fcount, String food, int id, String img, String keywords, String name, int rcount, String summary, String symptom) {
        this.count = count;
        this.description = description;
        this.disease = disease;
        this.fcount = fcount;
        this.food = food;
        this.id = id;
        this.img = img;
        this.keywords = keywords;
        this.name = name;
        this.rcount = rcount;
        this.summary = summary;
        this.symptom = symptom;
    }

    /**
     * count : 7474
     * description : 鹌鹑蛋对于肺病，肝炎，脑膜炎，胃病，糖尿病，哮喘，心脏病，神经衰弱，高血压，低血压，动脉硬化，小儿疳积等病症均有较好的辅助疗效
     * disease :
     * fcount : 0
     * food :
     * id : 4
     * img : /food/150804/4903e1e66e312a862a81682ce80884c9.jpg
     * keywords : 鹌鹑蛋 鸡蛋 月经不调 营养不良 神经衰弱
     * name : 鹌鹑蛋
     * rcount : 0
     * summary :
     <p></p>
     <p>1.鹌鹑蛋味甘，性平。</p>
     <p>2.有补益气血、强身健脑、丰肌泽肤等功效。</p>
     <p>3.鹌鹑蛋对贫血、营养不良、神经衰弱、月经不调、高血压、支气管炎、血管硬化等病人具有调补作用。</p>
     <p>4.对有贫血、月经不调的女性，其调补、养颜、美肤功用尤为显著。</p>
     <p></p>
     <p></p>
     * symptom :
     */

    private int count;
    private String description;
    private String disease;
    private int fcount;
    private String food;
    private int id;
    private String img;
    private String keywords;
    private String name;
    private int rcount;
    private String summary;
    private String symptom;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
