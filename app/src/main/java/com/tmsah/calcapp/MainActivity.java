package com.tmsah.calcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView sort;
    TextView sum;
    TextView num;
    TextView average;
    EditText editText;
    Button button;

    View.OnClickListener buttonClearListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            result = 0;
            numNum = 0;
            sumNum = 0;
            isOnesPlace = false;
            list.clear();

            textView.setText("input:---");
            sort.setText("sorted:---");
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
        sort = (TextView) findViewById(R.id.sort);
        sum = (TextView) findViewById(R.id.sum);
        num = (TextView) findViewById(R.id.num);
        average = (TextView) findViewById(R.id.average);
        editText= (EditText) findViewById(R.id.edittext);

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
        findViewById(R.id.button_sort).setOnClickListener(buttonSortListener);
        findViewById(R.id.button_undo).setOnClickListener(buttonUndoListener);
        findViewById(R.id.button_clear).setOnClickListener(buttonClearListener);
    }

    float result;  // 計算結果
    int numNum;
    int sumNum;
    boolean isOnesPlace = false;
    List<Integer> list = new ArrayList<>();
    String br = System.getProperty("line.separator");  //改行コード

    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            editText.append(button.getText());

            if (isOnesPlace) {
                int value = Integer.parseInt(editText.getText().toString());
                list.add(value);
                textView.setText("input:");
                displayList(textView, list);
                editText.setText("");
                numNum++;
                sumNum+=value;
                result = (float) sumNum / numNum;
                sum.setText("sum:" + String.valueOf(sumNum));
                num.setText("num:" + String.valueOf(numNum));
                average.setText("ave:" + String.format("%.1f", result));
            }
            isOnesPlace = !isOnesPlace;
        }
    };
    View.OnClickListener buttonSortListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            sort.setText("sorted:");
            displayList(sort, descentSort(list));
        }
    };
    View.OnClickListener buttonUndoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            if (list.size() != 0) {
                int value = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                numNum--;
                sumNum -= value;
                result = (float) sumNum / numNum;
                textView.setText("input:");
                displayList(textView, list);
                sort.setText("sorted:");
                displayList(sort, descentSort(list));
                sum.setText("sum:" + String.valueOf(sumNum));
                num.setText("num:" + String.valueOf(numNum));
                average.setText("ave:" + String.format("%.1f", result));
            }
        }
    };

    public void displayList(TextView text, List<Integer> l) {
        for (int i=0; i<l.size(); i++){
            text.append(br + l.get(i));
        }
    }

    public List<Integer> descentSort(List<Integer> l){
        List<Integer> copy = new  ArrayList<>(l);
        List<Integer> des = new ArrayList<>();
        Collections.sort(copy);
        for (int i=copy.size()-1; i>=0; i--){
            des.add(copy.get(i));
        }
        return des;
    }

}
