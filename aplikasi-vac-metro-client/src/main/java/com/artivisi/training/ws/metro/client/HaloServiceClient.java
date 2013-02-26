/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.ws.metro.client;

/**
 *
 * @author endy
 */
public class HaloServiceClient {
    public static void main(String[] args) {
        
        try { // Call Web Service Operation
            com.artivisi.training.ws.metro.HaloService_Service service = new com.artivisi.training.ws.metro.HaloService_Service();
            com.artivisi.training.ws.metro.HaloService port = service.getHaloServicePort();
            // TODO initialize WS operation arguments here
            java.lang.String name = "";
            // TODO process result here
            java.lang.String result = port.hello(name);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
}
