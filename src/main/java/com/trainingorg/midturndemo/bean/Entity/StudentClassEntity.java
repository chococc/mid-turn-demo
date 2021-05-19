package com.trainingorg.midturndemo.bean.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StudentClassEntity {
    protected String studentid;
    protected String studentname;
    protected String classid;
    protected String evaluationstate;
    protected String grade;
}
