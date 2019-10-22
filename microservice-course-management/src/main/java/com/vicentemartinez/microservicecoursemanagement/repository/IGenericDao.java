package com.vicentemartinez.microservicecoursemanagement.repository;

import java.util.List;

import org.hibernate.Session;

import com.vicentemartinez.microservicecoursemanagement.model.IModel;

public interface IGenericDao<T extends IModel> {
	T find(Long id);

	List<T> findAll();

	void save(T entity);

	T update(T entity);

	void delete(Long id);

	Session getSession();

}
