package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DeathDetailsActivity extends AppCompatActivity {

    TextView txt_deacesedName;
    TextView txt_DateOfDeath;
    TextView txt_deacesedSex;
    TextView txt_deacesedOcupation;
    TextView txt_placeOfDeath;
    TextView txt_deacesedAddress;
    TextView txt_deacesedAgeAtDeath;
    TextView txt_deacesedMaritalStatus;
    TextView txt_deacesedStateOfOrigin;
    TextView txt_deacesedEthnicOrigin;
    TextView txt_causeOfDeath;
    TextView txt_deacesedInformantName;
    TextView txt_DeacesedInformantRelationship;
    TextView txt_DeacesedInformantAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death_details);
        txt_deacesedName = findViewById(R.id.txt_DeacesedName);
        txt_DateOfDeath = findViewById(R.id.txt_dateOfBirth);
        txt_deacesedOcupation = findViewById(R.id.txt_DeacesedOccupation);
        txt_deacesedSex = findViewById(R.id.txt_DeacesedSex);
        txt_placeOfDeath = findViewById(R.id.txt_PlaceOfDeath);
        txt_deacesedAddress = findViewById(R.id.txt_DeacesedAddress);
        txt_deacesedAgeAtDeath = findViewById(R.id.txt_DeacesedAgeAtDeath);
        txt_deacesedMaritalStatus = findViewById(R.id.txt_DeacesedmaritalStatus);
        txt_deacesedStateOfOrigin = findViewById(R.id.txt_DeacesedStateOfOrigin);
        txt_deacesedEthnicOrigin = findViewById(R.id.txt_DeacesedEthnicOrigin);
        txt_causeOfDeath = findViewById(R.id.txt_causeOfDeath);
        txt_deacesedInformantName = findViewById(R.id.txt_DeacesedInformantName);
        txt_DeacesedInformantRelationship = findViewById(R.id.txt_DeacesedInformantRelationship);
        txt_DeacesedInformantAddress = findViewById(R.id.txt_DeacesedInformantAddress);


    }
}