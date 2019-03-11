package mx.edu.ittepic.aldairarriola.tpdm_u2_practica1_aldair_arriola;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    Proyecto vector[];

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.crud, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista =  findViewById(R.id.listaproyectos);


     }

    @Override
    protected void onStart() {
        Proyecto proyecto =  new Proyecto(this);
        vector = proyecto.selectall();
        String[] descripcion = null;

        if(vector == null){
            descripcion = new String[1];
            descripcion[0] = "No hay proyectos registrados";
        }else {
            descripcion = new String[vector.length];
            for(int i= 0; i<vector.length; i++){
                Proyecto temp = vector[i];
                descripcion[i] = temp.getDescripcion();
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, descripcion);
        lista.setAdapter(adaptador);

        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.consult:
                Intent a1 =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(a1);
                break;
            case R.id.insert:
                startActivity(new Intent(MainActivity.this,Main3Activity.class));

                break;
            case R.id.update:
                startActivity(new Intent(MainActivity.this,Main4Activity.class));
                break;
            case R.id.delete:
                startActivity(new Intent(MainActivity.this,Main5Activity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
