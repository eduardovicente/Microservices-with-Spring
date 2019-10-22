package com.vicentemartinez.microservicecoursemanagement.repository;

import java.util.List;

import com.vicentemartinez.microservicecoursemanagement.model.Transaction;

public interface TransactionRepository extends IGenericDao<Transaction> {
	List<Transaction> findAllTransactionsOfUser(Long userId);
	List<Transaction> findAllTransactionsOfCourse(Long courseId);
}
