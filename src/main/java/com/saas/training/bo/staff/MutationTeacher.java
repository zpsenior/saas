package com.saas.training.bo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.pub.BOBase;
import com.saas.training.dao.DAOTeacher;
import com.saas.training.vo.Teacher;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component("Staff.MutationTeacher")
public class MutationTeacher extends BOBase {
	
	@Autowired
	private DAOTeacher teacher;

	
	public boolean addTeacher(Teacher params) throws Exception{
		teacher.addTeacher(params);
		return true;
	}

	
	public boolean updateTeacher(Teacher params) throws Exception{
		teacher.updateTeacher(params);
		return true;
	}

}
