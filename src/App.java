import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.PersonaController;
import models.Persona;
public class App {
    public static void main(String[] args) throws Exception {
        // 1. Crear una lista de personas con casos de prueba clave
        List<Persona> listaPersonas = new ArrayList<>();
        
        listaPersonas.add(new Persona("Juan Pérez", 25)); 
        listaPersonas.add(new Persona("juan pérez", 25)); // Duplicado lógico de edad y nombre (case-insensitive)
        listaPersonas.add(new Persona("Ana Gómez", 35));
        listaPersonas.add(new Persona("Carlos Ruiz", 35)); // Misma edad que Ana, debe ir después alfabéticamente
        listaPersonas.add(new Persona("María López", 65));
        listaPersonas.add(new Persona("Juan Morales", 40)); // Mismo primer nombre que el primer Juan
        listaPersonas.add(new Persona("Luis Vargas", 15));  // Menor al umbral de edad de prueba

        listaPersonas.add(new Persona("Mateo Vargis", 25));
        listaPersonas.add(new Persona("Bryam Asnalema", 55));
        listaPersonas.add(new Persona("Fabricio Roman", 75));
        listaPersonas.add(new Persona("Fabi Romano", 45));
        listaPersonas.add(new Persona("Fer Tomillo", 35));
        listaPersonas.add(new Persona("Anali Estrada", 65));
        listaPersonas.add(new Persona("Marmoush Salah", 48));
        listaPersonas.add(new Persona("Mohammed Caguana", 39));
        listaPersonas.add(new Persona("Michelle Marca", 44));
        listaPersonas.add(new Persona("Valeria Jimenez", 22));
        listaPersonas.add(new Persona("Nathali Jimenez", 11));
        

        PersonaController controller = new PersonaController();

        System.out.println("=== LISTA ORIGINAL ===");
        for (Persona p : listaPersonas) {
            System.out.println(p.getNombre() + " - " + p.getEdad());
        }

        System.out.println("\n--------------------------------------------------");

        // --- PRUEBA MÉTODO A ---
        int umbralEdad = 20; // Luis Vargas (15) no debería aparecer
        System.out.println("=== MÉTODO A: Filtrar (Umbral >= " + umbralEdad + ") y Ordenar ===");
        
        Set<Persona> resultadoA = controller.filtrarYOrdenar(listaPersonas, umbralEdad);
        
        for (Persona p : resultadoA) {
            // Debería imprimir ordenado por edad descendente, y eliminar a "juan pérez"
            System.out.println("Nombre: " + p.getNombre() + " | Edad: " + p.getEdad());
        }

        System.out.println("\n--------------------------------------------------");

        // --- PRUEBA MÉTODO B ---
        System.out.println("=== MÉTODO B: Agrupar por Rango de Edad ===");
        
        Map<String, Set<String>> resultadoB = controller.agruparPorRangoEdad(listaPersonas);
        
        for (Map.Entry<String, Set<String>> entry : resultadoB.entrySet()) {
            System.out.println("Rango: " + entry.getKey());
            System.out.println("Nombres únicos: " + entry.getValue());
            System.out.println();
        }
    }
}
