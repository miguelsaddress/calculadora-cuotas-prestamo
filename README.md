# Calculadora de cuotas para préstamos

##Nota

Estos cálculos están hechos por curiosidad personal y con fines educativos, en ningún momento son vinculantes ni tienen ninguna validez práctica o legal


##Uso

Simplemente instancia una Calculadora con los valores requeridos

```scala
    val calculadora = Calculadora.conSistemaFrances(totalDeuda=600, nominal=0.1406, meses=12)
    // para imprimir la tabla mes a mes
    calculadora.printTable(com.mamoreno.util.Tabulator)

    //para simplemente obtener una lista de los calculos mensuales realizados
    val calculos = calculadora.calculosMensuales 
```

Donde:

* **totalDeuda**: es el total en unidades de pago (Por ejemplo, Euros)
* **nominal**: Es el TIN que te proporciona el banco normalizado a 1. Es decir, 14,06% es 0.1406
* **meses**: Número de meses o de plazos para el pago

## Ejemplo de la salida de imprimir la tabla

```
+--------+--------------+----------------+--------+---------+
Adeudado|interesmensual|Adeudado+Interes|   Cuota|Pendiente|
+--------+--------------+----------------+--------+---------+
  600,00|          7,03|          607,03|   53,89|   553,14|
  553,14|          6,48|          559,62|   53,89|   505,73|
  505,73|          5,93|          511,66|   53,89|   457,77|
  457,77|          5,36|          463,13|   53,89|   409,24|
  409,24|          4,79|          414,04|   53,89|   360,15|
  360,15|          4,22|          364,37|   53,89|   310,48|
  310,48|          3,64|          314,12|   53,89|   260,23|
  260,23|          3,05|          263,28|   53,89|   209,39|
  209,39|          2,45|          211,84|   53,89|   157,95|
  157,95|          1,85|          159,80|   53,89|   105,91|
  105,91|          1,24|          107,15|   53,89|    53,27|
   53,27|          0,62|           53,89|   53,89|     0,00|
+--------+--------------+----------------+--------+---------+

Cuota Mensual:    53,89
Total Deuda:   600,00
Total Pagado:   646,67
Total Intereses Pagados:    46,67
```

##Fuentes
* [Para el cálculo mes a mes](http://www.rankia.com/foros/bancos-cajas/temas/857588-tae-plago-aplazado-visa-oro-ing)

* [Para la estimación de la cuota](http://www.abanfin.com/?tit=prestamo-con-cuota-constante-sistema-frances-formulario-economico-financiero&name=Manuales&fid=ee0bcbt)

* [Tabulator](http://stackoverflow.com/a/7542476)
