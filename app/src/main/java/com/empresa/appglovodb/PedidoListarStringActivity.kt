package com.empresa.appglovodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.empresa.appglovodb.DAO.PedidoDAO
import kotlinx.android.synthetic.main.activity_pedido_listar_string.*

class PedidoListarStringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_listar_string)

        var ped_dao=PedidoDAO(this)
        //data en formato string de la tabla discos de la BD
        var arreglo_string:ArrayList<String> = ped_dao.ListarPedidosString()
        //crear un Adaptador que trabaje con el tipo ArrayList<String>
        var adaptador= ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            arreglo_string)
        //
        lvPedidos.adapter=adaptador

    }
}
