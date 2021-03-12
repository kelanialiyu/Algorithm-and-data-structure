import java.util.*;
/** 
*@author Aliyu Kelani
* Second version of the pizzaria that uses the quick termination strategy
* it uses a sorted list of pizzas
* it reduces every search algorithm to a complexity of n2 but it doesnt give the best solution
* 
 */
public class Pizzariav3{
    // Etart of the main method
    public static void main(String[] args){
        /* Code For Input File Processing */
        Scanner input= new Scanner(System.in);
        // declaring variables
        int totalPizza =0;
        int teamOfTwos=0;
        int teamOfThrees=0;
        int teamOfFours=0;
        Pizza[] pizzas=null;
        
        // tranversing through input file
        while(input.hasNext()){
            //initializing variables
            totalPizza = input.nextInt();
            teamOfTwos = input.nextInt();
            teamOfThrees = input.nextInt();
            teamOfFours = input.nextInt();
            pizzas = new Pizza[totalPizza];
            
            // loop for creating and adding each pizza to pizzas array
            for (int i = 0; i < totalPizza; i++) {
                int noOfIngredients = input.nextInt();
                String[] ingredients = new String[noOfIngredients];
                
                //loop for adding ingredients
                for (int j = 0; j < noOfIngredients; j++) {
                    ingredients[j] = input.next();
                }
                //creating a pizza with its ingredients and position
                pizzas[i] = new Pizza(i,ingredients);
            }
        }
        
        /** Code for creating a pizza hut and running the optimizer*/
        Pizzariav3 pizzahut= new Pizzariav3(totalPizza,teamOfTwos,teamOfThrees,teamOfFours,pizzas);
        pizzahut.optimize();
    }
    // End of the main method
    
    /* Codes  for the Pizzaria class */
    int totalPizza;
    int teamOfTwos;
    int teamOfThrees;
    int teamOfFours;
    ArrayList<Pizza> pizzas;
    
    // The Pizzaria Constructor
    Pizzariav3(int totalPizza,int teamOfTwos, int teamOfThrees, int teamOfFours ,Pizza[] pizzas){
        this.totalPizza = totalPizza;
        this.teamOfTwos= teamOfTwos;
        this.teamOfThrees = teamOfThrees;
        this.teamOfFours = teamOfFours;
        Arrays.sort(pizzas);
        this.pizzas = new ArrayList(Arrays.asList(pizzas));
    }
    // End of Pizzaria Constructor
    
    // Start of totalPizza function for getting the total number of pizza to be delivered before delivery
    int totalPizza(){
        int sum=0;
        if((2 * teamOfTwos)+sum <= this.totalPizza){
            sum+=2*teamOfTwos;
        }
        else{
            while(sum+2<= this.totalPizza){
                sum+=2;
            }
        }
        if((3 * teamOfThrees)+sum <= this.totalPizza){
            sum+=3*teamOfThrees;
        }
        else{
            while(sum+3<= this.totalPizza){
                sum+=3;
            }
        }
        if((4 * teamOfFours)+sum <= this.totalPizza){
            sum+=4*teamOfFours;
        }
        else{
            while(sum+4<= this.totalPizza){
                sum+=4;
            }
        }
        return sum;
    }
    //End of totalPizza function
    
    //Start of the optimize function
    /**
     *  print out an optimized way of delivering Pizza to teams of 2,3,4
     *  starts by completely delivering to teams of 2 then 3 and finally 4
     */
    public void optimize(){
        System.out.println(totalPizza());
        while(teamOfTwos>0 && pizzas.size()>=2){
            Pizza[] bestTwo = this.selectBestTwo();
            System.out.printf("%d %s %s \n", 2,bestTwo[0].name,bestTwo[1].name);
            pizzas.removeAll(Arrays.asList(bestTwo));
            teamOfTwos--;
        }
        while(teamOfThrees>0 && pizzas.size()>=3){
            Pizza[] bestThree = this.selectBestThree();
            System.out.printf("%d %s %s %s \n", 3,bestThree[0].name,bestThree[1].name,bestThree[2].name);
            pizzas.removeAll(Arrays.asList(bestThree));
            teamOfThrees--;
        }
        while(teamOfFours>0 && pizzas.size()>=4){
            Pizza[] bestFour = this.selectBestFour();
            System.out.printf("%d %s %s %s %s \n", 4,bestFour[0].name,bestFour[1].name,bestFour[2].name,bestFour[3].name);
            pizzas.removeAll(Arrays.asList(bestFour));
            teamOfFours--;
        }
    }
    // End of optimize function
    
