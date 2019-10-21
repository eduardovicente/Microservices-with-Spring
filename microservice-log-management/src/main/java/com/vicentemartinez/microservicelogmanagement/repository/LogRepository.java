package com.vicentemartinez.microservicelogmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.vicentemartinez.microservicelogmanagement.model.Log;

public interface LogRepository extends CrudRepository<Log, Long>{

}
