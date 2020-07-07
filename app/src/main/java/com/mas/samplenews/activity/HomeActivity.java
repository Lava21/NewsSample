package com.mas.samplenews.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.mas.samplenews.R;

import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialCardView cvHead, cvSports, cvTechnology, cvBusiness, cvHealth, cvEntertaiment;
    TextView tvToday;
    String today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvHead = findViewById(R.id.cvHeadline);
        cvHead.setOnClickListener(this);
        cvSports = findViewById(R.id.cvSports);
        cvSports.setOnClickListener(this);
        cvTechnology = findViewById(R.id.cvTechno);
        cvTechnology.setOnClickListener(this);
        cvBusiness = findViewById(R.id.cvBusiness);
        cvBusiness.setOnClickListener(this);
        cvHealth = findViewById(R.id.cvHealth);
        cvHealth.setOnClickListener(this);
        cvEntertaiment = findViewById(R.id.cvEntertaiment);
        cvEntertaiment.setOnClickListener(this);

        tvToday = findViewById(R.id.tvDate);
        Date dateNow = Calendar.getInstance().getTime();
        today = (String) DateFormat.format("EEEE", dateNow);
        if (today.equalsIgnoreCase("sunday")){
            today = "Minggu";
        } else if (today.equalsIgnoreCase("monday")){
            today = "Senin";
        } else if (today.equalsIgnoreCase("tuesday")){
            today = "Selasa";
        } else if (today.equalsIgnoreCase("wednesday")){
            today = "Rabu";
        } else if (today.equalsIgnoreCase("thursday")){
            today = "Kamis";
        } else if (today.equalsIgnoreCase("friday")){
            today = "Jum'at";
        } else if (today.equalsIgnoreCase("saturday")){
            today = "Sabtu";
        }

        getToday();
    }

    private void getToday(){
        Date date = Calendar.getInstance().getTime();
        String sDate = (String) DateFormat.format("d", date);
        String monthNumber = (String) DateFormat.format("M", date);
        String year = (String) DateFormat.format("yyyy", date);

        int month = Integer.parseInt(monthNumber);
        String sMonth = null;
        if (month == 1){
            sMonth = "Januari";
        } else if (month == 2){
            sMonth = "Februari";
        } else if (month == 3){
            sMonth = "Maret";
        } else if (month == 4){
            sMonth = "April";
        } else if (month == 5){
            sMonth = "Mei";
        } else if (month == 6) {
            sMonth = "Juni";
        } else if (month == 7) {
            sMonth = "Juli";
        } else if (month == 8) {
            sMonth = "Agustus";
        } else if (month == 9) {
            sMonth = "September";
        } else if (month == 10) {
            sMonth = "Oktober";
        } else if (month == 11) {
            sMonth = "November";
        } else if (month == 12) {
            sMonth = "Desember";
        }

        String formatTime = today + ", " + sDate + " " + sMonth + " " + year;
        tvToday.setText(formatTime);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cvHeadline) {
            Intent intent = new Intent(HomeActivity.this, HeadLineActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cvSports) {
            Intent intent = new Intent(HomeActivity.this, SportNewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cvTechno) {
            Intent intent = new Intent(HomeActivity.this, TechnologyNewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cvBusiness) {
            Intent intent = new Intent(HomeActivity.this, BusinessActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cvHealth) {
            Intent intent = new Intent(HomeActivity.this, HealthNewsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cvEntertaiment) {
            Intent intent = new Intent(HomeActivity.this, EntertaimentNewsActivity.class);
            startActivity(intent);
        }
    }
}
