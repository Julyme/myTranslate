package mytranslate.youdao;

import mytranslate.utils.HttpGet;

public class YoudaoTranslate {

    
    /**
     * 单词
     * {"translation":["英语"],"basic":{"us-phonetic":"'iŋɡliʃ","phonetic":"'iŋɡliʃ","uk-phonetic":"'iŋɡliʃ","explains":["n. 英语；英文；英国人；英格兰人","adj. 英文的；英国的；英国人的","vt. 把\u2026译成英语"]},"query":"english","errorCode":0,"web":[{"value":["英语","语言","英文"],"key":"English"},{"value":["气管炎","软骨病","英国病"],"key":"English disease"},{"value":["英语语法","英语语法","英文文法"],"key":"English grammar"}]}
     * 
     * 句子
     * {"translation":["我爱英语。"],"query":"i love english.","errorCode":0}
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        String host = "http://fanyi.youdao.com/openapi.do?keyfrom=&key=&type=data&doctype=json&version=1.1&q=i love english.";
        String str = HttpGet.get(host, null);
        System.out.println(str);
    }
    
}
