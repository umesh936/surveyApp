package com.interview.market.surveyCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.market.surveyCore.model.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {

}
