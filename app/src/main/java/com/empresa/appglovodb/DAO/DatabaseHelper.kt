package com.empresa.appglovodb.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, "BDGLOVO", null, 1) {

    //variables de tipo cadaena que contengan el comando de creacion de tablas
    var tabla_categoria="create table if not exists categoria(codcat integer primary key,"+
            "nomcar text);"

    var tabla_pedido="create table if not exists pedido(codped integer primary key,"+
            "nomped text, desped text, canped integer, precio real, codcat integer,"+
            "FOREIGN KEY(codcat) REFERENCES categoria(codcat));"

    var inserta_categoria1="insert into categoria values(1, 'Comidas del dia')"
    var inserta_categoria2="insert into categoria values(2, 'Fast foods')"
    var inserta_categoria3="insert into categoria values(3, 'Postres')"
    var inserta_categoria4="insert into categoria values(4, 'Bebidas')"
    var inserta_categoria5="insert into categoria values(5, 'Acompañamientos y Otros')"
    var inserta_pedido="insert into pedido values(1001, 'Big Mac', 'Hamburgues de carne+queso derretido+papas fritas',2, 19.80, 2)"

    override fun onCreate(db: SQLiteDatabase) {
        // ejecutar la instruccion: tabla_pedido
        db.execSQL(tabla_categoria)
        db.execSQL(tabla_pedido)
        //
        db.execSQL(inserta_categoria1)
        db.execSQL(inserta_categoria2)
        db.execSQL(inserta_categoria3)
        db.execSQL(inserta_categoria4)
        db.execSQL(inserta_categoria5)
        //
        db.execSQL(inserta_pedido)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}