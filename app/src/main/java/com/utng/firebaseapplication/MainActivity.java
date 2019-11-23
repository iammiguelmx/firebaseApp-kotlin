package com.utng.firebaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Declarar objetos para recibir los elemnetos de la vista
    private EditText extTema;
    private Spinner spiCarrera, spiMateria;
    private Button btnReg;
    //Referencia base de datos
    private DatabaseReference refDiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refDiario = FirebaseDatabase.getInstance().getReference("Clase");


        //Asociar los objetos de la vista
        extTema = (EditText)findViewById(R.id.etxTema);
        spiCarrera = (Spinner)findViewById(R.id.spiCarreras);
        spiMateria = (Spinner)findViewById(R.id.spiMaterias);
        btnReg = (Button)findViewById(R.id.btnRegistrar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regitrarClase();
            }
        });



    } // Fin del metodo oncreate

    public void regitrarClase(){
        String carrera = spiCarrera.getSelectedItem().toString();
        String materia = spiMateria.getSelectedItem().toString();
        String tema =  extTema.getText().toString();

        if (!TextUtils.isEmpty(tema)){
            //Se genera la clave para la bd
            String id = refDiario.push().getKey();
            Clase leccion = new Clase(id, carrera, materia, tema);
            //Se agrega un hijo dentro de la rama "Lecciones"
            refDiario.child("Lecciones").child(id).setValue(leccion);
            Toast.makeText(this, "Clase registrada",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Falta ingresar tema",Toast.LENGTH_LONG).show();
        }
    }


}
