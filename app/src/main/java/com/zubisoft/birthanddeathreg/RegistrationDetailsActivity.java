package com.zubisoft.birthanddeathreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zubisoft.birthanddeathreg.model.birthmodels.BirthRegData;
import com.zubisoft.birthanddeathreg.model.birthmodels.ChildBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.FatherBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.InformantBirthData;
import com.zubisoft.birthanddeathreg.model.birthmodels.MotherBirthData;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RegistrationDetailsActivity extends AppCompatActivity {

    TextView txt_ChildName,txt_dateOfBirth,txt_childSex,txt_PlaceOfBirth,txt_TypeOfBirth,txt_fatherName,
            txt_fatherNumber,txt_FatherAddress,txt_FatherNationalId,txt_FatherAgeAtBirth,txt_maritalStatus,txt_FatherStateOfOrigin
            ,txt_FatherEthnicOrigin,txt_FatherOccupation,txt_MotherName,txt_MotherNumber,txt_MotherAddress,txt_MotherNationalID,txt_MotherAgeAtBirth
            ,txt_MotherMaritalStatus,txt_MotherSateOfOrigin,txt_MotherEthnicOrigin,txt_MotherOccupation,txt_informantName,txt_informantRelationship,txt_informantPhoneNumber
            ,txt_informantNationalId,txt_informantAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);
        Intent intent = getIntent();

        txt_ChildName = findViewById(R.id.txt_ChildName);
        txt_dateOfBirth = findViewById(R.id.txt_dateOfBirth);
        txt_childSex = findViewById(R.id.txt_childSex);
        txt_PlaceOfBirth = findViewById(R.id.txt_PlaceOfBirth);
        txt_TypeOfBirth = findViewById(R.id.txt_TypeOfBirth);
        txt_fatherName = findViewById(R.id.txt_fatherName);
        txt_fatherNumber = findViewById(R.id.txt_fatherNumber);
        txt_FatherAddress = findViewById(R.id.txt_FatherAddress);
        txt_FatherNationalId = findViewById(R.id.txt_FatherNationalId);
        txt_FatherAgeAtBirth = findViewById(R.id.txt_FatherAgeAtBirth);
        txt_maritalStatus = findViewById(R.id.txt_maritalStatus);
        txt_FatherStateOfOrigin = findViewById(R.id.txt_FatherStateOfOrigin);
        txt_FatherEthnicOrigin = findViewById(R.id.txt_FatherEthnicOrigin);
        txt_FatherOccupation = findViewById(R.id.txt_FatherOccupation);
        txt_MotherName = findViewById(R.id.txt_MotherName);
        txt_MotherNumber = findViewById(R.id.txt_MotherNumber);
        txt_MotherAddress = findViewById(R.id.txt_MotherAddress);
        txt_MotherNationalID = findViewById(R.id.txt_MotherNationalID);
        txt_MotherMaritalStatus = findViewById(R.id.txt_MotherMaritalStatus);
        txt_MotherSateOfOrigin = findViewById(R.id.txt_MotherSateOfOrigin);
        txt_MotherEthnicOrigin = findViewById(R.id.txt_MotherEthnicOrigin);
        txt_MotherOccupation = findViewById(R.id.txt_MotherOccupation);
        txt_informantName = findViewById(R.id.txt_informantName);
        txt_MotherAgeAtBirth = findViewById(R.id.txt_MotherAgeAtBirth);
        txt_informantRelationship = findViewById(R.id.txt_informantRelationship);
        txt_informantPhoneNumber = findViewById(R.id.txt_informantPhoneNumber);
        txt_informantNationalId = findViewById(R.id.txt_informantNationalId);
        txt_informantAddress = findViewById(R.id.txt_informantAddress);


       BirthRegData data = (BirthRegData) intent.getSerializableExtra("data");
        ChildBirthData childBirthData = data.getChildBirthData();
        FatherBirthData fatherBirthData = data.getFatherBirthData();
        MotherBirthData motherBirthData = data.getMotherBirthData();
        InformantBirthData informantBirthData = data.getInformantBirthData();

       String date =   new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(childBirthData.getDateOfBirth());
        txt_ChildName.setText(childBirthData.getChildName());
        txt_dateOfBirth.setText(date);
        txt_childSex.setText(childBirthData.getSex());
        txt_PlaceOfBirth.setText(childBirthData.getPlaceOfBirth());
        txt_PlaceOfBirth.setText(childBirthData.getPlaceOfBirth());
        txt_fatherName.setText(fatherBirthData.getName());
        txt_fatherNumber.setText(fatherBirthData.getPhoneNumber());
        txt_FatherAddress.setText(fatherBirthData.getAddress());
        txt_FatherNationalId.setText(fatherBirthData.getNationalID());
        txt_FatherAgeAtBirth.setText(fatherBirthData.getAge());
        txt_maritalStatus.setText(fatherBirthData.getMaritalStatus());
        txt_FatherStateOfOrigin.setText(fatherBirthData.getStateOfOrigin());
        txt_FatherEthnicOrigin.setText(fatherBirthData.getEthnicGroup());
        txt_FatherOccupation.setText(fatherBirthData.getOccupation());
        txt_MotherName.setText(motherBirthData.getName());
        txt_MotherNumber.setText(motherBirthData.getPhoneNumber());
        txt_MotherAddress.setText(motherBirthData.getAddress());
        txt_MotherNationalID.setText(motherBirthData.getNationalID());
        txt_MotherMaritalStatus.setText(motherBirthData.getMaritalStatus());
        txt_MotherSateOfOrigin.setText(motherBirthData.getStateOfOrigin());
        txt_MotherEthnicOrigin.setText(motherBirthData.getEthnicGroup());
        txt_MotherOccupation.setText(motherBirthData.getOccupation());
        txt_informantName.setText(informantBirthData.getName());
        txt_MotherAgeAtBirth.setText(motherBirthData.getAge());
        txt_informantRelationship.setText(informantBirthData.getRelationship());
        txt_informantPhoneNumber.setText(informantBirthData.getPhoneNumber());
        txt_informantNationalId.setText(informantBirthData.getNationalId());
        txt_informantAddress.setText(informantBirthData.getAddress());
        txt_TypeOfBirth.setText(childBirthData.getTypeOfBirth());













    }
}