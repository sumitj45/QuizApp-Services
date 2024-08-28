package com.sumit.quizapp.service;


import com.sumit.quizapp.model.Question;
import com.sumit.quizapp.respository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public  Question getQuestionById(Integer id){
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question  not found with id:"+id));
    }

    // add
    public  ResponseEntity<String>  addQuestion(Question question){

            questionRepository.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);

    }

    public Question deleteById(Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        questionRepository.deleteById(id);
      return question;
    }

//find by categorie
    public ResponseEntity<List<Question>> getQuestionByCategorie(String categorie) {
       return new ResponseEntity<>(questionRepository.findByCategorie(categorie),HttpStatus.OK);
    }
}
