package agcryptoaritmetico2;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Iterator;

public class Poblacion {

    private Set<Individuo> individuos = new TreeSet();
    private Random r = new Random();
    private int numeroPoblacion;
    private String operacion;

    //Constructor para generar primer poblacion aleatoria
    public Poblacion(String operacion, int cantIndividuos, ArrayList<ArrayList<Integer>> restricciones) {
        this.operacion = operacion;
        String vectorPalabra = vectorPalabraOperacion(operacion);
        for (int i = 0; i < cantIndividuos; i++) {
            Individuo unIndividuo = new Individuo(mezclaVector(vectorPalabra), operacion, restricciones);
            individuos.add(unIndividuo);
        }
        this.numeroPoblacion = 1;
    }

    //Constructor para generar poblaciones nuevas a partir de una anterior utilizando los operadores
    public Poblacion(String operacion, int cantIndividuos, Poblacion poblacion, ArrayList<ArrayList<Integer>> restricciones, int porcentajeSeleccion, int porcentajeCruza, int porcentajeMutacion, int maximaAptitud) {
        this.numeroPoblacion = poblacion.getNumeroPoblacion() + 1;
        Individuo unIndividuo;
        Set<Individuo> individuosViejos = poblacion.getIndividuos();

        //Seleccion Mejores---------------------------------------------
//        Iterator it = individuosViejos.iterator();
//        for (int i = 0; i < porcentajeSeleccion; i++) {
//            unIndividuo = new Individuo((Individuo) it.next());
//            this.individuos.add(unIndividuo);
//        }

//        Seleccion Ruleta
        this.individuos.addAll(seleccionRuleta(poblacion, porcentajeSeleccion, maximaAptitud));

        //Cruza Ciclica  -------------------------------------------------------        
        this.individuos.addAll(cruzaCiclico(poblacion, porcentajeCruza, restricciones, operacion));

        //Mutacion -------------------------------------------------------
        Iterator it2 = individuosViejos.iterator();
        for (int i = 0; i < porcentajeMutacion; i++) {
            Individuo aux = (Individuo) it2.next();
            unIndividuo = new Individuo(aux.mutacion(), operacion, restricciones);
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

    public double aptitudProm() {
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

    private double redondear(double numero, int digitos) {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }

    private Set<Individuo> seleccionRuleta(Poblacion poblacionAnterior, int cantDeseado, int maximaAptitud) {
        Set<Individuo> individuosResultados = new TreeSet();

        double sum = 0;
        //Suma aptitud Poblacion
        for (Individuo aux : poblacionAnterior.getIndividuos()) {
            sum += (maximaAptitud - (aux.getAptitud() + 1));
        }

        double[] auxCalculo = new double[poblacionAnterior.getIndividuos().size()];
        double[] auxCalculoAcum = new double[poblacionAnterior.getIndividuos().size()];
        double sumatoria = 0;
        int cont = 0;
        for (Individuo aux : poblacionAnterior.getIndividuos()) {
            auxCalculo[cont] = redondear(((maximaAptitud - (aux.getAptitud() + 1)) / sum) * 1000, 0); //cantidad de espacios en la ruleta por individuo
            sumatoria += auxCalculo[cont];
            auxCalculoAcum[cont] = sumatoria - 1;
            cont++;
        }
        auxCalculo[poblacionAnterior.getIndividuos().size() - 1] += (1000 - sumatoria); //Corrige problema redondeo para el rango

        int aleatorio1 = 0;

        //Hasta acá es el calculo de los rangos.

        //Seleccion
        int pos, num;
        for (int i = 0; i < cantDeseado; i++) {
            aleatorio1 = r.nextInt(1000);
            pos = 0;
            for (int j = 0; j < poblacionAnterior.getIndividuos().size(); j++) {
                if (j == 0) {
                    if (aleatorio1 <= auxCalculoAcum[j]) {
                        j = poblacionAnterior.getIndividuos().size();
                    }
                } else {
                    if ((aleatorio1 >= auxCalculoAcum[j - 1] + 1) && (aleatorio1 <= auxCalculoAcum[j])) {
                        pos = j;
                        j = poblacionAnterior.getIndividuos().size();
                    }
                }
            }
            num = 0;
            for (Individuo aux : poblacionAnterior.getIndividuos()) {
                if (num == pos) {
                    Individuo unIndividuo = new Individuo(aux);
                    individuosResultados.add(unIndividuo);
                }
                num++;
            }
        }
        return individuosResultados;
    }

    private Set<Individuo> cruzaCiclico(Poblacion poblacionAnterior, int cantDeseado, ArrayList<ArrayList<Integer>> restricciones, String operacion) {
        Set<Individuo> individuosResultados = new TreeSet();
        Individuo unIndividuo;
        char[] padre = null, madre = null, hijo1 = new char[10], hijo2 = new char[10];
        boolean bandera = true;
        boolean cantImp = false;
        int aux1 = 1, aux2 = 1;       
        
        if (cantDeseado % 2 != 0) {
            cantDeseado = cantDeseado + 1;
            cantImp = true;
        }
 
        for (int j = 0; j < (cantDeseado / 2); j++) {
            hijo1 = new char[10];
            hijo2 = new char[10];
            bandera = true;
            aux1 = 1;
            aux2 = 1;
            
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
                        
            unIndividuo = new Individuo(String.copyValueOf(hijo1), operacion, restricciones);
            individuosResultados.add(unIndividuo);            
            
            if (!((cantImp) && (j+1 == (cantDeseado / 2)))) {
                unIndividuo = new Individuo(String.copyValueOf(hijo1), operacion, restricciones);
                individuosResultados.add(unIndividuo);
            }
        }
        System.out.println("segunda impresion " + individuosResultados.size());
        return individuosResultados;
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
