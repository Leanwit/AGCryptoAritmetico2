package agcryptoaritmetico2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;



public class Individuo implements Comparable {

    private double aptitud;
    private double aptitud2;
    private String genes;
    
    public Individuo(String palabra, String operacion) {
        this.genes = palabra;
        //Calculo de aptitud
        setAptitud(operacion);
        setAptitudSuma(operacion);
    }
    
    
    @Override
    public int compareTo(Object aux) {
        int retorno = 0;
        if (aux instanceof Individuo) {
            Individuo unIndividuo = (Individuo) aux;
            if (this.aptitud2 > unIndividuo.getAptitud2()) {
                retorno = -1;                
            }
            else{
                retorno=1;
            }
        }
        return retorno;
    }

}
