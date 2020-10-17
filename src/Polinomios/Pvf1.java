package Polinomios;

public class Pvf1 {

    private int[] arregloPolinomio;
    private char variable;

    /**
     * Constructor que crea un arreglo sin coeficientes de grado n
     *
     * @param n es el grado
     */
    public Pvf1(int n) {
        arregloPolinomio = new int[n + 2];
        for (int i = 0; i < arregloPolinomio.length; i++) {
            arregloPolinomio[i] = 0;
        }
        arregloPolinomio[0] = n;
    }

    /**
     * Constructor de un polinomio conociendo el arreglo de enteros
     *
     * @param arreglo
     */
    public Pvf1(int[] arreglo) {
        this.arregloPolinomio = arreglo;
    }

    /**
     * Constructor que cree un arreglo sin coeficiente
     */
    public Pvf1() {
        arregloPolinomio = new int[1];
        arregloPolinomio[0] = -1;
    }

    

    /**
     * Obtener el grado
     *
     * @return
     * @throws Exception
     */
    public int getGrado() throws Exception {
        if (arregloPolinomio != null) {
            return arregloPolinomio[0];
        } else {
            throw new NullPointerException("El arreglo esta nulo");
        }
    }

    /**
     * Obtiene el coeficiente dado un exponente
     *
     * @param exp
     * @return coeficiente
     * @throws java.lang.Exception
     */
    public int getCoeficiente(int exp) throws Exception {
        int pos = getGrado() - exp + 1;
        if (pos >= arregloPolinomio.length) {
            throw new ArrayIndexOutOfBoundsException("El arreglo es de menor tamaño que la posición calculada");
        }
        return arregloPolinomio[pos];
    }

    /**
     * Obtiene el exponente dado una posición
     *
     * @param pos
     * @return exponente
     * @throws java.lang.Exception
     */
    public int getExponente(int pos) throws Exception {
        if (pos >= arregloPolinomio.length) {
            throw new ArrayIndexOutOfBoundsException("El arreglo es de menor tamaño que la posición calculada");
        }
        int exp = getGrado() - pos + 1;
        return exp;
    }

    /**
     * Método que permite mostrar laa representación del polinomio
     */
    @Override
    public String toString() {
        StringBuilder polinomio = new StringBuilder();
        try {
            if (arregloPolinomio[0] == -1) {
                // Mostrar un cero cuando el polinomio no tiene datos
                polinomio.append("0");
            }

            for (int i = 1; i < arregloPolinomio.length; i++) {
                int j = arregloPolinomio[i];
                // Para adicionar el simbolo del coeficiente para numeros positivos, excluyendo
                // el simbolo + del primer termino si es positivo.
                if (j >= 0 && i != 1) {
                    polinomio.append(" +");
                }
                // polinomio.append(j).append("X^").append(getExponente(i)).append(" ");
                switch (getExponente(i)) {
                    case 0:
                        polinomio.append(j);
                        break;
                    case 1:
                        polinomio.append(j).append("X");
                        break;
                    default:
                        polinomio.append(j).append("X^").append(getExponente(i));
                }
            }
        } catch (Exception e) {
            polinomio.append("El arreglo estaba NULO");
        }
        return polinomio.toString();
    }

    /**
     * Cambiar un coeficiente de determinado exponente
     *
     * @param c nuevo coeficiente
     * @param e exponente
     * @throws java.lang.Exception
     */
    public void setCoeficiente(int c, int e) throws Exception {
        if (e > getGrado()) {
            throw new ArrayIndexOutOfBoundsException("El exponente es superior al grado del polinomio");
        }
        int pos = getGrado() - e + 1;
        arregloPolinomio[pos] = c;
    }

