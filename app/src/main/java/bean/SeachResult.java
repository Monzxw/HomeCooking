package bean;

import java.util.List;

public class SeachResult {

    /**
     * result : 1
     * msg : 成功
     * data : ["小龙虾"]
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
