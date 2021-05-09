package tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
@Component
public class OrderProps {

	@Min(value=5)
	@Max(value=25)
	private int pageSize=10;
}
