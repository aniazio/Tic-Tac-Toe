import java.util.Random;
import java.util.Scanner;

public class OiXlos {

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        char[] tab = {'1','2','3','4','5','6','7','8','9'};


        System.out.println("\t \t|\t \t|\t \t\n\t" + tab[0] + "\t|\t" + tab[1] + "\t|\t" + tab[2] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("  ---------------------");
        System.out.println("\t \t|\t \t|\t \t\n\t" + tab[3] + "\t|\t" + tab[4] + "\t|\t" + tab[5] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("  ---------------------");
        System.out.println("\t \t|\t \t|\t \t\n\t" + tab[6] + "\t|\t" + tab[7] + "\t|\t" + tab[8] + "\t\n\t \t|\t \t|\t \t");
        System.out.println("\nOto plansza do gry w kółko i krzyżyk. Numerami oznaczono kolejne pozycje.\nTwoje ruchy " +
                "będą zaznaczane krzyżykami. Wybór gracza rozpoczynającego grę dokonuje się losowo.");
        Random r = new Random();
        boolean ruch = r.nextBoolean(); //true, gdy gracz wykonuje ruch

        boolean dalejgramy=true, popr, wygrana = false, remis = false;
        int cc,i,pole=0, nr = 0, l, w, inteli;
        char f;
        popr = false;

        System.out.println("Czy chcesz, aby komputer grał całkowicie losowo? Jeśli tak wpisz 1, w innym razie wpisz 0.");
        inteli = 1;

        while(!popr){
            int intel = scan.nextInt(); //deklaracja zmiennej o zasięgu lokalnym (tylko w pętli while)
            if(intel==0 || intel==1) popr=true;
            else System.out.println("Wpisałeś złą wartość, spróbuj jeszcze raz.");
            inteli = intel;
        }


        if(ruch) System.out.println("Ty zaczynasz.");
        else System.out.println("Jako pierwszy ruch wykonuje komputer.");



        while(dalejgramy) {
            System.out.println();
            if(ruch) {
                System.out.println("Twój ruch. W którym miejscu chcesz postawić krzyżyk?");
                cc = scan.nextInt();
                popr=false;
                for(i=0;i<=8;i++) if(cc+48==tab[i]) {  //numery char dla cyfr rozpoczynają się od 48
                    tab[i]='X';
                    popr=true;
                }
                if(!popr) {
                    System.out.println("Ruch błędnie wykonany. Sprawdź, czy dane pole nie jest zajęte i czy" +
                            " wpisałeś \nliczbę naturalną od 1 do 9.");
                    ruch = !ruch;
                }
                else nr++;

            }
            else {

                pole = 0;
                if(inteli==0) {

                    for (w = 0; w < 2; w++) {
                        if (w == 0) f = 'O';
                        else f = 'X'; //najpierw uzupełnia swoje O do wygranej, a jeśli nie może, to blokuje

                        for (i = 0; i < 3; i++) {
                            // sprawdzanie wierszy
                            if (tab[3 * i + 0] == f && tab[3 * i + 0] == tab[3 * i + 1] && tab[3 * i + 2] == 3 * i + 2 + 49 && pole == 0) {
                                tab[3 * i + 2] = 'O';
                                pole = 3 * i + 3;
                            }
                            if (tab[3 * i + 1] == f && tab[3 * i + 1] == tab[3 * i + 2] && tab[3 * i + 0] == 3 * i + 0 + 49 && pole == 0) {
                                tab[3 * i + 0] = 'O';
                                pole = 3 * i + 1;
                            }
                            if (tab[3 * i + 0] == f && tab[3 * i + 0] == tab[3 * i + 2] && tab[3 * i + 1] == 3 * i + 1 + 49 && pole == 0) {
                                tab[3 * i + 1] = 'O';
                                pole = 3 * i + 2;
                            }

                            // sprawdzanie kolumn
                            if (tab[i + 0] == f && tab[i + 0] == tab[i + 3] && tab[i + 6] == i + 6 + 49 && pole == 0) {
                                tab[i + 6] = 'O';
                                pole = i + 7;
                            }
                            if (tab[i + 3] == f && tab[i + 3] == tab[i + 6] && tab[i + 0] == i + 0 + 49 && pole == 0) {
                                tab[i + 0] = 'O';
                                pole = i + 1;
                            }
                            if (tab[i + 0] == f && tab[i + 0] == tab[i + 6] && tab[i + 3] == i + 3 + 49 && pole == 0) {
                                tab[i + 3] = 'O';
                                pole = i + 4;
                            }
                        }

                        if (tab[0] == f && tab[0] == tab[4] && tab[8] == 8 + 49 && pole == 0) {
                            tab[8] = 'O';
                            pole = 9;
                        }
                        if (tab[0] == f && tab[0] == tab[8] && tab[4] == 4 + 49 && pole == 0) {
                            tab[4] = 'O';
                            pole = 5;
                        }
                        if (tab[8] == f && tab[8] == tab[4] && tab[0] == 0 + 49 && pole == 0) {
                            tab[0] = 'O';
                            pole = 1;
                        }

                        if (tab[2] == f && tab[2] == tab[4] && tab[6] == 6 + 49 && pole == 0) {
                            tab[6] = 'O';
                            pole = 7;
                        }
                        if (tab[6] == f && tab[6] == tab[4] && tab[2] == 2 + 49 && pole == 0) {
                            tab[2] = 'O';
                            pole = 3;
                        }
                        if (tab[2] == f && tab[2] == tab[6] && tab[4] == 4 + 49 && pole == 0) {
                            tab[4] = 'O';
                            pole = 5;
                        }
                    }

                }

                if(pole==0) {
                    int tu = r.nextInt(9-nr); //deklaracja zmiennej o zasięgu lokalnym (blok kodu po if)
                    l=0;
                    for(i=0;i<9;i++){
                        if(tab[i]==i+49) {if(l==tu){tab[i]='O'; pole=i+1;} l++;}
                    }
                }

                System.out.println("Komputer postawił kółko na polu " + pole + ".");
                nr++;
            }

            System.out.println("Aktualna plansza:");
            System.out.println("\t \t|\t \t|\t \t\n\t" + tab[0] + "\t|\t" + tab[1] + "\t|\t" + tab[2] + "\t\n\t \t|\t \t|\t \t");
            System.out.println("  ---------------------");
            System.out.println("\t \t|\t \t|\t \t\n\t" + tab[3] + "\t|\t" + tab[4] + "\t|\t" + tab[5] + "\t\n\t \t|\t \t|\t \t");
            System.out.println("  ---------------------");
            System.out.println("\t \t|\t \t|\t \t\n\t" + tab[6] + "\t|\t" + tab[7] + "\t|\t" + tab[8] + "\t\n\t \t|\t \t|\t \t");

            wygrana = wygrana || (tab[0]==tab[1] && tab[1]==tab[2]);
            wygrana = wygrana || (tab[3]==tab[4] && tab[4]==tab[5]);
            wygrana = wygrana || (tab[6]==tab[7] && tab[7]==tab[8]);
            wygrana = wygrana || (tab[0]==tab[3] && tab[3]==tab[6]);
            wygrana = wygrana || (tab[1]==tab[4] && tab[4]==tab[7]);
            wygrana = wygrana || (tab[2]==tab[5] && tab[5]==tab[8]);
            wygrana = wygrana || (tab[0]==tab[4] && tab[4]==tab[8]);
            wygrana = wygrana || (tab[2]==tab[4] && tab[4]==tab[6]);
            remis = (tab[0] != 49 && tab[1] != 50 && tab[2] != 51 && tab[3] != 52 && tab[4] != 53 && tab[5] != 54
                    && tab[6] != 55 && tab[7] != 56 && tab[8] != 57);
            wygrana = wygrana || remis;

            if(wygrana) dalejgramy=false;
            ruch = !ruch;

        }

        if(remis) System.out.println("Remis!");
        else if(!ruch) System.out.println("Gratulacje, wygrałeś!"); else System.out.println("Niestety, wygrał Twój przeciwnik. Może" +
                " następnym razem pójdzie ci lepiej.");

    }
}
