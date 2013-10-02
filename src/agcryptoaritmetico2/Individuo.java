package agcryptoaritmetico2;

import java.util.ArrayList;
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

    public double getAptitud() {
        return this.aptitud;
    }

    //Calcular la APTITUD del individuo respecto de la operacion guardada
    private void setAptitud(String operacion, ArrayList restricciones) {
    }
    
    //convierto la operacion de letras en numeros a partir de los genes del individuo
    //peeeero el resultado se calcula a partir de la operacion y no de los genes
    private String convOperacion(String operacion) {
        //traducir de letras a numeros
        String resultado = "", numResultado="";
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
            Logger.getLogger(Individuo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //rellenar con ceros cuando se pierden por el calculo
        //luego concatenar lo traducido con el resultado calculado
        int longResultado = operacion.length()-resultado.length()-1; // resto uno por el simbolo =
        
        if (longResultado == numResultado.length()){
            resultado += "=" + numResultado;
        }else{
            resultado += "=";
            for (int i=0; i < longResultado-numResultado.length(); i++){
                resultado += "0";
            }
            resultado += "=";
        }
        
        return (resultado);
    }
    
    /**
     * metodo para calcular el resultado de una expresion matematica definido en
     * un string
     */
    private static long calcularString(String cadena) throws ScriptException {
        String aux = "";
        boolean bandera = true;
        
        //Metodo para eliminar los ceros que estan adelante para que pueda calcular correctamente
        for (int i = 0; i < cadena.length(); i++) {
            if (bandera && cadena.charAt(i) == '0') {
                if (cadena.charAt(i + 1) != '0') {
                    bandera = false;
                }
            } else {
                if (cadena.charAt(i) == '+' || cadena.charAt(i) == '-' || cadena.charAt(i) == '/' || cadena.charAt(i) == '*') {
                    bandera = true;
                }
                aux += cadena.charAt(i);
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
            if (this.aptitud2 > unIndividuo.getAptitud2()) {
                retorno = -1;
            } else {
                retorno = 1;
            }
        }
        return retorno;
    }
}
