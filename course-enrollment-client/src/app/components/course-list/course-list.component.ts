import { Component, OnInit, ViewChild } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Course } from "../../model/Course";
import { Transaction } from "./../../model/Transaction";
import { MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses: Array<Course>;
  dataSource: MatTableDataSource<Course> = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  errorMessage: string;
  infoMessage: string;
  searchText: string;
  displayedColumns: string[] = ['detail', 'title', 'author', 'category', 'action'];


  constructor(private courseService: CourseService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {}


  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  detail(course: Course) {
    localStorage.setItem('currentCourse', JSON.stringify(course));
    this.router.navigate(['/course', course.id]);
  }

  enroll(course: Course) {
    var transaction = new Transaction();
    transaction.course = course;
    this.courseService.enroll(transaction)
      .subscribe(data => {
        this.infoMessage = "You enrolled the cource successfully.";
      }, err => {
        this.errorMessage = "Unexpected error is occurred.";
      }
      )
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  findAllCourses() {
    this.courseService.findAllCourses().subscribe(
      data => {
        this.dataSource.data = data;
      }
    );
  }


}
