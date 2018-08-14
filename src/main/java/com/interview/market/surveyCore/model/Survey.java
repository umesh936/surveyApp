package com.interview.market.surveyCore.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "survey")
public class Survey {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "survey_name")
	String name;

	@Column(name = "is_active")
	Boolean isActive;


	public static boolean isValidName(String name) {
		if (name != null && name.length() > 3) {
			return true;
		}
		return false;
	}

}
