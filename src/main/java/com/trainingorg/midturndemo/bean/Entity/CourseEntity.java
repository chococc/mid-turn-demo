package com.trainingorg.midturndemo.bean.Entity;

public class CourseEntity {

    protected String courseName;
    protected int courseID;
    protected float courseCost;
    protected int courseState;

    public void setCourseName(String CourseName) {
        this.courseName = CourseName;
    }

    public void setCourseID(int CourseID) {
        this.courseID = CourseID;
    }

    public void setCourseCost(float CourseCost) {
        this.courseCost = CourseCost;
    }

    public void setCourseState(int CourseState) {
        this.courseState = CourseState;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public float getCourseCost() {
        return courseCost;
    }

    public int getCourseState() {
        return courseState;
    }
}
