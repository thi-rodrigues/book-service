package com.trsystems.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trsystems.model.dto.ExchangeDTO;

//@FeignClient(name = "exchange-service", url = "localhost:8000")
@FeignClient(name = "exchange-service")
public interface ExchangeProxy {

	@GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
	public ExchangeDTO getExchange(
			@PathVariable BigDecimal amount, 
			@PathVariable String from, 
			@PathVariable String to);
}
