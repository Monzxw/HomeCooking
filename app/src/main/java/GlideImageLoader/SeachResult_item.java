package GlideImageLoader;

public class SeachResult_item {
    int imgurl;
    String title;
    String introduction;
    int peoplenumber;

    public SeachResult_item(int imgurl, String title, String introduction, int peoplenumber) {
        this.imgurl = imgurl;
        this.title = title;
        this.introduction = introduction;
        this.peoplenumber = peoplenumber;
    }

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getPeoplenumber() {
        return peoplenumber;
    }

    public void setPeoplenumber(int peoplenumber) {
        this.peoplenumber = peoplenumber;
    }
}
