package com.michal.lottochecker.mainLotto;

import com.google.gson.annotations.SerializedName;

public class LottoPost {
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
