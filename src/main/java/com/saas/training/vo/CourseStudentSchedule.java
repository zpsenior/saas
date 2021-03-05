package com.saas.training.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type
public class CourseStudentSchedule {

	@Field
	private Date appointDate;
	
	@Field
	private List<CourseItem> items;

	public void addItem(CourseItem item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
	}
}
