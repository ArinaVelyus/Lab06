package com.velus;

import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;

import java.io.*;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private boolean isConnected = false;
    private int port;
    private String ip;


    public Client(){


    }
    public void connect(String ip, int port){
        this.port = port;
        this.ip = ip;
        reconnect();

    }

    private void reconnect() {
        try {
            this.socket = new DatagramSocket();
            this.isConnected = true;

        } catch (IOException ex){
            ex.printStackTrace();
            this.isConnected = false;
        }
    }

    public void disconnect(){
        this.isConnected = false;
        this.socket.close();
    }

    public void waitConnection(){
        reconnect();
        if (this.isConnected) return;
        System.out.println("Сервер умер. Пробуем восстановить соединение");
        while (!this.isConnected) {
            reconnect();
        }
        System.out.println("Соединение восстановлено");

    }
    public Response receive(){
        try {
            Response resp;
            byte[] buffer = new byte[65536];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            InputStream inputStream = new ByteArrayInputStream(buffer);
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            resp = (Response) objectStream.readObject();
            return resp;
        } catch (Exception ex){
            ex.printStackTrace();
            waitConnection();
            return receive();
        }
    }

    public boolean sendRequest(Request rq){
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(rq);
            objectStream.flush();
            byte[] buffer = byteStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), port);
            socket.send(packet);
            return true;
        } catch (IOException | NullPointerException ex) {
            waitConnection();
            return sendRequest(rq);
        }
    }

    public boolean isConnected(){return this.isConnected;}
}
