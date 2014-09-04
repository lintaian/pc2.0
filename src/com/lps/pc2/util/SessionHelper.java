package com.lps.pc2.util;

import javax.servlet.http.HttpServletRequest;
import com.lepeisheng.flipped.rpc.ParentInfo;

public class SessionHelper {
	private SessionHelper() {
	}
	public static void set(HttpServletRequest req, String name, Object obj) {
		req.getSession().setAttribute(name, obj);
	}
	public static Object get(HttpServletRequest req, String name) {
		return req.getSession().getAttribute(name);
	}
	public static void setUser(HttpServletRequest req, ParentInfo user) {
		req.getSession().setAttribute("loginUser", user);
	}
	public static ParentInfo getUser(HttpServletRequest req) {
		Object obj = req.getSession().getAttribute("loginUser");
		return obj == null ? null : (ParentInfo)obj;
	}
	public static String getStudentUid(HttpServletRequest req) {
		return getUser(req).getStudentUid();
	}
	public static void invalidate(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	public static boolean isLogin(HttpServletRequest req) {
		return getUser(req) != null;
	}
}
