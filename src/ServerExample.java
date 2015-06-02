import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServerExample {
    private static final int PORT = 7022;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader readerFromClient;
    //private PrintWriter writerToClient;
    private PrintWriter writerToFile;
    private  ArrayList contactList = new ArrayList();


    public void start() {
        try
        {
            serverSocket = new ServerSocket(PORT);
            clientSocket = serverSocket.accept();
            readerFromClient = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            //writerToClient = new PrintWriter(clientSocket.getOutputStream());
            writerToFile = new PrintWriter(new FileOutputStream("contact.txt", true));


            String data;
            JDBConnector connector = new JDBConnector();



            while (true)

            {
                data = readerFromClient.readLine();

                if (data != null)
                {
                    String[] queryList = data.split(":");
                    if (queryList[0].equals("add"))
                    {
                        addContact(queryList[1], queryList[2], queryList[3]);
                        writerToFile.println("Name: " + queryList[1] + " Phone: " + queryList[2] + " Email: " + queryList[3]);
                        try {
                            connector.connect();
                            connector.insert(queryList[1], queryList[2], queryList[3]);
                            connector.disconnect();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }



                    } else
                    if (queryList[0].equals("getAllContact"))
                    {
                        System.out.println("lol");

//                        WriterThread writerThread = new WriterThread(contactList, clientSocket);
//                        writerThread.start();



                    }

                    else if (queryList[0].equals("stop"))
                    {

                        readerFromClient.close();
                        writerToFile.close();
                        serverSocket.close();
                        break;
                    }

                }



            }
        }
        catch (IOException ie)
        {
            ie.printStackTrace();
            try {
                readerFromClient.close();
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void addContact(String name, String phone, String email)
    {
        Contact contact = new Contact (name, phone, email);
        contactList.add(contact);
        contact.print();

    }


}
