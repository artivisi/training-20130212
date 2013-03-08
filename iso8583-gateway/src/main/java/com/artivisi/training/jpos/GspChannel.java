/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.training.jpos;

import java.io.IOException;
import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

/**
 *
 * @author endy
 */
public class GspChannel extends BaseChannel {

    private int MESSAGE_TRAILER = 255;
    private static final int MAX_MSG_LENGTH = 4096;

    @Override
    protected void sendMessageTrailler(ISOMsg m, byte[] b) throws IOException {
        serverOut.write(MESSAGE_TRAILER);
    }

    @Override
    protected byte[] streamReceive() throws IOException {
        byte[] buf = new byte[MAX_MSG_LENGTH];
        int len = 0;
        for (len = 0; len < MAX_MSG_LENGTH; len++) {
            int data = serverIn.read();
            if (data == MESSAGE_TRAILER) {
                break;
            }
            buf[len] = (byte) data;
        }
        if (len == MAX_MSG_LENGTH) {
            throw new IOException("packet too long");
        }

        byte[] d = new byte[len];
        System.arraycopy(buf, 0, d, 0, len);
        return d;
    }

    @Override
    protected int getMessageLength() throws IOException, ISOException {
        return -1;
    }

    public GspChannel() {
    }

    public GspChannel(String host, int port, ISOPackager p) {
        super(host, port, p);
    }

    public GspChannel(ISOPackager p) throws IOException {
        super(p);
    }
    
    
}
