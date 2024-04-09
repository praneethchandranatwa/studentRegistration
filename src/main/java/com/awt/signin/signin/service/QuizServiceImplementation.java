package com.awt.signin.signin.service;

import com.awt.signin.signin.entity.*;
import com.awt.signin.signin.repository.*;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImplementation implements QuizService {
   @Autowired
    private QuizRepository quizRepository;
   @Autowired
    private QuizHistoryRepository quizHistoryRepository;
@Autowired
private RegistrationRepository registrationRepository;
@Autowired
private SubSectionRepository subSectionRepository;
@Autowired
private QuestionRepository questionRepository;
@Autowired
private OptionRepository optionRepository;


    @Autowired
    public QuizServiceImplementation(QuizRepository quizRepository, QuizHistoryRepository quizHistoryRepository, RegistrationRepository registrationRepository) {
        this.quizRepository = quizRepository;
        this.quizHistoryRepository = quizHistoryRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Quiz createQuiz(Long subSectionId, Quiz quiz, List<Question> questions, List<Choices> choices) {
        SubSection subSection = (SubSection) subSectionRepository.findBySubSectionId(subSectionId).orElse(null);

        if (subSection != null) {
            quiz.setSubSection(subSection);


            Quiz savedQuiz = quizRepository.save(quiz);


            for (Question question : questions) {
                question.setQuiz(savedQuiz);


                Question savedQuestion = questionRepository.save(question);

                for (Choices choice : savedQuestion.getChoices()) {
                    choice.setQuestion(savedQuestion);
                    optionRepository.save(choice);
                }
            }
            return savedQuiz;
        } else {
            throw new IllegalArgumentException("Invalid subSectionId: " + subSectionId);
        }
    }



    @Override
    public Quiz getQuizById(Long quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }

    @Override
    public Quiz updateQuiz(Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(updatedQuiz.getQuizId()).orElse(null);
        if (existingQuiz != null) {
            existingQuiz.setTitle(updatedQuiz.getTitle());
            existingQuiz.setQuestions(updatedQuiz.getQuestions());
            existingQuiz.setSubSection(updatedQuiz.getSubSection());

            return quizRepository.save(existingQuiz);
        }
        return null;
    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();

    }

    @Override
    public List<Quiz> getAllQuizzesBySubSectionId(Long subSectionId) {
        return quizRepository.findAllBySubSection_SubSectionId(subSectionId);

    }

    @Override
    public QuizHistory submitQuiz(Long quizId, Long userId, Map<Long, Integer> questionToOptionMap) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        Registration registration = registrationRepository.findById(userId).orElse(null);

        if (quiz == null || registration == null) {
            return null;
        }

        List<Question> questions = quiz.getQuestions();

        int score = 0;

        for (Question question : questions) {
            int correctOptionIndex = question.getCorrectOptionIndex();
            int userSelectedOptionIndex = questionToOptionMap.getOrDefault(question.getQuestionId(), -1);

            if (userSelectedOptionIndex == correctOptionIndex) {
                score++;
            }
        }
        QuizHistory quizHistory = new QuizHistory();
        quizHistory.setSubmissionDate(LocalDateTime.now());
        quizHistory.setQuiz(quiz);
        quizHistory.setRegistration(registration);
        quizHistory.setScore(score);

        return quizHistoryRepository.save(quizHistory);
    }


}

