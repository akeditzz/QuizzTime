package com.akeditzz.quizztime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akshay on 25-03-2018.
 */

public class QuizzModel implements Parcelable{

    /**
     * Model to maintain questions and answers
     */

    private String question;
    private int image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private boolean answered;
    private int type; // type to define which layout to be used in view, 0 for radiobuttons, 1 for checkbox, 2 for editext.

    /**
     * Constructor
     * @param question
     * @param image
     * @param option1
     * @param option2
     * @param option3
     * @param option4
     * @param answer
     * @param type
     * @param answered
     */
    public QuizzModel(String question, int image, String option1, String option2, String option3, String option4, String answer, int type,boolean answered) {
        this.question = question;
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.type = type;
        this.answered = answered;
    }

    private QuizzModel(Parcel in) {
        question = in.readString();
        image = in.readInt();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answer = in.readString();
        answered = in.readByte() != 0;
        type = in.readInt();
    }

    public static final Creator<QuizzModel> CREATOR = new Creator<QuizzModel>() {
        @Override
        public QuizzModel createFromParcel(Parcel in) {
            return new QuizzModel(in);
        }

        @Override
        public QuizzModel[] newArray(int size) {
            return new QuizzModel[size];
        }
    };

    public int getType() {
        return type;
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

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeInt(image);
        parcel.writeString(option1);
        parcel.writeString(option2);
        parcel.writeString(option3);
        parcel.writeString(option4);
        parcel.writeString(answer);
        parcel.writeByte((byte) (answered ? 1 : 0));
        parcel.writeInt(type);
    }
}
