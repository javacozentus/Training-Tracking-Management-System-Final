package com.cozentus.TrainingTracking.RestController;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cozentus.TrainingTracking.DTO.CourseTopicDTO;
import com.cozentus.TrainingTracking.DTO.ProgramCourseDTO;
import com.cozentus.TrainingTracking.Model.Program;
import com.cozentus.TrainingTracking.Service.ProgramService;

@RestController
@RequestMapping("/program")
@CrossOrigin(origins = "*",allowedHeaders ="*") 
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProgramRestController {
	
	@Autowired
    private ProgramService programService;
    @GetMapping("/show/all")
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<Optional<Program>> getProgramById(@PathVariable Integer id) {
        Optional<Program> program = programService.findProgramById(id);
        return ResponseEntity.ok(program);
    }
    @PostMapping("/add")
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        Program createdProgram = programService.addProgram(program);
        return ResponseEntity.ok(createdProgram);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Program> updateProgram(@RequestBody Program program, @PathVariable Integer id) {
        Program updatedProgram = programService.updateProgram(program, id);
        return ResponseEntity.ok(updatedProgram);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Integer id) {
        programService.deleteProgramById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addProgramAndCourses")
    public ResponseEntity<Program> addProgramAndCourses(@RequestBody ProgramCourseDTO programCourseDTO) {
        Program savedProgram = programService.addProgramAndCourses(programCourseDTO);
        if (savedProgram != null) {
            return ResponseEntity.ok(savedProgram);
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
    
    @GetMapping("/topics/{programId}")
    public List<CourseTopicDTO> getTopicsWithStatusAndAveragePercentage(@PathVariable Integer programId) {
        return programService.getTopicsWithStatusAndAveragePercentage(programId);
    }
}