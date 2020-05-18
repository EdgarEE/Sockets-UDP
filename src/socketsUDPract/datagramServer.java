package socketsUDPract;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.StringTokenizer;

public class datagramServer {
	public static void main(String args[])
	{
		DatagramPacket datapacket, returnpacket;
		int port = 2025;
		int len = 1024;
		int n=0,cantidad1=0,cantidad2=0,resultado=0;
		String operando="";
		
		try{
			DatagramSocket datasocket = new DatagramSocket(port);
			byte buf[]= new byte[len];
			byte rbuffer[] = new byte[len];
			while(true)
			{
				try{
					datapacket = new DatagramPacket(buf, buf.length);
					datasocket.receive(datapacket);
					String retstring = new String( datapacket.getData(), 0, datapacket.getLength() );
					StringTokenizer st = new StringTokenizer(retstring);
					
					while (st.hasMoreTokens()) {
						if(n==0) {
							String token=st.nextToken();
							cantidad1 = Integer.parseInt(token);
							n=n+1;
						}
						if(n==1) {
						operando=st.nextToken();
						n=n+1;
						}
						if(n==2) {
							String token2=st.nextToken();
							cantidad2 = Integer.parseInt(token2);
							n=n+1;
							}
						}
					n=0;
				        if(operando.equals("+")) {
				        	resultado=cantidad1+cantidad2;
				        }
				        else  if(operando.equals("-")) {
				        	resultado=cantidad1-cantidad2;
				        }
				        else  if(operando.equals("*")) {
				        	resultado=cantidad1*cantidad2;
				        }
				        else  if(operando.equals("/")) {
				        	resultado=cantidad1/cantidad2;
				        }
				        else  if(operando.equals("^")) {
				        	resultado=(int) Math.pow(cantidad1,cantidad2);
				        }
				        String res = Integer.toString(resultado);
				      rbuffer = res.getBytes();
						returnpacket = new DatagramPacket( rbuffer, rbuffer.length,datapacket.getAddress(),
								datapacket.getPort());
				         
					System.out.println( resultado );
										
					datasocket.send(returnpacket);
					
					
				}catch( IOException e )
				{
					System.err.println(e);
				}
			}
		}catch(SocketException se)
		{
			System.err.println(se);
		}
	}

}
