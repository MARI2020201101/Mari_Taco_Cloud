package tacos.web.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DesignTacoControllerTest {
	
	MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext ctx;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void findByIdTest() throws Exception {
		mockMvc.perform(get("/design/1"))
			.andExpect(status().isOk());
				//.andDo(print());
	}
	
	@BeforeEach
	public void beforeTest() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilter(new CharacterEncodingFilter("UTF-8", true)).build();
	}
	
	@AfterEach
	public void afterTest() {
		//orderRepo.delete(order);
	}
	
	@Test
	public void postTacoTest() throws Exception{	
		Ingredient ingre1 = new Ingredient("FLTO","Flour Tortilla", Type.WRAP);
		Taco taco = new Taco("taco11",Arrays.asList(ingre1),11L,new Date());
		
		/*
		 * Order order = new Order("new Order요청",
		 * "어딘가의 거리","서울","서울시","02-001","1234-1234-1234","12/23","000", 1L,new
		 * Date(),Arrays.asList(taco),null);
		 */
		/*
		 * Gson gson = new Gson(); String jsonTaco =gson.toJson(taco);
		 */
		
		String jsonTaco = objectMapper.writeValueAsString(taco);
		mockMvc.perform(post("/design").contentType("application/json").content(jsonTaco))
				.andExpect(status().is2xxSuccessful());
		//.andDo(print());
				
	}
	
	@Test
	public void patchOrderTest() throws Exception{
		Order patch = new Order();
		patch.setDeliveryName("patch Test");
		
		String jsonPatch = objectMapper.writeValueAsString(patch);
		mockMvc.perform(patch("/design/11")
					.contentType("application/json")
					.content(jsonPatch))
			.andExpect(status().is2xxSuccessful());
			//.andDo(print());
		
	}
	@Test
	public void getTacoTest() throws Exception{
		mockMvc.perform(get("/design/recent"))
			.andDo(print());
	}
}
