package mytranslate.baidu;

import mytranslate.TranslateConf;

public class BaiduTranslateImpl implements BaiduTranslate {
    

    
    @Override
    public BaiduTranslateModel translate(String text, String from,
            String to) {
        BaiduTranslateModel mode = null;
        TransApi api = new TransApi(TranslateConf.BAIDU_APP_ID, TranslateConf.BAIDU_SECURITY_KEY);
        String result = api.getTransResult(text, from, to);
        if(result.contains("dst")){
           mode = new BaiduTranslateModel(result);
           return mode;
        }else{
            mode = new BaiduTranslateModel();
            mode.setDst(result);
            return mode;
        }
    }
    
  
    
    public static void main(String[] args){
        //String result = new GoogleTranslateImpl().translate("中国", "zh-CN", "en");
        //System.out.println(result);
        
     // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
        String text = "The String class represents character strings. All string literals in Java programs, such as 'abc', are implemented as instances of this class. ";
        BaiduTranslateModel mode = new BaiduTranslateImpl().translate(text, "auto", "zh");
        System.out.println(mode.getDst());
    }
    
}
