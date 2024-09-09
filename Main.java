/* ***************************************
  @author    Lim Jie Ying
  @date      29th November 2023
  @version   1

A program that records a tube stations name and step free access then allows users to query each station 
****************************************/
import java.util.Scanner;

class station
{
    String name;
    boolean step_free;
}


public class Main
{
    public static void main (String [] a)
    {
        final int MAX_STATIONS = 100;
        station[] stationData = new station [MAX_STATIONS];
        set_all_to_zero(stationData, MAX_STATIONS);

        askData(stationData, MAX_STATIONS);
        askOptions(stationData, MAX_STATIONS);
        return;
    } // END main

    public static void askOptions(station[] s, int MAX_STATIONS)
    {
        final int END_COMMAND = 1;
        print("1) End    2) does a given station have step free     3) print all stations with step free");

        int optionNumber = inputInt("Select an option:");

        while(optionNumber != END_COMMAND)
        {
            if(optionNumber == 2)
            {
                searchStation(s, MAX_STATIONS);
            }
            else if(optionNumber == 3)
            {
                print_all_step_free(s, MAX_STATIONS);
            }
            else
            {
                print("Please type 1, 2, or 3 ONLY!");
            }
            optionNumber = inputInt("Select an option:");
        }
        return; 
    }

    public static void searchStation(station[] s, int MAX_STATIONS)
    {
        String station_to_search = inputAnswer("Which station would you like to know about?");
        int positon = searchArray(s, station_to_search, MAX_STATIONS);

        if(positon == -1)
        {
            print( station_to_search +" Station does not exist in my database."); 
        }
        else
        {
            boolean stepFree_y_or_n = getStepFree(s[positon]);
            
            if(stepFree_y_or_n == true)
            {
                print(station_to_search + " HAVE step free access.");
            }
            else
            {
                print(station_to_search + " DOES NOT HAVE step free access.");
            }
        }
        blankLine();
        return;
    }

    
    public static int searchArray(station[] s, String station_to_search , int MAX_STATIONS)
    {
        for(int i = 0; i < MAX_STATIONS; i++)
        {
            String currentStation = getName(s[i]);
            if(currentStation.equals(station_to_search))
            {
                return i;
            }
        }
        return -1;
    }
    

    public static void print_all_step_free(station[] s, int MAX_STATIONS)
    {
        for(int i = 0; i < MAX_STATIONS; i++)
        {
            boolean currentStation_step_free = getStepFree(s[i]);
            if(currentStation_step_free == true)
            {
                print(getName(s[i]) + " Station has step free access.");
            }
        }
        blankLine();
        return;
    }

    public static void askData(station[] s, int MAX_STATIONS)
    {
        int counter = 0;
        
        String name_of_station = inputAnswer("What is the name of the station? (type STOP to end)");
        while(!name_of_station.equals("STOP") && counter < MAX_STATIONS)
        {
            s[counter] = setName(s[counter], name_of_station);

            s[counter] = askStepFree(s[counter]);
            
            counter = counter + 1;
            
            if(counter < MAX_STATIONS)
            {
                name_of_station = inputAnswer("What is the name of the station? (type STOP to end)");
            }
        }
        blankLine();
        return;
    }

    public static station askStepFree(station s)
    {
        String y_or_n = inputAnswer("Does this station have step free access? (please type yes or no)");
        while(!y_or_n.equals("yes") && !y_or_n.equals("no"))
        {
            print("Please type yes or no only!");
            y_or_n = inputAnswer("Does this station have step free access? (please type yes or no)");
        }

        if(y_or_n.equals("yes"))
        {
            s = setStepFree(s, true);
        }
        return s;
    }

    public static void set_all_to_zero(station[] stationData, int MAX_STATIONS)
    {
        for(int i = 0; i < MAX_STATIONS; i++)
        {
            stationData[i] = createStation("", false);
        }
        return;
    }

    public static station createStation(String name_of_station, boolean stepFree)
    {
        station s = new station();

        s.name = name_of_station;
        s.step_free = stepFree;

        return s;
    }

    /**************
    ADT for station 
    -sets the name of the station
    -sets the station's step free access
    -return the name of the station
    -return the station's step free access
    *******/

    public static station setName(station s, String name_of_station)
    {
        s.name = name_of_station;
        return s; 
    }

    public static station setStepFree(station s, boolean stepFree_T_or_F)
    {
        s.step_free = stepFree_T_or_F;
        return s; 
    }

    public static String getName(station s)
    {
        return s.name;
    }

    public static boolean getStepFree(station s)
    {
        return s.step_free;
    }

    //End of ADT

    //COMMON METHODS
    
    //Method to print any message
    public static void print(String message)
    {
        System.out.println(message);
        return;
    }//End print
    
    public static void blankLine()
    {
        print("\n");
    }
    
    //Method for users to input strings
    public static String inputAnswer(String question)
    {
        String answer;
        Scanner scanner = new Scanner(System.in);
    
        print(question);
        answer = scanner.nextLine();
    
        return answer;
    }//End inputAnswer
    
    //to convert Strings to integers
    public static int inputInt(String message)
    {
        int answerint = 0;
        String answer = inputAnswer(message);
        boolean input_is_an_int = checkInt(answer);
    
        while (input_is_an_int == false)
        {
            answer = inputAnswer(message);
            input_is_an_int = checkInt(answer);
        }
    
            answerint = Integer.parseInt(answer);
       
        return answerint;
    }//End inputInt

//to check if the input is an int or not
    public static boolean checkInt (String answer)
    {
        boolean input_is_an_int = false;

        for(int i = 0; i <= answer.length()-1; i++)
        {
            char current = answer.charAt(i);
        
            if(current == '0' || current =='1' || current =='2' || current == '3' || current == '4' || current == '5' || current =='6' ||current == '7' ||current =='8' ||current == '9')
            {
                input_is_an_int = true;
            } 
            else
            {
                return false;
            }
        }
    return input_is_an_int;
    }//End checkInt

    
}//End class

