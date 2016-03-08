
package com.mamoreno
import com.mamoreno.util.Tabulator

object Main extends App {

    val calculadora = Calculadora.conSistemaFrances(totalDeuda=600, nominal=0.1406, meses=12)
    calculadora.printTable(Tabulator)

    val calculadora2 = Calculadora.conSistemaFrances(totalDeuda=378, nominal=0.1317, meses=12)
    calculadora2.printTable(Tabulator)

}
