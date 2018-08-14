package com.interview.market.surveyCore.services;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.interview.market.surveyCore.dto.SurveyDTO;
import com.interview.market.surveyCore.model.Survey;
import com.interview.market.surveyCore.repository.SurveyRepository;

@Service
public class SurveyService {

	@Resource
	SurveyRepository suveryRepo;

	@Resource
	QuestionService questionService;

	public SurveyDTO createSurvey(String surveyName) {
		Survey survey = new Survey();
		survey.setName(surveyName);
		survey.setIsActive(true);
		survey = suveryRepo.saveAndFlush(survey);
		return new SurveyDTO(survey);
	}

	public SurveyDTO getSurvey(Long id) {
		Optional<Survey> surveyOption = suveryRepo.findById(id);
		if (!surveyOption.isPresent())
			return null;
		Survey survey = surveyOption.get();
		SurveyDTO surveydto = new SurveyDTO(survey);
		surveydto.getQuestions().addAll(questionService.getAllQuestionIdBySurveyId(survey.getId()));
		return surveydto;
	}

	public void updateSurvey(Long surveyId, Long questionId) throws Exception {
		Optional<Survey> surveyOption = suveryRepo.findById(surveyId);
		if (!surveyOption.isPresent())
			throw new Exception("Survey not found in db " + surveyId);
		questionService.addQuestioninSurvey(surveyId, questionId);
	}
}
