package mytranslate.impl;

import com.alibaba.fastjson.JSON;
import com.baidu.translate.demo.TransApi;

import mytranslate.BaiduTranslate;
import mytranslate.BaiduTranslateMode;
import mytranslate.TranslateConf;

public class BaiduTranslateImpl implements BaiduTranslate {
    

    
    @Override
    public BaiduTranslateMode translate(String text, String from,
            String to) {
        BaiduTranslateMode mode = null;
        TransApi api = new TransApi(TranslateConf.APP_ID, TranslateConf.SECURITY_KEY);
        String result = api.getTransResult(text, from, to);
        if(result.contains("dst")){
           mode = new BaiduTranslateMode(result);
           return mode;
        }else{
            mode = new BaiduTranslateMode();
            mode.setDst(result);
            return mode;
        }
    }
    
  
    
    public static void main(String[] args){
        //String result = new GoogleTranslateImpl().translate("中国", "zh-CN", "en");
        //System.out.println(result);
        
     // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
        String text = "The String class represents character strings. All string literals in Java programs, such as 'abc', are implemented as instances of this class. ";
        BaiduTranslateMode mode = new BaiduTranslateImpl().translate(text, "auto", "zh");
        System.out.println(mode.getDst());
    }
    
}
