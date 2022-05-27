package com.velus;

import com.velus.datatransfer.Request;
import com.velus.datatransfer.Response;
import com.velus.responseinterpreter.Interpreter;
import com.velus.workersmanager.WorkersManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

class ServerCommandController extends Thread {
    Server server;
    boolean stopped = false;

    public ServerCommandController(Server server) {
        this.server = server;
    }

    private void save() {
        try {
            this.server.manager.dump();
            System.out.println("Собрано в файл");
        } catch (IOException ex) {
            System.out.println("ошибка при работе с файлом");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (!stopped && scanner.hasNext()) {

                String command = scanner.nextLine();
                switch (command) {
                    case "exit":
                        save();
                        this.server.stop();
                        scanner.close();
                        stopped = false;
                        Thread.currentThread().interrupt();

                        break;
                    case "save":
                        save();
                        break;
                    default:
                        System.out.println("Команда не распознана");
                }

            }


        } catch (IllegalStateException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Server {
    private DatagramChannel datagramChannel;
    private ByteBuffer buffer = ByteBuffer.allocate(3000);
    private DatagramSocket datagramSocket;
    private int port;
    private Selector selector;

    private boolean stopped = false;
    private final Interpreter interpreter;
    public final WorkersManager manager;
    public static final Logger logger = (Logger) LogManager.getLogger();

    public Server(Interpreter interpreter) {

        logger.setLevel(Level.ALL);
        this.interpreter = interpreter;
        this.manager = this.interpreter.getManager();

    }

    public synchronized void stop() {
        this.stopped = true;
    }


    public void bind(int port) throws IOException {
        this.port = port;

        this.selector = Selector.open();
        this.datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        this.datagramSocket = datagramChannel.socket();
        datagramSocket.bind(new InetSocketAddress(port));
        datagramChannel.register(selector, SelectionKey.OP_READ);
        logger.info("Сервер размещён на порте {}", port);
    }

    public void run() {
        ServerCommandController controller = new ServerCommandController(this);
        controller.start();
        logger.info("Сервер начал работу");

        while (!this.stopped) {
            try {
                if (selector.selectNow() > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isReadable()) {
                            buffer.clear();
                            InetSocketAddress fromAddress = (InetSocketAddress) datagramChannel.receive(buffer);
                            ByteArrayInputStream stream = new ByteArrayInputStream(buffer.array());
                            ObjectInputStream os = new ObjectInputStream(stream);
                            Request request = (Request) os.readObject();
                            Response response = interpreter.execute(request);

                            logger.info("Получен запрос от {}", fromAddress.getAddress());

                            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
                            objectStream.writeObject(response);
                            objectStream.flush();
                            byte[] bytes = byteStream.toByteArray();
                            buffer.clear();
                            buffer.put(bytes);
                            buffer.flip();
                            datagramChannel.send(buffer, fromAddress);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
