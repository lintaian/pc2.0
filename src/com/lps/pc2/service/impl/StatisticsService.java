package com.lps.pc2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.protocol.TProtocol;
import org.nutz.ioc.loader.annotation.IocBean;

import rpc.BatchScore;
import rpc.ExamScore;
import rpc.KnowledgeScore;
import rpc.PowerScore;
import rpc.QuestionScore;
import rpc.Subject;
import rpc.TongJiService;

import com.lps.pc2.service.interfaces.StatisticsServiceIF;
import com.lps.pc2.util.Callback;

@IocBean
public class StatisticsService extends BaseService implements StatisticsServiceIF {

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamScore> loadExamScore(final String batchId, final String studentId)
			throws Exception {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadExamScore(batchId, studentId);
			}
		});
		return rs == null ? new ArrayList<ExamScore>() : (List<ExamScore>)rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BatchScore> loadBatchScore(final String studentId) throws Exception {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadBatchScore(studentId);
			}
		});
		return rs == null ? new ArrayList<BatchScore>() : (List<BatchScore>)rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PowerScore> loadPowerScore(final String examId, final String studentId)
			throws Exception {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadPowerScore(examId, studentId);
			}
		});
		return rs == null ? new ArrayList<PowerScore>() : (List<PowerScore>)rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KnowledgeScore> loadKnowledgeScore(final String examId,
			final String studentId) throws Exception {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadKnowledgeScore(examId, studentId);
			}
		});
		return rs == null ? new ArrayList<KnowledgeScore>() : (List<KnowledgeScore>)rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionScore> loadQuestionScore(final String examId, final String studentId)
			throws Exception {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadQuestionScore(examId, studentId);
			}
		});
		return rs == null ? new ArrayList<QuestionScore>() : (List<QuestionScore>)rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> loadStudentSubject(final String studentId) {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadStudentSubject(studentId);
			}
		});
		return rs == null ? new ArrayList<Subject>() : (List<Subject>)rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamScore> loadStudentSubjectBatch(final String subjectId,
			final String studentId) {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadStudentSubjectBatch(subjectId, studentId);
			}
		});
		return rs == null ? new ArrayList<ExamScore>() : (List<ExamScore>)rs;
	}

	@Override
	public BatchScore loadCurrentBatch(final String studentId) {
		Object rs = exec(new Callback() {
			@Override
			public Object run(TProtocol protocol) throws Exception {
				TongJiService.Client client = new TongJiService.Client(protocol);
				return client.loadCurrentBatch(studentId);
			}
		});
		return rs == null ? null : (BatchScore)rs;
	}
	
}
