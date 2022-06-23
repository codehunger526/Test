package com.agosh.account.test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "First name  cannot be blank")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	private String lastName;

	@NotBlank(message = "DOB cannot be blank")
	private LocalDateTime dateOfBirth;

	@NotBlank(message = "Email cannot be blank")
	@Column(unique=true)
	@Email(message = "Email should be valid")
	private String email;

	public UserEntity() {
		super();
	}

	public UserEntity(@NotNull(message = "First name  cannot be blank") String firstName,
			@NotBlank(message = "Last name cannot be blank") String lastName,
			@NotBlank(message = "DOB cannot be blank") LocalDateTime dateOfBirth,
			@Email(message = "Email should be valid") String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
