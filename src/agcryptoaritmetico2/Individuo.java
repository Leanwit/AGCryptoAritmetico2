package agcryptoaritmetico2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Individuo implements Comparable {

    private double aptitud;
    private String genes;

    public Individuo(String palabra, String operacion, ArrayList restricciones) { //palabra = string de letras únicas correspondiente a la operacion
        this.genes = palabra;

        //Calculo de aptitud
        setAptitud(convOperacion(operacion), restricciones); //convOperacion es para pasar operacion a numeros
    }

    public Individuo(Individuo unIndividuo) {
        this.aptitud = unIndividuo.getAptitud();
        this.genes = unIndividuo.getGenes();
    }

    public double getAptitud() {
        return this.aptitud;
    }

    //Calcular la APTITUD del individuo respecto de la operacion guardada
    private void setAptitud(String operacion, ArrayList restricciones) {
        int auxAptitud = restricciones.size();
        int[] aux;
        boolean bandera = true;
        char comparacion;

        for (int i = 0; i < restricciones.size(); i++) {
            aux = (int[]) restricciones.get(i);
            comparacion = operacion.charAt(aux[0]); //comparacion posee el valor de la primera ubicacion de la restriccion
            int contador = 0;
            for (int j = 0; j < aux.length; j++) {
                for (int k = contador; k < operacion.length(); k++) {
                    if (k == aux[j]) {
                        if (operacion.charAt(k) != comparacion) { //Si en la posicion de la restriccion tiene mismo valor.
                            bandera = false;
                            k = operacion.length();
                        } else {
                            k = operacion.length();
                        }
                    } else {
                        if (operacion.charAt(k) == comparacion) {
                            bandera = false;
                            k = operacion.length();
                        }

                    }
                    contador++;
                }
            }
            if (bandera) {
                auxAptitud--;
            }
            bandera = true;
        }

        this.aptitud = auxAptitud;
    }
//convierto la operacion de letras en numeros a partir de los genes del individuo
//peeeero el resultado se calcula a partir de la operacion y no de los genes

    private String convOperacion(String operacion) {
        //traducir de letras a numeros
        String resultado = "", numResultado = "";
        for (int i = 0; i < operacion.length(); i++) {
            if (operacion.charAt(i) == '=') {
                i = operacion.length();
            }//Corta al encontrar un =
            else {
                if (operacion.charAt(i) == '+' || operacion.charAt(i) == '-' || operacion.charAt(i) == '/' || operacion.charAt(i) == '*' || operacion.charAt(i) == '(' || operacion.charAt(i) == ')') {
                    resultado += String.valueOf(operacion.charAt(i));
                } else {
                    for (int j = 0; j < genes.length(); j++) {
                        if (operacion.charAt(i) == genes.charAt(j)) {
                            resultado += String.valueOf(j);
                        }
                    }
                }
            }
        }
        //calcular el resultado solamente
        try {
            numResultado = Long.toString(calcularString(resultado));


        } catch (ScriptException ex) {
            Logger.getLogger(Individuo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        //rellenar con ceros cuando se pierden por el calculo
        //luego concatenar lo traducido con el resultado calculado
        int longResultado = operacion.length() - resultado.length() - 1; // resto uno por el simbolo =

        if (longResultado == numResultado.length()) {
            resultado += "=" + numResultado;
        } else {
            resultado += "=";
            for (int i = 0; i < longResultado - numResultado.length(); i++) {
                resultado += "0";
            }
            resultado += numResultado;
        }
        return (resultado);
    }

    /**
     * metodo para calcular el resultado de una expresion matematica definido en
     * un string
     */
    private static long calcularString(String cadena) throws ScriptException {
        String aux = "";

        //Metodo para eliminar los ceros que estan adelante para que pueda calcular correctamente
        int num = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != '0' || i != num) {
                aux += cadena.charAt(i);
                if (cadena.charAt(i) == '+' || cadena.charAt(i) == '-' || cadena.charAt(i) == '*' || cadena.charAt(i) == '/') {
                    num = i + 1;
                }
            } else {
                //Para que no borre un 0 que tiene solo un digito
                if (cadena.charAt(i) == '0' && i == (cadena.length() - 1)) {
                    aux += cadena.charAt(i);
                } else {
                    if (cadena.charAt(i + 1) == '+' || cadena.charAt(i + 1) == '-' || cadena.charAt(i + 1) == '*' || cadena.charAt(i + 1) == '/') {
                        aux += cadena.charAt(i);
                    } else {
                        num++;
                    }
                }

            }

        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        long resultado = ((Number) engine.eval(aux)).longValue();

        return resultado;
    }

    @Override
    public int compareTo(Object aux) {
        int retorno = 0;
        if (aux instanceof Individuo) {
            Individuo unIndividuo = (Individuo) aux;
            if (this.aptitud > unIndividuo.getAptitud()) {
                retorno = -1;
            } else {
                retorno = 1;
            }
        }
        return retorno;
    }

    @Override
    public String toString() {
        return (this.genes + " - " + this.aptitud);
    }

    public String getGenes() {
        return this.genes;
    }

    public String mutacion() {
        Random r = new Random();
        char[] mutado = new char[10];
        int nrand1 = 0, nrand2 = 0;
        while (nrand1 == nrand2) {
            nrand1 = r.nextInt(10);
            nrand2 = r.nextInt(10);
        }
        mutado = this.genes.toCharArray();
        mutado[nrand1] = this.genes.charAt(nrand2);
        mutado[nrand2] = this.genes.charAt(nrand1);
        return String.copyValueOf(mutado);
    }
}
