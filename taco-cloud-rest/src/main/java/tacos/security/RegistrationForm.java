package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tacos.User;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public User toUser(RegistrationForm form, PasswordEncoder passwordEncoder) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		return new User(form);
	}
}
