package controller;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import models.Persona;

import java.util.LinkedHashSet;
public class PersonaController {
    // Método A
    public Set<Persona> filtrarYOrdenar(List<Persona> personas, int umbralEdad) {
        /*
         * Se utiliza un TreeSet con un Comparator personalizado. 
         * El TreeSet garantiza que los elementos estén ordenados según el Comparator.
         * Además, si el Comparator devuelve 0, el TreeSet asume que son duplicados (unicidad lógica),
         * permitiéndonos no modificar la clase Persona.
         */
        Set<Persona> personasFiltradas = new TreeSet<>(new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
                // 1. Ordenar por edad descendente
                if (p1.getEdad() != p2.getEdad()) {
                    return Integer.compare(p2.getEdad(), p1.getEdad()); 
                }
                // 2. Si la edad es igual, ordenar por nombre ascendente ignorando mayúsculas
                return p1.getNombre().compareToIgnoreCase(p2.getNombre());
            }
        });

        // Filtrado manual sin usar streams
        for (Persona p : personas) {
            if (p.getEdad() >= umbralEdad) {
                personasFiltradas.add(p);
            }
        }

        return personasFiltradas;
    }

    // Método B
    public Map<String, Set<String>> agruparPorRangoEdad(List<Persona> personas) {
        /*
         * Se utiliza TreeMap para que las claves ("ADULTO", "JOVEN", "MAYOR") 
         * se ordenen automáticamente alfabéticamente.
         */
        Map<String, Set<String>> agrupacion = new TreeMap<>();
        
        /*
         * Se inicializan los valores del mapa con LinkedHashSet.
         * LinkedHashSet garantiza valores únicos y respeta el orden de inserción.
         */
        agrupacion.put("ADULTO", new LinkedHashSet<>());
        agrupacion.put("JOVEN", new LinkedHashSet<>());
        agrupacion.put("MAYOR", new LinkedHashSet<>());

        // Recorrido manual sin usar streams
        for (Persona p : personas) {
            String categoria;
            int edad = p.getEdad();

            // Determinar el rango etario
            if (edad < 30) {
                categoria = "JOVEN";
            } else if (edad >= 30 && edad < 60) {
                categoria = "ADULTO";
            } else {
                categoria = "MAYOR";
            }

            // Extraer solo el primer nombre
            String[] partesNombre = p.getNombre().split(" ");
            String primerNombreRaw = partesNombre[0];

            // Normalizar el nombre: Primera letra mayúscula, el resto minúsculas (Ej: "jUaN" -> "Juan")
            String primerNombre = primerNombreRaw.substring(0, 1).toUpperCase() + primerNombreRaw.substring(1).toLowerCase();

            // Insertar en el set correspondiente
            agrupacion.get(categoria).add(primerNombre);
        }

        return agrupacion;
    }
}
