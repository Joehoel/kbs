package com.ictm2n2.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Gerrit
 */
public class Test {

    // beschikbaarheden
    private static final double beschikbaarheidEis = 99.99, firewall = 0.99999, dbloadbalancer = 0.99999; // beschikbaarheid
                                                                                                          // van de
                                                                                                          // firewall en
                                                                                                          // databaseloadbalancer
    private static final double dbserver1 = 0.90, dbserver2 = 0.95, dbserver3 = 0.98; // beschikbaarheid van de
                                                                                      // databaseservers
    private static final double webserver1 = 0.80, webserver2 = 0.90, webserver3 = 0.95; // beschikbaarheid webservers

    // prijzen
    private static final int firewall_prijs = 2000, dbloadbalancer_prijs = 2000; // prijzen van de firewall en
                                                                                 // databaseloadbalancer
    private static final int dbserver1_prijs = 5100, dbserver2_prijs = 7700, dbserver3_prijs = 12200; // prijzen van de
                                                                                                      // databaseservers
    private static final int webserver1_prijs = 2200, webserver2_prijs = 3200, webserver3_prijs = 5100; // prijzen van
                                                                                                        // de webservers

    // antwoord
    private double antwoord, antwoordBijOntwerp, antwoordFormule;

    // goedkoopste prijs gevonden
    private int[] goedkoopstePrijs = new int[2]; // 1e plek voor goedkoopste ontwerp, 2e plek om een waarde te
                                                 // vergelijken met plek 1.

    // prijs van infrastructuur
    private int prijs = 0;

    // aantal combinaties mogelijk
    private int aantalCombinatiesMogelijk, nFactor = 2, fFactor = 1, scope;

    private String[][] test = new String[1][1];

    private int[] mogelijkheden = new int[6];
    private int[] mogelijkhedenGoedOntwerp = new int[6];
    private int[] goedkoopstePrijsVergelijken = new int[10];

    String path = "./opslaan.txt";
    File file = new File(path);

    public Algorithm() {

    }

