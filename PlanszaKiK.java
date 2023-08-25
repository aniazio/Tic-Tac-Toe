import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PlanszaKiK {

    private char[] tab;         //tablica
    boolean ruch;       //true, gdy gracz X wykonuje ruch
    boolean dalejgramy, wygrana, remis;
    private boolean popr;
    int graczX, graczO;     //zapisuje, którą strategię będzie realizował każdy z graczy
    private int nr;             //zmienna pomocnicza, mówiąca, który ruch jest teraz wykonywany


    PlanszaKiK() {
        tab = new char[9];
        for (int i = 0; i < 9; i++) {
            tab[i] = (char) (i+49);
        }

        Random rr = new Random();
        ruch = rr.nextBoolean(); //true, gdy gracz X wykonuje ruch

        dalejgramy = true;
        popr = wygrana = false;
        remis = false;
        nr=0;
    }

    void wyswietlanieTablicy() {

        System.out.println("\t \t|\t \t|\t \t\n\t" + tab[0] + "\t|\t" + tab[1] + "\t|\t" + tab[2] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("  ---------------------");
        System.out.println("\t \t|\t \t|\t \t\n\t" + tab[3] + "\t|\t" + tab[4] + "\t|\t" + tab[5] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("  ---------------------");
        System.out.println("\t \t|\t \t|\t \t\n\t" + tab[6] + "\t|\t" + tab[7] + "\t|\t" + tab[8] + "\t\n\t \t|\t \t|\t \t");
    }



    void poczatek() {
        wyswietlanieTablicy();

        System.out.println("\nOto plansza do gry w kółko i krzyżyk. Numerami oznaczono kolejne pozycje.\nRuchy gracza X " +
                "będą zaznaczane krzyżykami, a gracza O - kółkami. Wybór gracza rozpoczynającego grę dokonuje się losowo.\n");

        try{
            Thread.sleep(2000);
        } catch(InterruptedException exc) {System.out.println("Metoda poczatek została przerwana.");}


        Scanner scan = new Scanner(System.in);


        System.out.println("\nWybierz strategię gracza X.\n\n" +
                "Jeśli chcesz sam sterować graczem X wpisz 0.\n" +
                "Jeśli chcesz, żeby graczem X sterował komputer w sposób losowy, wpisz 1.\n" +
                "Jeśli chcesz, aby graczem X sterował komputer, realizując dobrą strategię, wpisz 2.\n" +
                "Jeśli chcesz, żeby graczem X sterował komputer, realizując najlepszą możliwą strategię, wpisz 3.");

        while(!popr){
            int intel = scan.nextInt(); //zmienna o zasięgu lokalnym
            if(intel==0 || intel==1 || intel==2 || intel==3) popr=true;
            else System.out.println("Wpisałeś złą wartość, spróbuj jeszcze raz.");
            graczX = intel;
        }

        System.out.println("Wybierz strategię gracza O.\n\n" +
                "Jeśli chcesz sam sterować graczem O wpisz 0.\n" +
                "Jeśli chcesz, żeby graczem O sterował komputer w sposób losowy, wpisz 1.\n" +
                "Jeśli chcesz, aby graczem O sterował komputer, realizując dobrą strategię, wpisz 2.\n" +
                "Jeśli chcesz, żeby graczem O sterował komputer, realizując najlepszą możliwą strategię, wpisz 3.");

        popr=false;

        while(!popr){
            int intel = scan.nextInt(); //zmienna o zasięgu lokalnym
            if(intel==0 || intel==1 || intel==2 || intel==3) popr=true;
            else System.out.println("Wpisałeś złą wartość, spróbuj jeszcze raz.");
            graczO = intel;
        }


        if(ruch) System.out.println("\nZaczyna gracz X.");
        else System.out.println("\nZaczyna gracz O.");

    }



    synchronized void los(char znak) {           //losowa strategia komputera
        int pole=0;                     //zmienna mówiąca, na którym polu zostanie postawiony znacznik. Na razie zerowa
        Random rr = new Random();
        char moja, niemoja;             //moja to oznaczenie stosowane przez danego gracza
                                        //niemoja to oznaczenie przeciwne
        moja = znak;
        if(znak=='X') niemoja='O';
        else niemoja = 'X';

        boolean r = (znak == 'X') ? ruch : !ruch;

        if(r && dalejgramy) {

            int tu = rr.nextInt(9-nr);   //losuje pole spośród tych, które pozostały
            int l=0;
            for(int i=0;i<9;i++){
                if(tab[i]==i+49) {if(l==tu){tab[i]=moja; pole=i+1;} l++;}
            }

        try{
            Thread.sleep(3000);
        } catch(InterruptedException exc) {System.out.println("Metoda poczatek została przerwana.");}


        System.out.println("Gracz " + znak + " postawił znacznik na polu " + pole + ".");
        nr++;

        czyWygrana();       //jeśli ktoś wygrał, to powoduje dalejgramy = false

        ruch = !ruch;    //po ruchu zmieniamy gracza, który wykonuje ruch

        System.out.println("Aktualna plansza:");
        wyswietlanieTablicy();

    }
    notify();

        if(dalejgramy) {
        try {
            while (!r) {         //czekaj dopóki nie jest twój ruch
                wait();
                r = (znak == 'X') ? ruch : !ruch;
            }
        } catch (InterruptedException exc) {
            System.out.println("Działanie wątku gracza " + znak + " zostało przerwane.");
        }

    }

    }

    synchronized void polintelig(char znak) {        //pół-inteligentna strategia komputera
        int pole = 0;               //zmienna mówiąca, na którym polu zostanie postawiony znacznik. Na razie zerowa
        char f, moja, niemoja;      //f to zmienna pomocnicza. moja to oznaczenie stosowane przez danego gracza
                                    //niemoja to oznaczenie przeciwne
        Random rr = new Random();

        moja = znak;
        if(znak=='X') niemoja='O';
        else niemoja = 'X';

        boolean r = (znak == 'X') ? ruch : !ruch;

        if(r && dalejgramy) {

        for (int w = 0; w < 2; w++) {
            if (w == 0) f = moja;
            else f = niemoja; //najpierw uzupełnia swoje znaki do wygranej (w=0), a jeśli nie może, to blokuje przeciwnika (w=1)

            for (int i = 0; i < 3; i++) {
                // sprawdzanie wierszy
                if (tab[3 * i + 0] == f && tab[3 * i + 0] == tab[3 * i + 1] && tab[3 * i + 2] == 3 * i + 2 + 49 && pole == 0) {
                    tab[3 * i + 2] = moja;
                    pole = 3 * i + 3;
                }
                if (tab[3 * i + 1] == f && tab[3 * i + 1] == tab[3 * i + 2] && tab[3 * i + 0] == 3 * i + 0 + 49 && pole == 0) {
                    tab[3 * i + 0] = moja;
                    pole = 3 * i + 1;
                }
                if (tab[3 * i + 0] == f && tab[3 * i + 0] == tab[3 * i + 2] && tab[3 * i + 1] == 3 * i + 1 + 49 && pole == 0) {
                    tab[3 * i + 1] = moja;
                    pole = 3 * i + 2;
                }

                // sprawdzanie kolumn
                if (tab[i + 0] == f && tab[i + 0] == tab[i + 3] && tab[i + 6] == i + 6 + 49 && pole == 0) {
                    tab[i + 6] = moja;
                    pole = i + 7;
                }
                if (tab[i + 3] == f && tab[i + 3] == tab[i + 6] && tab[i + 0] == i + 0 + 49 && pole == 0) {
                    tab[i + 0] = moja;
                    pole = i + 1;
                }
                if (tab[i + 0] == f && tab[i + 0] == tab[i + 6] && tab[i + 3] == i + 3 + 49 && pole == 0) {
                    tab[i + 3] = moja;
                    pole = i + 4;
                }
            }

            //sprawdzanie przekątnych

            if (tab[0] == f && tab[0] == tab[4] && tab[8] == 8 + 49 && pole == 0) {
                tab[8] = moja;
                pole = 9;
            }
            if (tab[0] == f && tab[0] == tab[8] && tab[4] == 4 + 49 && pole == 0) {
                tab[4] = moja;
                pole = 5;
            }
            if (tab[8] == f && tab[8] == tab[4] && tab[0] == 0 + 49 && pole == 0) {
                tab[0] = moja;
                pole = 1;
            }

            if (tab[2] == f && tab[2] == tab[4] && tab[6] == 6 + 49 && pole == 0) {
                tab[6] = moja;
                pole = 7;
            }
            if (tab[6] == f && tab[6] == tab[4] && tab[2] == 2 + 49 && pole == 0) {
                tab[2] = moja;
                pole = 3;
            }
            if (tab[2] == f && tab[2] == tab[6] && tab[4] == 4 + 49 && pole == 0) {
                tab[4] = moja;
                pole = 5;
            }
        }

        //jeśli nie ma dobrej strategii, to losuje

        if (pole == 0) {
            int tu = rr.nextInt(9 - nr); //zmienna o zasięgu lokalnym
            int l = 0;
            for (int i = 0; i < 9; i++) {
                if (tab[i] == i + 49) {
                    if (l == tu) {
                        tab[i] = moja;
                        pole = i + 1;
                    }
                    l++;
                }
            }

        }

            try{
                Thread.sleep(3000);
            } catch(InterruptedException exc) {System.out.println("Metoda poczatek została przerwana.");}


            System.out.println("Gracz " + znak + " postawił znacznik na polu " + pole + ".");
        nr++;

            czyWygrana();       //jeśli ktoś wygrał, to powoduje dalejgramy = false

            ruch = !ruch;    //po ruchu zmieniamy gracza, który wykonuje ruch

            System.out.println("Aktualna plansza:");
            wyswietlanieTablicy();

        }
        notify();

        if(dalejgramy) {
            try {
                while (!r) {         //czekaj dopóki nie jest twój ruch
                    wait();
                    r = (znak == 'X') ? ruch : !ruch;
                }
            } catch (InterruptedException exc) {
                System.out.println("Działanie wątku gracza " + znak + " zostało przerwane.");
            }

        }

    }


    synchronized void intelig(char znak) {
        int pole=0;                 //zmienna mówiąca, na którym polu zostanie postawiony znacznik. Na razie zerowa
        char f, moja, niemoja;      //f to zmienna pomocnicza. moja to oznaczenie stosowane przez danego gracza
                                    //niemoja to oznaczenie przeciwne
        int[] zajete = {0,0,0,0,0,0,0,0}, zajete2 = {0,0,0,0,0,0,0,0};
            //tablice zajete i zajete2 kodują, czy w wierszu/kolumnie/przekątnej są jakieś znaki odpowiednio gracza i przeciwnika

        moja = znak;
        if(znak=='X') niemoja='O';
        else niemoja = 'X';

        boolean r = (znak == 'X') ? ruch : !ruch;

        if(r && dalejgramy) {

        //jeśli środek wolny, komputer stawia znak na środku
        if(tab[4]=='5') {
            tab[4]=moja;
            pole=5;
        }

        for(int w=0;w<2;w++){
            if(w==0) f=moja; //najpierw uzupełnia swoje znaki do wygranej (w=0), a jeśli nie może, to blokuje przeciwnika (w=1)
            else f=niemoja;

            for(int i=0;i<3;i++){
                // sprawdzanie wierszy
                if(tab[3*i+0]==f && tab[3*i+0]==tab[3*i+1] && tab[3*i+2]==3*i+2+49 && pole ==0) {tab[3*i+2]=moja; pole=3*i+3;}
                if(tab[3*i+1]==f && tab[3*i+1]==tab[3*i+2] && tab[3*i+0]==3*i+0+49 && pole ==0) {tab[3*i+0]=moja; pole=3*i+1;}
                if(tab[3*i+0]==f && tab[3*i+0]==tab[3*i+2] && tab[3*i+1]==3*i+1+49 && pole ==0) {tab[3*i+1]=moja; pole=3*i+2;}

                // sprawdzanie kolumn
                if(tab[i+0]==f && tab[i+0]==tab[i+3] && tab[i+6]==i+6+49 && pole ==0) {tab[i+6]=moja; pole=i+7;}
                if(tab[i+3]==f && tab[i+3]==tab[i+6] && tab[i+0]==i+0+49 && pole ==0) {tab[i+0]=moja; pole=i+1;}
                if(tab[i+0]==f && tab[i+0]==tab[i+6] && tab[i+3]==i+3+49 && pole ==0) {tab[i+3]=moja; pole=i+4;}
            }

            //sprawdzanie przekątnych

            if(tab[0]==f && tab[0]==tab[4] && tab[8]==8+49 && pole ==0) {tab[8]=moja; pole=9;}
            if(tab[0]==f && tab[0]==tab[8] && tab[4]==4+49 && pole ==0) {tab[4]=moja; pole=5;}
            if(tab[8]==f && tab[8]==tab[4] && tab[0]==0+49 && pole ==0) {tab[0]=moja; pole=1;}

            if(tab[2]==f && tab[2]==tab[4] && tab[6]==6+49 && pole ==0) {tab[6]=moja; pole=7;}
            if(tab[6]==f && tab[6]==tab[4] && tab[2]==2+49 && pole ==0) {tab[2]=moja; pole=3;}
            if(tab[2]==f && tab[2]==tab[6] && tab[4]==4+49 && pole ==0) {tab[4]=moja; pole=5;}
        }


            //Sprawdzanie, które kolumny i wiersze są zaczęte przez niemoja
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(tab[3*i+j]==niemoja){zajete2[i]=1; zajete2[3+j]=1;}
                }
            }
            //Sprawdzanie, czy któreś przekątne są zajęte
            if(tab[0]==niemoja || tab[4]==niemoja || tab[8]==niemoja) zajete2[6]=1;
            if(tab[2]==niemoja || tab[4]==niemoja || tab[6]==niemoja) zajete2[7]=1;


            //Sprawdzanie, które kolumny i wiersze są zaczęte przez moja
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(tab[3*i+j]==moja){zajete[i]=1; zajete[3+j]=1;}
                }
            }

            //Sprawdzanie, czy któreś przekątne są zajęte
            if(tab[0]==moja || tab[4]==moja || tab[8]==moja) zajete[6]=1;
            if(tab[2]==moja || tab[4]==moja || tab[6]==moja) zajete[7]=1;



            //Teraz sprytna strategia
            //Jeśli jakaś kolumna/wiersz oraz przekątna są napoczęte przez mnie, a nie ma tam znaków przeciwnika, to trzeba postawić tam, żeby zająć
            //zarówno wolną kolumnę/wiersz, jak i przekątną. Wtedy gracz będzie miał już dwa znaki (stary oraz ten nowo dodany) w kolumnie/wierszu oraz na przekątnej.
            for(int i=0;i<3;i++){
                if(zajete[i]==1 && zajete[6]==1 && zajete2[i]==0 && zajete2[6]==0 && pole==0 && tab[3*i+i]==3*i+i+49) {tab[3*i+i]=moja; pole=3*i+i+1;}
                if(zajete[3+i]==1 && zajete[6]==1 && zajete2[3+i]==0 && zajete2[6]==0 && pole==0 && tab[3*i+i]==3*i+i+49) {tab[3*i+i]=moja; pole=3*i+i+1;}
                if(zajete[i]==1 && zajete[7]==1 && zajete2[i]==0 && zajete2[7]==0 && pole==0 && tab[3*i+2-i]==3*i+2-i+49) {tab[3*i+2-i]=moja; pole=3*i+3-i;}
                if(zajete[3+i]==1 && zajete[7]==1 && zajete2[3+i]==0 && zajete2[7]==0 && pole==0 && tab[3*(2-i)+i]==3*(2-i)+i+49) {tab[3*(2-i)+i]=moja; pole=3*(2-i)+1+i;}
            }

            //Analogicznie jak wyżej, tylko z kolumną i wierszem.
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(zajete[i]==1 && zajete[3+j]==1 && zajete2[i]==0 && zajete2[3+j]==0 && tab[3*i+j]==3*i+j+49 && pole ==0) {tab[3*i+j]=moja; pole=3*i+j+1;}
                }
            }


            //Blokowanie sprytnej strategii przeciwnika
            for(int i=0;i<3;i++){
                if(zajete2[i]==1 && zajete2[6]==1 && zajete[i]==0 && zajete[6]==0 && pole==0 && tab[3*i+i]==3*i+i+49) {tab[3*i+i]=moja; pole=3*i+i+1;}
                if(zajete2[3+i]==1 && zajete2[6]==1 && zajete[3+i]==0 && zajete[6]==0 && pole==0 && tab[3*i+i]==3*i+i+49) {tab[3*i+i]=moja; pole=3*i+i+1;}
                if(zajete2[i]==1 && zajete2[7]==1 && zajete[i]==0 && zajete[7]==0 && pole==0 && tab[3*i+2-i]==3*i+2-i+49) {tab[3*i+2-i]=moja; pole=3*i+3-i;}
                if(zajete2[3+i]==1 && zajete2[7]==1 && zajete[3+i]==0 && zajete[7]==0 && pole==0 && tab[3*(2-i)+i]==3*(2-i)+i+49) {tab[3*(2-i)+i]=moja; pole=3*(2-i)+1+i;}
            }

            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(zajete2[i]==1 && zajete2[3+j]==1 && zajete[i]==0 && zajete[3+j]==0 && tab[3*i+j]==3*i+j+49 && pole ==0) {tab[3*i+j]=moja; pole=3*i+j+1;}
                }
            }


            //lepszy jest róg na przeciwko wolnego rogu, a jeśli takiego nie ma, to bok na przeciwko wolnego boku

            if(pole==0 && tab[0]=='1' && tab[8]=='9') {tab[0]=moja; pole=1;}
            if(pole==0 && tab[8]=='9' && tab[0]=='1') {tab[8]=moja; pole=9;}
            if(pole==0 && tab[2]=='3' && tab[6]=='7') {tab[2]=moja; pole=3;}
            if(pole==0 && tab[6]=='7' && tab[2]=='3') {tab[5]=moja; pole=6;}
            if(pole==0 && tab[1]=='2' && tab[7]=='8') {tab[1]=moja; pole=2;}
            if(pole==0 && tab[3]=='4' && tab[5]=='6') {tab[3]=moja; pole=4;}
            if(pole==0 && tab[5]=='6' && tab[3]=='4') {tab[4]=moja; pole=5;}
            if(pole==0 && tab[7]=='8' && tab[1]==2) {tab[7]=moja; pole=8;}


            //jeśli żadna strategia nie przynosi rezultatów, to stawiamy na dowolnym wolnym miejscu,
            //zaczynając od rogów DO POPRAWY

            if(pole==0 && tab[0]=='1') {tab[0]=moja; pole=1;}
            if(pole==0 && tab[8]=='9') {tab[8]=moja; pole=9;}
            if(pole==0 && tab[2]=='3') {tab[2]=moja; pole=3;}
            if(pole==0 && tab[5]=='6') {tab[5]=moja; pole=6;}
            if(pole==0 && tab[1]=='2') {tab[1]=moja; pole=2;}
            if(pole==0 && tab[3]=='4') {tab[3]=moja; pole=4;}
            if(pole==0 && tab[4]=='6') {tab[4]=moja; pole=5;}
            if(pole==0 && tab[7]=='8') {tab[7]=moja; pole=8;}

            try{
                Thread.sleep(3000);
            } catch(InterruptedException exc) {System.out.println("Metoda poczatek została przerwana.");}


            System.out.println("Gracz " + znak + " postawił znacznik na polu " + pole + ".");
            nr++;

            czyWygrana();       //jeśli ktoś wygrał, to powoduje dalejgramy = false

            ruch = !ruch;    //po ruchu zmieniamy gracza, który wykonuje ruch

            System.out.println("Aktualna plansza:");
            wyswietlanieTablicy();

        }
        notify();

        if(dalejgramy) {
            try {
                while (!r) {         //czekaj dopóki nie jest twój ruch
                    wait();
                    r = (znak == 'X') ? ruch : !ruch;
                }
            } catch (InterruptedException exc) {
                System.out.println("Działanie wątku gracza " + znak + " zostało przerwane.");
            }

        }

    }


    synchronized void gracz(char znak) {         //wczytywanie danych gracza


        Scanner scan = new Scanner(System.in);
        int cc;

        boolean r = (znak == 'X') ? ruch : !ruch;

        if(r && dalejgramy) {

            System.out.println("Ruch gracza " + znak + ". W którym miejscu chcesz postawić znak " + znak + "?");

            try {
                cc = scan.nextInt();
                popr = false;

                for (int i = 0; i <= 8; i++)
                    if (cc + 48 == tab[i]) {  //numery char dla cyfr rozpoczynają się od 48
                        tab[i] = znak;
                        popr = true;
                    }
                if(!popr) throw new ArrayIndexOutOfBoundsException();
                else nr++;

            } catch(InputMismatchException | ArrayIndexOutOfBoundsException exc) {       //
                System.out.println("Ruch błędnie wykonany. Sprawdź, czy dane pole nie jest zajęte i czy" +
                        " wpisałeś \nliczbę naturalną od 1 do 9.");
                ruch = !ruch;       //nie chcemy zmieniać gracza, jeśli ruch był błędnie wykonany, dlatego niwelujemy późniejszy efekt
            }


            czyWygrana();       //jeśli ktoś wygrał, to powoduje dalejgramy = false

            ruch = !ruch;    //po ruchu zmieniamy gracza, który wykonuje ruch

            System.out.println("Aktualna plansza:");
            wyswietlanieTablicy();

        }
        notify();

        if(dalejgramy) {
            try {
                while (!r) {         //czekaj dopóki nie jest twój ruch
                    wait();
                    r = (znak == 'X') ? ruch : !ruch;
                }
            } catch (InterruptedException exc) {
                System.out.println("Działanie wątku gracza " + znak + " zostało przerwane.");
            }

        }

    }

    void czyWygrana() {

        wygrana = wygrana || (tab[0]==tab[1] && tab[1]==tab[2]);
        wygrana = wygrana || (tab[3]==tab[4] && tab[4]==tab[5]);
        wygrana = wygrana || (tab[6]==tab[7] && tab[7]==tab[8]);
        wygrana = wygrana || (tab[0]==tab[3] && tab[3]==tab[6]);
        wygrana = wygrana || (tab[1]==tab[4] && tab[4]==tab[7]);
        wygrana = wygrana || (tab[2]==tab[5] && tab[5]==tab[8]);
        wygrana = wygrana || (tab[0]==tab[4] && tab[4]==tab[8]);
        wygrana = wygrana || (tab[2]==tab[4] && tab[4]==tab[6]);

        remis = (tab[0] != 49 && tab[1] != 50 && tab[2] != 51 && tab[3] != 52 && tab[4] != 53 && tab[5] != 54
                && tab[6] != 55 && tab[7] != 56 && tab[8] != 57 && !wygrana);
            //remis zachodzi, gdy wszystkie pola są zajęte i nie ma wygranej
        wygrana = wygrana || remis;

        if(wygrana) dalejgramy=false;

    }


}

