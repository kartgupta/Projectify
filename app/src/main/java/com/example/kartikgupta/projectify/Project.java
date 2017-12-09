package com.example.kartikgupta.projectify;

import android.widget.Button;

/**
 * Created by Ali on 12/6/17.
 */

public class Project {
    public String projectName, projectRole, projectDescription, projectApplicants, projectOwner;
    public Boolean projectAvailable = true;


    public Project(String projectName, String projectRole, String projectDescription, String projectApplicants, Boolean projectAvailable, String projectOwner ) {
        this.projectName = projectName;
//        this.projectCategory = projectCategory;
        this.projectRole = projectRole;
        this.projectDescription = projectDescription;
        this.projectApplicants = projectApplicants;
        this.projectAvailable = projectAvailable;
        this.projectOwner = projectOwner;
    }

    public Project(){

    }


}
