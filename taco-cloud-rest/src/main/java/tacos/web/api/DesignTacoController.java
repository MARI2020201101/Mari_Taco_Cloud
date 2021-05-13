package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/design",produces={"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class DesignTacoController {
	private TacoRepository tacoRepo;
	private OrderRepository orderRepo;
	
	@Autowired
	public DesignTacoController(TacoRepository tacoRepo
			,OrderRepository orderRepo ) {
		this.tacoRepo=tacoRepo;
		this.orderRepo=orderRepo;
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/recent")
	public CollectionModel<EntityModel<Taco>> recentTacos(){
		PageRequest page = PageRequest.of(
				0, 3, Sort.by("createdAt").descending() );
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		CollectionModel<EntityModel<Taco>> recentModels = CollectionModel.wrap(tacos);
		
		recentModels.add(
				WebMvcLinkBuilder
				
				
				.linkTo(DesignTacoController.class)
				
				.slash("recent")
				.withRel("recents"));
				
				//Link.of("http://localhost:8080/design/recent/","recents"));
		return recentModels;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> recentTaco(@PathVariable Long id) {
		/*
		 * return tacoRepo.findById(id).orElseThrow( ()->new
		 * IllegalArgumentException("Not Found : "+ id +" Taco"));
		 */
		Optional<Taco> optTaco = tacoRepo.findById(id);
		return optTaco.isPresent()? new ResponseEntity<Taco>(optTaco.get(),HttpStatus.OK)
				: new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco){
		return tacoRepo.save(taco);
	}
	
	@PutMapping(path = "/{orderId}", consumes = "application/json")
	public Order putOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}
	
	@PatchMapping(path = "/{orderId}", consumes = "application/json" )
	public Order patchOrder(@PathVariable Long orderId, @RequestBody Order patch) {
		Order order=orderRepo.findById(orderId).orElse(new Order());
		if(patch.getCcCVV()!=null) {
			order.setCcCVV(patch.getCcCVV());
		}
		if(patch.getCcExpiration()!=null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if(patch.getCcNumber()!=null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if(patch.getDeliveryCity()!=null) {
			order.setDeliveryCity(patch.getDeliveryCity());
		}
		if(patch.getDeliveryName()!=null) {
			order.setDeliveryName(patch.getDeliveryName());
		}
		if(patch.getDeliveryState()!=null) {
			order.setDeliveryState(patch.getDeliveryState());
		}
		if(patch.getDeliveryStreet()!=null) {
			order.setDeliveryStreet(patch.getDeliveryStreet());
		}
		if(patch.getDeliveryZip()!=null) {
			order.setDeliveryZip(patch.getDeliveryZip());
		}
		return orderRepo.save(order);
	}
	
	@DeleteMapping("/{orderId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable Long orderId) {
		try{
			orderRepo.deleteById(orderId);
		}catch(Exception e) {}
	}
}
