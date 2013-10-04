package agcryptoaritmetico2;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Iterator;

/**
 *
 * @author Leanwit
 */
public class Poblacion {

    private Set<Individuo> individuos = new TreeSet();
    private Random r = new Random();
    private int numeroPoblacion;
    private String operacion;
   

    //Constructor para generar primer poblacion aleatoria
    public Poblacion(String operacion, int cantIndividuos, ArrayList restriccion) {
        this.operacion = operacion;        
        String vectorPalabra = vectorPalabraOperacion(operacion);
        for (int i = 0; i < cantIndividuos; i++) {
            Individuo unIndividuo = new Individuo(mezclaVector(vectorPalabra), operacion,restriccion);
            individuos.add(unIndividuo);
        }
        this.numeroPoblacion = 1;
    }
    
    //Constructor para generar poblaciones nuevas a partir de una anterior utilizando los operadores
    public Poblacion(String operacion, int cantIndividuos, Poblacion poblacion) {
        this.numeroPoblacion = poblacion.getNumeroPoblacion() + 1;
        Individuo unIndividuo;

        //Seleccion -------------------------------------------------------
//        Set<Individuo> individuosViejos = poblacion.getIndividuos();
//        Iterator it = individuosViejos.iterator();
//        for (int i = 0; i < 10; i++) {
//            unIndividuo = new Individuo((Individuo) it.next);
//            this.individuos.add(unIndividuo);
//        }
//
//        //Cruza  -------------------------------------------------------        
//        for (int i = 0; i < 5; i++) {
//            String[] hijos = cruzaCiclico(poblacion);
//            unIndividuo = new Individuo(hijos[0], operacion);
//            this.individuos.add(unIndividuo);
//            unIndividuo = new Individuo(hijos[1], operacion);
//            this.individuos.add(unIndividuo);
//        }
//
//        //Mutacion -------------------------------------------------------
//        Iterator it2 = individuosViejos.iterator();
//        for (int i = 0; i < 80; i++) {
//            Individuo aux = (Individuo) it2.next();
//            unIndividuo = new Individuo(aux.mutacion(), operacion);
//            this.individuos.add(unIndividuo);
//        }
    }
    
    public int getNumeroPoblacion() {
        return this.numeroPoblacion;
    }

    private String vectorPalabraOperacion(String operacion) {
        String operando = "";
        String operacionCompleta = "";
        boolean bandera = true;
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) != '=' && bandera == true) {
                if ((operando.indexOf(operacion.charAt(i)) == -1) && (operacion.charAt(i) != '+') && (operacion.charAt(i) != '-') && (operacion.charAt(i) != ')') && (operacion.charAt(i) != '(') && (operacion.charAt(i) != '/') && (operacion.charAt(i) != '*') && (operacion.charAt(i) != ' ')) {
                    operando += operacion.charAt(i);
                    operacionCompleta += operacion.charAt(i);
                }
            } else {
                bandera = false;
                if ((operacionCompleta.indexOf(operacion.charAt(i)) == -1) && (operacion.charAt(i) != '+') && (operacion.charAt(i) != '-') && (operacion.charAt(i) != ')') && (operacion.charAt(i) != '(') && (operacion.charAt(i) != '/') && (operacion.charAt(i) != '*') && (operacion.charAt(i) != ' ') && (operacion.charAt(i) != '=')) {
                    operacionCompleta += operacion.charAt(i);
                }
            }
        }
        if (operacionCompleta.length() > 10) {
            operando = null;
        }        
        return operando;
    }
    
    private String mezclaVector(String palabra) {
        String resultado = "";        
        int nrand1, aux = palabra.length();
        
        for (int i = 0; i < aux; i++) {
            nrand1 = r.nextInt(palabra.length());
            resultado += palabra.charAt(nrand1);
            palabra = palabra.substring(0, nrand1) + palabra.substring(nrand1 + 1);
        }        
        return resultado;// retorna resultado
    }
    
    public Individuo esSolucion() {
        Individuo resultado = null;
        for (Individuo aux : individuos) {
            if (aux.getAptitud() == 0) {
                resultado = aux;
            }
        }
        return resultado;
    }
    
    public double aptitudProm2() {
        double sum = 0;
        for (Individuo aux : individuos) {
            sum += aux.getAptitud();
        }
        return (sum / individuos.size());
    }
    
    public void agregarIndividuo(Individuo individuo) {
        this.individuos.add(individuo);
    }

   

    public Set<Individuo> getIndividuos() {
        return this.individuos;
    }
}
