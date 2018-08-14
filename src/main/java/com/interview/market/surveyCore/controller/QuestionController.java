package com.interview.market.surveyCore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.market.surveyCore.dto.QuestionDTO;
import com.interview.market.surveyCore.services.QuestionService;

@RestController
@RequestMapping("/mgmt/questions/v1")
public class QuestionController {

	@Resource
	QuestionService questionService;

	@PostMapping("")
	public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questiondto) {
		return new ResponseEntity<Long>(questionService.createQuestion(questiondto), HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<?> getAllQuestion(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<List<QuestionDTO>>(questionService.getAllQuestion(page, size), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateQuestionId(@PathVariable("id") Long id, @RequestBody QuestionDTO questiondto) {
		Long newId = questionService.updateQuestion(id, questiondto);
		if (newId != id)
			return new ResponseEntity<Long>(newId, HttpStatus.CREATED);
		else
			return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getQuestionId(@PathVariable("id") Long id) {
		QuestionDTO questiondto= questionService.getQuestionDetail(id);
		if(questiondto==null)
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDTO>(questiondto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteQuestionId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
