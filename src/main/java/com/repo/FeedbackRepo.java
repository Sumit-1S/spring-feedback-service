package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.FeedbackEntity;

@Repository
public interface FeedbackRepo extends CrudRepository<FeedbackEntity, Integer> {
	@Modifying
	@Query("update FeedbackEntity f set f.feedbackAcknowledge = :value")
	void updateFeedBack(@Param("value") Boolean feedbackAcknowledge);

	List<FeedbackEntity> findByfeedbackAcknowledge(Boolean feedbackAcknowledge);

	List<FeedbackEntity> findByfeedbackFrom(String feedbackFrom);
}