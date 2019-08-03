import java.util.*;


class Main {


    public static void main(String[] args) throws InterruptedException {
        Scanner keyboard = new Scanner(System.in);

        World test = new World(100, 5);

        System.out.println("New world with bugs created: Begin Simulation");
        keyboard.nextLine();

        for (int i = 0; i < 100000; i++) {
            test.simulate();

            Thread.sleep(10);

        }


    }
}


