package com.jamilson.projectmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamilson.projectmanager.models.Task;
import com.jamilson.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	
	// Get All
	public List<Task> getAll() {
		return taskRepo.findAll();
	}
	
	// Get One
	public Task getOne(Long id) {
		return taskRepo.findById(id).orElse(null);
	}
	
	// Create
	public Task createOne(Task task) {
		return taskRepo.save(task);
	}
	
	
	// Update
	public Task updateOne(Task task) {
		return taskRepo.save(task);
	}
	
	// Delete
	public void deleteOne(Long id) {
		taskRepo.deleteById(id);
	}

}
