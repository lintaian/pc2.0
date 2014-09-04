package com.lps.pc2.module;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import rpc.BatchScore;

import com.lps.pc2.service.interfaces.StatisticsServiceIF;

@At("/test")
@IocBean
@InjectName
public class TestModule {
	@Inject
	StatisticsServiceIF statisticsService;
	
	@At("/t1")
	@Ok("json")
	public Object test() throws Exception {
		String studentId = "cabed93f-e6c2-4790-b9fd-48a951d4b9b0";
		List<BatchScore> batchScores = statisticsService.loadBatchScore(studentId);
		return null;
	}
}
