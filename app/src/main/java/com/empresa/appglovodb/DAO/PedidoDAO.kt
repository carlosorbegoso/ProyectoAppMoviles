package com.empresa.appglovodb.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.empresa.appglovodb.Entidades.Pedido

class PedidoDAO(contexto: Context) {
    //variable del tipo DatabaseHelper
    var helper= DatabaseHelper(contexto)
    var mi_contexto=contexto
    //metodos del Dao

    fun  GrabarPedido(objped:Pedido){
        //obtener el acceso a la bd atraves del helper
        var  bd:SQLiteDatabase = helper.writableDatabase
        //creando un objeto ContentValues
        var valores= ContentValues()
        //
          valores.put("codped",objped.codped)
          valores.put("nomped",objped.nomped)
          valores.put("desped",objped.desped)
          valores.put("canped",objped.canped)
          valores.put("precio",objped.precio)
        //
         valores.put("codcat",objped.codcat)
        //insertando el ContentValues en la tabla
        bd.insert("pedido", null,valores)
        //
        bd.close()
    }

    fun ListarPedidosString():ArrayList<String>
    {
     var lista = ArrayList<String>()
     var bd:SQLiteDatabase= helper.readableDatabase
     var c:Cursor=bd.rawQuery("select * from pedido", null)
     var cadena:String=""
        //si el cursor tiene fila lo correremos
        if(c.count>0)
        {

             while (c.moveToNext()){
                 cadena=""+c.getInt(0)+" - "+c.getString(1)+" - "+
                         c.getString(2) +" - "+c.getInt(3)   +" - "+
                         c.getDouble(4)
                 //
                 lista.add(cadena)
             }
        }
        c.close()
        bd.close()
        return  lista
    }


    fun ListarPedidos():ArrayList<Pedido>
    {
        var lista = ArrayList<Pedido>()
        var bd:SQLiteDatabase= helper.readableDatabase
        var c:Cursor=bd.rawQuery("select * from pedido", null)

        //si el cursor tiene fila lo correremos
        if(c.count>0)
        {

            while (c.moveToNext()){
               var objped=Pedido(c.getInt(0),c.getString(1),
                        c.getString(2),c.getInt(3),
                        c.getDouble(4),c.getInt(5))
                //
                lista.add(objped)
            }
        }
        c.close()
        bd.close()
        return  lista
    }

    //metodo que devuelva el identificado de una imgane drawable
  fun getImagenID(codigo:Int):Int{
        var nombre="p"+codigo
        var paquete = mi_contexto.packageName
        var devolver = mi_contexto.resources.getIdentifier(
            nombre,"drawable",paquete)
        return  devolver
    }
    //Obtener pedido
    fun  ObtenerPedido(objped:Int):Pedido{
    var bd:SQLiteDatabase=helper.readableDatabase
     var p:Cursor=bd.rawQuery("select * from pedido where codped="+objped,null)
     p.moveToFirst()
     var obj = Pedido(p.getInt(0),p.getString(1),
     p.getString(2),p.getInt(3),p.getDouble(4),
     p.getInt(5))

     p.close()
     bd.close()
     return  obj

 }

    fun  EliminarPedido(objped:Int):String{
        //obtener el acceso a la bd atraves del helper
        var  bd:SQLiteDatabase = helper.writableDatabase
        var cadSQL= "delete from pedido where codped="+objped
        bd.execSQL(cadSQL)
        //insertando el ContentValues en la tabla
       // bd.delete("pedido", "codped=?", arrayOf(objped))

        bd.close()
        return "Pedido: ${objped} fue Eliminado - OK"
    }

    fun PedidosporCategoria(codigo: Int):ArrayList<Pedido>
    {
        var lista = ArrayList<Pedido>()
        var bd:SQLiteDatabase= helper.readableDatabase
        var c:Cursor=bd.rawQuery("select * from pedido where codcat="+codigo, null)

        //si el cursor tiene fila lo correremos
        if(c.count>0)
        {
            while (c.moveToNext()){
                var objped=Pedido(c.getInt(0),c.getString(1),
                    c.getString(2),c.getInt(3),
                    c.getDouble(4),c.getInt(5))
                //
                lista.add(objped)
            }
        }
        c.close()
        bd.close()
        return  lista
    }
}