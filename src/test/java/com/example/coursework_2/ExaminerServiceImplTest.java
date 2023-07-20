package com.example.coursework_2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.coursework_2.exception.IncorrectAmountOfQuestions;
import com.example.coursework_2.model.Question;
import com.example.coursework_2.service.QuestionService;
import com.example.coursework_2.impl.ExaminerServiceImpl;

import java.util.Collection;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

	@Mock
	private QuestionService questionService;

	@InjectMocks
	private ExaminerServiceImpl examinerService;

	private final Collection<Question> questions = Set.of(
			new Question("Вопрос 1", "Ответ 1"),
			new Question("Вопрос 2", "Ответ 2"),
			new Question("Вопрос 3", "Ответ 3")
	);

	@Test
	public void getQuestionsNegativeTest() {
		when(questionService.getAll()).thenReturn(questions);

		Assertions.assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
				.isThrownBy(() -> examinerService.getQuestions(-1));
		Assertions.assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
				.isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
	}

	@Test
	public void getQuestionsTest() {
		when(questionService.getAll()).thenReturn(questions);

		when(questionService.getRundomQuestion()).thenReturn(
				new Question("Вопрос 3", "Ответ 3"),
				new Question("Вопрос 3", "Ответ 3"),
				new Question("Вопрос 2", "Ответ 2"),
				new Question("Вопрос 1", "Ответ 1")
		);

		Assertions.assertThat(examinerService.getQuestions(2))
				.hasSize(2)
				.containsExactlyInAnyOrder(
						new Question("Вопрос 3", "Ответ 3"),
						new Question("Вопрос 2", "Ответ 2")
				);
	}
}