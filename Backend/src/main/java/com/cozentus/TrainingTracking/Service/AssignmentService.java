package com.cozentus.TrainingTracking.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cozentus.TrainingTracking.Model.EvaluationName;
import com.cozentus.TrainingTracking.Repository.AssignmentRepository;
 
@Service
public class AssignmentService {
 
	@Autowired
	private AssignmentRepository assignmentRepository;
 
 
	public List<EvaluationName> listOfAssignments() {
		List<EvaluationName> assignment_list = (List<EvaluationName>) this.assignmentRepository.findAll();
		return assignment_list;
 
	}
 
	public EvaluationName addAssignment(EvaluationName evaluationName) {
		evaluationName.setType("Assignment");
		return assignmentRepository.save(evaluationName);
 
	}
 
	public EvaluationName updateAssignment(EvaluationName evaluationName, Integer evaluationId) {
		evaluationName.setEvaluationId(evaluationId);
		return assignmentRepository.save(evaluationName);
	}
 
	public void deleteAssignmentById(Integer evaluationId) {
		assignmentRepository.deleteById(evaluationId);
	}
 
	public Optional<EvaluationName> findByEvaluationId(Integer evaluationId) {
		Optional<EvaluationName> evaluationName = assignmentRepository.findById(evaluationId);
		return evaluationName;
	}
	
	
	public List<EvaluationName> getAssignmentsByType(String type) {
	    List<EvaluationName> assignments = assignmentRepository.findByType(type);
	    return assignments;
	}


}
