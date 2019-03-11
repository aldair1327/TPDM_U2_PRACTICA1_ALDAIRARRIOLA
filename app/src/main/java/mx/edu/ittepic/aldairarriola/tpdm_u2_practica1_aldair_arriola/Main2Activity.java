package mx.edu.ittepic.aldairarriola.tpdm_u2_practica1_aldair_arriola;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button consultar;
    EditText bus;
    TextView des;
    TextView ubi;
    TextView fec;
    TextView pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        consultar = findViewById(R.id.btnconsultar);
        bus = findViewById(R.id.descripcion);
        des = findViewById(R.id.txtdescripcion);
        ubi = findViewById(R.id.txtubicacion);
        fec = findViewById(R.id.txtfecha);
        pre = findViewById(R.id.txtpresupuesto);


        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

    }

    private void buscar(){
        Proyecto a = new Proyecto(this);
        String mensaje ="";

        Proyecto[] res = a.consultar(bus.getText().toString());
        if(res!=null){
            des.setText(res[0].getDescripcion());
            ubi.setText(res[0].getUbicacion());
            fec.setText(res[0].getFecha());
            pre.setText(res[0].getPresupuesto()+"");
            mensaje = "Datos encontrados" ;

        }else{
            mensaje = "Error! algo salió mal:\n" + a.error;
        }
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("ATENCIÓN").setMessage(mensaje).setPositiveButton("ok",null).show();


    }

}
