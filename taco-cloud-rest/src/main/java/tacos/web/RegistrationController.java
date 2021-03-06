package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.User;
import tacos.data.UserRepository;
import tacos.security.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder){
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String register(Model model) {
		model.addAttribute("registerForm", new RegistrationForm());
		return "registration";
	}
	
	@PostMapping
	public String regiseter(@ModelAttribute RegistrationForm form) {
		User user = form.toUser(form, passwordEncoder);
		userRepo.save(user);
		return "redirect:/login";
	}
}
