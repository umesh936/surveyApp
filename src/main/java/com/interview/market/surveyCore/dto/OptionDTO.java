package com.interview.market.surveyCore.dto;

import com.interview.market.surveyCore.model.Option;

import lombok.Data;

@Data
public class OptionDTO {
	private String optionText;
	//private Integer order;

	public OptionDTO() {
	}

	public OptionDTO(Option option) {
		this.optionText = option.getOptionText();
	}
}
