package com.cozentus.TrainingTracking.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentus.TrainingTracking.Model.Batch;
import com.cozentus.TrainingTracking.Repository.BatchRepository;

@Service
public class BatchService {
	@Autowired
	private BatchRepository batchRepository;
	public List<Batch> getAllBatches() {
		return batchRepository.findAll();
	}
	public Optional<Batch> getBatchById(int id) {
		return batchRepository.findById(id);
	}
	public Batch addBatch(Batch batch) {
		return batchRepository.save(batch);
	}
	public Batch updateBatch(Batch batch, int id) {
		batch.setBatchId(id);
		return batchRepository.save(batch);
	}
	public void deleteBatchById(int id) {
		batchRepository.deleteById(id);
	}	

	public Batch findLatestBatch() {
        return batchRepository.findLatestBatch();
    } 
}