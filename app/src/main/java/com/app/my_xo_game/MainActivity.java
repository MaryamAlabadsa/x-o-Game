package com.app.my_xo_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Size;
import android.view.View;
import android.widget.Toast;

import com.app.my_xo_game.databinding.ActivityMainBinding;
import com.app.my_xo_game.others.Methods;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    private Context context = MainActivity.this;
    Handler handler = new Handler();
    Object box1, box2, box3, box4, box5, box6, box7, box8, box9;
    String lastSign = "x";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        box1 = binding.box1.getTag();
        box2 = binding.box2.getTag();
        box3 = binding.box3.getTag();
        box4 = binding.box4.getTag();
        box5 = binding.box5.getTag();
        box6 = binding.box6.getTag();
        box7 = binding.box7.getTag();
        box8 = binding.box8.getTag();
        box9 = binding.box9.getTag();

        binding.box1.setOnClickListener(this::onClick);
        binding.box2.setOnClickListener(this::onClick);
        binding.box3.setOnClickListener(this::onClick);
        binding.box4.setOnClickListener(this::onClick);
        binding.box5.setOnClickListener(this::onClick);
        binding.box6.setOnClickListener(this::onClick);
        binding.box7.setOnClickListener(this::onClick);
        binding.box8.setOnClickListener(this::onClick);
        binding.box9.setOnClickListener(this::onClick);

    }

    private void checkWinner() {
        if (box1 != null && box1 == box2 && box1 == box3) {
            returnedXorO();
        } else if (box1 != null && box1 == box4 && box7 == box4) {
            returnedXorO();
        } else if (box1 != null && box1 == box5 && box5 == box9) {
            returnedXorO();
        } else if (box2 != null && box2 == box5 && box2 == box8) {
            returnedXorO();
        } else if (box3 != null && box3 == box6 && box3 == box9) {
            returnedXorO();
        } else if (box3 != null && box3 == box5 && box3 == box7) {
            returnedXorO();
        } else if (box6 != null && box6 == box5 && box6 == box4) {
            returnedXorO();
        } else if (box9 != null && box9 == box8 && box9 == box7) {
            returnedXorO();
        } else {
            boolean b = Methods.isTextViewEmpty(binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
            if (!b) {
                restartGame();
            }

        }
    }

    private void returnedXorO() {
        if (lastSign.equals("x")) {
            showWinner("o");
        } else if (lastSign.equals("o")) {
            showWinner("x");
        }
    }

    private void restartGame() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lastSign = "x";
                binding.boxMsg.setText(R.string.winner);
                binding.boxMsg.setVisibility(View.VISIBLE);
                Methods.clearTextView(binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
                Methods.clearTextViewTags(binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
                Methods.enableView(true, binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
                setTagsNull();
            }
        };
        handler.postDelayed(runnable, (long) (2 * 1000));
    }

    private void setTagsNull() {
        box1 = null;
        box2 = null;
        box3 = null;
        box4 = null;
        box5 = null;
        box6 = null;
        box7 = null;
        box8 = null;
        box9 = null;
    }

    private void showWinner(String s) {
        Methods.invisibleView(binding.box1, binding.box2, binding.box3, binding.box4, binding.box6, binding.box7, binding.box8, binding.box9);
        Methods.clearTextView(binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
        Methods.clearTextViewTags(binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
        binding.box5.setText(s);
        binding.boxMsg.setText(R.string.winner);
        binding.boxMsg.setVisibility(View.VISIBLE);
        setTagsNull();
        lastSign = "x";
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                binding.boxMsg.setVisibility(View.INVISIBLE);
                binding.box5.setText("");
                Methods.enableView(true, binding.box1, binding.box2, binding.box3, binding.box4, binding.box5, binding.box6, binding.box7, binding.box8, binding.box9);
                Methods.visibleView(binding.box1, binding.box2, binding.box3, binding.box4, binding.box6, binding.box7, binding.box8, binding.box9);
            }
        };
        handler.postDelayed(runnable, (long) (2 * 1000));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.box_1:
                binding.box1.setText(lastSign);
                binding.box1.setTag(lastSign);
                binding.box1.setEnabled(false);
                box1 = lastSign;
                changeSign();
                checkWinner();
                break;
            case R.id.box_2:
                box2 = lastSign;
                binding.box2.setText(lastSign);
                binding.box2.setTag(lastSign);
                binding.box2.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_3:
                box3 = lastSign;
                binding.box3.setText(lastSign);
                binding.box3.setTag(lastSign);
                binding.box3.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_4:
                box4 = lastSign;
                binding.box4.setText(lastSign);
                binding.box4.setTag(lastSign);
                binding.box4.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_5:
                box5 = lastSign;
                binding.box5.setText(lastSign);
                binding.box5.setTag(lastSign);
                binding.box5.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_6:
                box6 = lastSign;
                binding.box6.setText(lastSign);
                binding.box6.setTag(lastSign);
                binding.box6.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_7:
                box7 = lastSign;
                binding.box7.setText(lastSign);
                binding.box7.setTag(lastSign);
                binding.box7.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_8:
                box8 = lastSign;
                binding.box8.setText(lastSign);
                binding.box8.setTag(lastSign);
                binding.box8.setEnabled(false);
                changeSign();
                checkWinner();
                break;
            case R.id.box_9:
                box9 = lastSign;
                binding.box9.setText(lastSign);
                binding.box9.setTag(lastSign);
                binding.box9.setEnabled(false);
                changeSign();
                checkWinner();
                break;
        }
    }

    private void changeSign() {
        if (lastSign.equals("x"))
            lastSign = "o";
        else
            lastSign = "x";
    }

}