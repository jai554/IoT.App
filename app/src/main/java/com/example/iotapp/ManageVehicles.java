package com.example.iotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageVehicles extends AppCompatActivity {

    private Button viewAccess, addVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_vehicles);

        viewAccess = (Button) findViewById(R.id.viewaccess);
        addVehicles = (Button) findViewById(R.id.addvehicle);

        viewAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageVehicles.this, AccessHistory.class));
            }
        });

        addVehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageVehicles.this, AddVehicle.class));
            }
        });
    }
}