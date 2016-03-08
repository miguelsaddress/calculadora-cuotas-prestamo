package com.mamoreno

import com.mamoreno.traits._
import com.mamoreno.util.TableFormatter

class Calculadora(val totalDeuda: Double, val nominal: Double, val meses: Int) { 
this: CalculadorCuotaFija =>
    
    private def decStr(n: Double) = "%8.2f".format(n)

    case class CalculoMensual(capital: Double, cuotaFija: Double, interesMensual: Double) {
        def capitalMasInteres = capital + interesMensual
        def saldoPendiente = scala.math.abs(capitalMasInteres - cuotaFija)
        def toRawNumbers = List(decStr(capital), decStr(interesMensual), decStr(capitalMasInteres), decStr(cuotaFija), decStr(saldoPendiente))
    }

    val totalPagado = cuotaFija*meses
    val interesesPagados = totalPagado - totalDeuda

    // http://www.rankia.com/foros/bancos-cajas/temas/857588-tae-plago-aplazado-visa-oro-ing
    lazy val calculosMensuales: List[CalculoMensual] = {
        @scala.annotation.tailrec
        def loop(mCalculos: List[CalculoMensual], remanente: Double, times: Int): List[CalculoMensual] = {
            if(times == 0) { mCalculos }
            else {
                val interesMes = (remanente*nominal) / meses
                val calcMensual = CalculoMensual(remanente, cuotaFija, interesMes)
                loop(mCalculos ::: (calcMensual::Nil), calcMensual.saldoPendiente, times-1)
            }
        }
        loop(List[CalculoMensual](), totalDeuda, meses)
    }

    def printTable(tableFormatter: TableFormatter) = {
        val header = List("Adeudado","interesmensual","Adeudado+Interes","Cuota","Pendiente")
        val values = calculosMensuales.map(_.toRawNumbers)
        val table = tableFormatter.format(header::values)

        val output = f"""
        |$table
        |
        |Cuota Mensual: ${decStr(cuotaFija)}
        |Total Deuda: ${decStr(totalDeuda)}
        |Total Pagado: ${decStr(totalPagado)}
        |Total Intereses Pagados: ${decStr(interesesPagados)}
        """.stripMargin

        println(output)
    }
}

object Calculadora {
    def conSistemaFrances(totalDeuda: Double, nominal: Double, meses: Int) = {
        new Calculadora(totalDeuda, nominal, meses) with CalculadorCuotaFijaSistemaFrances
    }
}