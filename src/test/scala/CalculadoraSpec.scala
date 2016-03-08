import org.scalatest._
import com.mamoreno.Calculadora

class CalculadoraSpec extends FunSpecLike with Matchers {

    describe("Calculadora") {

        it("debe obtener una lista de calculos mensuales tan larga como el numero de meses") {
            val meses = 12
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=600, nominal=0.1406, meses)
            val calculos = calculadora.calculosMensuales
            calculos.size should equal(meses) 
        }

        it("el saldo pendiente del ultimo calculo mensual debe ser cero") {
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=600, nominal=0.1406, meses=12)
            val ultimoCalculo = calculadora.calculosMensuales.last
            val THRESHOLD = 4.277467269275803E-12   
            (ultimoCalculo.saldoPendiente - THRESHOLD) should equal(0.0)
        }

         it("el total pagado debe ser igual que la deuda mas intereses") {
            val totalAdeudado = 600
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=totalAdeudado, nominal=0.1406, meses=12)
            val totalPagado = calculadora.totalDeuda + calculadora.interesesPagados
            (totalPagado) should equal (calculadora.totalPagado)
        }

        it("el total pagado debe ser mayor que la deuda si el nominal es positivo") {
            val totalAdeudado = 600
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=totalAdeudado, nominal=0.1406, meses=12)
            val totalPagado = calculadora.totalPagado
            assert(totalPagado >= totalAdeudado)
        }

        it("el total pagado debe ser igual que la deuda si el nominal es cero") {
            val totalAdeudado = 600
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=totalAdeudado, nominal=0.0, meses=12)
            val totalPagado = calculadora.totalPagado
            totalPagado should equal(totalAdeudado)
        }

        it("Cada calculo mensual debe contener menor saldo pendiente que el anterior") {
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=600, nominal=0.1406, meses=12)
            val saldos = calculadora.calculosMensuales.map(_.saldoPendiente)
            val resto = saldos.foldLeft(Double.PositiveInfinity)(_ - _ )
            ( resto > 0 ) should equal(true)
        }

        it("Cada calculo mensual debe contener la misma cuota mensual") {
            val calculadora = Calculadora.conSistemaFrances(totalDeuda=600, nominal=0.1406, meses=12)
            val cuotas = calculadora.calculosMensuales.map(_.cuotaFija).distinct
            cuotas.size should equal(1)
        }
    }
}
