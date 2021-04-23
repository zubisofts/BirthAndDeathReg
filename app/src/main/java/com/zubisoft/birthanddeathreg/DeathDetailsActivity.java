package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zubisoft.birthanddeathreg.model.deathmodels.DeathRegData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedData;
import com.zubisoft.birthanddeathreg.model.deathmodels.DeceasedInformantData;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
        Intent intent = getIntent();
        txt_deacesedName = findViewById(R.id.txt_DeacesedName);
        txt_DateOfDeath = findViewById(R.id.txt_dateOfDeath);
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

        DeathRegData deathRegData = (DeathRegData) intent.getSerializableExtra("data");
        DeceasedData deceasedData = deathRegData.getDeceasedData();
        DeceasedInformantData deceasedInformantData = deathRegData.getDeceasedInformantData();
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(deceasedData.getDateOfDeath());
        txt_deacesedName.setText(deceasedData.getName());
        txt_DateOfDeath.setText(date);
        txt_deacesedOcupation.setText(deceasedData.getOccupation());
        txt_deacesedSex.setText(deceasedData.getGender());
        txt_placeOfDeath.setText(deceasedData.getPlaceOfDeath());
        txt_deacesedAddress.setText(deceasedData.getAddress());
        txt_deacesedAgeAtDeath.setText(String.valueOf(deceasedData.getAge()));
        txt_deacesedMaritalStatus.setText(deceasedData.getMaritalStatus());
        txt_deacesedStateOfOrigin.setText(deceasedData.getStateOrigin());
        txt_deacesedEthnicOrigin.setText(deceasedData.getEthnicGroup());
        txt_causeOfDeath.setText(deceasedData.getDeathCause());
        txt_deacesedInformantName.setText(deceasedInformantData.getName());
        txt_DeacesedInformantRelationship.setText(deceasedInformantData.getRelationShip());
        txt_DeacesedInformantAddress.setText(deceasedData.getAddress());




    }
}