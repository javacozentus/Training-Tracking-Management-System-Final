package com.cozentus.TrainingTracking.Service;
 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cozentus.TrainingTracking.Model.Attendance;
import com.cozentus.TrainingTracking.Repository.AttendanceRepository;
 
@Service
public class AttendanceService {
 
	@Autowired
	private AttendanceRepository attendanceRepository;
 
	public List<Attendance> listOfAttendances() {
		List<Attendance> attendance_list = (List<Attendance>) this.attendanceRepository.findAll();
		return attendance_list;
	}
 
	public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
 
	public Attendance updateAttendance(Attendance attendance, Integer attendanceId) {
		attendance.setAttendanceId(attendanceId);
		return attendanceRepository.save(attendance);
	}
 
	public void deleteAttendanceById(Integer attendanceId) {
		attendanceRepository.deleteById(attendanceId);
	}
 
	public Optional<Attendance> findByAttendanceId(Integer attendanceId) {
		Optional<Attendance> attendace_by_id = attendanceRepository.findById(attendanceId);
		return attendace_by_id;
	}
 
	// multiple attdences
	
	 public void addAttendances(List<Attendance> attendances) {
	        attendanceRepository.saveAll(attendances);
	 }
 
//	public void updateAttendances(List<Attendance> attendances) {
//		for (Attendance attendance : attendances) {
//			attendanceRepository.updateAttendances(attendance.getAttendance(), attendance.getStudentId(), attendance.getDate(), attendance.getBatchId(), attendance.getTopicId(), attendance.getTeacherId());
//		}
//	}
	 
	 
	 
//	 public List<Attendance> listOfAttendances() {
//	        List<Attendance> attendanceList = (List<Attendance>) this.attendanceRepository.findAll();
//	        return attendanceList;
//	    }
//
//	    public List<Attendance> addAttendance(AttendanceRequestDTO attendanceRequest) {
//	        List<Integer> studentIds = attendanceRequest.getStudentIds();
//	        Date date = attendanceRequest.getDate();
//	        Integer topicId = attendanceRequest.getTopicId();
//	        Integer batchId = attendanceRequest.getBatchId();
//	        Boolean attendance = attendanceRequest.getAttendance();
//
//	        List<Attendance> attendanceList = new ArrayList<>();
//
//	        for (Integer studentId : studentIds) {
//	            Attendance attendanceData = new Attendance();
//	            attendanceData.setStudentIds(studentIds);
//	            attendanceData.setDate(date);
//	            attendanceData.setTopicId(topicId);
//	            attendanceData.setBatchId(batchId);
//	            attendanceData.setAttendance(attendance);
//
//	            // Set other fields if needed
//
//	            attendanceList.add(attendanceData);
//	        }
//
//	        return attendanceRepository.saveAll(attendanceList);
//	    }
}
