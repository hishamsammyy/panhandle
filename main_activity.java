package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // define the global spinners variables so they can be accessed from anywhere
    Spinner spinnerPbase,spinnerTbase,spinnerP1,spinnerP2,spinnerT,spinnerD,spinnerL,spinnerH2H1,spinnerVi;
// define string variables to store the selected value of spinner
    String textPbase,textTbase,textP1,textP2,textT,textD,textL,textH2H1,textVi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //intialize the spinner variable with the spinner values(array value)
        setContentView(R.layout.activity_main);
        spinnerPbase= (Spinner) findViewById(R.id.spinnerPbase);
        spinnerPbase.setOnItemSelectedListener(this);
        spinnerTbase= (Spinner) findViewById(R.id.spinnerTbase);
        spinnerTbase.setOnItemSelectedListener(this);
        spinnerP1= (Spinner) findViewById(R.id.spinnerP1);
        spinnerP1.setOnItemSelectedListener(this);
        spinnerP2= (Spinner) findViewById(R.id.spinnerP2);
        spinnerP2.setOnItemSelectedListener(this);
        spinnerT= (Spinner) findViewById(R.id.spinnerT);
        spinnerT.setOnItemSelectedListener(this);
        spinnerD= (Spinner) findViewById(R.id.spinnerD);
        spinnerD.setOnItemSelectedListener(this);
        spinnerL= (Spinner) findViewById(R.id.spinnerL);
        spinnerL.setOnItemSelectedListener(this);
        spinnerH2H1= (Spinner) findViewById(R.id.spinnerH2H1);
        spinnerH2H1.setOnItemSelectedListener(this);
        spinnerVi= (Spinner) findViewById(R.id.spinnerVi);
        spinnerVi.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         // common spinner to store selected spinner (using the parent )
        Spinner spinner = (Spinner) parent;

        if (spinner.getId() == R.id.spinnerPbase) {
            textPbase = parent.getItemAtPosition(position).toString(); //storing the parent value
        }
        else if (spinner.getId() == R.id.spinnerTbase) {
            textTbase = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerP1) {
            textP1 = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerP2) {
            textP2 = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerT) {
            textT = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerD) {
            textD = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerL) {
            textL = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerH2H1) {
            textH2H1 = parent.getItemAtPosition(position).toString();
        }
        else if (spinner.getId() == R.id.spinnerVi) {
            textVi = parent.getItemAtPosition(position).toString();
        }
    }
    Double Pbase,Tbase,P1,P2,Tavg,Dpipe,Lpipe,Epipe,Zgas,Ggas,H2H1,Vi,Spipe,Lepipe;
    Double  panhandleA,panhandleB,waymouth,IGT;
    double panhandleAroundOff;
    @SuppressLint("DefaultLocale")
    public void calculate(View view) {
        EditText editTextPbase = (EditText) findViewById(R.id.editTextPbase);
        EditText editTextTbase = (EditText) findViewById(R.id.editTextTbase);
        EditText editTextP1 = (EditText) findViewById(R.id.editTextP1);
        EditText editTextP2 = (EditText) findViewById(R.id.editTextP2);
        EditText editTextT = (EditText) findViewById(R.id.editTextT);
        EditText editTextD = (EditText) findViewById(R.id.editTextD);
        EditText editTextL = (EditText) findViewById(R.id.editTextL);
        EditText editTextE = (EditText) findViewById(R.id.editTextE);
        EditText editTextG = (EditText) findViewById(R.id.editTextG);
        EditText editTextH2H1 = (EditText) findViewById(R.id.editTextH2H1);
        EditText editTextZ = (EditText) findViewById(R.id.editTextZ);
        EditText editTextVi = (EditText) findViewById(R.id.editTextVi);
        TextView textViewResult =(TextView) findViewById(R.id.textViewResult);

        if (editTextPbase.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter base pressure value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextTbase.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter Base T value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextP1.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter P1 value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextP2.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter Base P2 value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextT.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter Gas T value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextD.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter pipe diameter", Toast.LENGTH_SHORT).show();
        }
        else if (editTextL.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter pipe length value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextE.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter Pipe effeciency value", Toast.LENGTH_SHORT).show();
        }
        else if (editTextG.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter Gas Gravity", Toast.LENGTH_SHORT).show();
        }
        else if (editTextH2H1.getText().toString().isEmpty()) {
            H2H1=0.00;
        }
        else if (editTextZ.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "please enter compressibilty", Toast.LENGTH_SHORT).show();
        }
        else if (editTextVi.getText().toString().isEmpty()) {
            Vi=0.00;
        }
        else {
            if (textPbase.equals("Bar")) {
                Pbase = Double.parseDouble(editTextPbase.getText().toString()) * 100;
            } else if (textPbase.equals("Kpa")) {
                Pbase = Double.parseDouble(editTextPbase.getText().toString());
            } else if (textPbase.equals("Psi")) {
                Pbase = Double.parseDouble(editTextPbase.getText().toString()) * 6.89475729;
            }
           if (textTbase.equals("C")) {
                Tbase = Double.parseDouble(editTextTbase.getText().toString()) +273.15;
            } else if (textTbase.equals("K")) {
                Tbase = Double.parseDouble(editTextTbase.getText().toString());
            } else if (textTbase.equals("F")) {
                Tbase = (Double.parseDouble(editTextTbase.getText().toString())-32)*5/9 +273.15;
            } else if (textTbase.equals("R")) {
                Tbase = (Double.parseDouble(editTextTbase.getText().toString())-32-459.67)*5/9 +273.15;
            }
            if (textP1.equals("Bar")) {
                P1 = Double.parseDouble(editTextP1.getText().toString()) * 100;
            } else if (textP1.equals("Kpa")) {
                P1 = Double.parseDouble(editTextP1.getText().toString());
            } else if (textP1.equals("Psi")) {
                P1 = Double.parseDouble(editTextP1.getText().toString()) * 6.89475729;
            }
            if (textP2.equals("Bar")) {
                P2 = Double.parseDouble(editTextP2.getText().toString()) * 100;
            } else if (textP2.equals("Kpa")) {
                P2 = Double.parseDouble(editTextP2.getText().toString());
            } else if (textP2.equals("Psi")) {
                P2 = Double.parseDouble(editTextP2.getText().toString()) * 6.89475729;
            }
            if (textT.equals("C")) {
                Tavg = Double.parseDouble(editTextT.getText().toString()) +273.15;
            } else if (textT.equals("K")) {
                Tavg = Double.parseDouble(editTextT.getText().toString());
            } else if (textT.equals("F")) {
                Tavg = (Double.parseDouble(editTextT.getText().toString())-32)*5/9 +273.15;
            } else if (textT.equals("R")) {
                Tavg = (Double.parseDouble(editTextT.getText().toString())-32-459.67)*5/9 +273.15;
            }
            if (textD.equals("In")) {
                Dpipe = Double.parseDouble(editTextD.getText().toString()) * 10*2.54;
            } else if (textD.equals("Cm")) {
                Dpipe = Double.parseDouble(editTextD.getText().toString())*10;
            } else if (textD.equals("Mm")) {
                Dpipe = Double.parseDouble(editTextD.getText().toString());
            }
            if (textL.equals("Km")) {
               Lpipe = Double.parseDouble(editTextL.getText().toString()) ;
            } else if (textL.equals("Miles")) {
                Lpipe = Double.parseDouble(editTextL.getText().toString())*1.609344;
            } else if (textL.equals("M")) {
                Lpipe = Double.parseDouble(editTextL.getText().toString()) /1000;
            } else if (textL.equals("Feet")) {
            Lpipe = Double.parseDouble(editTextL.getText().toString()) *0.3048/1000;
            }
            if (textH2H1.equals("Km")) {
                H2H1 = Double.parseDouble(editTextH2H1.getText().toString())*1000 ;
            } else if (textH2H1.equals("Miles")) {
                H2H1 = Double.parseDouble(editTextH2H1.getText().toString())*1.609344*1000;
            } else if (textH2H1.equals("M")) {
                H2H1 = Double.parseDouble(editTextH2H1.getText().toString()) ;
            } else if (textH2H1.equals("Feet")) {
                H2H1 = Double.parseDouble(editTextH2H1.getText().toString()) *0.3048;
            }
            if (textVi.equals("Cp")) {
                Vi = Double.parseDouble(editTextVi.getText().toString()) / 100;
            } else if (textVi.equals("Poise")) {
                Vi = Double.parseDouble(editTextVi.getText().toString());
            } else if (textVi.equals("Pas")) {
                Vi = Double.parseDouble(editTextVi.getText().toString()) * 10;
            }else if (textVi.equals("Ib/Ft-s")) {
                Vi = Double.parseDouble(editTextVi.getText().toString()) * 14.881639;
            }
            Epipe = Double.parseDouble(editTextE.getText().toString());
            Zgas = Double.parseDouble(editTextZ.getText().toString());
            Ggas = Double.parseDouble(editTextG.getText().toString());
            Spipe=0.0684*Ggas*((H2H1)/Tavg/Zgas);
            if(Spipe==0){
                Lepipe=Lpipe;
            }else {
                Lepipe=Lpipe*(Math.pow(2.718,Spipe)-1)/Spipe;
            }
            panhandleA=4.5965/1000*Epipe*(Math.pow((Tbase/Pbase),1.0788))*(Math.pow((Math.pow(P1,2)-Math.pow(2.718,Spipe)*Math.pow(P2,2))/(Math.pow(Ggas,0.8539)*Tavg*Lepipe*Zgas),0.5394))*Math.pow(Dpipe,2.6182);

            DecimalFormat f = new DecimalFormat("##.00");
            panhandleAroundOff =  (double) Math.round(panhandleA * 100) / 100;


            Toast.makeText(MainActivity.this, panhandleA.toString(), Toast.LENGTH_SHORT).show();
            textViewResult.setText(f.format(panhandleA));
            editTextPbase.requestFocus();

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
