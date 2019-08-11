package com.xiuxian.exp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

public class MainActivity extends AppCompatActivity {
    private Button Culate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        Culate = findViewById(R.id.Caculate);
    }

    public void onClick(View view) {
        try {
            EditText GetVal = findViewById(R.id.levelnow);
            long now = Integer.parseInt(GetVal.getText().toString());
            GetVal = findViewById(R.id.edit_to_level);
            long to = Integer.parseInt(GetVal.getText().toString());
            long ans = 0;
            for (long i = now; i <= to; i++) {
                ans += ((Math.pow(i, 2.5) - 4000) / 100) * 1000;
            }
            GetVal = findViewById(R.id.Eexpnow);
            ans -= Long.parseLong(GetVal.getText().toString());


            double PerExp = 0;
            GetVal = findViewById(R.id.Edongfuexp);
            double dong = Double.parseDouble(GetVal.getText().toString());
            GetVal = findViewById(R.id.Eexp_plus);
            dong *= Double.parseDouble(GetVal.getText().toString()) / 100.0 + 1.0;
            PerExp += dong;

            GetVal = findViewById(R.id.EYouliexp);
            PerExp += Double.parseDouble(GetVal.getText().toString());

            GetVal = findViewById(R.id.Emengyouexp);
            PerExp += Double.parseDouble(GetVal.getText().toString());

            double Time = (double) ans / PerExp / 60;
            int Hour = (int) Time;
            int mini = (int) (Time - Hour) * 60;
            int sec = (int) (Time - Hour - (double) (mini / 60)) * 3600;

            java.util.Date Now = new Date(System.currentTimeMillis());
            Calendar aim = Calendar.getInstance();
            aim.setTime(Now);
            aim.add(Calendar.HOUR, Hour);
            aim.add(Calendar.MINUTE, mini);
            aim.add(Calendar.SECOND, sec);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat OutPut = new SimpleDateFormat("你大概会在yyyy年MM月dd日HH时mm分ss秒闭关，请提前准备迎接天劫到来");
            java.util.Date real = aim.getTime();
            String ansTime = OutPut.format(real);
            TextView Show = findViewById(R.id.Ans);
            Show.setText(ansTime);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "请正确输入",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
