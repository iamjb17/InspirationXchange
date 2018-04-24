package com.example.jessie.inspirationxchange;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    TextView mUserName;

    @BindView(R.id.homebutton)
    Button mHomeButton;

    @BindView(R.id.dailyInspiration)
    TextView mDailyInspiration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.homebutton)
    public void returnHome() {
        String home = "com.example.jessie.inspirationxchange.MainActivity";
        MainActivity mainActivity = new MainActivity();
        mainActivity.nextActivity(home);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
