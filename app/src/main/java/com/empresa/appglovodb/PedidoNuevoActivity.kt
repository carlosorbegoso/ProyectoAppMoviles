package com.empresa.appglovodb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.empresa.appglovodb.DAO.CategoriaDAO
import com.empresa.appglovodb.DAO.PedidoDAO
import com.empresa.appglovodb.Entidades.Categorias
import com.empresa.appglovodb.Entidades.Pedido
import kotlinx.android.synthetic.main.activity_pedido_nuevo.*

class PedidoNuevoActivity : AppCompatActivity() {
    var list_cat=ArrayList<Categorias>()
    var drawerLayout: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_nuevo)
        btnNuevo.setOnClickListener {
            edtCodigo.setText("")
            edtNombre.setText("")
            edtDescripcion.setText("")
            edtCantidad.setText("")
            edtPrecio.setText("")
            //
            edtCodigo.requestFocus()

        }
      MostrarCategorias()
        drawerLayout = findViewById(R.id.drawer_layout)
    }

    fun MostrarCategorias(){
     var crudcat=CategoriaDAO(this)
        list_cat=crudcat.ListarCategorias()
        var cadenas= ArrayList<String>()
     // recorrer la lista de categorias
        for (x in list_cat){
       cadenas.add(""+x.codcat+" - "+x.nomcat)
        }
            var adaptador=ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                cadenas)

            spinnerCategorias.adapter=adaptador

    }

    fun BotonGrabarPedido(v:View){
        var crud= PedidoDAO(this)
        var dato = spinnerCategorias.selectedItem.toString()
        var pos = dato.indexOf(' ')
        dato = dato.substring(0,pos).trim()

        //Toast.makeText(this,"codigo: "+dato,Toast.LENGTH_LONG).show()

        var xped= Pedido(edtCodigo.text.toString().toInt(),
                    edtNombre.text.toString(),
                     edtDescripcion.text.toString(),
                     edtCantidad.text.toString().toInt(),
                    edtPrecio.text.toString().toDouble(),dato.toInt())
        crud.GrabarPedido(xped)
        Toast.makeText(this,"Nuevo pedido grabado correctamente", Toast.LENGTH_LONG).show()
    }

    fun BotonListarPedidos(v:View){
        //var i=Intent(applicationContext, PedidoListarStringActivity::class.java)
        var i=Intent(applicationContext, PedidoListarActivity::class.java)
        //
        startActivity(i)
    }

    fun BotonNuevoPedido(v: View){
        edtCodigo.setText("")
        edtNombre.setText("")
        edtDescripcion.setText("")
        edtCantidad.setText("")
        edtPrecio.setText("")
        //
        edtCodigo.requestFocus()
    }

    fun BotonPedidoPorCategoria(v: View){
      var i= Intent(this,PedidosCategoriaActivity::class.java)
     startActivity(i)

    }
    fun ClickMenu(view: View?) {
        MenuActivity.openDrawer(drawerLayout)
    }

    fun ClickLogo(view: View?) {
        MenuActivity.closeDrawer(drawerLayout)
    }

    fun ClickHome(view: View?) {
        MenuActivity.redirectActivity(this, MenuActivity::class.java)
    }

    fun ClickDashboard(view: View?) {
        recreate()
    }

    fun ClickAboutUs(view: View?) {
        MenuActivity.redirectActivity(this, AboutUs::class.java)
    }

    fun ClickLogout(view: View?) {
        MenuActivity.logout(this)
    }

    override fun onPause() {
        super.onPause()
        // cerrar drawer
        MenuActivity.closeDrawer(drawerLayout)
    }

    fun salir(v:View){

        finish()
    }
}
