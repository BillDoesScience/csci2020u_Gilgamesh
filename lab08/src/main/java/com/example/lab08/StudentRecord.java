package com.example.lab08;

public class StudentRecord {
    private String StudentID,FinalMark;
    private float Midterm,Assignment,FinalExam;
    private char LetterGrade;

    public StudentRecord(String studentID, float assignment, float midterm, float finalExam) {
        StudentID = studentID;
        Midterm = midterm;
        Assignment = assignment;
        FinalExam = finalExam;

        float FMark = (Midterm * 0.3f) + (assignment * 0.2f) + (FinalExam * 0.5f);
        FinalMark = String.format("%.3f",FMark);

        if (FMark >= 80 && FMark <= 100) {
            LetterGrade = 'A';
        } else if (FMark >= 70 && FMark <= 79) {
            LetterGrade = 'B';
        } else if (FMark >= 60 && FMark <= 69) {
            LetterGrade = 'C';
        } else if (FMark >= 50 && FMark <= 59) {
            LetterGrade = 'D';
        } else {
            LetterGrade = 'F';
        }

    }

    public String getStudentID() {
        return this.StudentID;
    }

    public float getMidterm() {
        return this.Midterm;
    }

    public float getAssignment() {
        return this.Assignment;
    }

    public float getFinalExam() {
        return this.FinalExam;
    }

    public String getFinalMark() {
        return this.FinalMark;
    }

    public char getLetterGrade() {
        return this.LetterGrade;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public void setMidterm(float midterm) {
        Midterm = midterm;
    }

    public void setAssignment(float assignment) {
        Assignment = assignment;
    }

    public void setFinalExam(float finalExam) {
        FinalExam = finalExam;
    }

    public void setFinalMark(String finalMark) {
        FinalMark = finalMark;
    }

    public void setLetterGrade(char letterGrade) {
        LetterGrade = letterGrade;
    }
}
