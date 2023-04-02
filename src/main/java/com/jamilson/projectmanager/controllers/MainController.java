package com.jamilson.projectmanager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jamilson.projectmanager.dto.ProjectDTO;
import com.jamilson.projectmanager.models.LoginUser;
import com.jamilson.projectmanager.models.Project;
import com.jamilson.projectmanager.models.Task;
import com.jamilson.projectmanager.models.User;
import com.jamilson.projectmanager.services.ProjectService;
import com.jamilson.projectmanager.services.TaskService;
import com.jamilson.projectmanager.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private ProjectService mainServ;
	
	@Autowired
	private TaskService taskServ;

	
	// DashBoard
	@GetMapping("/dashboard") // All Serfs
	public String index(HttpSession session, Model model, @ModelAttribute("task") Task task) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}

		Long userId = (Long) session.getAttribute("user_id");
		User userLog = userServ.getUser(userId);
		model.addAttribute("userLog", userLog);
//		List<Project> allSerfs = mainServ.findAllByOrderByTitleAsc();
//		model.addAttribute("allSerfs", mainServ.getAll());
		model.addAttribute("allSerfs", mainServ.findAllByOrderByTitleAsc());
		
		return "dashboard.jsp";
	}
	
	
	@GetMapping("/projetos")
	public ResponseEntity<List<Project>> getProjetos() {
	    return ResponseEntity.status(HttpStatus.OK).body(mainServ.getAll());
	}
	
	@PostMapping("/projeto")
	public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
	    Project project = new Project();
	    project.setTitle(projectDTO.getTitle());
	    project.setDescription(projectDTO.getDescription());
	    project.setDate(projectDTO.getDate());
	    project.setCreatedAt(projectDTO.getCreatedAt());

	    Project createdProject = mainServ.createOne(project);
	    return ResponseEntity.ok(createdProject);
	}
	
	
	// Show One Serf
	@GetMapping("/serf/show/{id}")
	public String showOneSerf(@PathVariable("id") Long id, @ModelAttribute("project") Project project, Model model, HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long) session.getAttribute("user_id");

		model.addAttribute("userLog", userServ.getUser(userId));
		model.addAttribute("project", mainServ.getOne(id));
		return "showSerf.jsp";
	}
	


	// Create Serf Form
	@GetMapping("/newSerfForm")
	public String newSerfForm(@ModelAttribute("projectForm") Project project, HttpSession session, Model model) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("user_id");
		User userLog = userServ.getUser(userId);
		model.addAttribute("userLog", userLog);
		return "createSerf.jsp";
	}

	// Create Serf Process
	@PostMapping("/create/serf")
	public String createSerf(@Valid @ModelAttribute("projectForm") Project newSerf, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			 return "createSerf.jsp";
		}
		
		Long userId = (Long) session.getAttribute("user_id");
		User creator = userServ.getUser(userId);


		List<User> users = newSerf.getOwners();
