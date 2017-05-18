package mytranslate.handlers;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import mytranslate.Activator;
import mytranslate.TranslateConf;
import mytranslate.baidu.BaiduTranslateImpl;
import mytranslate.baidu.BaiduTranslateMode;
import mytranslate.views.MyTranslateView;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.StyledText;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class KeyHandler extends AbstractHandler {
    private StyledText text;
    private String translateResult = "";
    private IPreferenceStore store;
    
    
    public KeyHandler() {
    }

    /**
     * 使用快捷键时，会执行此方法
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        if (store == null){
            store = Activator.getDefault().getPreferenceStore();
            initKey();
            store.addPropertyChangeListener(new IPropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent arg0) {
                    initKey();
                }
            });
        }
        text = MyTranslateView.resultText;
        text.setText("翻译中...");
        String src = getCopyText();
        switch (TranslateConf.TRANSLATEPLATFORM) {
        case "all":
            translateResult = allTranslate(src);
            break;

        case "baidu":
            translateResult = baiduTranslate(src);
            break;
           
        case "youdao":
            translateResult = youdaoTranslate(src);
            break;
            
        default:
            break;
        }
       
        text.setText(translateResult);
       
        return null;
    }

    /**
     * 获取剪切板数据
     * @return
     */
    public String getCopyText() {
        // 取得系统剪贴板里可传输的数据构造的Java对象
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);
        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                // 因为原系的剪贴板里有多种信息, 如文字, 图片, 文件等
                // 先判断开始取得的可传输的数据是不是文字, 如果是, 取得这些文字
                return t.getTransferData(DataFlavor.stringFlavor).toString();
                // 同样, 因为Transferable中的DataFlavor是多种类型的,
                // 所以传入DataFlavor这个参数, 指定要取得哪种类型的Data.
            }
        } catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "剪切板没有值。";
    }
    
    /**
     * 初始化所需要的key
     */
    public void initKey(){
        TranslateConf.TRANSLATEPLATFORM = store.getString("TRANSLATE_PLATFORM");
        TranslateConf.BAIDU_APP_ID = store.getString("APP_ID");
        TranslateConf.BAIDU_SECURITY_KEY = store.getString("SECURITY_KEY");
        TranslateConf.YOUDAO_KEY = store.getString("YOUDAO_KEY");
        TranslateConf.YOUDAO_KEYFROM = store.getString("YOUDAO_KEYFROM");
    }
    
    /**
     * 百度翻译
     * @param src
     * @return
     */
    public String baiduTranslate(String src){
        BaiduTranslateMode mode = new BaiduTranslateImpl().translate(src, "auto", "zh");
        StringBuffer sb = new StringBuffer();
        sb.append("原文： "+mode.getSrc()).append("\r").append("译文： "+mode.getDst());
        return sb.toString();
    }

    /**
     * 有道翻译
     * @param src
     * @return
     */
    public String youdaoTranslate(String src){
        
        return "有道翻译";
    }
    
    /**
     * 调用全部翻译平台
     * @param src
     * @return
     */
    public String allTranslate(String src){
        
        return "调用全部翻译平台";
    }
    
}
