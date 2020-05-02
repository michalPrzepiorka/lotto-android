package com.michal.lottochecker.lottoPlus;

import com.google.gson.annotations.SerializedName;

public class LottoPlusPost {
    private String id;
    private String date;
    @SerializedName("numbers")
    private String numbers;

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getNumbers() {
        return numbers;
    }
}
