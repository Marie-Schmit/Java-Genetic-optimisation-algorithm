/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marie
 * Date 12/01/2023
 * Implementation of Weasel program for Bonus practical with objects and classes
 */
import java.util.ArrayList;

public class Weasel {
    //Constructor
    public Weasel() {
    }

        /*
    public static int numberIteration;
    //Number of copies
    public static int popSize;
    //Mutation rate
    double mutationRate = 0.05;
        */
    public static String targetString = "ME THINKS IT IS LIKE A WEASEL";
    
    
    //Main
    public static void main(String args[]) {
        //Ask number of iterations and maximum number of copies
        int numberIteration = 30;
        int popSize = 100;
        double mutationRate = 0.05;
                
        //Creation of Weasel object
        Weasel myWeasel = new Weasel();
        
        
        //Tests
        //System.out.print(Evolve.createRandomString(targetString));
        Evolve test = new Evolve();
        ArrayList population = Evolve.createPopulation(Evolve.createRandomString(targetString));

        //System.out.println(population);
        System.out.println("Elements of ArrayList are:");
        for (int i = 0; i < population.size(); i++) {
            System.out.println(population.get(i) + " ");
        }
        
        //System.out.println("_______________________");
        //System.out.println(Evolve.select(population, targetString));
        
        //System.out.println("_______________________");
        //System.out.println(Evolve.updatePopulation(population, targetString));
        
    }
}
