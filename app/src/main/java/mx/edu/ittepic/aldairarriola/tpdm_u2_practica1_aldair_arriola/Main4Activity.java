package mx.edu.ittepic.aldairarriola.tpdm_u2_practica1_aldair_arriola;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    Button search;
    Button upda;
    EditText s;
    EditText d;
    EditText u;
    EditText f;
    EditText p;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        search = findViewById(R.id.btnbuscar);
        upda =  findViewById(R.id.btnactualizar);
        s = findViewById(R.id.descriact);
        d = findViewById(R.id.descr);
        u = findViewById(R.id.ubica);
        f = findViewById(R.id.fech);
        p = findViewById(R.id.presu);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        upda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualiza();
            }
        });

    }

    public void buscar(){
        Proyecto a = new Proyecto(this);
        String mensaje ="";

        Proyecto[] res = a.consultar(s.getText().toString());
        if(res!=null){
            d.setText(res[0].getDescripcion());
            u.setText(res[0].getUbicacion());
            f.setText(res[0].getFecha());
            p.setText(res[0].getPresupuesto()+"");
            id = res[0].getId();
            mensaje = "Datos encontrados" ;

        }else{
            mensaje = "Error! algo salió mal:\n" + a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();

    }

    private void actualiza(){
        Proyecto a = new Proyecto(this);
        String mensaje;

        boolean respuesta = a.actualizar(new Proyecto(id,d.getText().toString(),
                u.getText().toString(),f.getText().toString(), Float.parseFloat(p.getText().toString())));

        if (respuesta){
            mensaje = "Se actualizó correctamente el proyecto " + d.getText().toString();
        }else{
            mensaje = "Error! algo salió mal: "+ a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();

    }
}
