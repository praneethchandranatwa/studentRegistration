package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.Choices;
import com.awt.signin.signin.entity.Question;
import com.awt.signin.signin.entity.Quiz;
import com.awt.signin.signin.entity.QuizHistory;

import java.util.List;
import java.util.Map;

public interface QuizService {
    Quiz createQuiz(Long subSectionId, Quiz quiz, List<Question> questions, List<Choices>choices);

    Quiz getQuizById(Long quizId);
     Quiz updateQuiz(Quiz updatedQuiz);
     void deleteQuiz(Long quizId);
     List<Quiz> getAllQuizzes();
     List<Quiz> getAllQuizzesBySubSectionId(Long subSectionId);
     QuizHistory submitQuiz(Long quizId, Long userId, Map<Long, Integer> questionToOptionMap);
}
