package AuthController;
 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Entity.User;
import UserService.UserService;


 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<?> registerUser (@RequestBody User user){ // For Registration
		try {
			User registeredUser = userService.registerUser(user);
		return ResponseEntity.ok("User register succesfuly");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error" + e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password){ // For Login
		Optional<User>user = userService.loginUser(email, password);
		if(user.isPresent()) {
			return ResponseEntity.ok("Succesfuly login");
		} 
		return ResponseEntity.status(401).body("Invalid email or password");
	}
}