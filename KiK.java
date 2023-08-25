public class KiK {          //gra w kółko i krzyżyk

    public static void main(String args[]) {


        MyThread.kik.poczatek();

        var mtX = new MyThread("gracz X", MyThread.kik.graczX);
        var mtO = new MyThread("gracz O", MyThread.kik.graczO);

        mtX.trd.start();
        mtO.trd.start();

        try {
            mtX.trd.join();
            mtO.trd.join();
        } catch(InterruptedException exc) {
            System.out.println("Wątek główny został przerwany.");
        }

        if(MyThread.kik.remis) System.out.println("Remis!");
        else if(!MyThread.kik.ruch) System.out.println("Grę wygrywa gracz X!"); else
            System.out.println("Grę wygrywa gracz O!");



    }


    }

