package com.lps.pc2.service.interfaces;

import java.util.List;

import rpc.BatchScore;
import rpc.ExamScore;
import rpc.KnowledgeScore;
import rpc.PowerScore;
import rpc.QuestionScore;
import rpc.Subject;

public interface StatisticsServiceIF {
	List<ExamScore> loadExamScore(String batchId, String studentId) throws Exception;
    List<BatchScore> loadBatchScore(String studentId) throws Exception;
    List<PowerScore> loadPowerScore(String examId, String studentId) throws Exception;
	List<KnowledgeScore> loadKnowledgeScore(String examId, String studentId) throws Exception;
	List<QuestionScore> loadQuestionScore(String examId, String studentId) throws Exception;
	List<Subject> loadStudentSubject(String studentId);
	List<ExamScore> loadStudentSubjectBatch(String subjectId, String studentId);
	BatchScore loadCurrentBatch(String studentId);
}
