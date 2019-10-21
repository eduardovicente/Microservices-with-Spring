package com.vicentemartinez.microservicelogmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicentemartinez.microservicelogmanagement.model.Log;
import com.vicentemartinez.microservicelogmanagement.model.Summary;
import com.vicentemartinez.microservicelogmanagement.service.LogService;

@RestController
@RequestMapping("/service")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/summary")
    public ResponseEntity<?> getSummaryOfCourse(@RequestBody Long courseId){
        return new ResponseEntity<>(logService.findSummaryByCourseId(courseId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveLog(@RequestBody Log log){
        log.setLogDate(LocalDateTime.now());
        logService.saveOrUpdate(log);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/popular")
    public ResponseEntity<?> findPopularCourses(){
        List<Long> idList = null;
        List<Summary> populars = logService.findPopularCourses();
        if(populars!=null && !populars.isEmpty()){
            idList =populars.parallelStream().map(s->s.getCourseId()).collect(Collectors.toList());
        }
        return ResponseEntity.ok(idList);
    }
}