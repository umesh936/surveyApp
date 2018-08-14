package com.interview.market.surveyCore.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.market.surveyCore.dto.SurveyDTO;
import com.interview.market.surveyCore.model.Survey;
import com.interview.market.surveyCore.services.SurveyService;

@RestController
@RequestMapping("/mgmt/survey/v1/")
public class SurveyController {

	@Resource
	SurveyService surveyService;

	@PostMapping(value = { "{name}" })
	public ResponseEntity<?> createSurvey(@PathVariable("name") String surveyName) {
		if (!Survey.isValidName(surveyName))
			return new ResponseEntity<String>("Name is not valid", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<SurveyDTO>(surveyService.createSurvey(surveyName), HttpStatus.CREATED);
	}

	@GetMapping("{sid}")
	public ResponseEntity<?> getSurvey(@PathVariable("sid") Long id) {
		SurveyDTO dto = surveyService.getSurvey(id);
		if (dto == null)
			return new ResponseEntity<String>("Id is not valid", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<SurveyDTO>(dto, HttpStatus.CREATED);
	}

	@PutMapping("{sid}")
	public ResponseEntity<?> updateSurvey(@PathVariable("sid") Long surveyId,
			@RequestParam("questionId") Long questionId) {
		try {
			surveyService.updateSurvey(surveyId, questionId);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
