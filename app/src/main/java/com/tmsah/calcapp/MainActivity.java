package com.tmsah.calcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView sum;
    TextView num;
    TextView average;
    EditText editText;
    Button button;

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            recentOperator = R.id.button_equal;
            result = 0;
            numNum = 0;
            sumNum = 0;
            isTensPlace = true;
            isOperatorKeyPushed = false;

            textView.setText("input:---");
            sum.setText("sum:---");
            num.setText("num:---");
            average.setText("ave:---");
            editText.setText("");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        sum = (TextView) findViewById(R.id.sum);
        num = (TextView) findViewById(R.id.num);
        average = (TextView) findViewById(R.id.average);
        editText= (EditText) findViewById(R.id.edittext);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonListener);

        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_00).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);
    }

    int recentOperator = R.id.button_equal; // 最近押された計算キー
    float result;  // 計算結果
    boolean isOperatorKeyPushed;    // 計算キーが押されたことを記憶
    int numNum;
    int sumNum;
    boolean isTensPlace = true;
    String br = System.getProperty("line.separator");  //改行コード

    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button operatorButton = (Button) view;
            float value = Float.parseFloat(editText.getText().toString());
            if (recentOperator == R.id.button_equal) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);
                editText.setText(String.valueOf(result));
            }

            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };
    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            editText.append(button.getText());

            if (isTensPlace) {
                isTensPlace = false;
            }
            else{
                int value = Integer.parseInt(editText.getText().toString());
                if (numNum == 0) {
                    textView.setText("input:" + br + String.valueOf(value));
                }
                else {
                    textView.append(br + String.valueOf(value));
                }
                editText.setText("");
                numNum++;
                sumNum+=value;
                result = (float) sumNum / numNum;
                sum.setText("sum:" + String.valueOf(sumNum));
                num.setText("num:" + String.valueOf(numNum));
                average.setText("ave:" + String.format("%.1f", result));
                isTensPlace = true;
            }

            isOperatorKeyPushed = false;
        }
    };
    float calc(int operator, float value1, float value2) {
        switch (operator) {
            case R.id.button_add:
                return value1 + value2;
            case R.id.button_subtract:
                return value1 - value2;
            case R.id.button_multiply:
                return value1 * value2;
            case R.id.button_divide:
                return value1 / value2;
            default:
                return value1;
        }
    }
}
