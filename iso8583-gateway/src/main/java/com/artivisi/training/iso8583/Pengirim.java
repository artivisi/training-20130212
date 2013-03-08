/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.iso8583;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author endy
 */
public class Pengirim {
    public static void main(String[] args) throws Exception {
        String server = "localhost";
        Integer port = 20000;
        
        String msg = "0200723E440108E1800002003800000000000000000306110600123456789012110600030613030307501000102123456789011234567890121234567890123456123456789012345ArtiVisi Jakarta                      ID0190101111123456789012360";
        Integer msgLength = msg.length();
        String strLength = StringUtils.leftPad(msgLength.toString(), 4, "0");
        
        Socket s = new Socket(server, port);
        
        OutputStream output = s.getOutputStream();
        output.write((strLength + msg).getBytes());
        
        InputStream input = s.getInputStream();
        byte[] byteReplyLength = new byte[4];
        input.read(byteReplyLength);
        
        String strReplyLength = new String(byteReplyLength);
        Integer replyLength = Integer.parseInt(strReplyLength);
        
        byte[] byteMsgReply = new byte[replyLength];
        input.read(byteMsgReply);
        
        String reply = new String(byteMsgReply);
        System.out.println("Reply : "+reply);
    }
}
