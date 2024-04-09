package com.awt.signin.signin.controller;

import com.awt.signin.signin.entity.Choices;
import com.awt.signin.signin.entity.Question;
import com.awt.signin.signin.entity.Quiz;
import com.awt.signin.signin.entity.QuizHistory;
import com.awt.signin.signin.service.QuizServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
@Autowired
    private QuizServiceImplementation quizServiceImplementation;

    @Autowired
    public QuizController(QuizServiceImplementation quizServiceImplementation) {
        this.quizServiceImplementation = quizServiceImplementation;
    }

    @PostMapping("/quiz")
    public Quiz createQuiz(@RequestParam Long subSectionId, @RequestBody Quiz quiz, @RequestBody List<Question> questions, @RequestBody List<Choices> choices) {
        return quizServiceImplementation.createQuiz(subSectionId, quiz, questions, choices);
    }


    @GetMapping("/{quizId}")
    public Quiz getQuizById(@PathVariable Long quizId) {
        return quizServiceImplementation.getQuizById(quizId);
    }

    @PutMapping("/{quizId}")
    public Quiz updateQuiz(@PathVariable Long quizId, @RequestBody Quiz updatedQuiz) {
        updatedQuiz.setQuizId(quizId);
        return quizServiceImplementation.updateQuiz(updatedQuiz);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId) {
        quizServiceImplementation.deleteQuiz(quizId);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizServiceImplementation.getAllQuizzes();
    }

    @GetMapping("/Subsection/{subSectionId}")
    public ResponseEntity<List<Quiz>> getAllQuizzesBySubSectionId(@PathVariable Long subSectionId) {
        List<Quiz> quizzes = quizServiceImplementation.getAllQuizzesBySubSectionId(subSectionId);
        if (quizzes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(quizzes);
        }
    }

    @PostMapping("/{quizId}/submit")
    public QuizHistory submitQuiz(
            @PathVariable Long quizId,
            @RequestParam Long userId,
            @RequestBody Map<Long, Integer> questionToOptionMap) {
        return quizServiceImplementation.submitQuiz(quizId, userId, questionToOptionMap);
    }


}
