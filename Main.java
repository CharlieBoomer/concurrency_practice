import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        int numStudents =0;
        String studName;
        Semaphore waitingRoom = new Semaphore(3);
        Semaphore office = new Semaphore(0);
        Semaphore helping = new Semaphore(0);
        Semaphore sleeping = new Semaphore(0);
        

        try{
            numStudents = Integer.parseInt(args[0]);
            
        }catch(NumberFormatException e){
            System.out.println("Bad args, only integers");
            return;
        }
        
        CountDownLatch latch = new CountDownLatch(numStudents);
        //student[] chairs = new student[3];
        
        teachingAssistant ta = new teachingAssistant(waitingRoom, office, helping, sleeping);
        Thread taThread = new Thread (ta);

        taThread.start();

        
        for(int i = 0; i < numStudents; i++)
            new Thread(new student(i, latch, waitingRoom, ta, office, sleeping)).start(); 
        
        

        
        try{latch.await();
        }catch(InterruptedException e){
            e.getStackTrace();
        }

        
        taThread.interrupt();
    }
}
