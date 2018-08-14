package com.interview.market.surveyCore.dto;

import java.util.ArrayList;
import java.util.List;

import com.interview.market.surveyCore.model.Survey;

import lombok.Data;

@Data
public class SurveyDTO {

	private Long surveyId;
	private String surveyName;
	List<QuestionDTO> questions;

	public SurveyDTO(Survey survey) {
		this.surveyId = survey.getId();
		this.surveyName = survey.getName();
	}

	public SurveyDTO() {
	}

	public List<QuestionDTO> getQuestions() {
		if (questions == null)
			questions = new ArrayList<QuestionDTO>();
		return questions;
	}

	public void addQuestion(QuestionDTO questionDto) {
		if (questions == null)
			questions = new ArrayList<QuestionDTO>();
		questions.add(questionDto);
	}
}
