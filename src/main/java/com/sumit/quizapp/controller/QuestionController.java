package com.sumit.quizapp.controller;


import com.sumit.quizapp.model.Question;
import com.sumit.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return  questionService.getAllQuestions();
    }

    @GetMapping("/{categorie}")
    public ResponseEntity<List<Question>> getQuestionByCategorie(@PathVariable String categorie){
        return questionService.getQuestionByCategorie(categorie);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable("id") Integer id) {
        return questionService.getQuestionById(id);
    }

    //add
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
     return   questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public Question deleteById(@PathVariable("id")Integer id){
        return questionService.deleteById(id);
    }


}
