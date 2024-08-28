package com.sumit.quizapp.respository;

import com.sumit.quizapp.model.Question;
import com.sumit.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Query("SELECT q FROM Question q WHERE q.categorie = :categorie ORDER BY function('RAND')")
    List<Question> findRandomQuestionsByCategory(@Param("categorie") String categorie);
}
