package com.sumit.quizapp.controller;


import com.sumit.quizapp.model.Question;
import com.sumit.quizapp.model.QuestionWrapper;
import com.sumit.quizapp.model.Response;
import com.sumit.quizapp.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String categorie, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(categorie,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response>responses){
        return quizService.calculateResult(id,responses);
    }



}
