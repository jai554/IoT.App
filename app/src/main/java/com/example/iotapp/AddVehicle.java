package com.example.iotapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddVehicle extends AppCompatActivity {

    private EditText plate, brand, model;
    private Button addBtn;

    private FirebaseUser user;
    private DatabaseReference reff, emailRef;
    CarLists carLists = new CarLists();

    private String userID;
//    private String sEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        final String[] sEmail = new String[1];

        plate = (EditText) findViewById(R.id.plateText);
        brand = (EditText) findViewById(R.id.brandText);
        model = (EditText) findViewById(R.id.modelText);
        addBtn = (Button) findViewById(R.id.add);

        user = FirebaseAuth.getInstance().getCurrentUser();
        emailRef = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        emailRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    sEmail[0] = userProfile.email;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddVehicle.this, "Cannot retrieve user's name!", Toast.LENGTH_LONG).show();
            }
        });

        carLists = new CarLists();
//        reff = FirebaseDatabase.getInstance().getReference().child("CarLists");
        reff = FirebaseDatabase.getInstance().getReference();

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                carLists.setAllowed("1");
                carLists.setMake(brand.getText().toString().trim());
                carLists.setModel(model.getText().toString().trim());
                carLists.setOwned(sEmail[0]);
                carLists.setPlate(plate.getText().toString().trim());

//                reff.push().setValue(carLists);
                reff.child("CarLists").setValue(carLists);

                Toast.makeText(AddVehicle.this, "Vehicle information added successfully!", Toast.LENGTH_LONG).show();

            }
        });
    }
}