
import java.util.*;

public class Trafficv2{
    public static void main(String[] args){
        int totalSimulationDuration;
        int noOfIntersection;
        int noOfStreets;
        int noOfCars;
        int totalCarLength=0; // to be used as a factor variable
        Intersection[] intersection; // for holding a all intersection
        HashMap<String,Street> streets = new HashMap<>(); // for holding all streets
        
        //processing input file
        
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
           totalSimulationDuration = input.nextInt();
           noOfIntersection = input.nextInt();
           intersection = new Intersection[noOfIntersection]; // initalizing the intersection array
           noOfStreets = input.nextInt();
           noOfCars = input.nextInt();
          /* not needed bonusPoint = */input.nextInt();
          
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
           double factor2 = factor / totalCarLength;
            factor /= (noOfCars);
            factor += factor2;
            
//            removing empty intersections and counting intersections
            int intersection_count=0;
            for (Intersection intersection1 : intersection) {
                if (intersection1 != null) {
                    intersection_count ++;
                    intersection1.streets.stream().filter((street) -> (streets.get(street).noOfCar<=0)).forEachOrdered((street) -> {
                        intersection1.streets.remove(street);
                    });
                }
            } 
            
//            printin results
            System.out.println(intersection_count);
            for (int i = 0; i <intersection.length; i++) {
                if(intersection[i]!=null){
                    System.out.println(i);
                    System.out.println(intersection[i].streets.size());
                    for (String street : intersection[i].streets) {
                        System.out.printf("%s %d\n",street,(int)Math.ceil(factor * streets.get(street).noOfCar));
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
    Street(String name, int length){
        this.name= name;
        this.length = length;
        this.noOfCar=0;
    }
    void addCar(){
        this.noOfCar++;
    }
    
}

 /** 
  *Intersection class
  */
class Intersection{
   int id ;
   ArrayList<String> streets;
   Intersection(int id){
      this.id = id; 
      streets = new ArrayList<>();
   }
   void addStreet(Street s){
       streets.add(s.name);
   }
}