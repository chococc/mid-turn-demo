package com.trainingorg.midturndemo.bean.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StudentClassEntity {
    protected String studentId;
    protected String studentName;
    protected String classId;
    protected String evaluationState;
    protected String grade;
}
