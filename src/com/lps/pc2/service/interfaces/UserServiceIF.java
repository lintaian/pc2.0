package com.lps.pc2.service.interfaces;

import com.lepeisheng.flipped.rpc.ParentInfo;

public interface UserServiceIF {
	public ParentInfo login(final String name, final String pwd) throws Exception;
}
