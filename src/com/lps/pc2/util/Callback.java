package com.lps.pc2.util;

import org.apache.thrift.protocol.TProtocol;

public interface Callback {
	public Object run(TProtocol protocol) throws Exception;
}
