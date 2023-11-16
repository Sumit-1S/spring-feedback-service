package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.FeedbackEntity;
import com.service.FeedbackService;

@RestController
@RequestMapping("/feedbackService")
@CrossOrigin(origins = "http://localhost:3000/")
public class AppController {

	@Autowired
	private FeedbackService service;

	@PostMapping("/addFeedback")
	public String addFeedback(@RequestBody FeedbackEntity entity) {
		service.addFeedback(entity);
		return "Service Added";
	}

	@GetMapping("/getUnacknowledge")
	private List<FeedbackEntity> getAllUnacknowledgedFeeback() {
		return service.getAllUnacknowledgedFeeback();
	}

	@GetMapping("/getAcknowledge")
	private List<FeedbackEntity> getAllAcknowledgedFeeback() {
		return service.getAllAcknowledgedFeeback();
	}

	@GetMapping("/feedbackByOwner/{feedbackFrom}")
	private List<FeedbackEntity> feedbackByOwner(@PathVariable String feedbackFrom) {
		return service.feedbackByOwner(feedbackFrom);
	}

	@PostMapping("/updateFeedback")
	private String updateFeeback(@RequestBody FeedbackEntity entity) {
		service.UpdateFeedback(entity);
		return "Updated";
	}

}
