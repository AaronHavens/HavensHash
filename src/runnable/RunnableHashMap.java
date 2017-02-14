package runnable;
import java.util.Scanner;

import hashmapping.HashMap;
public class RunnableHashMap {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		Scanner s = new Scanner(System.in).useDelimiter("\\n");
		System.out.println("This is an example cmd line interface of my hashMap. You will be able \n"
				+ "to construct a hashMap of a few datatypes with your size of choice \n"
				+ "You can select your datatype now: \n"
				+ "Enter: 1-Integers, 2-Doubles, 3-Strings : ");
		int dataType = in.nextInt();
		System.out.println("Now select the fixed size: ");
		int size = in.nextInt();
		HashMap<Integer> map1=null;
		HashMap<Double> map2=null;
		HashMap<String> map3=null;
		if(dataType == 1){
			map1 = new HashMap<Integer>(size);
			System.out.println("\n"+"Int map created!"+"\n");
		}
		else if(dataType == 2){
			map2 = new HashMap<Double>(size);
			System.out.println("Double map created!"+"\n");
		}
		else if(dataType == 3){
			map3 = new HashMap<String>(size);
			System.out.println("String map created!"+"\n");
		}
		else{
			map1 = new HashMap<Integer>(size);
			System.out.println("Your input may have been invalid, an int map was created!"+"\n");
		}
		
		boolean success=false;
		String key;
		int value1;
		Double value2;
		String value3;
		int operation;
		while(true){
			System.out.println("Use the following operations to utilize the hashMap \n"
					+ "1-Set Entry, 2-Delete Entry, 3-Get Entry, 4-Get Load, 5-Quit");
			operation = in.nextInt();
			
			if(operation == 1){
				System.out.println("Enter desired key String: ");
				key = s.next();
				System.out.println("Enter your Value: ");
				if(dataType == 1){
					value1 = in.nextInt();
					success = map1.set(key,value1);
				}
				else if(dataType == 2){
					value2 = in.nextDouble();
					success = map2.set(key,value2);
				}
				else if(dataType == 3){
					value3 = s.next();
					success = map3.set(key,value3);
				}
				if(success){
					System.out.println("\n"+"success"+"\n");
				}
				else{
					System.out.println("\n"+"failure"+"\n");
				}
			}
			else if(operation == 2){
				System.out.println("Enter desired key String: ");
				key = s.next();
				if(dataType == 1){
					value1 = map1.delete(key);
					System.out.println("\n"+value1 + " deleted"+"\n");
				}
				else if(dataType == 2){
					value2 = map2.delete(key);
					System.out.println("\n"+value2 + " deleted"+"\n");
				}
				else if(dataType == 3){
					value3 = map3.delete(key);
					System.out.println("\n"+value3 + " deleted"+"\n");
				}
			}
			else if(operation == 3){
				System.out.println("Enter desired key String: ");
				key = s.next();
				if(dataType == 1){
					System.out.println("\n"+"returned: " + map1.get(key)+"\n");
				}
				else if(dataType == 2){
					System.out.println("\n"+"returned: " + map2.get(key)+"\n");
				}
				else if(dataType == 3){
					System.out.println("\n"+"returned: " + map3.get(key)+"\n");
				}
			}
			else if(operation == 4){
				if(dataType == 1){
					System.out.println("\n"+"The current load of the hashMap is: " + map1.load()+"\n");
				}
				else if(dataType == 2){
					System.out.println("\n"+"The current load of the hashMap is: " + map2.load()+"\n");
				}
				else if(dataType == 3){
					System.out.println("\n"+"The current load of the hashMap is: " + map3.load()+"\n");
				}
				
			}
			else if(operation == 5){
				System.out.println("\n"+"Goodbye :)");
				return;
			}
			else{
				continue;
			}
		}
	}
}
