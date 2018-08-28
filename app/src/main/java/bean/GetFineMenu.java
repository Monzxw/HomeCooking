package bean;

import java.util.List;

public class GetFineMenu {

    /**
     * result : 1
     * msg : 成功
     * data : [{"id":1,"cate":"第一食堂","name":"青岛哈喇，鲜香美味","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531969559305&di=9d09cfe3442a8faabd5ce0d38a849720&imgtype=0&src=http%3A%2F%2Fimg1.qunarzz.com%2Ftravel%2Fd7%2F1506%2F4f%2Fa5ea9840ccf3b5.jpg"},{"id":2,"cate":"第二食堂","name":"烧烤时夏天的最爱","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531969880695&di=d44b5a013d167d85453b3771078a865b&imgtype=0&src=http%3A%2F%2Fimg02.tooopen.com%2Fimages%2F20151217%2Ftooopen_sy_151850658381.jpg"},{"id":3,"cate":"第三食堂","name":"肥而不腻，入口即化","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531969907778&di=c6cb506932807ffacd03e9dd7ff242c9&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1503%2F09%2Fc10%2F3650888_3650888_1425889453108_mthumb.jpg"},{"id":4,"cate":"第四食堂","name":"饭后甜点也是一种享受","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531969946992&di=133db04acee4b156803eb65d32f8d074&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201505%2F27%2F20150527174204_aThSR.jpeg"},{"id":5,"cate":"第五食堂","name":"水果拼牌不可少","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531969981758&di=e91bbb6b3c6cc5c01f2472ddf4c87097&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fb17eca8065380cd708ae52d1aa44ad345982815e.jpg"},{"id":6,"cate":"第六食堂","name":"酸辣汤也来点","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531989622667&di=dc970ec49450bb101130e5c9f618e6ba&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F95%2F20%2F70458PICTFf_1024.jpg"},{"id":7,"cate":"第七食堂","name":"主食吃意大利面吧","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531989644276&di=ab9188961d398c4b3a7d50d599adacf3&imgtype=0&src=http%3A%2F%2Fimg1.qunarzz.com%2Ftravel%2Fd8%2F1409%2F08%2Fe84f38201be89c11ffffffffc8d65eac.jpg_r_650x487x95_6fde3d05.jpg"},{"id":8,"cate":"第八食堂","name":"三明治也能吃出独有的滋味","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531989762321&di=93debbbe195fc761a0003acfb6725356&imgtype=0&src=http%3A%2F%2Fuploads.oh100.com%2Fallimg%2F1709%2F158-1F91Q35312-51.jpg"},{"id":9,"cate":"第九食堂","name":"蛋糕祝你生日快乐","imgurl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531989790547&di=815c3c3361020b0656829c82489004a6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fc995d143ad4bd113ddcfb60e51afa40f4bfb058f.jpg"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * cate : 第一食堂
         * name : 青岛哈喇，鲜香美味
         * imgurl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531969559305&di=9d09cfe3442a8faabd5ce0d38a849720&imgtype=0&src=http%3A%2F%2Fimg1.qunarzz.com%2Ftravel%2Fd7%2F1506%2F4f%2Fa5ea9840ccf3b5.jpg
         */

        private int id;
        private String cate;
        private String name;
        private String imgurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
