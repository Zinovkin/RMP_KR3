package com.example.zinovkinkr3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlin.math.roundToInt

class Order (
    var cntChildTickets:Int
    , var cntAdultTickets:Int
    , var flight:String
    , var name:String
    , var surname:String
    , var price:Float
) {
  override fun toString(): String {
    var cost:Float = (cntChildTickets + cntAdultTickets) * price

    cost = (cost*100).roundToInt()/100f
    return """Общая стоимость: $cost.
Количество взрослых билетов: $cntAdultTickets.
Количество детских билетов: $cntChildTickets."""
  }
}


class MainActivity : AppCompatActivity() {

  val orders:ArrayList<Order> = ArrayList()

  val ids = arrayOf(
    R.id.editTextNumber
    , R.id.editTextNumber2
    , R.id.editTextTextPersonName
    , R.id.editTextTextPersonName2
    , R.id.editTextTextPersonName3
    , R.id.editTextNumberDecimal
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun addOrder (view:View){
    val edits:ArrayList<EditText> = ArrayList()
    val data:ArrayList<String> = ArrayList()

    var emptyFlg:Boolean=false;
    
    for (id in ids){
      val currentEdit:EditText=findViewById(id)
      edits.add(currentEdit)
      if (currentEdit.text.toString()==""){
        emptyFlg=true
      }
      data.add(currentEdit.text.toString())
    }
    
    if (emptyFlg){
      Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
    }

    orders.add(
      Order(
        cntChildTickets = data[0].toIntOrNull()?:0
        , cntAdultTickets = data[1].toIntOrNull()?:0
        , flight = data[2]
        , name = data[3]
        , surname = data[4]
        , price = data[5].toFloatOrNull()?:0f
      )
    )

    for (edit in edits) {
      edit.setText("")
    }
  }

  fun endOrders (view: View){
    
    if (orders.size==0){
      Toast.makeText(this, "Список заказов пуст", Toast.LENGTH_SHORT).show()
      return
    }
    
    val intent = Intent(this, OrderList::class.java)
    val stringDataArray:ArrayList<String> = ArrayList()

    for (order in orders){
      stringDataArray.add(order.toString())
    }

    intent.putStringArrayListExtra("orders", stringDataArray)
    startActivity(intent)
  }
}