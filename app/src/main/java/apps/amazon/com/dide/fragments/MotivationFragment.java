package apps.amazon.com.dide.fragments;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import apps.amazon.com.dide.activities.HomeActivity;
import apps.amazon.com.dide.R;
import apps.amazon.com.dide.activities.UrgentEmergency;
import de.hdodenhof.circleimageview.CircleImageView;


public class MotivationFragment extends Fragment{

    int i = 0;

    public MotivationFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_motivation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.btn_proceed).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        getView().findViewById(R.id.imageHolder).setOnTouchListener(new View.OnTouchListener() {
            Handler handler = new Handler();

            int numberOfTaps = 0;
            long lastTapTimeMs = 0;
            long touchDownMs = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event){

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        touchDownMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);

                        if((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()){

                            numberOfTaps = 0;
                            lastTapTimeMs = 0;
                            break;
                        }

                        if(numberOfTaps > 0 && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                            numberOfTaps += 1;

                        }

                        else{
                            numberOfTaps = 1;
                        }

                        lastTapTimeMs = System.currentTimeMillis();

                        if (numberOfTaps == 4) {
                            startActivity(new Intent(getActivity().getApplicationContext(), UrgentEmergency.class));
                        }
                }

                return true;
            }
        });


        int[] quotes = {R.drawable.b1, R.drawable.b3, R.drawable.b4, R.drawable.b6,
                        R.drawable.b11, R.drawable.b12, R.drawable.b13, R.drawable.b14,
                        R.drawable.b15, R.drawable.b16, R.drawable.b17, R.drawable.b24,
                        R.drawable.b10, R.drawable.b18, R.drawable.b19, R.drawable.b21,
                        R.drawable.b22, R.drawable.b23, R.drawable.b28, R.drawable.b25,
                        R.drawable.b30, R.drawable.b31, R.drawable.b35};


        final int[] colours = {Color.parseColor("#adfb7777"), Color.parseColor("#a493d5fb"),
                         Color.parseColor("#a45fba88"), Color.parseColor("#a45c2462")};

        ((ImageView) getView().findViewById(R.id.imageHolder)).setImageResource(quotes[((new Random()).nextInt(quotes.length))]);

        new CountDownTimer(500, 1000){
            @Override
            public void onTick(long millisUntilFinished){

            }

            @Override
            public void onFinish(){
                if(i == colours.length){
                    i = 0;
                }
                ((getView().findViewById(R.id.btn_proceed))).setBackgroundColor(colours[i]);
                i++;
                start();
            }
        }.start();

    }

}