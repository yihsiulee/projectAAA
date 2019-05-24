package com.allpass.projectAAA.util;

public enum Study {

    Medicine(0,"醫學"),
    Biology(1,"生科"),
    Business(2,"商科"),
    Information(3,"資訊"),
    Engineer(4,"工程"),
    Society(5,"社會科學");

    private Study(Integer studyInteger,String studyName){
        this.studyInteger=studyInteger;
        this.studyName=studyName;
    }
    private Integer studyInteger;
    private String studyName;

    public static Study getStudy(int i){

        switch(i){
            case 0:
                return Medicine;
            case 1:
                return Biology;
            case 2:
                return Business;
            case 3:
                return Information;
            case 4:
                return Engineer;
            case 5:
                return Society;
            default:
                return null;
        }

    }

    public String getStudyName() {
        return studyName;
    }

    public Integer getStudyInteger() {
        return studyInteger;
    }
}
