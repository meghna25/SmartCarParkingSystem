package com.example.smartcarparkingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LotSelectionModule extends AppCompatActivity {
    FirebaseAuth mfirebaseauth;
    ImageView im1;
    ImageView im2;
    ImageView im3,im4,im5,im6,im7,im8;
    TextView time1;
    TextView mTextViewCountDown;

    private FirebaseAuth.AuthStateListener mauthstatelistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_selection_module);
        im1=findViewById(R.id.im1);
        im2=findViewById(R.id.im2);
        im3=findViewById(R.id.im3);
        im4=findViewById(R.id.im4);
        im5=findViewById(R.id.im5);
        im6=findViewById(R.id.im6);
        im7=findViewById(R.id.im9);
        im8=findViewById(R.id.im10);
        time1=findViewById(R.id.textView12);
        mTextViewCountDown=findViewById(R.id.editTextTime);

        im1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setImageResource(R.drawable.rectangle_15);
                //im1.setClickable(false);
                im2.setClickable(false);
                im3.setClickable(false);
                im4.setClickable(false);
                im5.setClickable(false);
                im6.setClickable(false);
                im7.setClickable(false);
                im8.setClickable(false);
            }
        });
        im2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                //im2.setClickable(false);
                im3.setClickable(false);
                im4.setClickable(false);
                im5.setClickable(false);
                im6.setClickable(false);
                im7.setClickable(false);
                im8.setClickable(false);
                im2.setImageResource(R.drawable.rectangle_15);
            }
        });
        im3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                im2.setClickable(false);
                //im3.setClickable(false);
                im4.setClickable(false);
                im5.setClickable(false);
                im6.setClickable(false);
                im7.setClickable(false);
                im8.setClickable(false);
                im3.setImageResource(R.drawable.rectangle_15);
            }
        });
        im4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                im2.setClickable(false);
                im3.setClickable(false);
                //im4.setClickable(false);
                im5.setClickable(false);
                im6.setClickable(false);
                im7.setClickable(false);
                im8.setClickable(false);
                im4.setImageResource(R.drawable.rectangle_15);
            }
        });
        im5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                im2.setClickable(false);
                im3.setClickable(false);
                im4.setClickable(false);
                //im5.setClickable(false);
                im6.setClickable(false);
                im7.setClickable(false);
                im8.setClickable(false);
                im5.setImageResource(R.drawable.rectangle_15);
            }
        });
        im6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                im2.setClickable(false);
                im3.setClickable(false);
                im4.setClickable(false);
                im5.setClickable(false);
                //im6.setClickable(false);
                im7.setClickable(false);
                im8.setClickable(false);
                im6.setImageResource(R.drawable.rectangle_15);
            }
        });
        im7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                im2.setClickable(false);
                im3.setClickable(false);
                im4.setClickable(false);
                im5.setClickable(false);
                im6.setClickable(false);
                //im7.setClickable(false);
                im8.setClickable(false);
                im7.setImageResource(R.drawable.rectangle_15);
            }
        });
        im8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTime();
                timer();
                im1.setClickable(false);
                im2.setClickable(false);
                im3.setClickable(false);
                im4.setClickable(false);
                im5.setClickable(false);
                im6.setClickable(false);
                im7.setClickable(false);
                //im8.setClickable(false);
                im8.setImageResource(R.drawable.rectangle_15);
            }
        });

    }

    private void timer()
    {
            final long START_TIME_IN_MILLIS = 600000;
            CountDownTimer mCountDownTimer;
            final boolean[] mTimerRunning = new boolean[1];
            final long[] mTimeLeftInMillis = {START_TIME_IN_MILLIS};


                mCountDownTimer = new CountDownTimer(mTimeLeftInMillis[0], 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTimeLeftInMillis[0] = millisUntilFinished;
                        //updateCountDownText();
                        int minutes = (int) (mTimeLeftInMillis[0] / 1000) / 60;
                        int seconds = (int) (mTimeLeftInMillis[0] / 1000) % 60;
                        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                        mTextViewCountDown.setText(timeLeftFormatted+" mins");
                    }
                    @Override
                    public void onFinish() {
                        mTimerRunning[0] = false;
                        im1.setImageResource(R.drawable.rectangle_14);
                        im2.setImageResource(R.drawable.rectangle_14);
                        im3.setImageResource(R.drawable.rectangle_14);
                        im4.setImageResource(R.drawable.rectangle_14);
                        im5.setImageResource(R.drawable.rectangle_14);
                        im6.setImageResource(R.drawable.rectangle_14);
                        im7.setImageResource(R.drawable.rectangle_14);
                        im8.setImageResource(R.drawable.rectangle_14);
                    }
                }.start();
                mTimerRunning[0] = true;

    }


    private void getTime()
    {
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        time1.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem)
            {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(LotSelectionModule.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
        return super.onOptionsItemSelected(item);
    }
}