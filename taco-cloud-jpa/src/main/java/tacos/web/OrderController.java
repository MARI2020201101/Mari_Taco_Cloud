package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;
	
	@Autowired
	OrderController(OrderRepository orderRepo){
		this.orderRepo=orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		//model.addAttribute("order",new Order()); order를 세션에서 받아온다
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order,
			Errors errors, SessionStatus sessionStatus) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		log.info("Order submitted: " + order);
		orderRepo.save(order);
		log.info("Order saved: " + order);
		sessionStatus.setComplete();
		
		return"redirect:/";
	}
	
}
