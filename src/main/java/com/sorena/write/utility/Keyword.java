/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sorena.write.utility;

/**
 *
 * @author sorena
 */
public interface Keyword {

    //Collection Name//
    String ACCOUNT_COLLECTION_NAME = "Account";
    String ADMIN_COLLECTION_NAME = "Admin";
    String PAYMENT_COLLECTION_NAME = "Payment";
    String TUITION_COLLECTION_NAME = "Tuition";
    String COURSE_TYPE_COLLECTION_NAME = "CourseType";
    String INSTITUTION_COLLECTION_NAME = "Institution";
    String COURSE_COLLECTION_NAME = "Course";
    String ROLE_COLLECTION_NAME = "Role";
    String PERSON_COLLECTION_NAME = "Person";
    String SEMESTER_COLLECTION_NAME = "Semester";
    String PROGRAM_COLLECTION_NAME = "Program";
    String PROGRAMS_COLLECTION_NAME = "Programs";
    String ROOM_COLLECTION_NAME = "Room";
    String SEMESTERS_COLLECTION_NAME = "Semesters";
    String CLASS_COLLECTION_NAME = "Class";
    String TYPE_COLLECTION_NAME = "Type";

    //DB CONSTANS//
    String MAIN_DB_NAME = "WriteMainDB";
    String IMAGE_DB_NAME = "WriteImageDB";
    String MONGO_IP = "localhost";
    int MONGO_PORT = 27017;
    
    //ROLE NAMES//
    String DEAN_ROLE_NAME_EN = "Dean";
    String DEAN_ROLE_NAME_FA = "مدیر موسسه";
    
    String ARCHIVE_ROLE_NAME_EN = "Archive";
    String ARCHIVE_ROLE_NAME_FA = "کارمند بایگانی";
    
    String NO_PERMISSION_ROLE_NAME_EN = "No Permission";
    String NO_PERMISSION_ROLE_NAME_FA = "بدون دسترسی";
    
    String STUDENT_ROLE_NAME_EN = "Student";
    String STUDENT_ROLE_NAME_FA = "دانشجو";
    
    
    
}
