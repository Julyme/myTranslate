package mytranslate.handlers;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import mytranslate.Activator;
import mytranslate.BaiduTranslateMode;
import mytranslate.TranslateConf;
import mytranslate.impl.BaiduTranslateImpl;
import mytranslate.views.MyTranslateView;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class KeyHandler extends AbstractHandler {
    private StyledText text;
    /**
     * The constructor.
     */
    public KeyHandler() {
    }

    /**
     * the command has been executed, so extract extract the needed information
     * from the application context.
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        initKey();
        text = MyTranslateView.resultText;
        text.setText("翻译中...");
        BaiduTranslateMode mode = new BaiduTranslateImpl().translate(getCopyText(), "auto", "zh");
        StringBuffer sb = new StringBuffer();
        sb.append("原文： "+mode.getSrc()).append("\r").append("译文： "+mode.getDst());
        text.setText(sb.toString());
       
        return null;
    }

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
     * 初始化百度翻译的key
     */
    public void initKey(){
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        String id = store.getString("APP_ID");
        String key = store.getString("SECURITY_KEY");
        if(id != null && key != null){
            TranslateConf.APP_ID = id;
            TranslateConf.SECURITY_KEY = key;
        }
    }
}
