import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class teachingAssistant implements Runnable {
    private boolean isNapping;
    private boolean isWaiting;
    private boolean isHelping;
    private Semaphore waitingRoom;
    private CountDownLatch latch;
    

    public teachingAssistant(CountDownLatch Latch, Semaphore waitingRoom){
        this.isNapping = true;
        this.isWaiting = false;
        this.isHelping = false;
    }

    public void setNap(boolean goSleep){
        this.isNapping = goSleep;
    }
    public boolean getNap(){
        return isNapping;
    }

    public void setHelping(boolean goHelp){
        this.isHelping = goHelp;
    }

    public boolean getHelping(){
        return isHelping;
    }

    public void finishHelp(student student){
        student.setGettingHelp(false);
        // student.setProgramming(true);
    }

    public void run(){

    }
}
