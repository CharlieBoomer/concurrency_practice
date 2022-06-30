import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class student implements Runnable {
    private int studentNumber;
    private boolean isProgramming;
    private boolean isWaiting;
    private boolean isGettingHelp;
    private Semaphore waitingRoom;
    private CountDownLatch latch;
    private teachingAssistant tA;
    private Semaphore office;
    private Semaphore sleeping;
    
    public student(int n, CountDownLatch latch, Semaphore waitingRoom, teachingAssistant tA, Semaphore office, Semaphore sleeping ){
        this.studentNumber = n;
        this.isProgramming = true;
        this.isWaiting = false;
        this.isGettingHelp =false;
        this.waitingRoom = waitingRoom;
        this.latch = latch;
        this.tA = tA;
        this.office = office;
        this.sleeping = sleeping;
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

    public void run(){
       int waitTime = (int) ((Math.random() * 20000) + 5000);
       System.out.println("Student " + studentNumber + " is created and programming");
       
        try{
        Thread.sleep(waitTime);
        do{


        if (waitingRoom.tryAcquire()){
            if(sleeping.availablePermits() == 0){
                System.out.println("Student: " + studentNumber + " is waking up TA");
                sleeping.release();
            }

            System.out.println("Student: " + studentNumber + " waiting for TA");
            office.acquire();
            waitingRoom.release();
            tA.requestHelp(this);
            
        }else{
            System.out.println("Waiting Room is full Student: " + studentNumber + " is going back to programming");
            waitTime = (int) ((Math.random() *5000) + 1000);
            Thread.sleep(waitTime);
        }
        }while(isGettingHelp == false);
        
    }catch(Exception e){
            e.printStackTrace();
            return;
        }
        System.out.println("Student: " + studentNumber + " got help and is going home");
        latch.countDown();
    }
}
