package com.interview.market.surveyCore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.interview.market.surveyCore.cache.CacheImpl;
import com.interview.market.surveyCore.dto.OptionDTO;
import com.interview.market.surveyCore.dto.QuestionDTO;
import com.interview.market.surveyCore.exception.QuestionNotFoundException;
import com.interview.market.surveyCore.model.Option;
import com.interview.market.surveyCore.model.Question;
import com.interview.market.surveyCore.repository.OptionRepository;
import com.interview.market.surveyCore.repository.QuestionRepository;

@Service
public class QuestionService {

	@Resource
	QuestionRepository questionRepo;
	@Resource
	OptionRepository optionRepo;
	@Resource
	CacheImpl questionCacheImpl;
	@Resource
	Map<Long, List<Long>> surveyCacheImpl;

	public Long createQuestion(QuestionDTO questiondto) {
		Question question = new Question();
		question.setQuestionText(questiondto.getQuestiontext());
		for (OptionDTO optionDto : questiondto.getOptions()) {
			Option option = new Option();
			option.setOptionText(optionDto.getOptionText());
			option = optionRepo.saveAndFlush(option);
			question.addOption(option);
		}
		question = questionRepo.saveAndFlush(question);
		return question.getId();
	}

	// @CacheEvict(value = "question")
	public Long updateQuestion(Long Id, QuestionDTO questiondto) {
		Optional<Question> questionOption = questionRepo.findById(Id);
		Question question = null;
		if (!questionOption.isPresent())
			question = new Question();
		question.setQuestionText(questiondto.getQuestiontext());
		for (OptionDTO optionDto : questiondto.getOptions()) {
			Option option = new Option();
			option.setOptionText(optionDto.getOptionText());
			option = optionRepo.saveAndFlush(option);
			question.addOption(option);
		}
		question = questionRepo.saveAndFlush(question);
		questionCacheImpl.remove(Id);
		return question.getId();
	}

	public List<QuestionDTO> getAllQuestion(int page, int size) {
		List<QuestionDTO> listToSend = new ArrayList<QuestionDTO>();
		Page<Question> pageResult = questionRepo.findAll(PageRequest.of(page, size));
		pageResult.forEach(question -> listToSend.add(new QuestionDTO(question)));
		return listToSend;
	}

	// @Cacheable("question")
	public QuestionDTO getQuestionDetail(Long id) {
		QuestionDTO objectToSend = questionCacheImpl.get(id);
		if (objectToSend == null) {
			Optional<Question> question = questionRepo.findById(id);
			if (question.isPresent()) {
				objectToSend = new QuestionDTO(question.get());
				questionCacheImpl.set(id, objectToSend);
			}
		}
		return objectToSend;
	}

	public void deleteQuestion(Long id) {
		questionCacheImpl.remove(id);
		Optional<Question> question = questionRepo.findById(id);
		if (question.isPresent()) {
			surveyCacheImpl.remove(question.get().getSurveyId());
			questionRepo.delete(question.get());
		}
	}

	public List<QuestionDTO> getAllQuestionIdBySurveyId(Long surveyId) {
		List<QuestionDTO> questionDtoList = new ArrayList<>();
		List<Long> isInCache = surveyCacheImpl.get(surveyId);
		if (isInCache == null) {
			List<Long> questionIds = new ArrayList<>();
			List<Question> listQuestion = questionRepo.findBySurveyId(surveyId);
			listQuestion.forEach(question -> {
				questionDtoList.add(new QuestionDTO(question));
				questionIds.add(question.getId());
			});
		} else {
			isInCache.forEach(questionId -> questionDtoList.add(getQuestionDetail(questionId)));
		}
		return questionDtoList;
	}

	public void addQuestioninSurvey(Long surveyId, Long questionId) throws QuestionNotFoundException {
		Optional<Question> question = questionRepo.findById(questionId);
		if (question.isPresent()) {
			QuestionDTO objectToSend = new QuestionDTO(question.get());
			questionCacheImpl.set(questionId, objectToSend);
			question.get().setSurveyId(surveyId);
		} else
			throw new QuestionNotFoundException("Question Id not valid :" + questionId);
	}

}