    public void calculateAvailabilityPercentage(double AvailabilityPercentage) {

        // berekenen scope
        d: for (scope = 1; scope <= fFactor; scope++) { // net zolang doorgaan totdat het beschikbaarheidspercentage
                                                        // gematched is
            calculateAvailabilityPercentage(fFactor, fFactor, fFactor, fFactor, fFactor, fFactor);
            System.out.println(antwoordFormule);
            if (antwoordFormule < AvailabilityPercentage) {
                fFactor++;
            } else {
                fFactor++;
                test = calculateCombinations(6, fFactor);
                break d;
            }
        }

        a: for (int k = 1; k < nFactor; k++) {

            b: for (int i = 0; i < test.length; i++) {

                c: for (int j = 0; j < test[i].length; j++) {

                    try {

                        int test123 = Integer.parseInt(test[i][j]);
                        mogelijkheden[j] = test123;
                        System.out.print(test[i][j] + "");

                    } catch (NumberFormatException nfe) {

                        System.out.println("Geen int gevonden!");

                    }

                }

                if ((mogelijkheden[0] == 0 && mogelijkheden[1] == 0 && mogelijkheden[2] == 0)
                        || (mogelijkheden[3] == 0 && mogelijkheden[4] == 0 && mogelijkheden[5] == 0)) {
                    System.out.println();
                } else {

                    antwoord = ((firewall * dbloadbalancer)
                            * (1 - ((Math.pow(1 - dbserver1, mogelijkheden[0]))
                                    * (Math.pow(1 - dbserver2, mogelijkheden[1]))
                                    * (Math.pow(1 - dbserver3, mogelijkheden[2]))))
                            * ((1 - ((Math.pow(1 - webserver1, mogelijkheden[3]))
                                    * (Math.pow(1 - webserver2, mogelijkheden[4]))
                                    * (Math.pow(1 - webserver3, mogelijkheden[5])))) * 100));

                    prijs = (int) ((firewall_prijs + dbloadbalancer_prijs) + (dbserver1_prijs * mogelijkheden[0])
                            + (dbserver2_prijs * mogelijkheden[1]) + (dbserver3_prijs * mogelijkheden[2])
                            + (webserver1_prijs * mogelijkheden[3]) + (webserver2_prijs * mogelijkheden[4])
                            + (webserver3_prijs * mogelijkheden[5]));

                    goedkoopstePrijs[1] = prijs; // prijs wordt telkens op plek 2 geplaats om vervolgens te vergelijken
                                                 // met plek 1.

                    if (antwoord >= AvailabilityPercentage && goedkoopstePrijs[0] == 0) { // initialiseren van plek 0 in
                                                                                          // array voor vergelijken
                        goedkoopstePrijs[0] = prijs;
                        mogelijkhedenGoedOntwerp[0] = mogelijkheden[0];
                        mogelijkhedenGoedOntwerp[1] = mogelijkheden[1];
                        mogelijkhedenGoedOntwerp[2] = mogelijkheden[2];
                        mogelijkhedenGoedOntwerp[3] = mogelijkheden[3];
                        mogelijkhedenGoedOntwerp[4] = mogelijkheden[4];
                        mogelijkhedenGoedOntwerp[5] = mogelijkheden[5];
                    }

                    if (goedkoopstePrijs[1] < goedkoopstePrijs[0] && antwoord >= AvailabilityPercentage) { // kijken of
                                                                                                           // een
                                                                                                           // gevonden
                                                                                                           // mogelijkheid
                                                                                                           // goedkoper
                                                                                                           // is als
                                                                                                           // plek 0 in
                                                                                                           // de array
                                                                                                           // qua prijs
                        goedkoopstePrijs[0] = goedkoopstePrijs[1];
                        antwoordBijOntwerp = antwoord;
                        mogelijkhedenGoedOntwerp[0] = mogelijkheden[0];
                        mogelijkhedenGoedOntwerp[1] = mogelijkheden[1];
                        mogelijkhedenGoedOntwerp[2] = mogelijkheden[2];
                        mogelijkhedenGoedOntwerp[3] = mogelijkheden[3];
                        mogelijkhedenGoedOntwerp[4] = mogelijkheden[4];
                        mogelijkhedenGoedOntwerp[5] = mogelijkheden[5];

                        schrijfResultaat(mogelijkhedenGoedOntwerp[0], mogelijkhedenGoedOntwerp[1],
                                mogelijkhedenGoedOntwerp[2], mogelijkhedenGoedOntwerp[3], mogelijkhedenGoedOntwerp[4],
                                mogelijkhedenGoedOntwerp[5]);
                    }

                    System.out.println(" ---> " + antwoord + "% voor €" + prijs + ",-");

                    aantalCombinatiesMogelijk++;
                }
            }
            goedkoopstePrijsVergelijken[k - 1] = goedkoopstePrijs[0];
            System.out.println(goedkoopstePrijs[0] + " " + mogelijkhedenGoedOntwerp[0] + ""
                    + mogelijkhedenGoedOntwerp[1] + "" + mogelijkhedenGoedOntwerp[2] + "" + mogelijkhedenGoedOntwerp[3]
                    + "" + mogelijkhedenGoedOntwerp[4] + "" + mogelijkhedenGoedOntwerp[5]);

            for (int ttt : goedkoopstePrijsVergelijken) {
                System.out.println(ttt);
            }

            if (nFactor == 2) {
                nFactor++;
            } else if (goedkoopstePrijsVergelijken[k - 2] == goedkoopstePrijsVergelijken[k - 1]
                    && (goedkoopstePrijsVergelijken[k - 2] != 0 || goedkoopstePrijsVergelijken[k - 1] != 0)) {
                break;
            } else {
                nFactor++;
            }
            fFactor++;
            test = calculateCombinations(6, fFactor);

        }

        try {

            boolean append = true;

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));

