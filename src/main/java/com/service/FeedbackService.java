package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.FeedbackEntity;


public interface FeedbackService {
	public	void addFeedback(FeedbackEntity feedback);

	public List<FeedbackEntity> getAllUnacknowledgedFeeback();

	public List<FeedbackEntity> getAllAcknowledgedFeeback();

	public List<FeedbackEntity> feedbackByOwner(String feedbackFrom);

	public String UpdateFeedback(FeedbackEntity entity);
}
