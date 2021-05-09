package tacos;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo
			, TacoRepository tacoRepo, OrderRepository orderRepo) {
		
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				
				Ingredient ingre1 = new Ingredient("FLTO","Flour Tortilla", Type.WRAP);
				Ingredient ingre2 = new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES);
				Ingredient ingre3 = new Ingredient("LETC","Lecttuce", Type.VEGGIES);
				Ingredient ingre4 = new Ingredient("CHED","Cheddar", Type.CHEESE);
				
				repo.save(new Ingredient("FLTO","Flour Tortilla", Type.WRAP));
				repo.save(new Ingredient("COTO","Corn Tortilla", Type.WRAP));
				repo.save(new Ingredient("GRBF","Ground Beef", Type.PROTEIN));
				repo.save(new Ingredient("CARN","Carnitas", Type.PROTEIN));
				repo.save(new Ingredient("TMTO","Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("LETC","Lecttuce", Type.VEGGIES));
				repo.save(new Ingredient("CHED","Cheddar", Type.CHEESE));
				repo.save(new Ingredient("JACK","Monterrey Jack", Type.CHEESE));
				repo.save(new Ingredient("SLSA","Salsa", Type.SAUCE));
				repo.save(new Ingredient("SRCR","Sour Cream", Type.SAUCE));
				
				Taco taco = new Taco("taco1",Arrays.asList(ingre1,ingre2,ingre3,ingre4),1L,new Date());
				
				tacoRepo.save(taco);
				tacoRepo.save(new Taco("taco2",Arrays.asList(ingre1,ingre2,ingre3,ingre4),2L,new Date()));
				tacoRepo.save(new Taco("taco3",Arrays.asList(ingre1,ingre2,ingre3,ingre4),3L,new Date()));
				tacoRepo.save(new Taco("taco4",Arrays.asList(ingre1,ingre2,ingre3,ingre4),4L,new Date()));
				tacoRepo.save(new Taco("taco5",Arrays.asList(ingre1,ingre2,ingre3,ingre4),5L,new Date()));
				tacoRepo.save(new Taco("taco6",Arrays.asList(ingre1,ingre2,ingre3,ingre4),6L,new Date()));
				tacoRepo.save(new Taco("taco7",Arrays.asList(ingre1,ingre2,ingre3,ingre4),7L,new Date()));
				tacoRepo.save(new Taco("taco8",Arrays.asList(ingre1,ingre2,ingre3,ingre4),8L,new Date()));
				tacoRepo.save(new Taco("taco9",Arrays.asList(ingre1,ingre2,ingre3,ingre4),9L,new Date()));
				tacoRepo.save(new Taco("taco10",Arrays.asList(ingre1,ingre2,ingre3,ingre4),10L,new Date()));
				
				orderRepo.save(new Order("new Order요청",
						"어딘가의 거리","서울","서울시","02-001","1234-1234-1234","12/23","000",
						1L,new Date(),Arrays.asList(taco),null));
				}
			};
	}
}
