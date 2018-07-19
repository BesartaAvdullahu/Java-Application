 
public class Parameters {
    // madhesia e popullates 10, 50, 100, 200 eksperimenti 1
public int popsize = 10;   
// madhesia e turit 5, 10, 15, 20 eksperimenti 2
public int tournamentSize=10;
public double bestValue;

// 500, 1000 2000, 5000 - eksperimenti 3 
public int[][][] answer ;
public int iterations =500; 
// gjasa per mutation 0.1, 0.3, 0.5, 0.7 eksperimeti 4
public double probMutation = 0.7;
// gjasa per crossover 0.5, 0. 7, 0.8, 1 eksperimenti 5   
public double probCrossover = 0.5;

public long start;    
public long time;    
}
