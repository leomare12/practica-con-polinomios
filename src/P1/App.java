package P1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import Polinomios.Pvf1;

public class App {
    public static void main(String[] args) throws Exception {
        char opc;
        char varuse=' ';
        int coef = 0;
        int exp = 0;
        List<Pvf1> polinomios = new ArrayList<>(); 

        do {
            opc = menu();
            switch(opc) {
                case '1': 
                    do {
                        Pvf1 polinomio = new Pvf1();
                        String pol = JOptionPane.showInputDialog(null, "Ingrese el polinomio Ej: aX^n +bX^(n-1) +cX^(n-2) ... +yX +z");
                        for (int i = 0; i < pol.split(" ").length; i++) {
                            String term = pol.split(" ")[i];
                            if (term.split("\\^").length == 2) {

                                if (split(term.split("\\^")[0]).isEmpty()) {
                                    if (Character.isLetter(term.split("\\^")[0].charAt(0))) {
                                        coef = 1;
                                    } else {
                                        //en este caso siempre entra si es una constante elevada a una potencia
                                        coef = Integer.parseInt(term.split("\\^")[0]);
                                    }
                                } else {
                                    varuse = split(term.split("\\^")[0]).get(1).charAt(0);
                                    if (term.split("\\^")[0].charAt(0)=='-') {
                                        coef = Integer.parseInt(split(term.split("\\^")[0]).get(0)) * -1;
                                    }else{
                                        coef = Integer.parseInt(split(term.split("\\^")[0]).get(0));
                                    }
                                }

                                exp = Integer.parseInt(term.split("\\^")[1]);

                            } else {
                                if (split(term.split("\\^")[0]).isEmpty()) {
                                    if (Character.isLetter(term.split("\\^")[0].charAt(0))) {
                                        coef = 1;
                                    } else {
                                        coef = Integer.parseInt(term.split("\\^")[0]);
                                    }
                                    
                                    exp = 0;
                                } else {
                                    varuse = split(term.split("\\^")[0]).get(1).charAt(0);
                                    if (term.split("\\^")[0].charAt(0)=='-') {
                                        coef = Integer.parseInt(split(term.split("\\^")[0]).get(0)) * -1;
                                    }else{
                                        coef = Integer.parseInt(split(term.split("\\^")[0]).get(0));
                                    }
                                    exp = 1;
                                }
                            
                            }

                            polinomio.setVariable(varuse);

                            if (i > 0) {
                                if (polinomio.getCoeficiente(exp) == 0) {
                                    polinomio = polinomio.addterm(coef, exp);
                                } else {
                                    throw new ArrayIndexOutOfBoundsException("No se permiten terminos con exponente repetido");
                                }
                            } else {
                                polinomio = polinomio.addterm(coef, exp);
                            }
                        //System.out.println(coef +"" + varuse + "^" + exp);
                        }
                        System.out.println(polinomio);
                        polinomios.add(polinomio);
                        
                    } while (polinomios.size() < 2);
                    
                break;
                case '2':

                    for (int i = 0; i < polinomios.size(); i++) {
                        System.out.println((i + 1) + ". " + polinomios.get(i));
                    }

                    String pol = JOptionPane.showInputDialog(null, "Ingrese la posicion del primer polinomio");
                    int pol1 = Integer.parseInt(pol);
                    

                    String poli2 = JOptionPane.showInputDialog(null, "Ingrese la posicion del segundo polinomio");
                    int pol2 = Integer.parseInt(poli2);

                    // Resultado de la multiplicacion en un nuevo polinomio
                    int gradoPolinomioProducto = polinomios.get(pol1 - 1).getGrado()
                            + polinomios.get(pol2 - 1).getGrado();
                    Pvf1 producto = new Pvf1(gradoPolinomioProducto);
                    producto = polinomios.get(pol1 - 1).Multiplicar(polinomios.get(pol2 - 1));
                    JOptionPane.showMessageDialog(null, "("+polinomios.get(pol1 - 1)+") ("+ polinomios.get(pol2 - 1) +") = " + producto, "Multiplicar Polinomios", 1);
                    //System.out.println("El resultado de la multiplicación es: " + producto);
                break;
                case '3':
                    for (int i = 0; i < polinomios.size(); i++) {
                        System.out.println((i + 1) + ". " + polinomios.get(i));
                    }

                    String poli3 = JOptionPane.showInputDialog(null, "Ingrese la posicion del polinomio que desea derivar");
                    int polinomioDerivar = Integer.parseInt(poli3);

                    // Garantizar que existe el polinomio
                    if (polinomioDerivar <= 0 || polinomioDerivar > (polinomios.size() + 1)) {
                        JOptionPane.showMessageDialog(null, "El polinomio no existe", "Error", 1);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "El resultado al derivar es: " + polinomios.get(polinomioDerivar - 1).Derivar(), "Derivar Polinomio", 1);
                    }
                    break;
                default:
                break;
            }
        } while(opc != '3');
        

    }
    public static char menu() {
        char opc;
        do {
            opc = JOptionPane.showInputDialog(null, "\n" + "Elija una opción" + "\n\n").charAt(0);
        } while (opc < '1' || opc > '3');

        return opc;
        
    }
    public static ArrayList<String> split(String text) {

        Pattern pattern = Pattern.compile("(\\d+)([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(text);
        ArrayList<String> result = new ArrayList<>();
        
        if (matcher.find() && matcher.groupCount() == 2) {
          result.add(matcher.group(1));
          result.add(matcher.group(2));
        }
        return result;
        }
}
