package com.empresa.appglovodb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.empresa.appglovodb.Adaptadores.PedidoAdaptador
import com.empresa.appglovodb.DAO.PedidoDAO
import com.empresa.appglovodb.Entidades.Pedido
import kotlinx.android.synthetic.main.activity_pedido_listar.*


class PedidoListarActivity : AppCompatActivity() {

    var arreglo_pedidos=ArrayList<Pedido>()
    var cod_pedido=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_listar)

     MostrarPedidosListview()

        //
        listviewPedidos.onItemLongClickListener= AdapterView.OnItemLongClickListener {
                parent, view, i, id ->

            var xnomped=arreglo_pedidos.get(i).nomped
           cod_pedido= arreglo_pedidos.get(i).codped
            Toast.makeText(applicationContext,"Pedido Selleccionado:"+ xnomped,
                  Toast.LENGTH_SHORT).show()

            EliminarPedido()
            true
        }

    }

    fun MostrarPedidosListview(){
    var ped_dao= PedidoDAO(this)
    //data en formato string de la tabla discos de la BD
    arreglo_pedidos = ped_dao.ListarPedidos()

    //crear un Adaptador del el tipo DiscoAdaptador
    var adaptador= PedidoAdaptador(this,
        R.layout.fila_pedido,
        arreglo_pedidos)
    //
    listviewPedidos.adapter=adaptador
    }


    fun EliminarPedido(){
        var i=Intent(applicationContext, PedidoEliminarActivity::class.java)
      i.putExtra("CODIGO",cod_pedido)
        startActivity(i)
    }

    //se produce cada vez que el Activity vuelve a estar activo
    override fun onResume() {
        super.onResume()
        //Llamar a MostrarPedidosListview
        MostrarPedidosListview()
    }
}
