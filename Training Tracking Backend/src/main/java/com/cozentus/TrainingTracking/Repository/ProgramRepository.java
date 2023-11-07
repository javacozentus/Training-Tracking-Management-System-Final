package com.cozentus.TrainingTracking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cozentus.TrainingTracking.Model.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer>{
	
	  @Query("SELECT MAX(p.programId) FROM Program p")
	    Integer findMaxProgramId();
	  
	    // Define a custom query to fetch programs by program IDs
	    @Query("SELECT p FROM Program p WHERE p.programId IN :programIds")
	    List<Program> findProgramsByProgramIds(@Param("programIds") List<Integer> programIds);

	
	
}
