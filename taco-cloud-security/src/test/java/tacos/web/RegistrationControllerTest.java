package tacos.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.User;
import tacos.security.RegistrationForm;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = tacos.security.SecurityConfig.class)
public class RegistrationControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Test
	public void securityTest() throws Exception {
		RegistrationForm form = RegistrationForm.builder()
												.username("mari")
												.password("1111")
												.fullname("마리")
												.phone("11111111")
												.city("서울")
												.state("어딘가1")
												.street("거리1")
												.zip("00000")
												.build();
		User user = form.toUser(form, passwordEncoder);
		ObjectMapper objectMapper = new ObjectMapper();		
		String content = objectMapper.writeValueAsString(user);
		mockMvc.perform(post("/register")
				.content(content))
				
				.andDo(print());
	}
}