    /**
     * Función para Sumar dos polinomios, entrega un nuevo polinomio resultado de la
     * operación suma. No modifica el polinomio que representa el objeto
     *
     * @param polinomioB
     * @return polinomioC
     * @throws java.lang.Exception
     */
    public Pvf1 sumar(Pvf1 polinomioB) throws Exception {

        Pvf1 polinomioC;
        if (polinomioB == null) {
            throw new Exception("El polinomio b es null");
        }

        // Bloque para validar si los arreglos son nulos o no
        int[] arregloPolinioB = polinomioB.getArreglo();
        if (arregloPolinioB == null) {
            if (arregloPolinomio == null) {
                polinomioC = new Pvf1();
            } else {
                int[] arregloNuevo = new int[arregloPolinomio.length];
                System.arraycopy(arregloPolinomio, 0, arregloNuevo, 0, arregloPolinomio.length);
                polinomioC = new Pvf1(arregloNuevo);
                return polinomioC;
            }
        } else {
            if (arregloPolinomio == null) {
                int[] arregloNuevo = new int[arregloPolinioB.length];
                System.arraycopy(arregloPolinioB, 0, arregloNuevo, 0, arregloPolinioB.length);
                polinomioC = new Pvf1(arregloNuevo);
                return polinomioC;
            }
        }

        // Este es el caso en que ambos arreglos no son nulos
        int gradoA = getGrado();
        int gradoB = polinomioB.getGrado();
        int nGrado = (gradoA > gradoB) ? gradoA : gradoB;
        polinomioC = new Pvf1(nGrado);

        int e = 0;
        while (e <= gradoA && e <= gradoB) {
            int nC = getCoeficiente(e) + polinomioB.getCoeficiente(e);
            polinomioC.setCoeficiente(nC, e);
            e++;
        }

        while (e <= gradoA) {
            polinomioC.setCoeficiente(getCoeficiente(e), e);
            e++;
        }

        while (e <= gradoB) {
            polinomioC.setCoeficiente(polinomioB.getCoeficiente(e), e);
            e++;
        }

        polinomioC.normalizar();
        return polinomioC;
    }

    public int getDiferentesCero() {
        return 0;
    }

    /**
     * Obtener el vector asociado a un polinomio
     *
     * @return arregloPolinomio
     * @throws java.lang.Exception
     */
    public int[] getArreglo() {
        return arregloPolinomio;
    }

    public void ingresar(double coeficiente, int exponente) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    /**
     * Permite validar el arreglo y garantizar que no tenga terminos iniciales en 0
     */
    private void normalizar() {

        // Si la primera posición es 0
        if (arregloPolinomio[1] == 0) {
            int i = 1;
            while (i < arregloPolinomio.length && arregloPolinomio[i] == 0) {
                i++;
            }
            if (i < arregloPolinomio.length) {
                int nuevoGrado = i - 1;
                int nuevoArreglo[] = new int[nuevoGrado + 2];
                nuevoArreglo[0] = nuevoGrado;
                System.arraycopy(arregloPolinomio, i, nuevoArreglo, 1, arregloPolinomio.length - i);
                arregloPolinomio = nuevoArreglo;
            } else {
                arregloPolinomio = new int[1];
                arregloPolinomio[0] = -1;
            }
        }
    }

    /**
     * Construir y simplificar el polinomio termino a termino
     *
     * @param coeficiente
     * @param exponente
     * @return
     * @throws Exception
     */
    public Pvf1 addterm(int coeficiente, int exponente) throws Exception {
        Pvf1 polB;
        if (coeficiente != 0) {
            /**
             * El tamaño del arreglo es de grado n + 2, como el polinomio resultante va a
             * ser de un solo termino, el grado es el mismo exponente
             */
            int[] arregloPol = new int[exponente + 2];
            for (int i = 0; i < arregloPol.length; i++) {
                arregloPol[i] = 0;
            }
            arregloPol[0] = exponente;
            arregloPol[1] = coeficiente;
            polB = new Pvf1(arregloPol);
        } else {
            polB = new Pvf1();
        }
        return this.sumar(polB);
    }

