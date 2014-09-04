package com.lps.pc2.filter;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import com.lps.pc2.util.SessionHelper;

public class LoginFilter implements ActionFilter {
	@Override
	public View match(ActionContext arg0) {
		if (!SessionHelper.isLogin(arg0.getRequest())) {
			return new ViewWrapper(new ServerRedirectView("/login"), null);
		}
		return null;
	}
}
