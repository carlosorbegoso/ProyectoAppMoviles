package com.empresa.appglovodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.empresa.appglovodb.DAO.PedidoDAO
import com.empresa.appglovodb.Entidades.Pedido
import kotlinx.android.synthetic.main.activity_pedido_eliminar.*

class PedidoEliminarActivity : AppCompatActivity() {

    var cod=0 // Codigo en cero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_eliminar)


     // recuperamos el intent que viene del activity
        var x=intent
        cod=x.getIntExtra("CODIGO",0)
        //mostrando el codigo del pedido en la barra del titutlo activity
        this.title="Codigo del Pedido a Eliminar:"+cod

        //recuperar los datos del disco  por medio de su codigo
        var peddao= PedidoDAO(this)
        var obj:Pedido=peddao.ObtenerPedido(cod)

        tvCodigo.text="Codigo: "+cod.toString()
        tvNombre.text="Nombre del producto: "+obj.nomped
        tvDescripcion.text="Descripcion del producto:"+obj.desped
        tvCantidad.text="Cantidad: "+obj.codcat.toString()
        tvPrecio.text="Precio: "+obj.precio.toString()
        tvCodcat.text="Categoria: "+obj.codcat.toString()

        imageViewPedido.setImageResource(peddao.getImagenID(cod))
        btnCancelar.setOnClickListener {
            finish()
        }
    }

    fun BotonEliminar(v:View){
    var peddao=PedidoDAO(this)
        var mensaje = peddao.EliminarPedido(cod)
    Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
    }
}
