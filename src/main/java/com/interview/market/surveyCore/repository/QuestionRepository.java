package com.interview.market.surveyCore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.market.surveyCore.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	public Page<Question> findAll(Pageable pageRequest);

	public List<Question> findBySurveyId(Long surveyId);
}
