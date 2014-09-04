var ioc = {
  	config : {
        type : 'org.nutz.ioc.impl.PropertiesProxy',
        fields : {
            paths : ['thrift.properties']
        }
    },
	thriftConfig: {
		type: 'com.lps.pc2.config.ThriftConfig',
		args: [{java: "$config.get('remote.ip')"}, 
		       {java: "$config.get('remote.port')"},
		       {java: "$config.get('remote.timeout')"}]
	},
	loginConfig: {
		type: 'com.lps.pc2.config.ThriftConfig',
		args: [{java: "$config.get('login.ip')"}, 
		       {java: "$config.get('login.port')"},
		       {java: "$config.get('login.timeout')"}]
	}
}