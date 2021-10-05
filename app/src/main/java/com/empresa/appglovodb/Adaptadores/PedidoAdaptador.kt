package com.empresa.appglovodb.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.empresa.appglovodb.DAO.PedidoDAO
import com.empresa.appglovodb.Entidades.Pedido
import kotlinx.android.synthetic.main.fila_pedido.view.*

class PedidoAdaptador(context: Context, resource: Int, objects: ArrayList<Pedido>) :
    ArrayAdapter<Pedido>(context, resource, objects) {
    //variables
    var mi_layout=resource
    var mi_lista=objects


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //definimos una variable del tipo pedido
        var objped=mi_lista.get(position)
        //definir una variale del tipo LayoutInflater
        var inflador:LayoutInflater=LayoutInflater.from(context)
        var mi_vista:View=inflador.inflate(mi_layout,null)
        //trasladamos la informacion del pedido a los elementos
        mi_vista.tvNombre.text="Nombre: "+objped.nomped.toString()
        mi_vista.tvDescripcion.text="Descripcion: "+objped.desped
        mi_vista.tvPrecio.text="Precio: s/. "+objped.precio.toString()

        var pdao = PedidoDAO(context)
        var cod_imagen=pdao.getImagenID(objped.codped)
        mi_vista.imgpedido.setImageResource(cod_imagen)
        return mi_vista
    }
}