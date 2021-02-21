/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregashell;

import entrada.Entrada;

/**
 *
 * @author autom
 */
public class EntregaShell {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        byte opcion;
        String[] historial = new String [Shell.MAXHISTORIAL];
        
        do {
            opcion = menu();

            switch (opcion) {
                case 1:
                    Shell.engadirComando(historial);
                    break;
                case 2:  
                    Shell.procurarComando(historial);
                    break;
                case 3:
                    Shell.procurarComandos(historial);
                    break;
                case 4:
                    Shell.baleirarHistorico(historial);
                    break;
                case 5:
                    Shell.consultarHistorico(historial);
                    break;
                case 6:
                    System.out.println("Saíndo. Moitas grazas.");
                    break;
            }
        } while (opcion != 6);
    }

    static byte menu() {
        boolean repetir = false;
        byte opcion = 0;

        do {
            System.out.println("\n=======================\n"
                    + "\tMENÚ\n"
                    + "=======================\n"
                    + "1. Engadir comando\n"
                    + "2. Procurar un comando\n"
                    + "3. Procurar todos os comandos\n"
                    + "4. Baleirar histórico\n"
                    + "5. Consultar histórico\n"
                    + "6. Saír\n");
            System.out.print("Elixa unha opción: ");

            opcion = Entrada.lerByte();

            if (opcion < 1 || opcion > 6) {
                System.out.println("\tATENCIÓN! Opción non dispoñible. "
                        + "Volva a seleccionar. ");
                repetir = true;
            }
        } while (repetir);

        return opcion;
    }
    
}
