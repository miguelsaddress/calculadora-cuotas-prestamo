import org.scalatest._
import com.mamoreno.traits.CalculadorCuotaFijaSistemaFrances

class CalculadorCuotaFijaSistemaFrancesSpec extends FunSpecLike with Matchers {
    describe("Trait CalculadorCuotaFijaSistemaFrances") {
        //Helper para instanciar el trait con una clase anonima y poder probar
        def CalculadorCuotaFijaSistemaFrances(td: Double, n: Double, m: Int): CalculadorCuotaFijaSistemaFrances = {
            new CalculadorCuotaFijaSistemaFrances {
                def totalDeuda = td
                def nominal = n
                def meses = m
            }
        }

        it("Si el nominal es cero las cuotas son totalDeuda/meses") {
            val calculador = CalculadorCuotaFijaSistemaFrances(920.40, 0.0, 60)
            calculador.cuotaFija should equal(920.40/60)
        }

        it("Si el nominal no es cero las cuotas siguen el sistema frances") {
            /**
             * Formula aqui
             * http://www.abanfin.com/?tit=prestamo-con-cuota-constante-sistema-frances-formulario-economico-financiero&name=Manuales&fid=ee0bcbt
             */
            val calculador = CalculadorCuotaFijaSistemaFrances(600.0, 0.1406, 12)

            val i = 0.1406/12
            val iExpN = scala.math.pow(1+i, 12)
            val cuota = 600.0 * (i*iExpN / (iExpN -1))
            calculador.cuotaFija should equal(cuota)
        }
    }
}
