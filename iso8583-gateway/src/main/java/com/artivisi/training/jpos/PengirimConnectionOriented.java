/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.jpos;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMUX;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequest;
import org.jpos.util.Log4JListener;
import org.jpos.util.LogSource;

/**
 *
 * @author endy
 */
public class PengirimConnectionOriented {
    public static void main(String[] args) throws Exception {
        ISOMsg msg = new ISOMsg();
        msg.setMTI("0200");
        msg.set(2, "00");
        msg.set(3, "380000");
        msg.set(4, "000000000000");
        msg.set(7, new SimpleDateFormat("MMddHHmmss").format(new Date()));
        msg.set(11, "123456789012");
        msg.set(12, new SimpleDateFormat("HHmmss").format(new Date()));
        msg.set(13, new SimpleDateFormat("MMdd").format(new Date()));
        msg.set(14, new SimpleDateFormat("yyMM").format(new Date()));
        msg.set(15, new SimpleDateFormat("MMdd").format(new DateTime().plusDays(1).toDate()));
        msg.set(18, "5010");
        msg.set(22, "001");
        msg.set(32, "12345678901");
        msg.set(37, "123456789012");
        msg.set(41, "1234567890123456");
        msg.set(42, "123456789012345");
        msg.set(43, "ArtiVisi Jakarta                      ID");
        msg.set(48, "0101111123456789012");
        msg.set(49, "360");
        
        msg.setPackager(new ArtivisiPackager());
        String data = new String(msg.pack());
        System.out.println("Data : "+data);
        
        String server = "localhost";
        Integer port = 20000;
        
        ISOMUX mux = new ISOMUX(new GspChannel(server, port, new ArtivisiPackager())){

            @Override
            protected String getKey(ISOMsg m) throws ISOException {
                if(m.getString(11) != null){
                    return m.getString(11) + m.getString(41);
                }
                return m.getString(12) + m.getString(41);
            }
            
        };
        
        // pasang logger
        org.jpos.util.Logger logger = new org.jpos.util.Logger();
        Log4JListener log4JListener = new Log4JListener();
        log4JListener.setLevel("info");
        logger.addListener(log4JListener);
        ((LogSource) mux.getISOChannel()).setLogger(logger, "client-channel");
        mux.setLogger(logger, "mux");
        
        new Thread(mux).start();
        
        ISORequest request = new ISORequest(msg);
        mux.queue(request);
        
        ISOMsg reply = request.getResponse(10 * 1000);
        if(reply == null){
            System.out.println("Tidak dapat reply sampai timeout");
        }
        
        System.out.println("Reply : "+new String(reply.pack()));
    }
}
