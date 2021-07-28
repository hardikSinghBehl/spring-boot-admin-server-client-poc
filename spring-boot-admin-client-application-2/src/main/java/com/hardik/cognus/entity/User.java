package com.hardik.cognus.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 3984633609189421628L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "email_id", nullable = false)
	private String emailId;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	void setUp() {
		this.id = this.id == null ? UUID.randomUUID() : this.id;
		this.createdAt = LocalDateTime.now();
	}

}
