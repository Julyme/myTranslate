package mytranslate.views;


import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MyTranslateView extends ViewPart {

    public static final String ID = "mytranslate.views.MyTranslateView";
    public static volatile StyledText resultText;
    
    @Override
    public void createPartControl(Composite parent) {
        resultText = new StyledText(parent, 0);
    }
    
    @Override
    public void setFocus() {
    }

   

}
