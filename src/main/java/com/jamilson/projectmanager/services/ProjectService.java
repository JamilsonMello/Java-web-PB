package com.jamilson.projectmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamilson.projectmanager.models.Project;
import com.jamilson.projectmanager.models.Task;
import com.jamilson.projectmanager.models.User;
import com.jamilson.projectmanager.repositories.ProjectRepository;
import com.jamilson.projectmanager.repositories.TaskRepository;
import com.jamilson.projectmanager.repositories.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	
	// Get All
	public List<Project> getAll() {
		return projectRepo.findAll();
	}
	
	
	public List<Project> findAllByOrderByTitleAsc() {
		return projectRepo.findAllByOrderByTitleAsc();
	}
	
	
	
	// Get One
	public Project getOne(Long id) {
		return projectRepo.findById(id).orElse(null);
	}
	
	// Create
	public Project createOne(Project project) {
		return projectRepo.save(project);
	}
	
	
	// Update
	public Project updateOne(Project project) {
		return projectRepo.save(project);
	}
	
	// Delete
	public void deleteOne(Long id) {
		projectRepo.deleteById(id);
	}
	
	
	
	// Many to Many
	
//	// Add -thing-
//	public void addExtra(User user, Project project) {
//		
//		// Get array list of Users from main object
//		List<User> adders = project.getTask();
//		
//		// Add User to list
//		adders.add(user);
//
//		projectRepo.save(project);
//	}
//	
//	// Remove -thing-
//	public void removeExtra(User user, Project project) {
//		
//		// Get array list of Users from main object
//		List<User> adders = project.getLikers();
//		
//		// Add User to list
//		adders.remove(user);
//
//		projectRepo.save(project);
//	}
	
	// Add -thing-
	public void addExtra(User user, Project project) {
//		List<User> users = project.getOwners(); 

		List<Project> projects = user.getProjects();
//		users.add(user);
		projects.add(project);
//		task.setUserTask(user);
		projectRepo.save(project);
		userRepo.save(user);
	}
	
	// Remove -thing-
	public void removeExtra(User user, Project project) {
		
//		List<User> users = project.getOwners(); 
		List<Project> projects = user.getProjects();
		
		projects.remove(project);
//		users.remove(user);
//		task.setUserTask(null);
		projectRepo.save(project);
		userRepo.save(user);
	}
}
