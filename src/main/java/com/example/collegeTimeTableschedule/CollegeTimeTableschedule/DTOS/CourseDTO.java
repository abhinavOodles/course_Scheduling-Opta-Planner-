package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CourseDTO {
    private String subject ;
    private String teacher;
    private String studentGroup ;

}
