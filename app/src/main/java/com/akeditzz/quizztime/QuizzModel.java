package com.akeditzz.quizztime;

/**
 * Created by Akshay on 25-03-2018.
 */

public class QuizzModel {

    private String question;
    private int image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private int type; // type to define which layout to be used in view, 0 for radiobuttons, 1 for checkbox, 2 for editext.
    private int correctanswersforcheckbox;



    public QuizzModel(String question, int image, String option1, String option2, String option3, String option4, String answer,int type) {
        this.question = question;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.type = type;
    }

    public QuizzModel(String question, int image, String option1, String option2, String option3, String option4, String answer, int type, int correctanswersforcheckbox) {
        this.question = question;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.type = type;
        this.correctanswersforcheckbox = correctanswersforcheckbox;
    }

    public int getType() {
        return type;
    }

    public int getCorrectanswersforcheckbox() {
        return correctanswersforcheckbox;
    }

    public String getQuestion() {
        return question;
    }

    public int getImage() {
        return image;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }
}
