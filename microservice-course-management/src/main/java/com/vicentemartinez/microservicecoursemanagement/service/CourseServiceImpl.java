package com.vicentemartinez.microservicecoursemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicentemartinez.microservicecoursemanagement.model.Course;
import com.vicentemartinez.microservicecoursemanagement.model.Transaction;
import com.vicentemartinez.microservicecoursemanagement.repository.CourseRepository;
import com.vicentemartinez.microservicecoursemanagement.repository.TransactionRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Course> allCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> filterCoursesByIdList(final List<Long> idList){
        return courseRepository.filterCoursesByIdList(idList);
    }

    @Override
    public List<Course> filterCourses(final String content) {
        return courseRepository.filterCourses(content);
    }

    @Override
    public List<Transaction> filterTransactionsOfUser(final Long userId){
        return transactionRepository.findAllTransactionsOfUser(userId);
    }

    @Override
    public List<Transaction> filterTransactionsOfCourse(final Long courseId){
        return transactionRepository.findAllTransactionsOfCourse(courseId);
    }

    @Override
    public void saveTransaction(final Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Override
    public Course findCourseById(Long courseId){
        return courseRepository.find(courseId);
    }
}