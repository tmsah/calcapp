package com.tmsah.calcapp;

import android.os.Handler;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
//import android.view.animation.Animation.AnimationListener;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView sort;
    TextView sum;
    TextView num;
    TextView average;
    TextView editText;

    float result;  // 計算結果
    int numNum;
    int sumNum;
    boolean isOnesPlace = false;
    boolean doneSort = false;
    List<Integer> list = new ArrayList<>();
    String br = System.getProperty("line.separator");  //改行コード
    Animation outAnimation;

    //初期化
    public void initialize(){
        result = 0;
        numNum = 0;
        sumNum = 0;
        isOnesPlace = false;
        doneSort = false;
        list.clear();

        textView.setText("input:---");
        sort.setText("sorted:---");
        sum.setText("sum:---");
        num.setText("num:---");
        average.setText("ave:---");
        editText.setText("");

        outAnimation= (Animation) AnimationUtils.loadAnimation(this, R.anim.out_animation);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        sort = (TextView) findViewById(R.id.sort);
        sum = (TextView) findViewById(R.id.sum);
        num = (TextView) findViewById(R.id.num);
        average = (TextView) findViewById(R.id.average);
        editText= (TextView) findViewById(R.id.edittext);

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
        findViewById(R.id.button_clear).setOnClickListener(buttonClearListener);
        findViewById(R.id.button_undo).setOnClickListener(buttonUndoListener);
        findViewById(R.id.button_sort).setOnClickListener(buttonSortListener);

        initialize();
    }

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
                numNum++;
                sumNum+=value;
                outputResults();
//                editText.startAnimation(outAnimation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        editText.setText("");
                    }
                }, 200);
            }
            isOnesPlace = !isOnesPlace;
        }
    };
    View.OnClickListener buttonSortListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sort.setText("sorted:");
            displayList(sort, descentSort(list));
            doneSort = true;
        }
    };
    View.OnClickListener buttonUndoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        if (list.size() == 0) return;
        if (list.size() == 1){
            initialize();
        }
        else {
            int value = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            numNum--;
            sumNum -= value;
            textView.setText("input:");
            displayList(textView, list);
            if (doneSort) {
                sort.setText("sorted:");
                displayList(sort, descentSort(list));
            }
            outputResults();
        }
        }
    };

    View.OnClickListener buttonClearListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initialize();
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

    public void outputResults(){
        result = (float) sumNum / numNum;
        sum.setText("sum:" + String.valueOf(sumNum));
        num.setText("num:" + String.valueOf(numNum));
        average.setText("ave:" + String.format("%.1f", result));
    }

}
