import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) throws IOException {
        while (true) {

            String servidor;
            Integer porta;
            String teclado;
            char operador = 0;
            String n1;
            String n2 = "";

            Scanner scanner = new Scanner(System.in);


            while (true) {
                System.out.println("Escolha a operação desejada:");
                System.out.println("[1] Adição");
                System.out.println("[2] Subtração");
                System.out.println("[3] Multiplicação");
                System.out.println("[4] Divisão");
                System.out.println("[5] Porcentagem");
                System.out.println("[6] Raiz quadrada ");
                System.out.println("[7] Potenciação");
                System.out.println("Aperte 0 para Finalizar:");
                teclado = scanner.nextLine();

                if ("0".equals(teclado)){
                    System.exit(0);
                }

                System.out.println("Digite o primeiro número: ");
                n1 = scanner.nextLine();

                if (!"6".equals(teclado)) {
                    System.out.println("Digite o segundo número: ");
                    n2 = scanner.nextLine();
                }
                switch (Integer.parseInt(teclado)) {
                    case 1:
                        operador = '+';
                        break;
                    case 2:
                        operador = '-';
                        break;
                    case 3:
                        operador = '*';
                        break;
                    case 4:
                        operador = '/';
                        break;
                    case 5:
                        operador = '%';
                        break;
                    case 6:
                        operador = 'q';
                        n2 = "0";
                        break;
                    case 7:
                        operador = '^';
                        break;
                    default:
                        System.out.println("Opção Inválida");
                        continue;
                }

                servidor = "localhost";
                porta = (Integer.parseInt(teclado) >= 1 && Integer.parseInt(teclado) <= 4) ? 4000 : 5000;

                break;
            }

            String resultado = n1.concat(String.valueOf(operador)).concat(n2);

            Socket socket = new Socket(servidor, porta);
            PrintStream saida = new PrintStream(socket.getOutputStream());
            saida.println(resultado);
            InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputReader);
            String x = reader.readLine();
            System.out.println("Cliente:" + x);

        }
    }
}