            writer.write("Aantal combinaties geprobeerd: " + aantalCombinatiesMogelijk);
            writer.newLine();
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String[][] calculateCombinations(int R, int N) {

        int limit = (int) StrictMath.pow(N, R); // limiet berekenen van de formule
        String[][] result = new String[limit][R]; // resultaat array aanmakne

        for (int i = 0; i < limit; i++) { // over alle mogelijkheden iterateren

            String base = Long.toString(i, N); // converten naar een base
            StringBuilder intermediate = new StringBuilder(R); // StringBuilder houdt onze tijdelijke waardes

            for (int sub = R - base.length(); sub > 0; sub--) { // vul de waarde vanaf het begin in met nullen indien
                                                                // nodig
                intermediate.append('0');
            }

            intermediate.append(base); // voeg het nummer toe
            result[i] = intermediate.toString().split(""); // toevoegen aan resultaat
        }
        return result;
    }

    public void schrijfResultaat(int a, int b, int c, int d, int e, int f) {

        try {

            boolean append = true;

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));

            writer.write("======================================");
            writer.newLine();
            writer.write("1 × Firewall(99.999%) voor €2000,-");
            writer.newLine();
            writer.write("1 × Loadbalancer(99.999%) voor €2000,-");
            writer.newLine();
            writer.newLine();
            writer.write("Databaseservers:");
            writer.newLine();
            writer.write(a + " × HAL9001DB(90%) voor €" + a * dbserver1_prijs + ",-");
            writer.newLine();
            writer.write(b + " × HAL9002DB(95%) voor €" + b * dbserver2_prijs + ",-");
            writer.newLine();
            writer.write(c + " × HAL9003DB(98%) voor €" + c * dbserver3_prijs + ",-");
            writer.newLine();
            writer.newLine();
            writer.write("Webservers:");
            writer.newLine();
            writer.write(d + " × HAL9001W(80%) voor €" + d * webserver1_prijs + ",-");
            writer.newLine();
            writer.write(e + " × HAL9002W(90%) voor €" + e * webserver2_prijs + ",-");
            writer.newLine();
            writer.write(f + " × HAL9003W(95%) voor €" + f * webserver3_prijs + ",-");
            writer.newLine();
            writer.newLine();
            writer.write("totaal: €" + prijs + ",- " + "(" + antwoord + "%)");
            writer.newLine();
            writer.write("======================================");
            writer.newLine();
            writer.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void calculateAvailabilityPercentage(int db1, int db2, int db3, int web1, int web2, int web3) {
        antwoordFormule = ((0.99999 * 0.99999)
                * (1 - ((Math.pow(1 - 0.90, db1)) * (Math.pow(1 - 0.95, db2)) * (Math.pow(1 - 0.98, db3))))
                * ((1 - ((Math.pow(1 - 0.80, web1)) * (Math.pow(1 - 0.90, web2)) * (Math.pow(1 - 0.95, web3)))) * 100));
    }

    public int getPrijs() {
        return this.goedkoopstePrijs[0];
    }

    public double getAvailabilityPercentage() {
        return this.antwoordBijOntwerp;
    }

    public int[] getMogelijkhedenGoedOntwerp() {
        return mogelijkhedenGoedOntwerp;
    }

    public static int getDbserver1_prijs() {
        return dbserver1_prijs;
    }

    public static int getDbserver2_prijs() {
        return dbserver2_prijs;
    }

    public static int getDbserver3_prijs() {
        return dbserver3_prijs;
    }

    public static int getWebserver1_prijs() {
        return webserver1_prijs;
    }

    public static int getWebserver2_prijs() {
        return webserver2_prijs;
    }

    public static int getWebserver3_prijs() {
        return webserver3_prijs;
    }

    public static double getDbserver1() {
        return dbserver1;
    }

    public static double getDbserver2() {
        return dbserver2;
    }

    public static double getDbserver3() {
        return dbserver3;
    }

    public static double getWebserver1() {
        return webserver1;
    }

    public static double getWebserver2() {
        return webserver2;
    }

    public static double getWebserver3() {
        return webserver3;
    }

}
