package com.trsystems.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book implements Serializable {
	private static final long serialVersionUID = -5039062784365222648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "author", nullable = false, length = 180)
	private String author;
	
	@Column(name = "title", nullable = false, length = 250)
	private String title;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "launch_date", nullable = false)
	private Date launchDate;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Transient
	private String currency;
	
	@Transient // JPA ignora
	private String environment;

}
