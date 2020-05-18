package socketsUDPract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class datagramClient {
	public static void main(String[] args) {
		String hostname;
		int port = 2025;
		int len = 1024;

		DatagramPacket sPacket, rPacket;

		if( args.length > 0 )
			hostname = args[0];
		else
			hostname = "localhost";

		try{
			InetAddress ia = InetAddress.getByName(hostname);
			DatagramSocket datasocket = new DatagramSocket();
			BufferedReader stdinp = new BufferedReader(new InputStreamReader(System.in));

			while( true )
			{
				try{
				    System.out.print("Mensaje: ");
					String echoline = stdinp.readLine();
					if( echoline.equals("done") )
						break;

					byte buffer[] = new byte[echoline.length()];
					buffer = echoline.getBytes();
					sPacket = new DatagramPacket( buffer, buffer.length, ia, port );
					datasocket.send(sPacket);

					byte rbuffer[] = new byte[len];
					rPacket = new DatagramPacket( rbuffer, rbuffer.length );
					datasocket.receive( rPacket );

					String retstring = new String( rPacket.getData(), 0, rPacket.getLength() );
					System.out.println("Mensaje del Servidor: " + retstring);

				}catch( IOException e )
				{
					System.err.println( e );
				}
			}
		}catch( UnknownHostException e )
		{
			System.err.println( e );
		}catch( SocketException se )
		{
			System.err.println( se );
		}
	}

}
