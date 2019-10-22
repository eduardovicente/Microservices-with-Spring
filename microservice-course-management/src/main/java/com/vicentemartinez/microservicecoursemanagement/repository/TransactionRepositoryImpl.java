package com.vicentemartinez.microservicecoursemanagement.repository;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vicentemartinez.microservicecoursemanagement.model.Transaction;

@Transactional
@Repository
public class TransactionRepositoryImpl extends AbstractGenericDao<Transaction> implements TransactionRepository {
	@Override
	public List<Transaction> findAllTransactionsOfUser(final Long userId) {
		String hql = "Select t from Transaction t Where t.userId = :pUserId";
		Query query = em.createQuery(hql);
		query.setParameter("pUserId", userId);
		return query.getResultList();
	}

	@Override
	public List<Transaction> findAllTransactionsOfCourse(final Long courseId) {
		String hql = "Select t from Transaction t Where t.course.id = :pCourseId";
		Query query = em.createQuery(hql);
		query.setParameter("pCourseId", courseId);
		return query.getResultList();
	}
}
