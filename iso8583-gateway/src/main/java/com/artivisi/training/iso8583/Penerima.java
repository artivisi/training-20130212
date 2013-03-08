/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.iso8583;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author endy
 */
public class Penerima {
    public static void main(String[] args) throws Exception {
        Integer port = 20000;
        
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server ready di port "+port);
        Socket s = server.accept();
        
        InputStream input = s.getInputStream();
        byte[] byteRequestLength = new byte[4];
        input.read(byteRequestLength);
        
        String strRequestLength = new String(byteRequestLength);
        Integer requestLength = Integer.parseInt(strRequestLength);
        
        byte[] byteMsgRequest = new byte[requestLength];
        input.read(byteMsgRequest);
        
        String request = new String(byteMsgRequest);
        System.out.println("Request : "+request);
        
        String replyLength = StringUtils.leftPad(String.valueOf(request.length()), 4, "0");
        OutputStream output = s.getOutputStream();
        output.write((replyLength + request).getBytes());
        
        output.close();
        input.close();
        s.close();
    }
}
