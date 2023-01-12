/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marie
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap;

public class Evolve {
    static int numberIterations;
    static int popSize;
    static double mutationRate;
    
    //Constructor
    public Evolve() {
        this.numberIterations = 50;
        this.popSize = 100;
        this.mutationRate = 0.05;
    }
    
    //Override constructor to pass parameters
    public Evolve(int nbIterations, int popSize, double mutRate) {
        numberIterations = nbIterations;
        popSize = popSize;
        mutationRate = mutRate;
    }
    
    //Get a random character
    public static char getRandomChar(){
        //Initialisation of random character
        char randomCharacter;
        //Get a random value between 0 and 26
        int randomValue = (int)(Math.random()*27);
        
        //Blank space if value is 0
        if(randomValue == 0){
            randomCharacter = (char) 32;
        }
        //Get a random character (alphabet letter) corresponding to the random value
        else{
            randomCharacter = (char) (randomValue+64);
        }
        return randomCharacter;
    }
    
    //Create a random string with the same size as the target string
    public static StringBuffer createRandomString(String targetString){
        int lengthTarget = targetString.length();
        int i;
        
        //CReate new string buffer
        StringBuffer initialString = new StringBuffer(lengthTarget);
        
        //Create random string
        for (i=0; i<lengthTarget;i++){
            initialString.append(getRandomChar());
        }
        return (initialString);     
    }
    
    //Mutate a string at the given probability per character
    public static StringBuffer mayMutate(StringBuffer randomString){
        int i;
        int len = randomString.length();
        //Create a copy of the string, so all the copies are independant
        StringBuffer copyString = new StringBuffer(randomString);
        
        
        //Mutate or not each character
        for (i = 0; i < len; i++){
            //Calculation of prabability
            double probability = Math.random();
            
            if (probability <= mutationRate){ //There is a mutation of the character
                copyString.setCharAt(i, getRandomChar());
            }
        }
        return copyString;
    }
    
    
    //Method to create an initial population of popSize random individuals
        //Create copies of the initial random string
    public static ArrayList<StringBuffer> createPopulation(StringBuffer randomString){
        //Create an ArrayList to store the population
        ArrayList<StringBuffer> createdPop = new ArrayList<StringBuffer>();
        //Add the first individual of the population
        createdPop.add(randomString);
        int i;
        
        //Store mutate sentence
        StringBuffer mutateString = new StringBuffer();
        
        //Create mutated copies of original random string
        for (i = 0; i < popSize; i++){
            mutateString = mayMutate(randomString);
            createdPop.add(new StringBuffer(mutateString));
        }
        return createdPop;
    }
    
    //Score each string by the number of letters that are correct in the correct position
    public static Object[] select(ArrayList<StringBuffer> population, String targetString){
        ArrayList createdPop = new ArrayList<StringBuffer>();
        
        int numberStrings = population.size(); //Number of strings in the population
        int score = 0; //Number of correct letter in the correct position for each string
        int maxScore = 0; //Maximal score
        
        //Best fit
        StringBuffer bestFit = new StringBuffer();
        //Compteurs
        int i;
        int j;
        
        //For each string in the population
        for (i = 0; i < numberStrings; i++){
            StringBuffer individual = population.get(i);
            score = 0;
            //For each character in the string
            for (j = 0; j < individual.length(); j++){
                char testCharacter = individual.charAt(j);
                char targetCharacter = targetString.charAt(j);
                
                //If characters of string and target strings ar equal, score increases from 1
                if (testCharacter == targetCharacter){
                    score ++;
                }
            }
            
            //If score is greater than maximal score, replace maximal score by score and actualise the best fit
            if (score > maxScore){
                bestFit = individual;
                maxScore = score;
            }
        }
        
        //Pair containing best score and coresponding best fit
        return new Object[]{bestFit, maxScore};
    }
    
    //Update population: create 100 of mutated copies of the selected string
    public static ArrayList updatePopulation(ArrayList previousGeneration, String targetString){
        //Selected string
        StringBuffer selectedString = (StringBuffer)select(previousGeneration, targetString)[0];
        
        //Create next generation from selected string
        ArrayList nextGeneration = createPopulation(selectedString);
        
        return nextGeneration;
    }
    
    //Creation of a generation loop. 
    //Create an initial population, update it until perfect score or maximum number of generation are hit.
    public static StringBuffer evolve(String targetString){
        //Initial population
        ArrayList previousPop = createPopulation(createRandomString(targetString));
        //Updated population
        ArrayList nextPop = new ArrayList();
        
        //Perfect score: every letter of the targetted string correspond
        int perfectScore = targetString.length();
        int score = (int)select(previousPop, targetString)[1]; //Score of the initial population
        int nbGenerations = 1; //Number of generations 
        
        //Stop is any string has a perfect score, or if the maximum number of generations has been reached
        do{
            if(nbGenerations == 1){
                //The previous population is the random one
                //Update generation
                nextPop = updatePopulation(previousPop, targetString);
                //Save updated score
                score = (int)select(previousPop, targetString)[1];
                //Count iterations
                nbGenerations ++;
            }
            else{
                //Update generation
            nextPop = updatePopulation(nextPop, targetString);
            //Save updated score
            score = (int)select(nextPop, targetString)[1];
            //Count iterations
            nbGenerations ++;
            
            System.out.println("score: " + score + "\n best individual: " + (StringBuffer)select(nextPop, targetString)[0] + "\n" + "Iterations: " + nbGenerations);
            }
        } while ((score < perfectScore) | (nbGenerations < numberIterations));
        
        return((StringBuffer)select(nextPop, targetString)[0]);
    }
    
    
}
