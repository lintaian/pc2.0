package com.lps.pc2.module;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

import com.lps.pc2.service.interfaces.StatisticsServiceIF;
import com.lps.pc2.util.SessionHelper;

@IocBean
@InjectName
@At("/statistic")
public class StatisticsModule {
	@Inject
	StatisticsServiceIF statisticsService;
	
	@At("/loadBatchScore")
	@Ok("json")
	@GET
	public Object loadBatchScore(HttpServletRequest req) throws Exception {
		return statisticsService.loadBatchScore(SessionHelper.getStudentUid(req));
	}
	@At("/loadExamScore")
	@Ok("json")
	@GET
	public Object loadExamScore(String batchId, HttpServletRequest req) throws Exception {
		return statisticsService.loadExamScore(batchId, SessionHelper.getStudentUid(req));
	}
	@At("/loadQuestionScore")
	@Ok("json")
	@GET
	public Object loadQuestionScore(String examId, HttpServletRequest req) throws Exception {
		return statisticsService.loadQuestionScore(examId, SessionHelper.getStudentUid(req));
	}
	@At("/loadKnowledgeScore")
	@Ok("json")
	@GET
	public Object loadKnowledgeScore(String examId, HttpServletRequest req) throws Exception {
		return statisticsService.loadKnowledgeScore(examId, SessionHelper.getStudentUid(req));
	}
	@At("/loadPowerScore")
	@Ok("json")
	@GET
	public Object loadPowerScore(String examId, HttpServletRequest req) throws Exception {
		return statisticsService.loadPowerScore(examId, SessionHelper.getStudentUid(req));
	}
	@At("/loadStudentSubject")
	@Ok("json")
	@GET
	public Object loadStudentSubject(HttpServletRequest req) throws Exception {
		return statisticsService.loadStudentSubject(SessionHelper.getStudentUid(req));
	}
	@At("/loadStudentSubjectBatch")
	@Ok("json")
	@GET
	public Object loadStudentSubjectBatch(String subjectId, HttpServletRequest req) throws Exception {
		if ("total".equals(subjectId)) {
			return statisticsService.loadBatchScore(SessionHelper.getStudentUid(req));
		}
		return statisticsService.loadStudentSubjectBatch(subjectId, SessionHelper.getStudentUid(req));
	}
	@At("/loadCurrentBatch")
	@Ok("json")
	@GET
	public Object loadCurrentBatch(HttpServletRequest req) throws Exception {
		return statisticsService.loadCurrentBatch(SessionHelper.getStudentUid(req));
	}
}
