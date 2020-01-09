package com.ye3z3.word;

import com.ye3z3.word.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private WordDAO wordDAO;

	@Test
	void contextLoads() {
		List<Word> byWordd = wordDAO.findByWordd("apple", Sort.by(Sort.Direction.DESC, "lastUpdatedTime"));
		System.out.println(byWordd);
/*		for (int i = 0; i < 30; i++) {
			Word word = new Word();
//			word.setName("许湘岳" + i);
//			word.setPhone("1375188001" + i);
			wordDAO.save(word);
		}
//		List<Word> words = wordDAO.findByNameLike("湘岳");
//		System.out.println(words);
		ExampleMatcher matcher1 = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("name", matcher -> matcher.ignoreCase().contains());
		Word prode = new Word();
//		prode.setName("湘岳");
		PageRequest pageRequest = PageRequest.of(1, 10,Sort.by("id").descending());
		Page<Word> all = wordDAO.findAll(Example.of(prode, matcher1), pageRequest);
		System.out.println(all.getContent());
		new Specification<Word>() {

			@Override
			public Predicate toPredicate(Root<Word> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				return null;
			}
		};*/
	}

}
