package mx.edu.ittepic.aldairarriola.tpdm_u2_practica1_aldair_arriola;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    EditText desc;
    EditText ubica;
    EditText fech;
    EditText presu;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        desc = findViewById(R.id.des);
        ubica = findViewById(R.id.ubi);
        fech = findViewById(R.id.fec);
        presu = findViewById(R.id.pres);
        agregar = findViewById(R.id.btninsertar);


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    insertar();
            }
        });
    }

    private void insertar(){
        //Validar campos vacios
        String mensaje="";
        Proyecto proyecto = new Proyecto(this);
        boolean respuesta = proyecto.insertar(new Proyecto(0,desc.getText().toString(),
                ubica.getText().toString(), fech.getText().toString(),
                Float.parseFloat(presu.getText().toString())));

        if(respuesta){
            mensaje = "Se pudo insertar el proyecto " + desc.getText() ;
        }else {
            mensaje = "Error! no se pudo crear el proyecto, respuesta de SQLITE: " + proyecto.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();
    }
}
