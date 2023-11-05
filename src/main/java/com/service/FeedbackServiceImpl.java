package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.FeedbackEntity;
import com.repo.FeedbackRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepo feedbackrepo;

	@Override
	public void addFeedback(@ModelAttribute FeedbackEntity feedback) {
		feedbackrepo.save(feedback);
	}

	@Override
	public List<FeedbackEntity> getAllUnacknowledgedFeeback() {
		return feedbackrepo.findByfeedbackAcknowledge(false);

	}

	@Override
	public List<FeedbackEntity> getAllAcknowledgedFeeback() {
		return feedbackrepo.findByfeedbackAcknowledge(true);
	}

	@Override
	public List<FeedbackEntity> feedbackByOwner(@RequestBody String feedbackFrom) {
		return feedbackrepo.findByfeedbackFrom(feedbackFrom);
		
	}

	@Override
	@Transactional
	public String UpdateFeedback(@ModelAttribute FeedbackEntity entity) {
		feedbackrepo.updateFeedBack(entity.getFeedbackAcknowledge());
		return "Updated";
	}

}
