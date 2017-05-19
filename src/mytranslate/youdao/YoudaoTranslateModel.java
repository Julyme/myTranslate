package mytranslate.youdao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class YoudaoTranslateModel {

    private List<String> translation = new ArrayList<String>();
    private String query = "";
    private Basic basic= new Basic();        
    private Web web = new Web();
    
    
    
    @SuppressWarnings("unchecked")
    public void initModel(String text){
        Map<String,Object> map = JSON.parseObject(text,Map.class);
        //System.out.println(map);
        if("0".equals(map.get("errorCode").toString())){
            translation = JSON.parseObject(map.get("translation").toString(),List.class);
            query = map.get("query").toString();
            if(map.get("basic") != null){
               Map<String,Object> basicMap = JSON.parseObject(map.get("basic").toString(),Map.class);
               if(basicMap.get("phonetic") != null)
                   basic.phonetic = basicMap.get("phonetic").toString();
               if(basicMap.get("uk-phonetic") != null)
                   basic.uk_phonetic = basicMap.get("uk-phonetic").toString();
               if(basicMap.get("us-phonetic") != null)
                   basic.us_phonetic = basicMap.get("us-phonetic").toString();
               basic.explains = JSON.parseObject(basicMap.get("explains").toString(),List.class);
               basic.value = true;
            }
            if(map.get("web") != null){
                web.webList = JSON.parseObject(map.get("web").toString(),List.class);
                web.value = true;
            }
        }else{
            query = "errorCode="+map.get("errorCode").toString();
        }
    }
    
    public String printResult(){
        StringBuffer sb = new StringBuffer();
        sb.append("原文：").append(query).append("\r")
        .append("译文：");
        for (Object o : translation) {
            sb.append(o).append("\r");
        }
        if(basic.value){
            sb.append("有道词典-基本词典：").append("\r").append("发音：")
            .append(basic.phonetic).append("\r").append("英式发音：")
            .append(basic.uk_phonetic).append("\r").append("美式发音：")
            .append(basic.us_phonetic).append("\r");
            for (String str : basic.explains) {
                sb.append(str).append("\r");
            }
        }
        if(web.value){
            sb.append("有道词典-网络释义").append("\r");
           for (int i = 0; i < web.webList.size(); i++) {
            Map m = web.webList.get(i);
            Object obj = m.get("value");
            sb.append(m.get("key")).append("    ").append(obj).append("\n");
           }
        }
        return sb.toString();
    }
    class Basic {
        public boolean value = false;
        public String us_phonetic = "";
        public String phonetic = "";
        public String uk_phonetic ="";
        public List<String> explains = new ArrayList<String>();
    }
    class Web{
        public boolean value = false;
        public List<HashMap<String, Object>> webList = new ArrayList<HashMap<String,Object>>();
    }
    
}
