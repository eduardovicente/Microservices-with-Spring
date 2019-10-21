package com.vicentemartinez.microservicelogmanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vicentemartinez.microservicelogmanagement.model.Log;
import com.vicentemartinez.microservicelogmanagement.model.Summary;
import com.vicentemartinez.microservicelogmanagement.repository.LogRepository;
import com.vicentemartinez.microservicelogmanagement.repository.SummaryRepository;

@Transactional
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogRepository logRepository;

	@Autowired
	private SummaryRepository summaryRepository;

	@Override
	public Log saveOrUpdate(Log log) {
		Summary existSummary = summaryRepository.findByCourseId(log.getCourseId()).orElse(null);
		if (existSummary != null) {
			summaryRepository.delete(existSummary);//In cassandra we can only update non primary keys values this is why delete is called
			existSummary.setHitCount(existSummary.getHitCount() + 1);
			summaryRepository.save(existSummary);
		} else {
			Summary summary = new Summary();
			summary.setCourseId(log.getCourseId());
			summary.setHitCount(1L);
			summaryRepository.save(summary);
		}
		log.setId(UUID.randomUUID());
		logRepository.save(log);
		return log;
	}

	@Override
	public Summary saveOrUpdate(Summary summary) {
		summaryRepository.save(summary);
		return summary;
	}

	@Override
	public List<Summary> findPopularCourses() {
		return summaryRepository.findPopularCourses();
	}
	
	@Override
	public Summary findSummaryByCourseId(Long courseId) {
		return summaryRepository.findByCourseId(courseId).orElse(null);
	}
}
