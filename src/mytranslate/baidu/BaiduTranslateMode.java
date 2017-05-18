package mytranslate.baidu;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;


public class BaiduTranslateMode {

    private String result = null;
    private String from = null;
    private String to = null;
    private String src = "";
    private String dst = "";

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
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSON.parseObject(result, Map.class);
        //System.out.println(result);
        this.from = map.get("from").toString();
        this.to = map.get("to").toString();
        String trans_result = map.get("trans_result").toString();
        //System.out.println(trans_result);
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> resultList = JSON.parseObject(trans_result,List.class);
        for (Map<String, Object> map2 : resultList) {
            this.src = this.src + map2.get("src").toString() +"\r";
            this.dst = this.dst + map2.get("dst").toString() + "\r";
        }
        
    }
    
    

}
