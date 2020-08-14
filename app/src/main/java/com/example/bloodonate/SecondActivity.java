package com.example.bloodonate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodonate.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SecondActivity extends AppCompatActivity {

    private Button btnAgregar;
    private EditText nombreD,apeP,apeM,dni,emailD,direccionD,telefonoD;
    private Spinner tipoSangreD,rhD;
    private CheckBox donoD;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference  databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnAgregar = findViewById(R.id.btnGuardarDonor);
        nombreD = findViewById(R.id.txt_nombreDonador);
        apeP = findViewById(R.id.txt_apellidoPDonador);
        apeM = findViewById(R.id.txt_apellidoMDonador);
        dni = findViewById(R.id.txt_dniDonador);
        emailD = findViewById(R.id.txt_emailDonador);
        direccionD = findViewById(R.id.txt_direccionDonador);
        telefonoD = findViewById(R.id.txt_telefonoDonador);
        tipoSangreD = findViewById(R.id.tipoDonador);
        rhD = findViewById(R.id.rhDonador);
        donoD = findViewById(R.id.donoDonador);
        inicializarFirebase();
        setupSpinners();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarDonador();
                //Intent i = new Intent(this, .class);
                //startActivity(i);
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void agregarDonador() {
        int dono;
        if(donoD.isChecked()){
            dono = 1;
        }else{
            dono = 0;
        }
        String nombre = nombreD.getText().toString();
        String apellidoP = apeP.getText().toString();
        String apellidoM = apeM.getText().toString();
        String DNI = dni.getText().toString();
        String email = emailD.getText().toString();
        String direccion = direccionD.getText().toString();
        String telefono = telefonoD.getText().toString();
        String tipoSangre = (String) tipoSangreD.getSelectedItem();
        String  RH = (String) rhD.getSelectedItem();
        dono = donoD.getText().hashCode();
        //dono =  Integer.parseInt(donoD.getText().toString());

        if(nombre.equals("")){
            validacion();
        }else{
            Persona p = new Persona();
            p.setID(UUID.randomUUID().toString());
            p.setNombre(nombre);
            p.setApellidoPaterno(apellidoP);
            p.setApellidoMaterno(apellidoM);
            p.setDNI(DNI);
            p.setEmail(email);
            p.setDireccion(direccion);
            p.setTelefono(telefono);
            p.setTipoSangre(tipoSangre);
            p.setRH(RH);
            p.setDono(dono);
            databaseReference.child("Donador").child(p.getID()).setValue(p);
            Toast.makeText(this,"Donador Agregado...!!!", Toast.LENGTH_LONG).show();
            limpiarCajas();
        }
    }

    private void setupSpinners(){
        //Tipo de Sangre
        List<String> spinnerTipo = new ArrayList<>();
        spinnerTipo.add("A");
        spinnerTipo.add("B");
        spinnerTipo.add("AB");
        spinnerTipo.add("O");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerTipo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSangreD.setAdapter(adapter);
        //Tipo de RH
        List<String> spinnerRh = new ArrayList<>();
        spinnerRh.add("+");
        spinnerRh.add("-");
        ArrayAdapter<String> adapterRh = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerRh);
        adapterRh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rhD.setAdapter(adapterRh);
    }

    private void limpiarCajas() {
        nombreD.setText("");
        apeP.setText("");
        apeM.setText("");
        dni.setText("");
        emailD.setText("");
        direccionD.setText("");
        telefonoD.setText("");
        donoD.setText("");
    }

    private void validacion() {
        String nombre = nombreD.getText().toString();
        String apellidoP = apeP.getText().toString();
        String apellidoM = apeM.getText().toString();
        String DNI = dni.getText().toString();
        String email = emailD.getText().toString();
        String direccion = direccionD.getText().toString();
        String telefono = telefonoD.getText().toString();
        String tipoSangre = (String) tipoSangreD.getSelectedItem();
        String  RH = (String) rhD.getSelectedItem();
        String dono = donoD.getTag().toString();

        if(nombre.equals("")){
            nombreD.setError("Required");
        }else if(apellidoP.equals("")){
            apeP.setError("Required");
        }else if(apellidoM.equals("")) {
            apeM.setError("Required");
        }else if(DNI.equals("")) {
            dni.setError("Required");
        }else if(email.equals("")) {
            emailD.setError("Required");
        }else if(direccion.equals("")) {
            direccionD.setError("Required");
        }else if(telefono.equals("")) {
            telefonoD.setError("Required");
        }else if(tipoSangre.equals("")) {
            tipoSangreD.setEnabled(false);
        }else if(RH.equals("")) {
            rhD.setEnabled(false);
        }else if(dono.equals("")) {
            donoD.setError("Required");
        }
    }
}
