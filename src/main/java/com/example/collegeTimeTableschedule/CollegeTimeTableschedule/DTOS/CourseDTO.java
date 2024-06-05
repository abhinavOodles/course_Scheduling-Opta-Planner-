package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private String subject ;
    private String teacher;
    private String studentGroup ;

}
