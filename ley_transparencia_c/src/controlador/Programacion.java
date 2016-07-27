package controlador;

import java.util.Date;

import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class Programacion {
    //El horario de la tarea
    private Scheduler horario;
    //Metodo que crea el horario
    
    private void crearProgramacio() {
        try{
            //Creando la programacion
            SchedulerFactory factoria = new StdSchedulerFactory();
            //Obteniendo el horario
            horario = factoria.getScheduler();
            //Damos inicio al horario
            horario.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    //Metodo que da la informacion mas detallada sobre el horario, como hora de inicio de la tarea y cada
    //cuanto tiempo se ejecute la tarea
    public void iniciarTarea() throws SchedulerException {
    	BasicConfigurator.configure();
        if (this.horario == null) {
            this.crearProgramacio();
        }
        //Informacion sobre la tarea
        //La informacion que se pide un nombre, a que grupo pertenece 
        //y a que clase que implemente de Job va a ser llamado
        SchedulerFactory sf=new StdSchedulerFactory();
        Scheduler sched=sf.getScheduler();
        sched.start();
        //JobDetail job = new JobDetail("EnviarNotificacion", sched.DEFAULT_GROUP, TareaInvocar.class);
        //JobDetail job = new JobDetail(withIdentity("z"),);
        		//(TareaInvocar.class).withIdentity("myJob", "group1").build();
        //Creamos un trigger y le decimos cada cuanto queremos que se invoque la tarea o Job
        //Tenemos los metodos como los siguientes
        //TriggerUtils.makeSecondlyTrigger(tiempo) --> Para invocar una tarea cada cierto segundos
        //TriggerUtils.makeMinutelyTrigger(tiempo) --> Para invocar una tarea cada cierto minutos
        //TriggerUtils.makeHourlyTrigger(tiempo) --> Para invocar una tarea cada cierta hora
        //Entre otros mas que pueden revisar en la documentacion
      // Trigger trigger = TriggerUtils;
        //En que momento va a iniciar la tarea
      //  trigger.setStartTime(new Date());
        //El nombre del trigger que debe ser unico
       // trigger.setName("CadaDiezMinutos");
        try {
         //   this.horario.scheduleJob(job, trigger);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
