package com.example.aneshkagoyal.stela;

public class UserStudent {
    public UserStudent(){

    }
    public UserStudent(String name, String eroll, String branch, String year, String email,String mobile,String address) {
        this.name = name;
        this.eroll = eroll;
        this.branch = branch;
        this.year = year;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        //this.courses=;
    }


    public String name,eroll,branch,year,email,mobile,address;
    public String[] courses;


}
