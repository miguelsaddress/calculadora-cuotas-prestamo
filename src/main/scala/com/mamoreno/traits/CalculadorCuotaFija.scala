package com.mamoreno.traits

trait CalculadorCuotaFija {

    require(totalDeuda > 0, s"El total adeudado tiene que ser positivo $totalDeuda")
    require(nominal >= 0, "El nominal tiene que ser positivo")
    require(nominal <= 1, "El nominal tiene que ser menor que uno")
    require(meses > 0, "El numero de meses tiene que ser mayor que cero")

    def totalDeuda: Double
    def nominal: Double
    def meses: Int
    def cuotaFija: Double

}

trait CalculadorCuotaFijaSistemaFrances extends CalculadorCuotaFija {
    def cuotaFija: Double = if (nominal == 0) cuotaSinIntereses else cuotaConIntereses

    private lazy val cuotaSinIntereses: Double = totalDeuda / meses

    // formula: http://www.abanfin.com/?tit=prestamo-con-cuota-constante-sistema-frances-formulario-economico-financiero&name=Manuales&fid=ee0bcbt
    private lazy val cuotaConIntereses: Double = {
        val interesMensual: Double = if (nominal > 0) nominal / meses else 0
        val i = interesMensual //for shortening
        val iExpN = scala.math.pow(1+i, meses)
        val cuota = totalDeuda * (i*iExpN / (iExpN -1))
        cuota
    }
}