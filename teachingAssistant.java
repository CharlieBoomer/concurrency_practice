
import java.util.concurrent.Semaphore;

public class teachingAssistant implements Runnable {
    private boolean isNapping;
    private Semaphore waitingRoom;
    private Semaphore office;
    private Semaphore helping;
    private Semaphore sleeping;
    

    public teachingAssistant(Semaphore waitingRoom, Semaphore office, Semaphore helping, Semaphore sleeping){
        this.isNapping = true;
        this.helping = helping;
        this.waitingRoom = waitingRoom;
        this.office = office;
        this.sleeping = sleeping;
    }

    public void setNap(boolean goSleep){
        this.isNapping = goSleep;
    }
    public boolean getNap(){
        return isNapping;
    }
    
    public void requestHelp(student student){
        
        try{
        System.out.println("TA is helping student: " + student.getStudentNumber());
        Thread.sleep(1000);
        
        System.out.println("TA finished helping student: " + student.getStudentNumber());
        student.setGettingHelp(true);
        helping.release();
        
        }catch(InterruptedException e){
            System.out.println("Problem occured");
        }
    }

    public void run(){
        
    try{    
        while(true){
           System.out.println("TA is going to nap");
           sleeping.acquire();
           sleeping.release();
           System.out.println("TA was woken up");
            while(waitingRoom.availablePermits() != 3){

                office.release();
                helping.acquire();
                System.err.println("TA is checking hallway");
                if(waitingRoom.availablePermits() == 3){
                    System.out.println("No one is waiting");
                    sleeping.acquire();
                }
            }
            office.drainPermits();
        }
    } catch(InterruptedException e){
        System.out.println("Office hours are done TA is going home.");
        return;
    }
    
}
}
