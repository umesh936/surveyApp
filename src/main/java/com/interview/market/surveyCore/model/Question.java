package com.interview.market.surveyCore.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "question_text")
	String questionText;

	@Column(name = "survey_id")
	Long surveyId;

	@OneToMany
	@JoinColumn(name = "question_id")
	List<Option> options;

	public void addOption(Option option) {
		if (options == null)
			options = new ArrayList<>();
		options.add(option);

	}
}
