package com.trsystems.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO implements Serializable {
	private static final long serialVersionUID = 7623651704749695020L;

	private Long id;
	
	private String from;
	
	private String to;
	
	private BigDecimal conversionFactory;
	
	@Transient // JPA ignora
	private BigDecimal conversionValue;
	
	@Transient // JPA ignora
	private String environment;

}
