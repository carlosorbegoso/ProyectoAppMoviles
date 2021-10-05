package com.empresa.appglovodb.DAO

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.empresa.appglovodb.Entidades.Categorias

class CategoriaDAO(contexto:Context) {
    var helper= DatabaseHelper(contexto)
    // devolvemos una lista de categorias
    //data class categorias
    fun ListarCategorias():ArrayList<Categorias>{
        var lista= ArrayList<Categorias>()
        var bd:SQLiteDatabase=helper.readableDatabase
        var c:Cursor=bd.rawQuery("select * from categoria",null)

        if(c.count>0){
            while (c.moveToNext()){
                var x=Categorias(c.getInt(0), c.getString(1))
                //
                lista.add(x)
            }
        }
        c.close()
        bd.close()
        return lista
    }
}