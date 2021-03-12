
import java.util.*;

public class Traffic{
    public static void main(String[] args){
        int totalSimulationDuration;
        int noOfIntersection;
        int noOfStreets;
        int noOfCars;
        int totalCarLength=0; // to be used as a factor variable
        Intersection[] intersection; // for holding a all intersection
        HashMap<String,Street> streets = new HashMap<>(); // for holding all streets
        final double REPETITION =71.4;
        double repetition;
        double bonusIntercept;
        //processing input file
        
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
           totalSimulationDuration = input.nextInt();
           noOfIntersection = input.nextInt();
           intersection = new Intersection[noOfIntersection]; // initalizing the intersection array
           noOfStreets = input.nextInt();
           noOfCars = input.nextInt();
          /* not needed bonusPoint = */int bonusPoint=input.nextInt();
          
          // processing streets
            for (int i = 0; i < noOfStreets; i++) {
                /* not needed streetStart = */input.next();
                int streetEnd = input.nextInt();
                String streetName = input.next();
                int streetLength = input.nextInt();
                Street street = new Street(streetName,streetLength);
                streets.put(streetName, street);
                if(intersection[streetEnd]==null){
                    intersection[streetEnd]= new Intersection(streetEnd);
                }
                intersection[streetEnd].addStreet(street);
            }
          //processing cars
            for (int i = 0; i < noOfCars; i++) {
                int noOfStreet = input.nextInt();
                for (int j = 0; j < noOfStreet; j++) {
                    String currentStreet= input.next();
                    streets.get(currentStreet).addCar();
                    totalCarLength += streets.get(currentStreet).length;
                }
                
            }
           double factor = totalSimulationDuration*1.0;
            factor /= (noOfCars);
            factor /= REPETITION;
            repetition = totalCarLength;
            repetition/= (noOfCars*1.0);
            bonusIntercept= bonusPoint*1.0/noOfCars;
            System.out.printf("the calculated repetition is %.2f for %d cars and totalCarLength of %d\n",repetition,noOfCars,totalCarLength);
            System.out.printf("the calculated bonusIntercept is %.2f\n",bonusIntercept);
//            removing empty intersections and counting intersections
            int intersection_count=0;
            for (int i = 0; i<intersection.length;i++) {
                if (intersection[i] != null) {
                    intersection_count ++;
                    for (int j = 0; j < intersection[i].intersectionstreets.size(); j++) {
                        if(streets.get(intersection[i].intersectionstreets.get(j)).noOfCar <=0){
                            intersection[i].intersectionstreets.remove(j);
                            j--;
                        }
                    }
                    if(intersection[i].intersectionstreets.isEmpty()){
                        intersection[i]=null;
                       intersection_count --;
                    }
                }
            } 
            
//            printin results
            //System.out.println(intersection_count);
            for (int i = 0; i <intersection.length; i++) {
                if(intersection[i]!=null){
                    //System.out.println(i);
                    //System.out.println(intersection[i].intersectionstreets.size());
                    for (String street : intersection[i].intersectionstreets) {
                        //System.out.printf("%s %d\n",street,(int)Math.ceil(factor * streets.get(street).noOfCar));
                    }
                }
            } 
        }
        
    }
    
}
  /** 
  *Street class
  */
class Street {
    String name;
    int noOfCar;
    int length;
    int total_distance;
    Street(String name, int length){
        this.name= name;
        this.length = length;
        this.noOfCar=0;
        this.total_distance =0;
    }
    void addCar(){
        this.noOfCar++;
    }
    void addDistance(int distance){
        this.total_distance = distance;
    }
}

 /** 
  *Intersection class
  */
class Intersection{
   int id ;
   ArrayList<String> intersectionstreets;
   Intersection(int id){
      this.id = id; 
      intersectionstreets = new ArrayList<>();
   }
   void addStreet(Street s){
       intersectionstreets.add(s.name);
   }
}