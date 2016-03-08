import org.scalatest._
import com.mamoreno.traits.CalculadorCuotaFija

class CalculadorCuotaFijaSpec extends FunSpecLike with Matchers {
    describe("Trait CalculadorCuotaFija") {

        //Helper para instanciar el trait con una clase anonima y poder probar
        def CalculadorCuotaFija(
            td: Double = 600.0, n: Double = 0.1406, m: Int = 12, c: Double = 0.0
        ): CalculadorCuotaFija = {
            new CalculadorCuotaFija {
                def totalDeuda = td
                def nominal = n
                def meses = m
                def cuotaFija = c
            }
        }

        it("debe tener una deuda total mayor que cero") {
            noException should be thrownBy CalculadorCuotaFija(td = 750)

            intercept[java.lang.IllegalArgumentException] {
                val calculadora = CalculadorCuotaFija(td = 0)
            }
        }

        it("debe tener un tipo nominal entre cero y uno inclusive") {
            noException should be thrownBy CalculadorCuotaFija(n=0.1406)
            noException should be thrownBy CalculadorCuotaFija(n=(-0))
            noException should be thrownBy CalculadorCuotaFija(n=0)
            noException should be thrownBy CalculadorCuotaFija(n=1)

            intercept[java.lang.IllegalArgumentException] {
                val calculadora = CalculadorCuotaFija(n = (-0.1406))
            }
        }

        it("debe obtener una cantidad positiva de meses") {
            noException should be thrownBy CalculadorCuotaFija(m = 12)

            intercept[java.lang.IllegalArgumentException] {
                val calculadora = CalculadorCuotaFija(m = 0)
            }

            intercept[java.lang.IllegalArgumentException] {
                val calculadora = CalculadorCuotaFija(m = (-8))
            }
        }
    }
}
