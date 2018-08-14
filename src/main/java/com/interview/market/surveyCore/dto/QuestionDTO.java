package com.interview.market.surveyCore.dto;

import java.util.ArrayList;
import java.util.List;

import com.interview.market.surveyCore.model.Question;

import lombok.Data;

@Data
public class QuestionDTO {

	private String questiontext;
	private List<OptionDTO> options;

	public List<OptionDTO> getOptions() {
		if (options == null)
			options = new ArrayList<OptionDTO>();
		return options;
	}

	public QuestionDTO(Question question) {
		this.questiontext = question.getQuestionText();
		question.getOptions().forEach(option -> this.options.add(new OptionDTO(option)));
	}
}
