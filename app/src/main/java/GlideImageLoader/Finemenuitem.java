package GlideImageLoader;

import android.graphics.Bitmap;

public class Finemenuitem {
    Bitmap bitmap;
    String cate;
    String name;

    public Finemenuitem(Bitmap bitmap, String cate, String name) {
        this.bitmap = bitmap;
        this.cate = cate;
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
