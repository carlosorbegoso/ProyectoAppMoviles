package com.empresa.appglovodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.empresa.appglovodb.Adaptadores.PedidoAdaptador
import com.empresa.appglovodb.DAO.CategoriaDAO
import com.empresa.appglovodb.DAO.PedidoDAO
import com.empresa.appglovodb.Entidades.Categorias
import com.empresa.appglovodb.Entidades.Pedido
import kotlinx.android.synthetic.main.activity_platos_categoria.*

class PlatosCategoriaActivity : AppCompatActivity() {

    var list_cat = ArrayList<Categorias>()
    var arreglo_pedidos = ArrayList<Pedido>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platos_categoria)

      MostrarCategorias()
        //establecer evento OnItemSelected
        spnCategorias.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var cod = position+1
                ListarPedidosPorCategoria(cod)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }


    fun ListarPedidosPorCategoria(cod:Int){
    var peddao= PedidoDAO(this)
        arreglo_pedidos = peddao.PedidosporCategoria(cod)
        var adaptador = PedidoAdaptador(this,R.layout.fila_pedido,arreglo_pedidos)
    lvPedidosCategoria.adapter = adaptador
    }

    fun MostrarCategorias(){
        var crudcat= CategoriaDAO(this)
        list_cat=crudcat.ListarCategorias()
        var cadenas= ArrayList<String>()
        // recorrer la lista de categorias
        for (x in list_cat){
            cadenas.add(""+x.codcat+" - "+x.nomcat)
        }
        var adaptador= ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item,
            cadenas)

        spnCategorias.adapter=adaptador
    }
}
