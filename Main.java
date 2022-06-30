import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        int numStudents =0;
        Semaphore waitingRoom = new Semaphore(3);

        try{
            numStudents = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.out.println("Bad args, only integers");
            return;
        }
        
        student[] chairs = new student[3];
        
        numStudents += 1;

        System.out.println(numStudents);

    }
}
