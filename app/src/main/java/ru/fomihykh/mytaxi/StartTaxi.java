package ru.fomihykh.mytaxi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartTaxi extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.taxi_start);
    }

    public void startTaxi(View view) {
        SharedPreferences pref = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor;
        prefEditor = pref.edit();
        EditText cashEditView = findViewById(R.id.cash);
        EditText nonCashEditView = findViewById(R.id.nonCash);
        EditText mileageEditView = findViewById(R.id.mileage);

        int cash = Integer.parseInt(cashEditView.getText().toString());
        int nonCash = Integer.parseInt(nonCashEditView.getText().toString());
        int mileage = Integer.parseInt(mileageEditView.getText().toString());
        prefEditor.putBoolean("isStartTaxi",true);
        prefEditor.putInt("cash",cash);
        prefEditor.putInt("nonCash",nonCash);
        prefEditor.putInt("mileage",mileage);
        prefEditor.apply();
        finish();

    }

    public void cansel(View view) {
        finish();
    }
}