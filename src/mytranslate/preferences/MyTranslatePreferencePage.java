package mytranslate.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import mytranslate.Activator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class MyTranslatePreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

    public MyTranslatePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("请填写需要使用的翻译平台相关的验证信息。");
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    public void createFieldEditors() {
        /*
         * addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH,
         * "&Directory preference:", getFieldEditorParent())); addField( new
         * BooleanFieldEditor( PreferenceConstants.P_BOOLEAN,
         * "&An example of a boolean preference", getFieldEditorParent()));
         */

        addField(new RadioGroupFieldEditor(
                PreferenceConstants.TRANSLATE_PLATFORM,
                "选择翻译平台，请注意你选择的平台必须填写授权的id和key，若勾选全部，请全部填写。", 1,
                new String[][] { { "全部", "all" }, { "百度翻译", "baidu" },
                        { "有道翻译", "youdao" } }, getFieldEditorParent()));
        addField(new StringFieldEditor(PreferenceConstants.APP_ID,
                "百度翻译的APP_ID:", getFieldEditorParent()));
        addField(new StringFieldEditor(PreferenceConstants.SECURITY_KEY,
                "百度翻译的SECURITY_KEY:", getFieldEditorParent()));
        addField(new StringFieldEditor(PreferenceConstants.YOUDAO_KEY,
                "有道翻译的KEY:", getFieldEditorParent()));
        addField(new StringFieldEditor(PreferenceConstants.YOUDAO_KEYFROM,
                "有道翻译的KEYFROM:", getFieldEditorParent()));

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

}