public class Time extends Thread{

    private static int seconds;

    @Override
    public void run(){
        while(!interrupted()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}

            seconds++;
        }
    }

    public static int getSeconds() {
        return seconds;
    }

    public static void setSeconds(int seconds) {
        Time.seconds = seconds;
    }
}
