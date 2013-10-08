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
            Individuo unIndividuo = new Individuo(mezclaVector(vectorPalabra), operacion, restriccion);
            individuos.add(unIndividuo);
        }
        this.numeroPoblacion = 1;
    }

    //Constructor para generar poblaciones nuevas a partir de una anterior utilizando los operadores
    public Poblacion(String operacion, int cantIndividuos, Poblacion poblacion, ArrayList restriccion) {
        this.numeroPoblacion = poblacion.getNumeroPoblacion() + 1;
        Individuo unIndividuo;

//        Seleccion -------------------------------------------------------
        Set<Individuo> individuosViejos = poblacion.getIndividuos();
        Iterator it = individuosViejos.iterator();
        for (int i = 0; i < 150; i++) {
            unIndividuo = new Individuo((Individuo) it.next());
            this.individuos.add(unIndividuo);
        }

        //Cruza  -------------------------------------------------------        
         for (int i = 0; i < 250; i++) {
            String[] hijos = cruzaCiclico(poblacion);
            unIndividuo = new Individuo(hijos[0], operacion, restriccion);
            this.individuos.add(unIndividuo);
            unIndividuo = new Individuo(hijos[1], operacion, restriccion);
            this.individuos.add(unIndividuo);
        }

        //Mutacion -------------------------------------------------------
        Iterator it2 = individuosViejos.iterator();
        for (int i = 0; i < 350; i++) {
            Individuo aux = (Individuo) it2.next();
            unIndividuo = new Individuo(aux.mutacion(), operacion, restriccion);
            this.individuos.add(unIndividuo);
        }
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
        } else {
            for (int i = operando.length(); i < 10; i++) {
                operando += '#';
            }
        }
        System.out.println("operando: " + operando);
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
    
     private String[] cruzaCiclico(Poblacion poblacionAnterior) {
        char[] padre = null, madre = null, hijo1 = new char[10], hijo2 = new char[10];
        boolean bandera = true;
        int aux1 = 1, aux2 = 1;

        padre = progenitorAleatorio(poblacionAnterior).toCharArray();
        madre = progenitorAleatorio(poblacionAnterior).toCharArray();

        for (int i = 0; i < 10; i++) {
            if (padre[i] == '#') {
                padre[i] = Character.forDigit(aux1, 10);
                aux1++;
            }
            if (madre[i] == '#') {
                madre[i] = Character.forDigit(aux2, 10);
                aux2++;
            }
        }

        //si son iguales los cromosomas hay que elegir otro para porque sino quedan iguales los hijos
        int pos = 0;
        for (int i = 0; i < 10; i++) {
            if (padre[i] != madre[i]) {
                pos = i;
                i = 10;
            }
        }
        hijo1[pos] = padre[pos];
        hijo2[pos] = madre[pos];

        while (bandera) {
            for (int i = 0; i < 10; i++) {
                if (hijo2[pos] == padre[i]) {
                    if (hijo1[i] == 0) {
                        hijo1[i] = padre[i];
                        hijo2[i] = madre[i];
                        pos = i;
                        i = -1;
                    } else {
                        bandera = false;
                        i = 10;
                    }
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            if (hijo1[i] == 0) {
                hijo1[i] = madre[i];
                hijo2[i] = padre[i];
            }
        }

        
        for (int i = 0; i < 10; i++) {
            if (Character.isDigit(hijo1[i])) {
                hijo1[i] = '#';              
            }
            if (Character.isDigit(hijo2[i])) {
                hijo2[i] = '#';                
            }
        }

        String[] resultado = new String[2];
        resultado[0] = String.copyValueOf(hijo1);
        resultado[1] = String.copyValueOf(hijo2);

        return resultado;
    }
     
     private String progenitorAleatorio(Poblacion poblacionAnterior) {
        int aleatorio1 = 0, cont = 0;
        String padre = null;
        aleatorio1 = r.nextInt(poblacionAnterior.getIndividuos().size());
        for (Individuo aux : poblacionAnterior.getIndividuos()) {
            if (cont == aleatorio1) {
                padre = aux.getGenes();
            }
            cont++;
        }
        return padre;
    }
}
