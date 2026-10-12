/*
Contexto General
Una empresa de turismo administra diferentes tipos de vehículos utilizados para realizar excursiones y servicios turísticos.
Todos los vehículos comparten características comunes, pero cada tipo posee atributos y comportamientos específicos.

La empresa trabaja con:
-
Colectivos turísticos
-
Vans ejecutivas
-
Lanchas turísticas
Requisitos del Sistema
Todos los vehículos poseen:
-
patente (String)
-
marca (String)
-
capacidadPasajeros (int)
-
anioFabricacion (int)
Tipos de Vehículos y Atributos Específicos
ColectivoTuristico:
-
cantidadPisos: 1 o 2
VanEjecutiva:
-
aireAcondicionado: true/false
LanchaTuristica:
-
tipoMotor: NAFTA, DIESEL o ELECTRICO
 */
package primerparcialprog2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.time.Year;

public class PrimerParcialProg2 {

    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int anioActual = Year.now().getValue();
    
    public static void main(String[] args) {
        int opcion = 0;
        
        System.out.println("====================================================");
        System.out.println("ACTIVANDO SISTEMA DE GESTION DE TRANSPORTE TURISTICA");
        System.out.println("====================================================");

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion (1 al 8): ");
            
            switch (opcion) {
                case 1: 
                    agregarVehiculo(); 
                    break;
                    
                case 2: 
                    mostrarTodosLosVehiculos(); 
                    break;
                    
                case 3: 
                    realizarServiciosTuristicos(); 
                    break;
                    
                case 4: 
                    buscarVehiculoPorPatente(); 
                    break;
                    
                case 5: 
                    mostrarVehiculosPorCapacidad(); 
                    break;
                    
                case 6: 
                    ordenarPorAnioDescendente(); 
                    break;
                    
                case 7: 
                    ordenarPorCapacidadDescendente(); 
                    break;
                    
                case 8: 
                    System.out.println("CERRANDO EL SISTEMA..."); 
                    break;
                    
                default: 
                    System.out.println("!!!ERROR: OPCION INVALIDA!!!. INTENTE DE NUEVO.");
            }
        } while (opcion != 8);
        
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Agregar vehiculo.");
        System.out.println("2. Mostrar todos los vehiculos.");
        System.out.println("3. Realizar servicios turisticos.");
        System.out.println("4. Buscar vehiculo por patente.");
        System.out.println("5. Mostrar vehiculos con capacidad mayor a un valor.");
        System.out.println("6. Mostrar vehiculos ordenados por año de fabricacion (Descendente).");
        System.out.println("7. Mostrar vehiculos ordenados por capacidad (Descendente).");
        System.out.println("8. Salir.");
    }

    private static void agregarVehiculo() {
        System.out.println("\n--- AGREGAR VEHICULO ---");
        System.out.println("1. Colectivo Turistico");
        System.out.println("2. Van Ejecutiva");
        System.out.println("3. Lancha Turistica");
        
        int tipo = 0;
        
        while (tipo < 1 || tipo > 3) {
            tipo = leerEntero("Seleccione el tipo de vehiculo (1-3): ");
            
            if (tipo < 1 || tipo > 3) {
                System.out.println("ERROR: SELECCIONE UN TIPO VALIDO.");
            }
        }

        String patente = leerPatenteValida();
        
        System.out.print("Ingrese la marca (Predefinida: Desconocida): ");
        String marca = scanner.nextLine().trim();
        
        if (marca.isEmpty()) { 
            marca = "Desconocida"; 
        }

        int capacidad = leerCapacidadValida();
        int anio = leerAnioValido();

        if (tipo == 1) {
            int pisos = 0;
            
            while (pisos != 1 && pisos != 2) {
                pisos = leerEntero("Ingrese cantidad de pisos (1 o 2): ");
                
                if (pisos != 1 && pisos != 2) {
                    System.out.println("ERROR: LA CANT. DE PISOS DEBE SER DE '1 o 2'.");
                }
            }
            
            vehiculos.add(new ColectivoTuristico(patente, marca, capacidad, anio, pisos));
            System.out.println("!COLECTIVO AGREGADO CON EXITO!");

        } else if (tipo == 2) {
            boolean tieneAire = false;
            
            while (true) {
                System.out.print("¿Tiene aire acondicionado? (S/N): ");
                String resp = scanner.nextLine().trim().toUpperCase();
                
                if (resp.equals("S")) { 
                    tieneAire = true; 
                    break; 
                } 
                else if (resp.equals("N")) { 
                    tieneAire = false; 
                    break; 
                }
                
                System.out.println("ERROR: INGRESE 'S' PARA SI o 'N' PARA NO.");
            }
            
            vehiculos.add(new VanEjecutiva(patente, marca, capacidad, anio, tieneAire));
            System.out.println("!VAN EJECUTIVA AGRAGADA CON EXITO!");

        } else if (tipo == 3) {
            String motor = "";
            
            while (true) {
                System.out.print("Ingrese tipo de motor (NAFTA, DIESEL o ELECTRICO): ");
                motor = scanner.nextLine().trim().toUpperCase();
                
                if (motor.equals("NAFTA") || motor.equals("DIESEL") || motor.equals("ELECTRICO")) 
                { 
                    break; 
                }
                
                System.out.println("ERROR: TIPO DE MOTOR INVÀLIDO.");
            }
            
            vehiculos.add(new LanchaTuristica(patente, marca, capacidad, anio, motor));
            System.out.println("!LANCHA TURISTICA AGREGADA CON EXITO!");
        }
    }

    private static void mostrarTodosLosVehiculos() {
        System.out.println("\n--- LISTA DE VEHICULOS ---");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return;
        }
        
        for (Vehiculo v : vehiculos) {
            System.out.println(v.toString());
        }
    }

    private static void realizarServiciosTuristicos() {
        System.out.println("\n--- REALIZANDO SERVICIOS TURISTICOS ---");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return;
        }
        
        for (Vehiculo v : vehiculos) {
            v.realizarServicioTuristico(); 
        }
    }

    private static void buscarVehiculoPorPatente() {
        System.out.println("\n--- BUSCAR VEHICULO POR PATENTE ---");
        
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return; 
        }
        
        System.out.print("Ingrese la patente a buscar: ");
        String patenteBuscada = scanner.nextLine().trim();

        boolean encontrado = false;
        
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equalsIgnoreCase(patenteBuscada)) {
                System.out.println("Vehiculo encontrado: ");
                System.out.println(v.toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontro ningun vehiculo con la patente: " + patenteBuscada);
        }
    }

    private static void mostrarVehiculosPorCapacidad() {
        System.out.println("\n--- FILTRAR VEHICULOS POR CAPACIDAD ---");
        
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return; 
        }
        
        int limite = -1;
        
        while (limite < 0) {
            limite = leerEntero("Ingrese la capacidad minima a buscar (NO INCLUYE EL NUMERO INGRESADO): ");
            
            if (limite < 0) {
                System.out.println("ERROR: LA CAPACIDAD INTRODUCIDA NO PUEDE SER MENOR A '0'.");
            }
        }
                
        boolean hayResultados = false;

        for (Vehiculo v : vehiculos) {
            if (v.getCapacidadPasajeros() > limite) {
                System.out.println(v.toString());
                hayResultados = true;
            }
        }
        
        if (!hayResultados) {
            System.out.println("No se encontraron vehiculos con capacidad mayor a " + limite);
        }
    }

    // =====================================
    // ORDENAMIENTOS UTILIZANDO COLLECTIONS 
    // =====================================

    private static void ordenarPorAnioDescendente() {
        System.out.println("\n--- VEHICULOS ORDENADOS POR AÑO (DESCENDENTE) ---");
        
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return;
        }

        Collections.sort(vehiculos, new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo v1, Vehiculo v2) {
                return Integer.compare(v2.getAnioFabricacion(), v1.getAnioFabricacion());
            }
        });
        
        mostrarTodosLosVehiculos();
    }

    private static void ordenarPorCapacidadDescendente() {
        System.out.println("\n--- VEHICULOS ORDENADOS POR CAPACIDAD (DESCENDENTE) ---");
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados en el sistema.");
            return;
        }

        Collections.sort(vehiculos, new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo v1, Vehiculo v2) {
                return Integer.compare(v2.getCapacidadPasajeros(), v1.getCapacidadPasajeros());
            }
        });
        
        mostrarTodosLosVehiculos();
    }

    // ==========================================
    // METODOS AUXILIARES Y MANEJO DE EXCEPCIONES
    // ==========================================

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("!ATENCION! Debe ingresar un numero entero valido. No se admiten letras ni simbolos.");
            }
        }
    }

    private static String leerPatenteValida() {
        String patente = "";
        while (true) {
            System.out.print("Ingrese patente: ");
            patente = scanner.nextLine().trim();
            
            if (patente.isEmpty()) {
                System.out.println("ERROR: LA PATENTE NO PUEDE ESTAR VACIA.");
                continue;
            }
            
            boolean duplicada = false;
            
            for (Vehiculo v : vehiculos) {
                if (v.getPatente().equalsIgnoreCase(patente)) {
                    duplicada = true;
                    break;
                }
            }
            
            if (duplicada) {
                System.out.println("ERROR: YA EXISTE UN VEHICULO REGISTRADO CON LA PATENTE INGRESADA.");
            } else {
                break;
            }
        }
        return patente.toUpperCase();
    }

    private static int leerCapacidadValida() {
        int cap = 0;
        while (true) {
            cap = leerEntero("Ingrese capacidad de pasajeros (Mayor a '0'): ");
            if (cap > 0) { 
                break; 
            }
            System.out.println("ERROR: LA CAPACIDAD DEBE SER MAYOR A '0'.");
        }
        return cap;
    }

    private static int leerAnioValido() {
        int anio = 0;
        while (true) {
            anio = leerEntero("Ingrese año de fabricacion: ");
            if (anio >= 1990 && anio <= anioActual) { 
                break; 
            }
            System.out.println("ERROR: EL AÑO DEBE ESTAR ENTRE '1990' y EL AÑO ACTUAL ('" + anioActual + "').");
        }
        return anio;
    }
}
