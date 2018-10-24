package org.asalas.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class persistent_logins {
	@Id
	@GeneratedValue
	private String series;
	private String username;
	private String token;
	private LocalDateTime last_used;
}
