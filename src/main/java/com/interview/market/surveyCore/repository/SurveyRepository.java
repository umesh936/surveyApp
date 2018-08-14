package com.interview.market.surveyCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.market.surveyCore.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long>{

}
