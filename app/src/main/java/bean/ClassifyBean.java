package bean;

import java.util.List;

public class ClassifyBean {

    /**
     * result : 1
     * msg : 成功
     * data : ["菜式","菜系","特色","烘培","主食","器具","烹饪方式","口味","场合","节日"]
     */

    private String result;
    private String msg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
