import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class student implements Runnable {
    private int studentNumber;
    private boolean isProgramming;
    private boolean isWaiting;
    private boolean isGettingHelp;
    private Semaphore waitingRoom;
    private CountDownLatch latch;
    
    public student(int n, CountDownLatch latch, Semaphore waitingRoom, teachingAssistant TA){
        this.studentNumber = n;
        this.isProgramming = true;
        this.isWaiting = false;
        this.isGettingHelp =false;
        this.waitingRoom = waitingRoom;
        this.latch = latch;
    }

    public void setProgramming(boolean program){
        this.isProgramming = program;
    }
    public boolean getProgramming(){
        return isProgramming;
    }

    public void setWaiting(boolean goHelp){
        this.isWaiting = goHelp;
    }

    public boolean getWaiting(){
        return isWaiting;
    }

    public void setGettingHelp(boolean goHelp){
        this.isGettingHelp = goHelp;
    }

    public boolean getGettingHelp(){
        return isGettingHelp;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
    
    public int getStudentNumber() {
        return studentNumber;
    }

    public void wakeTA(teachingAssistant tA){
        tA.setNap(false);
        tA.setHelping(true);

    }
    public void run(){
       int waitTime = (int) ((Math.random() * 5000) + 1000);

        try{
        do{
        Thread.sleep(waitTime);

        if (waitingRoom.tryAcquire()){

        }
        }while(isGettingHelp == false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
