import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Servidor2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectou");

        InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
        PrintStream saida = new PrintStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(inputReader);

        String expressao = reader.readLine();
        String expressaoSemEspacos = expressao.replaceAll("\\s+", "");
        StringTokenizer tokenizer = new StringTokenizer(expressaoSemEspacos, "[%q^]", true);

        String primeiroNumero = tokenizer.nextToken();
        String operadorString = tokenizer.nextToken();
        char operador = operadorString.charAt(0);
        String segundoNumero = tokenizer.nextToken();

        Double n1 =  Double.parseDouble(primeiroNumero);
        Double n2 = Double.parseDouble(segundoNumero);

        Double resultado;

        switch (operador) {
            case '%':
                resultado = (n1 * n2)/100;
                break;
            case 'q':
                resultado = Math.sqrt(n1);
                break;
            case '^':
                resultado = Math.pow(n1, n2);
                break;
            default:
                resultado = 0.0;
        }
        saida.println("Servidor1-Resultado: " + String.format("%.2f", resultado));
        }
    }
