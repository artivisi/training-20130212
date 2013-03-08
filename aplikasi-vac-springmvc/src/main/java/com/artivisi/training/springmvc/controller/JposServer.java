/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.springmvc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOServer;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.Log4JListener;
import org.jpos.util.LogSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author endy
 */
@Component
public class JposServer {
    
    private ISOServer isoServer;
    
    @PostConstruct
    public void startServer() throws Exception {
        Integer port = 20000;
        
        ServerChannel channel = new ASCIIChannel(new ISO87APackager());
        
        isoServer = new ISOServer(port, channel, null);
        isoServer.addISORequestListener(new ISORequestListener() {

            public boolean process(ISOSource pengirim, ISOMsg request) {
                try {
                    ISOMsg reply = (ISOMsg) request.clone();
                    reply.setMTI("0210");
                    reply.set(39, "00");
                    System.out.println("Request : "+new String(request.pack()));
                    System.out.println("Reply : "+new String(reply.pack()));
                    pengirim.send(reply);
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(JposServer.class.getName()).log(Level.SEVERE, null, ex);
                } 
                return false;
            }
        });
        
        // pasang logger
        org.jpos.util.Logger logger = new org.jpos.util.Logger();
        Log4JListener log4JListener = new Log4JListener();
        log4JListener.setLevel("info");
        logger.addListener(log4JListener);
        ((LogSource) channel).setLogger(logger, "server-channel");
        isoServer.setLogger(logger, "server");
        
        new Thread(isoServer).start();
        System.out.println("Server siap di port "+port);
    }
    
    public String counter(){
        return isoServer.getCountersAsString();
    }
    
    public Integer jumlahKoneksi(){
        return isoServer.getActiveConnections();
    }
    
    @PreDestroy
    public void stopServer(){
        isoServer.shutdown();
    }
}
