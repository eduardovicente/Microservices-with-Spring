import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Course } from "./../../model/Course";
import { Transaction } from "./../../model/Transaction";
import { MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  constructor(private courseService: CourseService, private router: Router) { }

  ngOnInit() {
  }

}
