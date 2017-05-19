# myTranslate
一款基于eclipse的插件，用于翻译。
有时候在看源码时会碰到一些生疏的单词，需要借助翻译软件来查看，但是复制来复制去比较麻烦，所以开发了这款插件，可以直接用快捷键在eclipse上翻译。

一、下载插件

下载地址：https://github.com/Julyme/myTranslate 可以下载源码自己导出或者直接下载jar包。

下载jar包后放到eclipse/plugins文件夹里，重启eclipse。

二、配置首页项

打开windows->prefrences->myTranslate prefrences。

请选择翻译平台和填写改平台的id和key，如果选择全部，请全部填写key和id。

百度翻译API接口注册链接http://api.fanyi.baidu.com/api/trans/product/index

有道翻译API接口注册链接http://fanyi.youdao.com/openapi?path=data-mode

（PS：以上API接口都是免费申请的）

三、打开翻译窗口

在windows->show view ->other 里找到myTranslate view.点击OK

四、使用快捷键

1、选取要翻译的英文，按Ctrl+C。

2、点击myTranslate view的空白处（获取焦点才能触发快捷键），按Ctrl+Shift+C。

翻译结果会显示在myTranslate view里面

PS：目前只支持百度翻译的API，后续会支持google翻译、英文语音等功能。

更多详细情况请访问http://www.julyme.com/20170518/87.html
