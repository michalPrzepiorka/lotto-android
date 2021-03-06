package com.michal.lottochecker.mainLotto;

import java.util.List;

public class LottoDateHelper {
    private String date;
    private List<Integer> numbers;

    public LottoDateHelper(String date, List<Integer> numbers) {
        this.date = date;
        this.numbers = numbers;
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getDrawDate() {
        return date;
    }

    public String getHittedNumbers() {
        return numbers.toString()
                .replace("[", "")
                .replace("]", "");
    }

    public String getAmountOfHittedNumbers() {
        return String.valueOf(numbers.size());
    }
}
