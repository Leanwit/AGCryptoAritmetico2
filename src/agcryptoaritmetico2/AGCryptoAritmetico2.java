package agcryptoaritmetico2;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import agcryptoaritmetico2.interfaz.ventanita;
import java.util.ArrayList;

public class AGCryptoAritmetico2 {

    public static void main(String[] args) {
        ventanita unaVentana = new ventanita();
        unaVentana.setVisible(true);
    }

    public static void comenzarAlgoritmo(String operacion, int cantIndividuos, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion) {

        //Generar primer población ALEATORIA
        ArrayList restriccion = obtenerRestriccion(operacion);
        Poblacion poblacion = new Poblacion(operacion, cantIndividuos, restriccion);
        //Crear nueva generacion de poblacion        
        int cantIt = 0;
        porcentajeSeleccion = (porcentajeSeleccion * cantIndividuos) / 100;
        porcentajeCruza = (porcentajeCruza * cantIndividuos) / 100;
        porcentajeMutacion = (porcentajeMutacion * cantIndividuos) / 100;
        int valorMin = ((int)(0.10*cantIndividuos)), valorMax=((int)(0.50*cantIndividuos));
        int cte=0;
        System.out.println("valor min: "+valorMin + "valor max: "+ valorMax);
        while (poblacion.esSolucion() == null) {

            //poblacion.mostrar();

            System.out.println("Población Número: " + poblacion.getNumeroPoblacion() + " Aptitud: " + poblacion.aptitudProm2());

            cantIt++;
            Poblacion nuevaPoblacion = new Poblacion(operacion, cantIndividuos, poblacion, restriccion, porcentajeSeleccion, porcentajeCruza, porcentajeMutacion);
            //if (poblacion.aptitudProm(operacion)>newPoblacion.aptitudProm(operacion)){ 
            poblacion = nuevaPoblacion;
            
            //calculo de temperatura por convergencia
            
            cte+= ((int)0.05*cantIndividuos);
            System.out.println("Cte: "+ cte);
            if (porcentajeMutacion < valorMax) {
                porcentajeMutacion += cte;
                porcentajeCruza-=cte;
                
            } else {
                porcentajeMutacion = valorMax;
                porcentajeCruza=((int)0.40*cantIndividuos);
            }
            System.out.println("Porcentaje Mutacion: "+porcentajeMutacion);
            //}              
        }
        //CARTEL GANASTE o LLEGASTE A LAS 100
        if (poblacion.esSolucion() != null) {
            System.out.println("\n" + poblacion.esSolucion().toString());
            System.out.println("Cantidad de Iteracciones: " + poblacion.getNumeroPoblacion());
        }
    }

    public static ArrayList obtenerRestriccion(String operacion) {
        ArrayList restriccion = new ArrayList();
        int[] vector = null;
        int posicion = 0, contVector = 0;
        boolean bandera = false;

        //Saca la primer posicion del primer caracter despues del resultado
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '=') {
                posicion = i + 1;
            }
        }

        for (int i = posicion; i < operacion.length(); i++) {

            for (int k = 0; k < restriccion.size(); k++) { // Verifica si el caracter del resultado ya existe
                for (int l = 0; l < vector.length; l++) {  // Para no agregar dos restricciones iguales
                    if (vector[l] == i) {
                        bandera = true;
                    }
                }
            }

            if (bandera == false) { //Si no existe se agrega
                vector = new int[20];
                for (int j = 0; j < operacion.length(); j++) {//Recorre toda la operacion buscando igualdad con el caracter tomado
                    if (operacion.charAt(j) == operacion.charAt(i)) {
                        vector[contVector] = j;
                        contVector++;
                    }
                }

                //Se crear un vector auxiliar para crear vectores dinamicos, con la cantidad de restricciones
                int[] vectorAuxiliar = new int[contVector];
                for (int j = 0; j < contVector; j++) {
                    vectorAuxiliar[j] = vector[j];
                }
                restriccion.add(vectorAuxiliar);
                contVector = 0;
            }
            bandera = false;
        }
        return restriccion;
    }

    private static void actualizarMutacion() {
    }
}
