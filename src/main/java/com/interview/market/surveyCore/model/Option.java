package com.interview.market.surveyCore.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "options")
public class Option {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "option_text")
	String optionText;

	@ManyToOne
	Question question;

}
