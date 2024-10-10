package com.example.practica1_calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private var expresion = "";
    private var resultadoAnterior: Double? = null
    private var hayPunto: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Vinculo los botoncitos graficos
        val pantalla = findViewById<TextView>(R.id.pantalla);
        val memoria = findViewById<TextView>(R.id.pantalla2)

        val bot0 = findViewById<Button>(R.id.Bot_0)
        val bot1 = findViewById<Button>(R.id.Bot_1)
        val bot2 = findViewById<Button>(R.id.Bot_2)
        val bot3 = findViewById<Button>(R.id.Bot_3)
        val bot4 = findViewById<Button>(R.id.Bot_4)
        val bot5 = findViewById<Button>(R.id.Bot_5)
        val bot6 = findViewById<Button>(R.id.Bot_6)
        val bot7 = findViewById<Button>(R.id.Bot_7)
        val bot8 = findViewById<Button>(R.id.Bot_8)
        val bot9 = findViewById<Button>(R.id.Bot_9)
        val sqrt = findViewById<Button>(R.id.sqrt)
        val expo = findViewById<Button>(R.id.cuadrado)
        val decimal = findViewById<Button>(R.id.decimal)

        val botsuma = findViewById<Button>(R.id.Sumar)
        val botresta = findViewById<Button>(R.id.restar)
        val botdivision = findViewById<Button>(R.id.Dividir)
        val botmultiplicar = findViewById<Button>(R.id.Multiplicar)

        val botReset = findViewById<Button>(R.id.bot_AC)
        val botIgual = findViewById<Button>(R.id.bot_igual)
        val botC = findViewById<Button>(R.id.bot_C)

        // Al hacer click
        bot0.setOnClickListener { pulsarBoton("0", pantalla, memoria) }
        bot1.setOnClickListener { pulsarBoton("1", pantalla, memoria) }
        bot2.setOnClickListener { pulsarBoton("2", pantalla, memoria) }
        bot3.setOnClickListener { pulsarBoton("3", pantalla, memoria) }
        bot4.setOnClickListener { pulsarBoton("4", pantalla, memoria) }
        bot5.setOnClickListener { pulsarBoton("5", pantalla, memoria) }
        bot6.setOnClickListener { pulsarBoton("6", pantalla, memoria) }
        bot7.setOnClickListener { pulsarBoton("7", pantalla, memoria) }
        bot8.setOnClickListener { pulsarBoton("8", pantalla, memoria) }
        bot9.setOnClickListener { pulsarBoton("9", pantalla, memoria) }
        sqrt.setOnClickListener { pulsarBoton("sqrt", pantalla, memoria) }
        expo.setOnClickListener { pulsarBoton("expo", pantalla, memoria) }
        decimal.setOnClickListener { pulsarBoton(".", pantalla, memoria) }

        botsuma.setOnClickListener { pulsarBoton(",+,", pantalla, memoria) }
        botresta.setOnClickListener { pulsarBoton(",-,", pantalla, memoria) }
        botmultiplicar.setOnClickListener { pulsarBoton(",*,", pantalla, memoria) }
        botdivision.setOnClickListener { pulsarBoton(",/,", pantalla, memoria) }

        botReset.setOnClickListener { limpiarExpresion(pantalla, memoria) }
        botIgual.setOnClickListener { evaluarExpresion(pantalla, memoria) }
        botC.setOnClickListener { limpiarUltimoBoton(pantalla) }
    }

    //Pone 0 en la pantalla pero limpia la expresion
    private fun limpiarExpresion(pantalla: TextView, memoria: TextView) {
        expresion = ""
        hayPunto = false
        pantalla.text = "0"
        resultadoAnterior = 0.0;
        memoria.text = "0" // Limpiar memoria también
    }

    //Borra el ultimo elemento introducido
    private fun limpiarUltimoBoton(pantalla: TextView) {
        if (expresion.isNotEmpty()) {
            if (expresion.last() == '.') {
                hayPunto = false;
            }
            expresion = if (expresion.last() == ',') {
                expresion.substring(0, expresion.length - 3)
            } else {
                expresion.substring(0, expresion.length -1)
            }
            pantalla.text = expresion.replace(",", "")
        } else {
            return
        }
    }

    //Maneja la pulsacion de botones y creacion de la expresion
    private fun pulsarBoton(valor: String, pantalla: TextView, memoria: TextView) {
        //Si el valor no es un numero
        if (valor in listOf(",+,", ",-,", ",*,", ",/,", ".")) {
            //Para seguir operando con el resultado obtenido
            if (resultadoAnterior != null && expresion.isEmpty() && valor != ".") {
                expresion += resultadoAnterior.toString()
            //Para evitar que se puedan meter varios operadores seguidos
            } else if (expresion.isNotEmpty() && expresion.last() == ',') {
                return
            //Para evitar que se pongan varios puntos seguidos
            } else if (expresion.isNotEmpty() && expresion.last() == '.') {
                return
                //Para evitar que empieces con un punto o operador solo
            } else if (expresion.isEmpty() || expresion.last().toString()
                    .matches(Regex("[.+\\-* /]"))) {
                return
                //Para evitar que se ponga mas de un punto
            } else if (valor == "." && hayPunto) {
                return
            }
        }
        //Si el valor introducido es un punto, cambiar booleana a true
        if (valor == ".") {
            hayPunto = true;
        }
        //Manejo del squareroot
        if (valor == "sqrt" && expresion.isNotEmpty()) {
            if (!expresion.contains(",") && expresion.last().toString().matches(Regex("[0-9]"))) {
                val resultado = sqrt(expresion)
                pantalla.text = resultado.toString()
                memoria.text = resultado.toString()
                resultadoAnterior = resultado
                expresion = "";
            } else {
                return
            }
        }

        //Manejo del elevado al cuadrado/Potencia 2
        if (valor == "expo" && expresion.isNotEmpty()) {
            if (!expresion.contains(",") && expresion.last().toString().matches(Regex("[0-9]"))) {
                val resultado = exponencial(expresion)
                pantalla.text = resultado.toString()
                memoria.text = resultado.toString()
                resultadoAnterior = resultado
                expresion = "";
            } else {
                return
            }
        }

        //Evitar se añada a la expresion sqrt o expo como string
        if (valor != "sqrt" && valor != "expo") {
            expresion += valor
            pantalla.text = expresion.replace(",", "")
        }
    }

    //Uso de la clase math de kotlin para sqrt (Y truncar el resultado)
    private fun sqrt(valor: String): Double {
        val squareroot = kotlin.math.sqrt(valor.toDouble())
        val truncar = squareroot.toString().take(5)
        val resultado = truncar.toDouble()
        return resultado
    }

    //Uso de la clase math de kotlin para power
    private fun exponencial(valor: String): Double {
        val base = valor.toDouble()
        val expo = base.pow(2)
        return expo
    }

    //Inicia la evaluacion de la expresion
    private fun evaluarExpresion(pantalla: TextView, memoria: TextView) {
        //Verigica la expresion tenga contenido
        if (expresion.isNotEmpty()) {
            //Asegura que la expresion no termine en una coma
            if (expresion.last() != ',') {
                //Corre la funcion que evalua el string
                val resultado = separarString(expresion)
                //Corta el string a maximo 8 caracteres
                pantalla.text = resultado.toString().take(8)
                memoria.text = resultado.toString().take(8)
                val truncar = resultado.toString().take(8)
                val resultadoCorto = truncar.toDouble()
                //Guarda el resultado anterior con la version corta
                resultadoAnterior = resultadoCorto
                //Vacia la expresion
                expresion = ""
            }
        } else {
            return
        }
    }

    //Separa la expresion por coma a una listaMutable (Como ArrayLists pero kotlin)
    private fun separarString(expresion: String): Double {
        val elementos = expresion.split(",").toMutableList();
        return evaluarString(elementos);
    }

    //Evalua el string separado
    private fun evaluarString(listaElementos: MutableList<String>): Double {
        var resultado = 0.0;
        var i = 0;
        //Uso un while porque voy a jugar con el tamaño de la lista y usar for causaria problemas
        //Primera pasada para buscar divisiones y multiplicaciones
        while (i < listaElementos.size) {
            if (listaElementos[i] == "*" || listaElementos[i] == "/") {
                val num1 = listaElementos[i - 1].toDouble();
                val num2 = listaElementos[i + 1].toDouble();
                if (listaElementos[i] == "*") {
                    resultado = num1 * num2;
                //Evitar division entre cero
                } else if (listaElementos[i] == "/" && num2 == 0.0) {
                    return Double.NaN
                //Si num2 no es cero, dividir normal
                } else if (listaElementos[i] == "/" && num2 != 0.0) {
                    resultado = num1 / num2;
                }
                //Guardar el resultado
                listaElementos[i - 1] = resultado.toString();
                //Borrar el operador y segundo numero dejando solo el resultado
                listaElementos.removeAt(i);
                listaElementos.removeAt(i);
                //retroceder la i un paso para evaluar la posicion previa al resultado obtenido
                i--;
            }
            //aumentar index en 1
            i++;
        }
        i = 0

        //Mismo concepto pero con suma y resta
        while (i < listaElementos.size) {
            if (listaElementos[i] == "+" || listaElementos[i] == "-") {
                val num1 = listaElementos[i - 1].toDouble();
                val num2 = listaElementos[i + 1].toDouble();
                resultado = if (listaElementos[i] == "+") num1 + num2 else num1 - num2
                listaElementos[i - 1] = resultado.toString();
                listaElementos.removeAt(i)
                listaElementos.removeAt(i)
                i--
            }
            i++
        }
        //Devuelve la posicion 0 que contiene el resultado final de toda la operacion
        return listaElementos[0].toDouble();
    }
}