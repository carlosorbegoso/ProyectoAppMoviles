package com.empresa.appglovodb.Entidades

class Pedido(xcod:Int, xnom:String, xdescrip:String, xcantidad: Int,xprecio:Double, xcodcat:Int) {

 var codped:Int=0
 var nomped:String=""
 var desped:String=""
 var canped:Int=0
 var precio:Double=0.0
    //
    var codcat:Int=0

    init {
        codped= xcod
        nomped=xnom;
        desped=xdescrip;
        canped=xcantidad
        precio=xprecio
     codcat=xcodcat
    }

}