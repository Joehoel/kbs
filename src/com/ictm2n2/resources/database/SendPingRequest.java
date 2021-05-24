package com.ictm2n2.resources.database;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SendPingRequest {
    private String naam;
    private byte[] ipAddress;

    public SendPingRequest(String naam, byte[] ipAddress) {
        this.naam = naam;
        this.ipAddress = ipAddress;
    }

    public boolean sendPingRequest() throws UnknownHostException, IOException {
        InetAddress geek = InetAddress.getByAddress(this.ipAddress);
        // System.out.println("Sending Ping Request to " + this.ipAddress);
        if (geek.isReachable(5000)) {
            // System.out.println(this.naam+" reachable");
            return true;
        } else {
            // System.out.println(this.naam+" unreachable");
            return false;
        }
    }
}
