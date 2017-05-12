package mytranslate;

import java.util.Map;

import com.alibaba.fastjson.JSON;

public class BaiduTranslateMode {

    private String result = null;
    private String from = null;
    private String to = null;
    private String src = null;
    private String dst = null;

    public BaiduTranslateMode() {
        super();
    }

    public BaiduTranslateMode(String result) {
        super();
        this.result = result;
        init();
    }

    /**
     * 获取原文
     * @return
     */
    public String getSrc() {
        return src;
    }
    /**
     * 获取译文
     * @return
     */
    public String getDst() {
        return dst;
    }
    public String getText() {
        return result;
    }

    public void setText(String text) {
        this.result = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    /**
     * 解析json
     */
    public void init() {
        Map map = JSON.parseObject(result, Map.class);
        this.from = map.get("from").toString();
        this.to = map.get("to").toString();
        String trans_result = map.get("trans_result").toString();
        trans_result = trans_result.substring(1, trans_result.length() - 1);
        //System.out.println(trans_result);
        Map mapResult = JSON.parseObject(trans_result, Map.class);
        this.src = mapResult.get("src").toString();
        this.dst = mapResult.get("dst").toString();
    }

}
