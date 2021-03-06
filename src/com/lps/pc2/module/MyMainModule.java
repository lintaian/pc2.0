package com.lps.pc2.module;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import com.lepeisheng.flipped.rpc.ParentInfo;
import com.lps.pc2.filter.LoginFilter;
import com.lps.pc2.service.interfaces.UserServiceIF;
import com.lps.pc2.util.SessionHelper;

@IocBean
@InjectName
@At("/")
@Fail("json")
public class MyMainModule {
	@Inject
	UserServiceIF userService;

	@At("login")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	@POST
	public Object loginPost(Map<String, String> body, HttpServletRequest req) throws Exception {
		Map<String, Object> re = new HashMap<String, Object>();
		re.put("status", false);
		String name = body.containsKey("username") ? body.get("username") : "";
		String password = body.containsKey("password") ? body.get("password") : "";
		if (name != null && !"".equals(name) && password != null && !"".equals(password)) {
//			ParentInfo user = userService.login(name, password);
			ParentInfo user = new ParentInfo();
			user.setStudentUid("cabed93f-e6c2-4790-b9fd-48a951d4b9b0");
			if (user != null) {
				re.put("status", true);
				SessionHelper.setUser(req, user);
			} else {
				re.put("msg", "用户名或密码错误!");
			}
		} else {
			re.put("msg", "用户名和密码不能为空!");
		}
		return re;
	}
	
	@At("login")
	@Ok("jsp:jsp.login")
	@Fail("redirect:/main")
	public void loginGet(HttpServletRequest req) throws Exception {
		if (SessionHelper.isLogin(req)) {
			throw new Exception();
		}
	}
	@At("main")
	@Filters({@By(type=LoginFilter.class)})
	@Ok("jsp:jsp.main")
	public void main(HttpServletRequest req) throws Exception {
	}
	@At("")
	@Ok("redirect:/main")
	public void index() {
	}
	@At("logout")
	@Ok("redirect:/login")
	public void logout(HttpServletRequest req) throws IOException {
		req.getSession().invalidate();
	}
	@At("session")
	@Ok("json")
	public Object getSession(String name, HttpServletRequest req) {
		return SessionHelper.get(req, name);
	}
	@At("application")
	@Ok("json")
	public Object getApplication(String name, HttpServletRequest req) {
		return req.getSession().getServletContext().getAttribute(name);
	}
}
