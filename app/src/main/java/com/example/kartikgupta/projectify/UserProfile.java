package com.example.kartikgupta.projectify;

/**
 * Created by debbyhsu on 12/6/17.
 */


public class UserProfile  {
    public String skill, interest, experience, designation, moreInfo;

    public UserProfile () {
    }

    public UserProfile (String skill, String interest, String experience, String designation, String moreInfo) {
        this.skill = skill;
        this.interest = interest;
        this.experience = experience;
        this.designation = designation;
        this.moreInfo = moreInfo;
    }
}