/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregashell;

import entrada.Entrada;
import java.util.Arrays;

/**
 *
 * @author autom
 */
public class Shell {
    public static final byte MAXHISTORIAL = 100; //máximo de rexistros que garda o historial
    
    private static final String[] COMANDOS = {"cat", "cd", "cp", "dir",
                                            "del", "date", "find", "grep", 
                                            "help", "ls", "man", "mv", "rm"}; //comandos permitidos
    private static byte numComandos = 0; //número de comandos actuais

    /**
     * Método para introducir unha instrucción. 
     * Comproba que a instrucción é correcta, comenzando por un dos comandos do array COMANDOS. 
     * Ten en conta maiúsculas e minúsculas.
     * 
     * @param hist array String[] co historial dos comandos. Se se alcanzase o límite, perderase a entrada máis antiga.
     */
    public static void engadirComando(String[] hist) {

        System.out.println("Introduza o comando, por favor:");
        String comando = Entrada.lerString();

        if (eComando(comando) && numComandos < MAXHISTORIAL) {

            hist[numComandos] = comando;

            numComandos++;

        } else if (eComando(comando) && numComandos >= MAXHISTORIAL) {

            for (int i = 0; i < hist.length; i++) {

                if (i < (hist.length - 1)) {

                    hist[i] = hist[i + 1];

                } else {

                    hist[i] = comando;

                }

            }

        } else {

            System.out.println("O comando introducido non existe");

        }

    }

    /**
     * Método para procurar ao usuario a última coincidencia co que escriba.
     * Ten en conta maiúsculas e minúsculas.
     * Se non atopa ninguna coincidencia, avisará ao usuario
     * 
     * @param hist array String[] co historial dos comandos.
     */
    public static void procurarComando(String[] hist) {

        if (numComandos > 0) {

            String entrada = pedirInicialComando();
            
            String comando = "";

            for (int i = 0; i < numComandos; i++) {

                // el más reciente es el último en encontrarse
                if (hist[i].substring(0,entrada.length()).equals(entrada)) {

                    comando = hist[i];

                }

            }

            if (comando.equals("")) {

                System.out.println("Non atopáronse coincidencias");

            } else {

                System.out.println(comando);

            }
        } else {
            System.out.println("O historial de comandos está baleiro");
        }

    }

    /**
     * Método para procurar ao usuario todas as coincidencias co introducido, do máis recente ao que menos.
     * Ten en conta maiúsculas e minúsculas.
     * Se non atopa ninguna coincidencia, avisará ao usuario.
     * 
     * @param hist array String[] co historial dos comandos.
     */
    public static void procurarComandos(String[] hist) {

        if (numComandos > 0) {
            
            String entrada = pedirInicialComando();
            int num = contadorProcuras(hist, entrada);
            int numInicial = 0;
            String[] comands = new String[num];

            if (num > 0) {
                
                for (int i = (numComandos - 1); i >= 0; i--) {

                    if (hist[i].substring(0,entrada.length()).equals(entrada)) {

                        comands[numInicial] = hist[i];
                        numInicial++;

                    }
                }

                System.out.println("Comandos: " + Arrays.toString(comands));

            } else {

                System.out.println("Non se atoparon resultados");

            }
        } else {
            System.out.println("O historial de comandos está baleiro");
        }

    }

    /**
     * Baleira todos os rexistros do histórico. 
     * Establece o número de comandos a 0.
     * 
     * @param hist array String[] co historial dos comandos.
     */
    public static void baleirarHistorico(String[] hist) {

        Arrays.fill(hist, null);

        numComandos = 0;

        System.out.println("O histórico quedou baleiro.");
    }

    /**
     * Devolve o histórico de comandos válidos introducidos polo usuario, dende o máis recente ao que menos
     * 
     * @param hist array String[] co historial dos comandos.
     */
    public static void consultarHistorico(String[] hist) {

        if (numComandos > 0) {

            System.out.println("Histórico de comandos: ");
            for (int i = numComandos; i > 0; i--) {
                System.out.println("\t" + hist[i - 1]);

            }
        } else {
            System.out.println("O historial de comandos está baleiro");
        }

    }

    private static int contadorProcuras(String[] hist, String entrada) {

        int contador = 0;

        for (int i = 0; i < numComandos; i++) {

            if (hist[i].substring(0,entrada.length()).equals(entrada)) {

                contador++;

            }

        }

        return contador;

    }

    private static boolean eComando(String com) {
        boolean correcto = false;

        for (int i = 0; i < COMANDOS.length; i++) {

            if (com.startsWith(COMANDOS[i] + " ")) {

                correcto = true;

            }

        }

        return correcto;
    }

    private static String pedirInicialComando() {

        System.out.println("Escriba:");
        String inicio = Entrada.lerString();
        return inicio;
    }
}
