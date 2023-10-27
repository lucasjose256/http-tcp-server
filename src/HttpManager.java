import java.io.*;
import java.net.Socket;

public class HttpManager {

    HttpManager(String m,Socket s) throws IOException {
    getRequest(m,s);

    }

    void getRequest(String path, Socket clientSocket) throws IOException {
        String caminhoDiretorio = "C://Users//Rodrigo//IdeaProjects//http-tcp-server//src";
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
            File error = new File(caminhoDiretorio, "/404.html");
            // Arquivo não encontrado - envie uma resposta 404
            writer.writeBytes("HTTP/1.1 404 Not Found\r\n\r\n");
            FileInputStream fileInputStream = new FileInputStream(error);
            byte[] buffer = new byte[(int) error.length()];


            // Envie o conteúdo do arquivo
            while (fileInputStream.read(buffer) != -1) {
                writer.write(buffer);
            }








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
