package com.lps.pc2.service.impl;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.nutz.ioc.loader.annotation.IocBean;

import com.lepeisheng.flipped.rpc.FlippedService;
import com.lepeisheng.flipped.rpc.ParentInfo;
import com.lps.pc2.service.interfaces.UserServiceIF;

@IocBean
public class UserService extends BaseService implements UserServiceIF {
	@Override
	public ParentInfo login(String name, String pwd) throws Exception {
		ParentInfo obj = null;
		TTransport transport = new TSocket(getLoginConfig().getIp(), getLoginConfig().getPort());
		try {
			TProtocol protocol = new TBinaryProtocol(transport);
			transport.open();
			FlippedService.Client client = new FlippedService.Client(protocol);
			obj = client.parentLogin(name, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transport.close();
		}
		return obj;
	}
}
