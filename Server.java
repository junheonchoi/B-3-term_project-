import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.net.*;
import java.util.HashSet;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.*;

public class Server {

    private static final int PORT = 9001;
    private static ArrayList<information> infos = new ArrayList<information>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    public static void main(String[] args) throws Exception {
        System.out.println("2% server is working");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
    
    private static class restoringimage
    {
    	String imagepath;
    	String name;
    	BufferedImage image = null;
    	File f = null;
    	
    	public void set(String IMP, String n)
    	{
    		imagepath = IMP;
    		name = n;
    	}
    	
    	public void restore()
    	{	
    		try
    		{
    			f = new File(imagepath);
    			image = ImageIO.read(f);
    		}
    		catch(IOException e)
    		{
    			System.out.println("Error:"+e);
    		}
    		
    		try
    		{
    			f= new File("C:\\clientimage\\"+name+".PNG");
    			ImageIO.write(image,"PNG",f);
    		}
    		catch(IOException e)
    		{
    			System.out.println("Error: "+e);
    		}
    	}

    }
    
    private static class information
    {	
    	  private String imagepath;
    	  private String name;
          private String age;
          private String height;
          private String weight;
          private String job;
          private String hobby;
          private String sii;
          
          public void setName(String name)
          {
        	  this.name=name;
          }
          
          public void setImagepath(String imagepath)
          {
        	  this.imagepath = imagepath;
          }
          
          public void setAge(String age)
          {
        	  this.age=age;
          }
          
          public void setHeight(String height)
          {
        	  this.height=height;
          }
          
          public void setWeight(String weight)
          {
        	  this.weight=weight;
          }
          
          public void setJob(String job)
          {
        	  this.job=job;
          }
          
          public void setHobby(String hobby)
          {
        	  this.hobby=hobby;
          }
          
          public void setSii(String sii)
          {
        	  this.sii=sii;
          }
          
          public String getImagepath()
          {
        	  return this.imagepath;
          }
          
          public String getName()
          {
        	  return this.name;
          }
          
          public String getAge()
          {
        	  return this.age;
          }
          
          public String getHeight()
          {
        	  return this.height;
          }
          
          public String getWeight()
          {
        	  return this.weight;
          }
          
          public String getJob()
          {
        	  return this.job;
          }
          
          public String getHobby()
          {
        	  return this.hobby;
          }
          
          public String getSii()
          {
        	  return this.sii;
          }
          
    }
    private static class Handler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private information info;
        private restoringimage ri;

        public Handler(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {

                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("MEMBERSHIP");
                    info = new information();
                    
                    info.setName(in.readLine());
                    if (info.getName() == null) {
                        return;
                    }
                    info.setImagepath(in.readLine());
                    if(info.getImagepath()== null) {
                    	return;
                    }
                    ri = new restoringimage();
                    ri.set(info.getImagepath(),info.getName());
                    ri.restore();
                    
                    info.setAge(in.readLine());
                    if (info.getAge() == null) {
                        return;
                    }
                    info.setHeight(in.readLine());
                    if (info.getHeight() == null) {
                        return;
                    }
                    info.setWeight(in.readLine());
                    if (info.getWeight() == null) {
                        return;
                    }
                    info.setJob(in.readLine());
                    if (info.getJob() == null) {
                        return;
                    }
                    info.setHobby(in.readLine());
                    if (info.getHobby() == null) {
                        return;
                    }
                    info.setSii(in.readLine());
                    if (info.getSii() == null) {
                        return;
                    }
                    break;
                }
                infos.add(info);

                out.println("Congratulations!");
                writers.add(out);
               
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " +": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}