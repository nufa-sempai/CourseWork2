package com.example.coursework_2.impl;

import org.springframework.stereotype.Service;
import com.example.coursework_2.exception.IncorrectAmountOfQuestions;
import com.example.coursework_2.model.Question;
import com.example.coursework_2.service.ExaminerService;
import com.example.coursework_2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new IncorrectAmountOfQuestions();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRundomQuestion());
        }
        return result;
    }
}