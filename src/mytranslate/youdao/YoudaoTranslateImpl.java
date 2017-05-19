package mytranslate.youdao;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import mytranslate.TranslateConf;
import mytranslate.utils.HttpGet;

public class YoudaoTranslateImpl implements YoudaoTranslate{

    private Map<String ,String> paramsMap = new HashMap<String, String>();
    
    @Override
    public String translate(String src) {
        return translate(src, paramsMap);
    }
    
    public String translate(String src, Map<String ,String> paramsMap) {
        initParamsMap(src,paramsMap);
        String str = HttpGet.get(TranslateConf.YOUDAO_HOST, paramsMap);
//        try {
//            str = new String(str.getBytes(),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return str;
    }
    
    public void initParamsMap(String src,Map<String ,String> paramsMap){
        paramsMap.put("key", TranslateConf.YOUDAO_KEY);
        paramsMap.put("keyfrom", TranslateConf.YOUDAO_KEYFROM);
        paramsMap.put("type", TranslateConf.YOUDAO_TYPE);
        paramsMap.put("doctype", TranslateConf.YOUDAO_DOCTYPE);
        paramsMap.put("version", TranslateConf.YOUDAO_VERSION);
        paramsMap.put("q", src);
    }
    
    
    
}
