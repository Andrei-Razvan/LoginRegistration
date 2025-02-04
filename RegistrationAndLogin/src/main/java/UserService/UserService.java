package UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import Entity.User;
import UserRepository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public Optional<User> loginUser(String email, String password){
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			boolean passwordMatch = passwordEncoder.matches(password, user.get().getPassword()) ;
		if(passwordMatch) {
			System.out.println("Login succesfuly");
			return user;
		} else {
			System.out.println("Passord does not match");
		}
		} else {
			System.out.println("Email not found");
		}
		return Optional.empty();
}
}
