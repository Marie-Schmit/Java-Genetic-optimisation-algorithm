/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marie
 */

import java.util.ArrayList;

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
    public static StringBuffer select(ArrayList<StringBuffer> population, String targetString){
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
        return bestFit;
    }
    
    //Update population: create 100 of mtated copies of the selected string
    public static ArrayList updatePopulation(ArrayList previousGeneration, String targetString){
        StringBuffer selectedString = select(previousGeneration, targetString);
        ArrayList nextGeneration = createPopulation(selectedString);
        return nextGeneration;
    }
    
    
}
