package com.cozentus.TrainingTracking.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cozentus.TrainingTracking.Model.EvaluationName;
import com.cozentus.TrainingTracking.Repository.ExamRepository;
 
@Service
public class ExamService {
 
	@Autowired
	private ExamRepository examRepository;
 
	public List<EvaluationName> listOfExams() {
		List<EvaluationName> exam_list = (List<EvaluationName>) this.examRepository.findAll();
		return exam_list;
	}
 
	public EvaluationName addExam(EvaluationName evaluationName) {
		evaluationName.setType("Exam");
		return examRepository.save(evaluationName);
	}
 
	public EvaluationName updateExam(EvaluationName evaluationName, Integer evaluationId) {
		evaluationName.setEvaluationId(evaluationId);
		return examRepository.save(evaluationName);
	}
 
	public void deleteExamById(Integer evaluationId) {
		examRepository.deleteById(evaluationId);
	}
 
	public Optional<EvaluationName> getByEvaluationId(Integer id) {
		Optional<EvaluationName> evaluationName = examRepository.findById(id);
		return evaluationName;
	}
	
//	public void addMarks(List<EvaluationName> marks) {
//		for (EvaluationName mark : marks) {
//			examRepository.save(mark);
//		}
//	}
 
	public void updateMarks(List<EvaluationName> marks) {
		for (EvaluationName mark : marks) {
			examRepository.updateMarks(mark.getMarksSecured(),mark.getTotalMarks(),mark.getSubmissionDate(),mark.getEvaluationName(), mark.getStudentId(), mark.getBatchId(),mark.getCourseId(),mark.getTeacherId());
		}
	}
	
	public List<EvaluationName> getExamsByType(String type) {
	    List<EvaluationName> exams = examRepository.findByType(type);
	    return exams;
	}
	
	public void addMarks(List<EvaluationName> marks, List<Integer> studentIds) {
        for (Integer studentId : studentIds) {
            for (EvaluationName mark : marks) {
                EvaluationName newMark = new EvaluationName();
                newMark.setMarksSecured(mark.getMarksSecured()); // You can add marksSecured field here
                newMark.setTotalMarks(mark.getTotalMarks());
                newMark.setSubmissionDate(mark.getSubmissionDate());
                newMark.setEvaluationName(mark.getEvaluationName());
                newMark.setBatchId(mark.getBatchId());
                newMark.setCourseId(mark.getCourseId());
                newMark.setTeacherId(mark.getTeacherId());
                newMark.setType("Exam");
                newMark.setStudentId(studentId); // Set the studentId for this mark
                examRepository.save(newMark);
            }
        }
    }
}