//		newSerf.setOwners(creator);
//		users.add(creator);
//		newSerf.setOwner(userServ.getUser(userId));
		mainServ.createOne(newSerf);
		return "redirect:/dashboard";
	}
	
	
	// Edit Serf Form
	@GetMapping("/edit/serf/{id}")
	public String editSerfForm(@PathVariable("id") Long id, @ModelAttribute("project") Project serf, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long) session.getAttribute("user_id");
		User userLog = userServ.getUser(userId);
		model.addAttribute("userLog", userLog);
		
		model.addAttribute("project", mainServ.getOne(id));
		return "editSerf.jsp";
		
	}
	
	// Edit Serf Process
	@PutMapping("/edit/serf/proc/{id}")
	public String updateSerf(@PathVariable("id") Long id, @Valid @ModelAttribute("project") Project serf, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {

			Long userId = (Long) session.getAttribute("user_id");
			User userLog = userServ.getUser(userId);
			
			model.addAttribute("userLog", userLog);
//			model.addAttribute("project", mainServ.getOne(id));
			return "editSerf.jsp";
		}
		Project editedProj = mainServ.getOne(id);
		List<User> users = editedProj.getOwners();

		Long userId = (Long) session.getAttribute("user_id");
//		User creator = userServ.getUser(userId);
		
//		users.add(creator);
		serf.setOwners(users);
		mainServ.updateOne(serf);
		return "redirect:/serf/show/{id}";
	}
	

	// Delete Serf
	@GetMapping("/delete/serf/{id}")
	public String deleteSerf(@PathVariable("id") Long id) {
		mainServ.deleteOne(id);
		return "redirect:/dashboard";
	}


	// Side form
	@GetMapping("/newSideForm/{id}")
	public String newSideForm(@PathVariable("id") Long id, @ModelAttribute("task") Task task, Model model, HttpSession session) {	  
//		List<Project> everything = mainServ.getAll();
		Long userId = (Long) session.getAttribute("user_id");

		model.addAttribute("userLog", userServ.getUser(userId));
//		model.addAttribute("everything", everything);
		model.addAttribute("main", mainServ.getOne(id));
		return "createSide.jsp";
	}

	// Create Side Processing with MAIN dropdown
	@PostMapping("/create/side/{id}")
	public String createSide(@PathVariable("id") Long id, @Valid @ModelAttribute("task") Task access, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User userLog = userServ.getUser(userId);
		
		Project main = mainServ.getOne(id);
		//  	Long questionId = obj.getQuestion().getId();
		if(result.hasErrors()) {
			List<Project> everything = mainServ.getAll();

			
			model.addAttribute("userLog", userServ.getUser(userId));
//			model.addAttribute("everything", everything);
			model.addAttribute("main", mainServ.getOne(id));
			return "createSide.jsp";
	}
		taskServ.createOne(access);

		return String.format("redirect:/serf/show/" + id);
	}


	// Add Task
	@PostMapping("/addTask")
	private String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result, HttpSession session, Model model, @RequestParam("main") Long id) {
		if(result.hasErrors()) {
			Long userId = (Long) session.getAttribute("user_id");
			User userLog = userServ.getUser(userId);
			model.addAttribute("userLog", userLog);
//			model.addAttribute("main", mainServ.getOne(id));
//			model.addAttribute("allSerfs", mainServ.getAll());
			return "createSide.jsp";
		}
		Long userId = (Long) session.getAttribute("user_id");
//		Project mainAdder = mainServ.getOne(id);
//		List<Task> mainTasks = mainAdder.getTasks();
//		User userAdder = userServ.getUser(userId);
//		List<Task> userTasks = userAdder.getTasks();
//		userTasks.add(task);
//		mainTasks.add(task);
//		task.setMainOwner(mainAdder);
//		task.setUserTask(userAdder);

//		task.setUserTask(userServ.getUser(userId));
//		task.setMainOwner(mainServ.getOne(id));

		taskServ.createOne(task);
		return "redirect:/serf/show/" + id;
		
	}
	
	// Remove Task
	@GetMapping("/removeTask/{id}")
	private String removeTask(@PathVariable("id") Long id, @RequestParam("project") Project project) {

		Long mainId = project.getId();
		System.out.println(mainId);

		taskServ.deleteOne(id);
		return "redirect:/serf/show/" + mainId;
		
	}
	
 @GetMapping("/")
 public String loginPage(Model model) {
     model.addAttribute("newUser", new User());
     model.addAttribute("newLogin", new LoginUser());
     return "login.jsp";
 }
 
 // Create User Process
 @PostMapping("/registerUser")
 public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
 	userServ.register(newUser, result);
     if(result.hasErrors()) {
         model.addAttribute("newLogin", new LoginUser());
         return "login.jsp";
     }
     session.setAttribute("user_id", newUser.getId());
     return "success.jsp";
 }
 
 // Login User Process
 @PostMapping("/loginUser")
 public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
     User user = userServ.login(newLogin, result);
     if(result.hasErrors()) {
         model.addAttribute("newUser", new User());
         return "login.jsp";
     }
     session.setAttribute("user_id", user.getId());
     return "redirect:/dashboard";
 }
 
 // Show One User
 @GetMapping("/user/show/{id}")
 public String showUser(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long) session.getAttribute("user_id");
		model.addAttribute("userLog", userServ.getUser(userId));
 	model.addAttribute("user", userServ.getUser(id));
     return "showSerf.jsp";
 }
 
 // Logout User
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// Delete User
	@GetMapping("/delete/user/{id}")
	public String deleteSUser(@PathVariable("id") Long id) {
		userServ.deleteOne(id);
		return "redirect:/dashboard";
	}
	
	
	// User M2M Serf Process
	@GetMapping("/borrow/{id}")
	public String RSVP(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User borrower = userServ.getUser(userId);

		Project borrowed = mainServ.getOne(id);
		mainServ.addExtra(borrower, borrowed);
		
		return "redirect:/dashboard";
	}
	
	
	// User M2M Serf Process undo
	@GetMapping("/unborrow/{id}")
	public String unRSVP(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User borrower = userServ.getUser(userId);

		Project borrowed = mainServ.getOne(id);
		mainServ.removeExtra(borrower, borrowed);
		
		return "redirect:/dashboard";
	}

}
