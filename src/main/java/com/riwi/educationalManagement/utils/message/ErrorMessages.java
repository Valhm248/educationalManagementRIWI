package com.riwi.educationalManagement.utils.message;

public class ErrorMessages {
    public static final String RequiredUsername = "The username is required";
    
    public static final String RequiredPassword= "Password is required";
    
    public static final String RequiredEmail= "Email is required";
    
    public static final String RequiredLessonTitle= "Lesson title is required";
    
    public static final String RequiredCourseName= "Course name is required";
    
    public static final String RequiredAssignmentTitle= "Assignment title is required";
    
    public static String IdNotFound(String entity) {

        final String message = "There are no records with entity %s with the supplied id";
        return String.format(message, entity);
    }
}
