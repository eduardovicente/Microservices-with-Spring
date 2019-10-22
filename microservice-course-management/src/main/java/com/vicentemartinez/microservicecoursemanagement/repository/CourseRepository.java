package com.vicentemartinez.microservicecoursemanagement.repository;

import java.util.List;

import com.vicentemartinez.microservicecoursemanagement.model.Course;

public interface CourseRepository extends IGenericDao<Course> {
	List<Course> filterCourses(String text);
    List<Course> filterCoursesByIdList(List<Long> idList);
}
