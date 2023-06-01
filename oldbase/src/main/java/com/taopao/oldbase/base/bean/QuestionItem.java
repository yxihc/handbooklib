package com.taopao.oldbase.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by blacKey on 2015/11/25.
 */
public class QuestionItem implements Parcelable {
    private String correctAnswer;
    private String[] arrayOption;
    private String title;

    public QuestionItem(String correctAnswer, String[] arrayOption, String title) {
        this.correctAnswer = correctAnswer;
        this.arrayOption = arrayOption;
        this.title = title;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getArrayOptions() {
        return arrayOption;
    }

    public void setArrayOption(String[] arrayOption) {
        this.arrayOption = arrayOption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(correctAnswer);

        //这几句话是写数组的,因为数组的长度不确定，所以先确定数组长度，如果为空就不写，但是要把0 给发过去
        //让下面的好判断能不能读数组，也就是说下面如果读到的长度是0，那么就不读数组了，否则就创建相同长度的数组去读
        if (arrayOption == null) {
            out.writeInt(0);
        } else {
            out.writeInt(arrayOption.length);
        }
        //如果数组为空，就可以不写
        if (arrayOption != null) {
            out.writeStringArray(arrayOption);
        }
        out.writeStringArray(arrayOption);
    }

    public static final Creator<QuestionItem> CREATOR = new Creator<QuestionItem>() {
        public QuestionItem createFromParcel(Parcel in) {
            return new QuestionItem(in);
        }

        public QuestionItem[] newArray(int size) {
            return new QuestionItem[size];
        }
    };

    private QuestionItem(Parcel in) {
        title = in.readString();
        correctAnswer = in.readString();

        /** 数组 读出 */
        int length = in.readInt();
        arrayOption = new String[length];
        in.readStringArray(arrayOption);
        in.readStringArray(arrayOption);
    }
}
