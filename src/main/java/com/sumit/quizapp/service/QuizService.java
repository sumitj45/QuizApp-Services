package com.sumit.quizapp.service;

import com.sumit.quizapp.model.Question;
import com.sumit.quizapp.model.QuestionWrapper;
import com.sumit.quizapp.model.Quiz;
import com.sumit.quizapp.model.Response;
import com.sumit.quizapp.respository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String categorie, int numQ, String title) {
        List<Question> questions = quizRepository.findRandomQuestionsByCategory(categorie);

        if (questions.size() > numQ) {
            questions = questions.subList(0, numQ);
        }

        if (questions.isEmpty() || questions.size() < numQ) {
            return new ResponseEntity<>("Not enough questions available in the category.", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setQuestionTittle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created successfully.", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (!quiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if quiz not found
        }

        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (Question q : questionFromDB) {
            // Log the questionTittle to check if it's null
            System.out.println("Question ID: " + q.getId() + " - Question Title: " + q.getQuestionTittle());

            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTittle(), // Check if this is null
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz>quiz=quizRepository.findById(id);
         List<Question> questions= quiz.get().getQuestions();

         int right=0;
         int i=0;
         for(Response response:responses){
             if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                 right++;

             i++;
         }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

