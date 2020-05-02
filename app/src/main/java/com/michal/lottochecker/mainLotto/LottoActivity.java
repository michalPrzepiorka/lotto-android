package com.michal.lottochecker.mainLotto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.michal.lottochecker.InputFilterMinMax;
import com.michal.lottochecker.R;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LottoActivity extends AppCompatActivity {
    private TextView textViewResult;
    private TextView calendarTextView;
    private TextView dateDraw;
    private TextView correctHittedNumbers;
    private TextView amountOfHittedNumbers;
    private CalendarView calendar;
    private Button allResultBtn;
    private Button checkDrawBtn;
    private EditText firstNum;
    private EditText secondNum;
    private EditText thirdNum;
    private EditText fourthNum;
    private EditText fifthNum;
    private EditText sixthNum;

    private void setUiViews() {
        textViewResult = findViewById(R.id.textViewResult);
        calendarTextView = findViewById(R.id.calendarTextView);
        dateDraw = findViewById(R.id.dateDraw);
        correctHittedNumbers = findViewById(R.id.hittedNumbers);
        amountOfHittedNumbers = findViewById(R.id.amountNumbers);
        allResultBtn = findViewById(R.id.allResultBtn);
        calendar = findViewById(R.id.calendarView);
        checkDrawBtn = findViewById(R.id.checkDrawBtn);
        firstNum = findViewById(R.id.firstNumID);
        secondNum = findViewById(R.id.secondNumID);
        thirdNum = findViewById(R.id.thirdNumID);
        fourthNum = findViewById(R.id.fourthNumID);
        fifthNum = findViewById(R.id.fifthNumID);
        sixthNum = findViewById(R.id.sixthNumID);

        firstNum.setFilters(new InputFilter[]{new InputFilterMinMax("1", "49")});
        secondNum.setFilters(new InputFilter[]{new InputFilterMinMax("1", "49")});
        thirdNum.setFilters(new InputFilter[]{new InputFilterMinMax("1", "49")});
        fourthNum.setFilters(new InputFilter[]{new InputFilterMinMax("1", "49")});
        fifthNum.setFilters(new InputFilter[]{new InputFilterMinMax("1", "49")});
        sixthNum.setFilters(new InputFilter[]{new InputFilterMinMax("1", "49")});
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.164:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiLotto jsonPlaceHolderApiLotto = retrofit.create(JsonPlaceHolderApiLotto.class);
        Call<List<LottoPost>> call = jsonPlaceHolderApiLotto.getPosts();

        call.enqueue(new Callback<List<LottoPost>>() {
            @Override
            public void onResponse(@NotNull Call<List<LottoPost>> call, @NotNull Response<List<LottoPost>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<LottoPost> lottoPosts = response.body();
                if (lottoPosts != null) {
                    for (LottoPost lottoPost : lottoPosts) {
                        String content = "";
                        content += "Date: " + lottoPost.getDate() + "\n";
                        content += "Numbers: " + lottoPost.getNumbers() + "\n\n";

                        textViewResult.append(content);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<LottoPost>> call, @NotNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getData(String date, List<Integer> list) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.164:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiLotto jsonPlaceHolderApiLotto = retrofit.create(JsonPlaceHolderApiLotto.class);
        Call<LottoDateHelper> call = jsonPlaceHolderApiLotto.getData(new LottoDateHelper(date, list));

        call.enqueue(new Callback<LottoDateHelper>() {
            @Override
            public void onResponse(@NotNull Call<LottoDateHelper> call, @NotNull Response<LottoDateHelper> response) {
                LottoDateHelper body = response.body();
                if (body != null) {
                    dateDraw.setText(body.getDrawDate());
                    correctHittedNumbers.setText(body.getHittedNumbers());
                    amountOfHittedNumbers.setText(body.getAmountOfHittedNumbers());
                    Snackbar.make(amountOfHittedNumbers, "Checking!", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<LottoDateHelper> call, @NotNull Throwable t) {
                Snackbar.make(firstNum, "No draw for the selected date!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeListeners() {
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = String.format(new Locale("pl"), "%02d-%02d-%4d", dayOfMonth, (month + 1), year);
                calendarTextView.setText(date);
            }
        });

        allResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosts();
            }
        });

        checkDrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> numbersToCheckList = new LinkedList<>();
                try {
                    numbersToCheckList.add(Integer.parseInt(firstNum.getText().toString()));
                    numbersToCheckList.add(Integer.parseInt(secondNum.getText().toString()));
                    numbersToCheckList.add(Integer.parseInt(thirdNum.getText().toString()));
                    numbersToCheckList.add(Integer.parseInt(fourthNum.getText().toString()));
                    numbersToCheckList.add(Integer.parseInt(fifthNum.getText().toString()));
                    numbersToCheckList.add(Integer.parseInt(sixthNum.getText().toString()));

                    String date = calendarTextView.getText().toString();
                    getData(date, numbersToCheckList);
                } catch (Exception e) {
                    Snackbar.make(v, "Please insert numbers!", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setCustomToolbarView(){
        Toolbar toolbar = findViewById(R.id.mCustomToolbar);
        AppCompatImageView img = toolbar.findViewById(R.id.toolbarImageView);
        img.setImageResource(R.drawable.lotto);
        TextView title = toolbar.findViewById(R.id.toolbarTitle);
        title.setText("Lotto Checker");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);
        setUiViews();
        initializeListeners();
        setCustomToolbarView();
    }
}
