import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadSockets extends Thread {
    private Socket socket;

    public ThreadSockets(Socket s) {
        this.socket = s;
    }



    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName()); //Imprime o nome da thread atual.

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

// Leia a solicitação linha a linha
            String line;
            StringBuilder request = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // A leitura se encerrará quando encontrar uma linha em branco
                if (line.isEmpty()) {
                    break;
                }
                if(line.contains("GET")){
                    String document= line.split(" ")[1];
                            System.out.println(document);
                    HttpManager httpManager= new HttpManager(document,socket);
                }

                request.append(line).append("\n");
            }

// Exiba ou processe a solicitação
            System.out.println("Requisição HTTP do cliente:\n" + request.toString());

        } catch (Exception e) {
            e.printStackTrace(); // Trata qualquer exceção lançada durante o processo e imprime o rastreamento da pilha.
        }
    }
}

