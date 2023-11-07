package com.cozentus.TrainingTracking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cozentus.TrainingTracking.Model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer>{
	 @Query("SELECT b FROM Batch b WHERE b.batchId = (SELECT MAX(batchId) FROM Batch)")
	    Batch findLatestBatch();

}
