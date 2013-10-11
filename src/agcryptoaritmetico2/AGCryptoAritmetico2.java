package agcryptoaritmetico2;

import agcryptoaritmetico2.interfaz.ventanita;
import java.util.ArrayList;

public class AGCryptoAritmetico2 {

    static int maximaAptitud;

    public static void main(String[] args) {
        ventanita unaVentana = new ventanita();
        unaVentana.setVisible(true);
    }

    public static void comenzarAlgoritmo(String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion) {
        int poblacionNumero = 1;
        Poblacion poblacionActual, poblacionNueva;

        //Calcular porcentajes de Seleccion/Cruza/Mutacion
        porcentajeSeleccion = (porcentajeSeleccion * cantIndividuos) / 100;
        porcentajeCruza = (porcentajeCruza * cantIndividuos) / 100;
        porcentajeMutacion = (porcentajeMutacion * cantIndividuos) / 100;

        //generar las restricciones para la operacion
        ArrayList<ArrayList<Integer>> restricciones = obtenerRestricciones(operacion);
        //calcular pesima aptitud
        obtenerMaximaAptitud(restricciones);

        //Generar primer población ALEATORIA        
        poblacionActual = new Poblacion(operacion, cantIndividuos, restricciones);
        System.out.println("Población Número: " + poblacionNumero + " Aptitud: " + poblacionActual.aptitudProm() + " %Mutación: " + porcentajeMutacion);

        int valorMax = ((int) (0.50 * cantIndividuos));
        double acumulador = 0;

        //generar poblaciones nuevas a partir de una vieja mientras no se alcance un individuo resultado
        while (poblacionActual.esSolucion() == null) {
            poblacionNueva = new Poblacion(operacion, cantIndividuos, poblacionActual, restricciones, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion, maximaAptitud);
            poblacionActual = poblacionNueva;
            poblacionNumero++;
            System.out.println("Población Número: " + poblacionNumero + " Aptitud: " + poblacionActual.aptitudProm() + " %Mutación: " + porcentajeMutacion);

            //calculo de mutacion adaptativa por temperatura ascendente
            acumulador += 0.00025 * cantIndividuos;
            if (acumulador >= 1) {
                if (porcentajeMutacion < valorMax) {
                    porcentajeMutacion += (int) acumulador * 4; //aumento 4 individuos en mutacion     
                    porcentajeCruza -= acumulador * 2; //disminuyo 4individuos en Cruza
                } else {
                    porcentajeMutacion = valorMax;
                    porcentajeCruza = (100 - porcentajeMutacion - porcentajeSeleccion) / 2;
                }
            }
            if (acumulador >= 1) {
                acumulador = 0; //Setea devuelta a 0 para solucionar el problema que sumaba siempre                
            }

        }
        //CARTEL GANASTE
        if (poblacionActual.esSolucion() != null) {
            System.out.println("\n" + poblacionActual.esSolucion().toString());
            System.out.println("Cantidad de Iteracciones: " + poblacionNumero);
            System.out.println("%Seleccion: " + porcentajeSeleccion + " %Cruza: " + (porcentajeCruza * 2) + " %Mutacion: " + porcentajeMutacion + " CantIndividuos: " + poblacionActual.getIndividuos().size());
        }
    }

    public static ArrayList<ArrayList<Integer>> obtenerRestricciones(String operacion) {
        ArrayList<ArrayList<Integer>> restricciones = new ArrayList<>();
        ArrayList<Integer> posiciones;
        boolean existeRestriccion = false, bandera = false;

        for (int i = 0; i < operacion.length(); i++) {            
            if (bandera) {                
                //verifica que no existra la restriccion ya creada
                for (int j = 0; j < restricciones.size(); j++) {
                    if (restricciones.get(j).contains(i)) {
                        existeRestriccion = true; //quiere decir que existe ya la restriccion
                        j = restricciones.size();
                    }
                }
                //Si no existe paso a crearla
                if (existeRestriccion == false) {
                    posiciones = new ArrayList<Integer>();
                    for (int k = 0; k < operacion.length(); k++) {//Recorre toda la operacion buscando igualdad con el caracter tomado
                        if (operacion.charAt(k) == operacion.charAt(i)) {
                            posiciones.add(k);
                        }
                    }
                    restricciones.add(posiciones);
                }
                existeRestriccion = false;
            }
            if (operacion.charAt(i) == '='){                
                bandera=true;
            }
        }
        return restricciones;
    }

    //calcula la cantidad de restricciones que es igual a la peor aptitud del peor individuo
    private static void obtenerMaximaAptitud(ArrayList<ArrayList<Integer>> restricciones) {
        for (int i = 0; i < restricciones.size(); i++) {
            maximaAptitud += restricciones.get(i).size();
        }
    }
}
