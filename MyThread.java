public class MyThread implements Runnable {

    Thread trd;
    int strategia;
    static PlanszaKiK kik = new PlanszaKiK();

    MyThread(String name, int st) {
        trd = new Thread(this,name);
        strategia = st;
    }

    public void run() {

        boolean r;
        char nazwa = trd.getName().charAt(6);



        while(kik.dalejgramy) { //dopóki gra się toczy, wykonujemy ruchy

            r= (nazwa=='X') ? kik.ruch : !kik.ruch;


                switch (strategia) {
                    case 0:
                        kik.gracz(trd.getName().charAt(6));
                        break;
                    case 1:
                        kik.los(trd.getName().charAt(6));
                        break;
                    case 2:
                        kik.polintelig(trd.getName().charAt(6));
                        break;
                    case 3:
                        kik.intelig(trd.getName().charAt(6));
                        break;

            }


        }


    }

}
