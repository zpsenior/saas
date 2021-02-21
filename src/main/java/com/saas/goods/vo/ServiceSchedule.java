package com.saas.goods.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type
public class ServiceSchedule {

	@Field
	private Date appointDate;
	
	@Field
	private List<ServiceItem> items;

	public void addItem(ServiceItem item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
	}
}
