package main.java.chap01;

public class LambdaEx01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Normal Run Thread.");
            }
        }).start();

        //람다식
        new Thread(() -> {
            System.out.println("Lambda run...");
        }).start();
    }
}
