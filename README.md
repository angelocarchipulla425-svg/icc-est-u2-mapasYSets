# Métodos de Mapas y Sets

## Datos del Estudiante

* **Universidad:** Universidad Politécnica Salesiana
* **Carrera:** Ingeniería en Ciencias de la Computación
* **Nombre:** Angelo Miguel Carchipulla Pulla
* **Grupo:** Grupo 3 / 2do Ciclo
* **Fecha:** 30 de junio de 2026

---
# Explicación Técnica de la Implementación

A continuación, se detalla la lógica aplicada en la clase `PersonaController` haciendo uso de estructuras de datos, sin modificar el modelo original ni utilizar utilidades de ordenamiento externo o *Streams*.

## Método A: `filtrarYOrdenar`

* **¿Qué implementación de Set se utilizó?**

    Se utilizó la implementación `TreeSet`.
* **¿Por qué se eligió esa implementación?**

    Se eligió el `TreeSet` permite recibir un `Comparator` en su constructor, haciendo que ordene y determine la igualdad de los objetos, cumpliendo así con lo pedido sin alterar el modelo.
* **¿Cómo se garantiza la unicidad de los datos?**
    La unicidad se garantiza mediante la interfaz del `Comparator`. En un `TreeSet`, si el método `compare(Persona p1, Persona p2)` devuelve `0`, la estructura trata a ambos elementos como duplicados y descarta el segundo. Al comparar primero la edad y luego el nombre (`compareToIgnoreCase`), cualquier par de objetos con misma edad y mismo nombre sin importar mayúsculas devolverá `0`, conservando solo uno.
* **¿Cómo se conserva o define el orden de los resultados?**
    El orden está definido explícitamente en el `Comparator`. Para la edad descendente se evalúa `Integer.compare(p2.getEdad(), p1.getEdad())` (invirtiendo el orden convencional de p1 y p2). Si las edades son idénticas, se recurre a `p1.getNombre().compareToIgnoreCase(p2.getNombre())` para el ordenamiento alfabético ascendente. `TreeSet` reubica el nodo automáticamente en la posición correcta del árbol durante la inserción.
* **¿Cómo funciona la lógica aplicada en el método?**
    Se crea el `TreeSet` con la lógica de comparación descrita. Luego, se itera manualmente la lista original mediante un ciclo `for-each`. Por cada objeto `Persona`, se evalúa si su edad cumple la condición (mayor o igual al umbral). Si la cumple, se inserta mediante `.add()`; el `TreeSet` filtra duplicados y ordena automáticamente el registro sobre la marcha.

## Método B: `agruparPorRangoEdad`

* **¿Qué implementación de Map y Set se utilizó?**
    Se empleó `TreeMap` para el mapa principal y `LinkedHashSet` para la colección de nombres asociados a cada clave.
* **¿Por qué se eligieron esas implementaciones?**

    Se eligió `TreeMap` porque sus claves (String) se ordenan alfabéticamente de manera natural e inmediata al insertarse, cumpliendo con el requisito de ordenar el mapa por clave. Por su parte, `LinkedHashSet` era la única estructura idónea para los valores, ya que combina la unicidad de un `Set` con la capacidad de preservar de manera estricta el orden en el que los elementos fueron añadidos.
* **¿Cómo se garantiza la unicidad de los datos?**

    La unicidad recae en el comportamiento base del `Set`. Antes de registrar el dato, se toma el nombre completo de la persona y se extrae la primera palabra mediante `split(" ")[0]`. Al hacer un `.add(primerNombre)` sobre el `LinkedHashSet`, la estructura internamente verifica si esa cadena de texto exacta ya existe; si está repetida, simplemente ignora la inserción sin alterar el conjunto.
* **¿Cómo se conserva o define el orden de los resultados?**
    El mapa mantiene sus claves ordenadas de forma alfabética intrínsecamente ("ADULTO" -> "JOVEN" -> "MAYOR"). El orden de los nombres únicos se preserva gracias a los punteros internos de lista enlazada que posee `LinkedHashSet`, lo cual garantiza que los nombres aparezcan exactamente en el mismo orden de aparición en el que fueron procesados desde la lista original.
* **¿Cómo funciona la lógica aplicada en el método?**

    El método preinicializa el `TreeMap` insertando las tres categorías exigidas con su respectivo `LinkedHashSet` vacío. A continuación, itera la lista de personas con un `for-each`. Mediante una estructura condicional `if-else`, clasifica la edad actual para encontrar la clave de mapa correspondiente. Acto seguido, aísla el primer nombre y lo inyecta en el Set de dicha categoría, permitiendo que la propia estructura resuelva la regla de orden y duplicidad.