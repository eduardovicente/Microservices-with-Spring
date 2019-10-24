import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import { User } from "../../model/user";
import { Log } from "../../model/log";
import { Ip } from "../../model/ip";
import { Course } from "../../model/course";
import { CourseService } from "../../services/course.service";
import { LogService } from "../../services/log.service";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  courseId: string;
  currentCourse: Course;
  currentLog: Log;
  courseHitCount: any;
  displayedColumns: string[] = ['name'];
  dataSource: MatTableDataSource<string> = new MatTableDataSource();

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  constructor(private courseService: CourseService, private logService: LogService,
    private router: Router, private route: ActivatedRoute) {
    this.currentCourse = JSON.parse(localStorage.getItem('currentCourse'));
  }

  ngOnInit() {
    this.route.paramMap.subscribe(
      params => {
        if (params.has("id")) {
          this.courseId = params.get("id");
          this.currentLog.courseId = this.courseId;
          this.createLog();
          this.showSummary();
        }
      }
    );
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  createLog() {
    this.logService.getIpClient().subscribe(
      (data: Ip) => {
        this.currentLog.ip = data.ip;
        this.hit(this.currentLog.ip);
      }
    );
  }

  hit(ip) {
    this.logService.createLog(this.currentLog).subscribe(
      data => {
        console.log("hit : " + ip);
      }
    );
  }

  showSummary(){
    this.logService.getSummaryOfCourse(this.courseId).subscribe(
      data => {
        if(data){
          this.courseHitCount = data.hitCount;
        }else{
          this.courseHitCount = 0;
        }
      }
    );
  }

}
