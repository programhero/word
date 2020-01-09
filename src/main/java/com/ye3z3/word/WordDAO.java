package com.ye3z3.word;

import com.ye3z3.word.Word;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordDAO extends CrudRepository<Word, Integer> {

    Page<Word> findAll(Example<Word> example, Pageable pageable);
    List<Word> findAll(Example<Word> example);


    List<Word> findByWordd(String wordd, Sort sort);

}
