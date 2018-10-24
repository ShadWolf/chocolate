package org.asalas.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Achat extends AbstractDomainClass {
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Ingredient ingredient;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha = new Date();
	@Column(precision=5, scale=2)
	private Double prixht;
	private Long quantity;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Unity unit;
	
}
