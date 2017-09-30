/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.httpserverheroku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gabriel
 */
public class ServidorMultiplesSolicitudes {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(36000);

        Socket clientSocket = null;
        while (true) {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            outputLine
                    = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF-8\">\n"
                    + "<title>Servidor con multiples solicitudes</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>Pagina de prueba</h1>\n"
                    + "</body>\n"
                    + "</html>\n" + inputLine;
            out.println(outputLine);
        }
    }

}
