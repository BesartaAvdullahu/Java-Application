
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class GeneticAlgorithm {

//  The Genetic Algorithm (GA)
//1: popsize desired population size. This is basically llambda. Make it even.
//2: P {} 
//3: for popsize times do
//4: P P[{new random individual}
//5: Best 2
//6: repeat
//7: for each individual Pi 2 P do
//8: AssessFitness(Pi)
//9: if Best = 2 or Fitness(Pi) > Fitness(Best) then
//10: Best Pi
//11: Q {} . Here’s where we begin to deviate from (μ, l)
//12: for popsize/2 times do
//13: Parent Pa SelectWithReplacement(P)
//14: Parent Pb SelectWithReplacement(P)
//15: Children Ca, Cb Crossover(Copy(Pa), Copy(Pb))
//16: Q Q[{Mutate(Ca),Mutate(Cb)}
//17: P Q . End of deviation
//18: until Best is the ideal solution or we have run out of time
//19: return Best
    
    Parameters parameters = new Parameters();
    Functions functions = new Functions();   
      
    public int[][] applyGeneticAlgorithm(int m, int n, int r) throws IOException {
        // step 1 : popsize desired population size.
        int popsize = parameters.popsize;
          // step 2: P<--{}
        int [][][] P = new int[parameters.popsize][m][n];

        //3: for popsize times do
        for (int i = 0; i < popsize; i++) {
            int[][] currentMember = new int[m][n];
                 currentMember=functions.generateAMember(r,m,n);
              
                 //4: P U P[{new random individual}
                 functions.copy(currentMember, P, i,m,n);
                
                       
        }
      
        // step 5: Best<--[]
        int [][] Best = new int[m][n];       
       
        int t = 0;
        Best=P[0];
         //step 6: repeat
       while(t<=parameters.iterations)
       {
        //step 7: for each individual Pi<-- P do
        for (int i = 0; i < popsize; i++) {
           // 8: AssessFitness(Pi)
            int assessFitness = functions.assessFitness(P[i]);
             //9: if Best = [] or Fitness(Pi) > Fitness(Best) then
            if ((Best == null || assessFitness<functions.assessFitness(Best))&&functions.assessFitness(Best)!=0) {
                //10: Best <-- Pi
                Best=P[i];
                              
            }
        }
       // 11: Q <-- {}
         int [][][] Q = new int[parameters.popsize][m][n];
         
         //12: for popsize/2 times do    
         
         for (int k = 0; k < popsize/2; k++) {
            //13: Parent Pa <-- SelectWithReplacement(P)
            // t.selection
           int [][] Pa = functions.tournamentSelection(P,parameters.tournamentSize); 
           //14: Parent Pb <-- SelectWithReplacement(P)
           int [][] Pb = functions.tournamentSelection(P, parameters.tournamentSize);
            // CROSSOVER
            // 15: Children Ca, Cb Crossover(Copy(Pa), Copy(Pb))
              
           Object [][] O = functions.crossover(Pa, Pb, r, parameters.probCrossover);
           int [][] Ca = (int[][])O[0];         
           int [][] Cb = (int[][])O[1];
           //16: Q U Q[{Mutate(Ca),Mutate(Cb)}
           
           Ca = functions.mutation(Ca,r,parameters.probMutation);
           Cb = functions.mutation(Cb,r, parameters.probMutation);
           functions.copyMember(Ca, Q[k]);  
           if(popsize%2==0)
           
           {  functions.copyMember(Cb, Q[k+(popsize/2)]); 
           }
           else
           {  functions.copyMember(Cb, Q[k+(popsize/2)+1]);   }
         
           }
         // 17: P <-- Q
           P = Q;
           t++;
           //18: until Best is the ideal solution or we have run out of time
       }
            //19: return Best
            
            return Best;
        }
        
     
       
    public static void main(String[] args) throws IOException {
          
             long timeElapsed = 0;long startTime=0;long endTime =0;
             GeneticAlgorithm ag = new GeneticAlgorithm();            
             BufferedReader br = null;
             Functions f = new Functions();

             int row=0;int column =0;int numberOfOnes=0;
             int[][] best = null;
             double ll = 0.0;
             double λ = 0.0;
            try {
                  br = new BufferedReader(new FileReader("D:\\Diploma\\MatApp\\build\\classes\\file.txt"));
                  String line;
                  while ((line = br.readLine()) != null) {

                  String[] parts = line.split("-");
                  String sm = (parts[0]); 
                  String sn = (parts[1]); 
                  String sr = (parts[2]); 

                  column = new Integer(sn);
                  row = new Integer(sm);
                  numberOfOnes = new Integer(sr);
                  best = new int[row][column];
                  System.out.println("M="+ row+"x"+column+", r = "+numberOfOnes);

                  startTime  = System.nanoTime();
                   for (int a = 0; a < 20; a++) 
                   {
                       best = ag.applyGeneticAlgorithm(row,column,numberOfOnes);               
                       ll  = ll + f.λ(best);               
                   } 
                    ll = ll/20;
                    λ = f.avgλ(row, ll);
                    endTime = System.nanoTime();
                    timeElapsed  = endTime - startTime;
                    int[][] ans = best;
                    for (int i = 0; i < row; i++) {
                           for (int j = 0; j < column; j++) {
                               System.out.print(ans[i][j]+" ");
                           }
                           System.out.println();
                       }
                    System.out.println("Vlera e funksionit fitnes:  "+λ );
                 System.out.println("Koha e ekzekutimit në sekonda: " + timeElapsed / 1000000/1000);
                 System.out.println();
                }
                System.out.println();
           
       } 
          catch (IOException e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                }
        }
          
         
              
             
    }
}