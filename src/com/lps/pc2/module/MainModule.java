package com.lps.pc2.module;
/*
_ooOoo_
o8888888o
88" . "88
(| -_- |)
O\  =  /O
____/`---'\____
.'  \\|     |//  `.
/  \\|||  :  |||//  \
/  _||||| -:- |||||-  \
|   | \\\  -  /// |   |
| \_|  ''\---/''  |   |
\  .-\__  `-`  ___/-. /
___`. .'  /--.--\  `. . __
."" '<  `.___\_<|>_/___.'  >'"".
| | :  `- \`.;`\ _ /`;.`/ - ` : | |
\  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
`=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
佛祖保佑       永无BUG
*/
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.lps.pc2.util.MySetup;

@Modules(scanPackage = true)
@IocBy(type=ComboIocProvider.class, 
	args={"*org.nutz.ioc.loader.json.JsonLoader", 
		"/ioc.js", 
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
		"com.lps.pc2."})
@SetupBy(MySetup.class)
@At("/")
@Fail("json")
public class MainModule {
}
