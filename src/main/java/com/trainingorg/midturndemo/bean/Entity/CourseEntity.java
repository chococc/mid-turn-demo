package com.trainingorg.midturndemo.bean.Entity;

import lombok.*;

@Data
@Setter
@Getter
public class CourseEntity {

    protected String courseName;
    protected int courseID;
    protected float courseCost;
    protected int courseState;

}
