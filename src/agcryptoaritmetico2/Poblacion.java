package agcryptoaritmetico2;

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
    public Poblacion(String operacion, int cantIndividuos) {
        this.operacion = operacion;
        String vectorPalabra = vectorPalabraOperacion(operacion);
        for (int i = 0; i < cantIndividuos; i++) {
            Individuo unIndividuo = new Individuo(mezclaVector(vectorPalabra), operacion);
            individuos.add(unIndividuo);
        }
        this.numeroPoblacion = 1;
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
        int nrand1;
        for (int i = 0; i < palabra.length(); i++) {
            nrand1 = r.nextInt(palabra.length());
            resultado += palabra.charAt(nrand1);
            palabra = palabra.substring(0, nrand1) + palabra.substring(nrand1 + 1);
        }
        return resultado;
    }
}
