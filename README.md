# Métodos de Mapas y Sets

## Datos del Estudiante

* **Universidad:** Universidad Politécnica Salesiana
* **Carrera:** Ingeniería en Ciencias de la Computación
* **Nombre:** Angelo Miguel Carchipulla Pulla
* **Grupo:** Grupo 3 / 2do Ciclo
* **Fecha:** 30 de junio de 2026

---
# Explicación Técnica de la Implementación

## Método A: `filtrarYOrdenar`

* **¿Qué implementación de Set se utilizó?** 

    Usé la clase `TreeSet`.

* **¿Por qué se eligió esa implementación?** 

    Porque me permite pasarle un `Comparator` personalizado, así ordeno y quito duplicados sin tener que tocar el código de la clase `Persona`.

* **¿Cómo se garantiza la unicidad de los datos?** 

    En el `TreeSet`, si el `Comparator` detecta que tienen la misma edad y nombre (devuelve `0`), cuenta como duplicado y solo guarda uno.

* **¿Cómo se conserva o define el orden de los resultados?** 

    Lo definí yo mismo en el `Comparator`: primero revisa que la edad sea de mayor a menor, y si empatan, ordena el nombre alfabéticamente.

* **¿Cómo funciona la lógica aplicada en el método?**   
Hago un ciclo `for` y si la persona pasa el umbral de edad, la agrego al `TreeSet`; la estructura hace el resto del trabajo solita.

---

## Método B: `agruparPorRangoEdad`

* **¿Qué implementación de Map y Set se utilizó?** 

    Usé un `TreeMap` para el mapa principal y un `LinkedHashSet` para guardar los nombres.

* **¿Por qué se eligieron esas implementaciones?**

    El `TreeMap` ordena las categorías (las claves) alfabéticamente por defecto, y el `LinkedHashSet` no permite nombres repetidos pero sí respeta el orden de llegada.

* **¿Cómo se garantiza la unicidad de los datos?** 

    Saco la primera palabra del nombre y la meto al `Set`; por regla de la estructura, si ese nombre ya existe, lo ignora y no lo repite.

* **¿Cómo se conserva o define el orden de los resultados?** 

    Las categorías quedan alfabéticas gracias al `TreeMap`, y los nombres se quedan tal cual fueron apareciendo en la lista original por el `LinkedHashSet`.

* **¿Cómo funciona la lógica aplicada en el método?** 

    Uso unos `if` para ver en qué rango de edad cae la persona, saco su primer nombre y lo agrego directo al grupo que le toca dentro del mapa.