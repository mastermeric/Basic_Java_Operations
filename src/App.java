
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    
    //Thread example 
    void ThreadOrnek() {
        Runnable thread1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i + ".  Thread ");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.run();
    }


    //LINQ operasyon : Predicate Example
    public void PredicateExample() {
        Predicate<Integer> pr = a -> (a > 18); // Creating predicate  
        System.out.println(pr.test(10));    // Calling Predicate method    

        System.out.println("--------------------------------------");

        Integer[] list = new Integer[]{5, 7, 11, 10, 17, 25};
        Arrays.stream(list).filter(x -> x > 10).forEach(System.out::println);

        System.out.println("--------------------------------------");

        Predicate<Integer> greaterThan10 = x -> x < 10;
        Stream<Integer> integerList = Arrays.stream(list).filter(greaterThan10);
        integerList.forEach(System.out::println);

        System.out.println("--------------------------------------");

        String[] nameList = new String[]{"Ahmet", "Mehmet", "Cemal", "Yusuf", "Metin", "Dilek", "Ahmet"};
        Arrays.stream(nameList).filter(x -> x.startsWith("M")).forEach(System.out::println);
    }        

    //LINQ operasyon : find even numbers
    public void findEvenNumbers() {

        ArrayList<Integer> baseCollection = new ArrayList<>();
        baseCollection.add(45);
        baseCollection.add(46);
        baseCollection.add(47);
        baseCollection.add(48);

        Predicate<Integer> streamsPredicate = item -> item % 2 == 0;
    
        ArrayList<Integer> res =  (ArrayList<Integer>) baseCollection.stream()
          .filter(streamsPredicate)
          .collect(Collectors.toList());

          System.out.println(res);
    }

    //File read/write operations..
    static void FileOperations() {
        
        //-----------  WRITE to a File   ----------------
        String str = LocalDate.now() + " --> Test Lorem Ipsum text..\r\n";
        try (FileWriter fw = new FileWriter("filename.txt",true)) {
            fw.write(str);
            System.out.println("Writing successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //------------------------------------------------


        // -----------  READ from a File  ----------------
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
          } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        //------------------------------------------------
    }
    void StringOperations() {
       // "_" notation is good for readability in numeric literals..
        int maxVal = 123_450_000;

        // _ StringBuilder/StringBufefr better for concatinated strings..
        StringBuilder st = new StringBuilder();
        st.append("SELECT * FROM ");
        st.append("WHERE VALUE = ");
        st.append(Integer.toString(maxVal));
        System.out.println(st);
        
    }
    

    public int[] sortArray(int[] A) {

        if(A == null) {
            return null;
        }

        try {
            
            Arrays.sort(A);
            return A ;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int stackOperations(String S) {

        if(S == null)
        return -1;

        Stack<Integer> tempStack = new Stack<Integer>();
        
        try {

            boolean lastCommandIsInteger = false;

            // Case 1
            String[] commandList =  S.split(" ");

            //Error case 2
            if(commandList.length == 0 || commandList.length>2000) {
                return -1;
            }

            for (int i = 0; i < commandList.length; i++) {
                String command = commandList[i];
                if(isNumeric(command) == true) {
                    int tempNumber = Integer.parseInt(command);                    
                    tempStack.push(tempNumber);
                    
                    //check.
                    lastCommandIsInteger = true;
                } else {

                    if(lastCommandIsInteger == false ) {
                        return -1;
                    }
                    
                    //Command situations...
                    switch (command) {
                        case "DUP":  
                                int temp = tempStack.pop();
                                tempStack.push(temp);
                                tempStack.push(temp);
                            break;
                    
                            case "POP":                
                                tempStack.pop();                                            
                            break;

                            case "-":                
                            int temp1 = tempStack.pop();            
                            int temp2 = tempStack.pop();            

                            //We dont want negative substraction result.
                            int res = temp1-temp2;                            
                            if(res<0) {
                                return -1;
                            }
                            tempStack.push(res);
                            break;

                            case "+":        
                            int tempSum1 = tempStack.pop();            
                            int tempSum2 = tempStack.pop();            
                            
                            int tempSum = tempSum1 + tempSum2;
                            tempStack.push(tempSum);
                            break;

                        default:
                            System.out.println("Unsupported Command !");
                            return -1;
                    }
                }
            }
        
            return tempStack.pop();

        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        FileOperations();        
    }
}
