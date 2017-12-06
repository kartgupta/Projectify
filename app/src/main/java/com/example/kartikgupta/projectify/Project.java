package com.example.kartikgupta.projectify;

/**
 * Created by Ali on 12/6/17.
 */

public class Project {
    public String projectName, projectCategory, projectRole, projectDescription, projectSkills;

    public Project(String projectName, String projectCategory, String projectRole, String projectDescription ) {
        this.projectName = projectName;
        this.projectCategory = projectCategory;
        this.projectRole = projectRole;
        this.projectDescription = projectDescription;
    }

    public Project(){

    }


}
