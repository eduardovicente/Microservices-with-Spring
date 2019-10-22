package com.vicentemartinez.microservicecoursemanagement.service;

import java.util.List;

import com.vicentemartinez.microservicecoursemanagement.model.Course;
import com.vicentemartinez.microservicecoursemanagement.model.Transaction;

public interface CourseService {
	List<Course> allCourses();

	List<Course> filterCoursesByIdList(List<Long> idList);

	List<Course> filterCourses(String content);

	List<Transaction> filterTransactionsOfUser(Long userId);

	List<Transaction> filterTransactionsOfCourse(Long courseId);

	void saveTransaction(Transaction transaction);

	Course findCourseById(Long courseId);
}
