import java.util.*;
public class Pizzaria{
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
    }
    int teamOfTwos;
    int teamOfThrees;
    int teamOfFours;
    ArrayList<Pizza> pizzas;
    public Pizzaria(){
        
    }
    public void Optimizer(){
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
    Pizza[] selectBestTwo(){
        int max = 0;
        int firstPizza=0;
        int secondPizza=0;
        for (int i = 0; i < pizzas.size()-1; i++) {
            for (int j = i+1; j < pizzas.size(); j++) {
               if(totalIngredient(pizzas.get(i),pizzas.get(j))>max){
                   firstPizza=i;
                   secondPizza=j;
               } 
            }
        }
        return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza)};
    }
    Pizza[]selectBestThree(){
        int max = 0;
        int firstPizza=0;
        int secondPizza=0;
        int thirdPizza=0;
        for (int i = 0; i < pizzas.size()-2; i++) {
            for (int j = i+1; j < pizzas.size()-1; j++) {
                for (int k = j+1; k < pizzas.size(); k++) {
                    if(totalIngredient(pizzas.get(i),pizzas.get(j),pizzas.get(k))>max){
                        firstPizza=i;
                        secondPizza=j;
                        thirdPizza=k;
                    }
                } 
            }
        }
        return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza),pizzas.get(thirdPizza)};
    }
    Pizza[] selectBestFour(){
        int max = 0;
        int firstPizza=0;
        int secondPizza=0;
        int thirdPizza=0;
        int fourthPizza=0;
        for (int i = 0; i < pizzas.size()-3; i++) {
            for (int j = i+1; j < pizzas.size()-2; j++) {
                for (int k = j+1; k < pizzas.size()-1; k++) {
                    for (int l = k+1; l <pizzas.size() ; l++) {
                        if(totalIngredient(pizzas.get(i),pizzas.get(j),pizzas.get(k),pizzas.get(l))>max){
                            firstPizza=i;
                            secondPizza=j;
                            thirdPizza=k;
                            fourthPizza=l;
                        }
                    }
                    
                } 
            }
        }
        return new Pizza[]{pizzas.get(firstPizza),pizzas.get(secondPizza),pizzas.get(thirdPizza),pizzas.get(fourthPizza)};
    }
    int totalIngredient(Pizza ...p2){
        HashSet<String> tmp = new HashSet<>();
        for (Pizza pizza : p2) {
            tmp.addAll(pizza.ingredients);
        }
        return tmp.size();
    }
}

class Pizza implements Comparable<Pizza>{
    String name;
    int noOfIngredient;
    HashSet<String> ingredients;
    public Pizza(String name,int noOfIngredient, String[] ingredients){
            this.name = name;
            this.noOfIngredient = noOfIngredient;
            this.ingredients = new HashSet<>();
            this.ingredients.addAll(Arrays.asList(ingredients));

    }

    @Override
    public int compareTo(Pizza p2) {
      if(this.noOfIngredient > p2.noOfIngredient){
          return 1;
      }
      else if(this.noOfIngredient == p2.noOfIngredient){
          return 0;
      }
      else {
          return -1;
      }
    }

}