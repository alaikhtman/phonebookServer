import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Student on 15.05.2015.
 */
public class WriterThread extends  Thread
{
    private ArrayList  contactList;
    private Socket clientSocket;
    private PrintWriter writerToClient;


    public WriterThread (ArrayList  contactList, Socket clientSocket)
    {
        this.contactList = contactList;
        this.clientSocket = clientSocket;

    }

    @Override
    public void run()
    {
        try
        {
            writerToClient = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            for (int i = 0; i < contactList.size(); i++)
            {
                writerToClient.println (contactList.get(i));
                writerToClient.flush();
            }


        }

    }

}
