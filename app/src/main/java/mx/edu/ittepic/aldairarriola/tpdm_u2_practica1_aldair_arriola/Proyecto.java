package mx.edu.ittepic.aldairarriola.tpdm_u2_practica1_aldair_arriola;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Proyecto {
    private BaseDatos base;
    private int id;
    private String descripcion;
    private String ubicacion;
    private String fecha;
    private float presupuesto;
    protected String error;
    Proyecto[] proyecto = null;
    Proyecto a;
    Cursor c;

    public Proyecto(Activity activity){
        base = new BaseDatos(activity,"proyectos",null,1);
    }

    public Proyecto(int id, String descripcion, String ubicacion, String fecha, float p){
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        presupuesto = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public boolean insertar(Proyecto proyecto){
     try {
         SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
         ContentValues datos = new ContentValues();
         datos.put("DESCRIPCION", proyecto.getDescripcion());
         datos.put("UBICACION", proyecto.getUbicacion());
         datos.put("FECHA",proyecto.getFecha());
         datos.put("PRESUPUESTO",proyecto.getPresupuesto());

         long resultado = transaccionInsertar.insert("PROYECTOS", "IDPROYECTO", datos);

         if(resultado == -1) return false;
     }catch (SQLiteException e){
         error = e.getMessage();
         return false;
     }
     return true;
    }

    public Proyecto[] consultar( String desc){

        try{
            Proyecto[] a = null;
            SQLiteDatabase busqueda = base.getReadableDatabase();
            String sql = "SELECT * FROM PROYECTOS WHERE DESCRIPCION ="+ "'" + desc + "'";
            Cursor c = busqueda.rawQuery(sql,null);

            if(c.moveToFirst()){
                a = new Proyecto[c.getCount()];
                int pos = 0;
                do{
                    a[pos]=new Proyecto(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3),
                            c.getFloat(4));
                    pos++;
                }while(c.moveToNext());
            }
            return a;
        }catch (SQLiteException e){
            return null;
        }
    }

    public  boolean eliminar(String desc){
        int resultado;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String[] claves={desc};
            resultado = transaccionEliminar.delete("PROYECTOS","DESCRIPCION=?",claves);
            transaccionEliminar.close();
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado >0;
    }





    public boolean actualizar(Proyecto proyecto){
        try{

            SQLiteDatabase actualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyecto.getDescripcion());
            datos.put("UBICACION", proyecto.getUbicacion());
            datos.put("FECHA",proyecto.getFecha());
            datos.put("PRESUPUESTO",proyecto.getPresupuesto());

            long resultado = actualizar.update("PROYECTOS",datos,"IDPROYECTO=?",
                    new String[]{""+proyecto.getId()});
            actualizar.close();
            if(resultado == -1) return false;
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }


    public Proyecto[] selectall(){

        try{
            Proyecto[] a = null;
            SQLiteDatabase busqueda = base.getReadableDatabase();
            String sql = "SELECT * FROM PROYECTOS";
            Cursor c = busqueda.rawQuery(sql,null);

            if(c.moveToFirst()){
                a = new Proyecto[c.getCount()];
                int pos = 0;
                do{
                    a[pos]=new Proyecto(c.getInt(0), c.getString(1),
                            c.getString(2),c.getString(3), c.getFloat(4));
                    pos++;
                }while(c.moveToNext());
            }
            return a;
        }catch (SQLiteException e){
            return null;
        }

    }
}










