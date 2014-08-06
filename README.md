2d-graph-equation
=================

Graficador de funciones de una variable. e.g  y=x, y = x^2 ...

Uste puede compilar las dos clases de las cuales consa el proyecto y ejecutar directamente el ``java``para correr el programa.

Para hacer algo un tanto más elegante, puede empaquetar esto en un jar.

Para hacer el jar debe seguir los siguientes pasos:

Enmpaquetando JAR
-----------------
Para seguir con esto usted debe saber que unicamente es necesario poseer los .class (bytecodes) dentro del JAR.

1. Como primer paso es necesario compilar las clases ``javac *.java``, dentro del directorio del proyecto.
2. Ya conseguidos los ``.class``, debemos empaquetarlos a un nuevo archivo 

 ``jar cmf manifest.txt <jar_name>.jar *.class *.jar``
 
  a. El flag *c* representa la creacion de un archivo.
  b. El flag *m* representa que indicaremos el archivo manifiesto a partir de uno existente.
  c. Y *f* será en nombre de salida del jar.
  d. El resto de parametros *.class *.jar, son los archivos a empaquetar.
  
Vemos que se indicó un ``manifest.txt``, este es un archivo manifiesto para el JAR que existe, que debería tener el siguiente contenido:

> Main-Class: Graph <br/>
> Class-Path: exp4j-0.4.0.ALPHA-3.jar
> <br/>

Acá solo definicion el Main para el jar, y agregar al Class-path el JAR  que nos sirve de ayuda.

Una vez hecho esto, deberíamos poder ejecutar tecleando simplemente

``
  java -jar <jar_name>.jar
``



