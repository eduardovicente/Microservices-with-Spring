package com.vicentemartinez.microservicelogmanagement.service;

import java.util.List;

import com.vicentemartinez.microservicelogmanagement.model.Log;
import com.vicentemartinez.microservicelogmanagement.model.Summary;

public interface LogService {

	Log saveOrUpdate(Log log);

	Summary saveOrUpdate(Summary summary);

	List<Summary> findPopularCourses();

	Summary findSummaryByCourseId(Long courseId);

}
