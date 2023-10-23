import java.io.*;
import java.net.Socket;

public class HttpManager {
    String message;
    Socket socket;
    HttpManager(String m,Socket s) throws IOException {
    this.message=m;
    this.socket=s;
    getRequest(message,socket);

    }

    void getRequest(String path, Socket clientSocket) throws IOException {
        String caminhoDiretorio = "C://Users//Estagiario//IdeaProjects//http_tcp_server//src";
        // String nomeArquivo = "arquivo.txt";

        File file = new File(caminhoDiretorio, path);
        DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
        if (file.exists() && file.isFile()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];

            // Envie cabeçalhos de resposta HTTP
            writer.writeBytes("HTTP/1.1 200 OK\r\n");
            writer.writeBytes("Content-Type: " + getContentType(path) + "\r\n");
            writer.writeBytes("Content-Length: " + file.length() + "\r\n\r\n");

            // Envie o conteúdo do arquivo
            while (fileInputStream.read(buffer) != -1) {
                writer.write(buffer);
            }

            fileInputStream.close();
        } else {

            // Arquivo não encontrado - envie uma resposta 404
            writer.writeBytes("HTTP/1.1 404 Not Found\r\n\r\n");

        }
        writer.close();
    }

    // Função para obter o tipo MIME com base na extensão do arquivo
    private static String getContentType(String resource) {
        if (resource.endsWith(".html") || resource.endsWith(".htm")) {
            return "text/html";
        } else if (resource.endsWith(".jpg") || resource.endsWith(".jpeg")) {
            return "image/jpeg";
        } else {
            return "application/octet-stream";
        }
    }




}