    // Start of the selectBestTwo 
    //function which returns two pizza with maximum distinct ingredients by searching exhaustively
    Pizza[] selectBestTwo(){
        int max = 0;
        int firstPizza=0;
        int secondPizza=0;
        for (int i = 0; i < pizzas.size()-1; i++) {
            boolean isconsecutive=true;
            for (int j = i+1; j < pizzas.size(); j++) {
               if(totalIngredient(pizzas.get(i),pizzas.get(j))>max){
                   firstPizza=i;
                   secondPizza=j;
                   max = totalIngredient(pizzas.get(i),pizzas.get(j));
               }
               if(isconsecutive && (pizzas.get(i).noOfIngredient +pizzas.get(j).noOfIngredient)<=max){
                    return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza)}; 
               }
               isconsecutive=false;
            }
        }
        return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza)};
    }
    // End of selectBestTwo Function
    
    //Start of selectBestThree function
    //function which returns three pizza with maximum distinct ingredients by searching exhaustively
    Pizza[]selectBestThree(){
        int max = 0;
        int firstPizza=0;
        int secondPizza=0;
        int thirdPizza=0;
        for (int i = 0; i < pizzas.size()-2; i++) {
            boolean isconsecutive=true;
            int j = i+1;
            for (int k = j+1; k < pizzas.size(); k++) {
                if(totalIngredient(pizzas.get(i),pizzas.get(j),pizzas.get(k))>max){
                    firstPizza=i;
                    secondPizza=j;
                    thirdPizza=k;
                    max = totalIngredient(pizzas.get(i),pizzas.get(j),pizzas.get(k));
                }
                if(isconsecutive && max >= ((pizzas.get(i).noOfIngredient +pizzas.get(j).noOfIngredient +pizzas.get(j).noOfIngredient)) ){
                    return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza),pizzas.get(thirdPizza)};
                }
                isconsecutive = false;
            }
            
        }
        return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza),pizzas.get(thirdPizza)};
    }
    // End of selectBestThree function
    
    // Start of selectBestFour Function
    //function which returns four pizza with maximum distinct ingredients by searching exhaustively
    Pizza[] selectBestFour(){
        int max = 0;
        int firstPizza=0;
        int secondPizza=0;
        int thirdPizza=0;
        int fourthPizza=0;
        for (int i = 0; i < pizzas.size()-3; i++) {
            boolean isconsecutive = true;
            int j = i+1;
            for (int k = j+1; k < pizzas.size()-1; k++) {
                int l = k+1;  
                if(totalIngredient(pizzas.get(i),pizzas.get(j),pizzas.get(k),pizzas.get(l))>max){
                    firstPizza=i;
                    secondPizza=j;
                    thirdPizza=k;
                    fourthPizza=l;
                    max = totalIngredient(pizzas.get(i),pizzas.get(j),pizzas.get(k),pizzas.get(l));
                }
                if(isconsecutive && max >= ((pizzas.get(i).noOfIngredient +pizzas.get(j).noOfIngredient +pizzas.get(j).noOfIngredient + pizzas.get(k).noOfIngredient)) ){
                    return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza),pizzas.get(thirdPizza),pizzas.get(fourthPizza)};
                }
                isconsecutive = false;
            } 
        }
        return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza),pizzas.get(thirdPizza),pizzas.get(fourthPizza)};
    }
    // End of selectBestFour Function
    
    //Start of totalIngredient function
    // which the total distinct ingredient in 2 0r more pizzas
    int totalIngredient(Pizza ...p2){
        HashSet<String> tmp = new HashSet<>();
        for (Pizza pizza : p2) {
            tmp.addAll(pizza.ingredients);
        }
        return tmp.size();
    }
    //End of totalIngredient function
}
//End of Pizzaria class

/**
 * Pizza class
 * creates a comparable pizza which can be sorted based on the number of pizza
*/
class Pizza implements Comparable<Pizza>{
    int name;
    int noOfIngredient;
    HashSet<String> ingredients;
    
    public Pizza(int name, String[] ingredients){
            this.name = name;
            this.noOfIngredient = ingredients.length;
            this.ingredients = new HashSet<>();
            this.ingredients.addAll(Arrays.asList(ingredients));

    }

    @Override
    public int compareTo(Pizza p2) {
      if(this.noOfIngredient > p2.noOfIngredient){
          return -1;
      }
      else if(this.noOfIngredient == p2.noOfIngredient){
          if(this.name > p2.name){
                return 1;
            }
            else {
                return -1;
            }
      }
      else {
          return 1;
      }
    }

}