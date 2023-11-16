package com.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FeedbackEntity {
	@Id
	@GeneratedValue
private Integer feedbackId;
private String feedbackTitle;
private String feedbackFrom;
private String feedbackBody;
private Date feedbackDate;
private Boolean feedbackAcknowledge;
}
