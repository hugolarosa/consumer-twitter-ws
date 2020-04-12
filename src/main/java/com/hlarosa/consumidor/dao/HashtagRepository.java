package com.hlarosa.consumidor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hlarosa.consumidor.dto.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>{
	
	@Query(value = " SELECT TEXT, COUNT(TEXT) AS CONT FROM HASHTAG GROUP BY TEXT ORDER BY COUNT(TEXT) DESC LIMIT :num ",
			nativeQuery = true)
	public List<Object[]> hashtagsMoreUsed(@Param("num") int num);

}