    /**
     * Multiplicar al polinomio a (this) con otro polinomio, la estrategia es crear
     * un nuevo polinomio del tamaño de la suma de los dos grados de los polinomios
     * e ir sumando terminos
     *
     * @param p2
     * @return polNuevo
     * @throws Exception
     */
    public Pvf1 Multiplicar(Pvf1 p2) throws Exception {

        int[] Arreglo = getArreglo();
        int[] Arreglo2 = p2.getArreglo();

        Pvf1 polNuevo = new Pvf1();

        for (int i = 1; i < Arreglo.length; i++) {

            // Para que cada termino se multiplique por todos los terminos del polinomio p2

            for (int j = 1; j < Arreglo2.length; j++) {

                int mExp = getExponente(i) + p2.getExponente(j);
                int mCoef = getCoeficiente(getExponente(i)) * p2.getCoeficiente(p2.getExponente(j));

                // sumar cada termino con los terminos del mismo coeficiente
                polNuevo = polNuevo.addterm(mCoef, mExp);

            }

        }
        return polNuevo;
    }

    /**
     * Dividir al polinomio a (this) con otro polinomio, la estrategia es crear un
     * nuevo polinomio para que guarde el resultado.
     *
     * @param p2
     * @return cociente
     * @throws Exception
     */
    public Pvf1 Dividir(Pvf1 p2) throws Exception {

        int[] Arreglo = getArreglo();
        int[] Arreglo2 = p2.getArreglo();

        // grado del nuevo polinomio

        int gradoCociente = Arreglo[0] - Arreglo2[0];

        Pvf1 cociente = new Pvf1(gradoCociente);
        Pvf1 polDividendo = new Pvf1(Arreglo);

        Pvf1 polDivisor = new Pvf1(Arreglo2);

        while (polDividendo.getGrado() >= polDivisor.getGrado()) {

            int expCocoiente = polDividendo.getExponente(0) - polDivisor.getExponente(0);

            int coeCociente = polDividendo.getCoeficiente(polDividendo.getExponente(1))
                    / polDivisor.getCoeficiente(polDivisor.getExponente(1));
            // Adiciono los terminos al cociente
            cociente.setCoeficiente(coeCociente, expCocoiente);

            Pvf1 termino = new Pvf1(expCocoiente);
            termino.setCoeficiente(coeCociente, expCocoiente);
            // polinomio auxiliar para cambiar de signo a cada termino
            Pvf1 aux = new Pvf1(0);
            aux.setCoeficiente(-1, 0);

            Pvf1 producto = polDivisor.Multiplicar(termino);
            producto = producto.Multiplicar(aux);
            polDividendo = polDividendo.sumar(producto);

        }

        return cociente;
    }

    /**
     * Derivar el polinomio a (this) , la estrategia es crear un nuevo polinomio de
     * grado - 1 al del polinomio e ir mofidificando los coeficientes por el
     * anterior coeficiente multiplicado por su exponente
     *
     * @return polNuevo
     * @throws Exception
     */
    public Pvf1 Derivar() throws Exception {

        int[] Arreglo = getArreglo();
        int gradoNue = Arreglo[0] - 1;

        // nuevo polinomio para no modificar el valor de a (this)

        Pvf1 polNuevo = new Pvf1(gradoNue);

        for (int i = 1; i < Arreglo.length - 1; i++) {

            polNuevo.setCoeficiente(Arreglo[i] * getExponente(i), getExponente(i) - 1);
        }
        return polNuevo;

    }

    /**
     * Evaluar al polinomio a (this) con un dato determinado, se crear un nuevo
     * polinomio del tamaño de la suma de los dos grados de los polinomios e ir
     * sumando terminos
     *
     * @param dato
     * @return resultado
     * @throws Exception
     */
    public int Evaluar(int dato) throws Exception {

        int resultado = 0;

        int[] Arreglo = getArreglo();
        // Recorrer cada termino y sustituir a x
        for (int i = 1; i < Arreglo.length; i++) {

            resultado = resultado + Arreglo[i] * (int) Math.pow(dato, getExponente(i));
        }
        return resultado;
    }

    public char getVariable() {
        return variable;
    }

    public void setVariable(char variable) {
        this.variable = variable;
    }
}