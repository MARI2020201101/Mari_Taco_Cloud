package tacos;

import static org.hamcrest.CoreMatchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void homeTest() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(content().string(containsString("Welcome")))
			.andExpect(view().name("home"))
			.andExpect(status().isOk());
	}

}
