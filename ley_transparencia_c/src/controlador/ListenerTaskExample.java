package controlador;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ListenerTaskExample implements ServletContextListener {
    private Thread t = null;
    private ServletContext context;
    public void contextInitialized(ServletContextEvent contextEvent) {
        t =  new Thread(){
            //task
            public void run(){                
                try {
                    while(true){
                        System.out.println("Se ejecuta una vez cada segundo");
                        JobExecutionContext jec=null;
                        new TareaInvocar().execute(jec);
                        Thread.sleep(500000000);
                    }
                } catch (InterruptedException e) {} catch (JobExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }            
        };
        t.start();
        context = contextEvent.getServletContext();
        // you can set a context variable just like this
        context.setAttribute("TEST", "TEST_VALUE");
    }
    public void contextDestroyed(ServletContextEvent contextEvent) {
        // si se destruye el contexto se interrumpe el thread
        t.interrupt();
    }            
}
