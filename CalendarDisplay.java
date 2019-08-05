/****
 * This program displays a calendar for any given month and year.
 * Module 4 assignment
 * By: Alex Sandberg-Bernard
 ****/


// import Java scanner
import java.util.Scanner;

public class CalendarDisplay
{
    public static void main( String[] args )
    {
        // establish scanner input
        Scanner input = new Scanner( System.in );

        // establish string for program while loop
        String answer;

        // use do while loop to execute program repeatedly if desired
        do
        {
            int month = 0; // establish variable for month

            int year = -1; // establish int variable for year

            // use while loop to ensure user enters month correctly (1-12)
            while ( ( month > 12 ) || ( month < 1 ) )
            {
                // prompt user to input month
                System.out.println( "Please enter a month (1-12): " );
                month = input.nextInt();
            }

            // use while loop to ensure year entry is valid
            while (year < 0)
            {
                // prompt user to input month and year
                System.out.println( "Please enter a year (e.g., 2012): " );
                year = input.nextInt();
            }

            // use getMonthName method and store result in string variable
            String monthName = getMonthName( month );

            // use method isLeapYear to determine leap year and store in boolean
            boolean leapYear = isLeapYear( year );

            int daysPerYear; // establish int to store days per year

            // use if else to determine days per year based on boolean leapYear
            if ( leapYear )
            {
                daysPerYear = 366;
            }
            else
            {
                daysPerYear = 365;
            }

            /* use method getNumDaysInMonth to determine number of days in month
            and then store result in int variable
             */
            int daysInMonth = getNumDaysInMonth( month, daysPerYear );

            // get starting starting day using getStartDay() method
            int dayNum = getStartDay( month, year );

            // Display calendar using printMonthCalendar() method
            printMonthCalendar( monthName, daysInMonth, year, dayNum );

            // prompt user to select another cal or to exit program
            do { // use do while loop to evaluate response validity
                System.out.println( "\n" + "Would you like to view " +
                        "another calendar? (enter Y/N): ");
                answer = input.next();
            } while ( ( !"Y".equals( answer ) ) && ( !"N".equals( answer ) ) );

        // program will restart if answer = "Y"
        } while ( answer.equals( "Y" ) );
    }


    /****
     The method getMonthName() implements a switch based on the integer value
     of variable "month" and returns a String of the corresponding month name.

     Pre-conditions: int month is an integer value (1-12)

     Post-conditions: corresponding month name is returned in String form
     ****/


    public static String getMonthName( int month )
    {
        String monthName = "month"; // establish String to store month name

        // use switch to determine which name is associated with given month
        switch (month)
        {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName; // return name of month based on switch determination
    }


    /****
     The method isLeapYear() determines whether a given year is a leap year and
     returns a boolean value. The method works by implementing an if else
     structure to calculate leap year determination.

     Pre-conditions: The year value (year) is the full year, e.g., 2012.

     Post-conditions: A boolean value of true or false is returned.
     ****/


    public static boolean isLeapYear ( int year )
    {
        boolean leap; // establish boolean for use in if else statements

        // use leap year calculations in if else structure
        if( year % 4 == 0 )
        {
            if( year % 100 == 0 )
            {
                if ( year % 400 == 0 )
                    leap = true;
                else
                    leap = false;
            }
            else
                leap = true;
        }
        else
            leap = false;

        return leap; // return boolean value of true or false
    }


    /****
     The method getStartDay() implements Zeller's Algorithm for determining the
     day of the week the first day of a month is. The method adjusts Zeller's
     numbering scheme for day of week ( 0=Saturday, ..., 6=Friday ) to conform
     to a day of week number that corresponds to ISO conventions (i.e.,
     1=Monday, ..., 7=Sunday)

     Pre-Conditions: The month value, m,  is 1-12
     The year value, y, is the full year (e.g., 2012)

     Post-Conditions: A value of 1-7 is returned, representing the first day of
     the month: 1 = Monday, ..., 7 = Sunday
     ****/


    public static int getStartDay( int month,  int year )
    {
        final int day = 1; // Must be set to day 1 for this to work.  JRD.


        // Adjust month number & year to fit Zeller's numbering system
        if ( month < 3 )
        {
            month = month + 12;
            year = year - 1;
        }

        int yearInCent = year % 100;      // k Calculate year within century
        int century = year / 100;      // j Calculate century term
        int firstDay  = 0;            // h Day number of first day in month 'm'

        firstDay = (day + (13 * (month + 1) / 5) + yearInCent +
                (yearInCent / 4) + (century / 4) + (5 * century)) % 7;

        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ((firstDay + 5) % 7) + 1;

        return dayNum;
    }


    /****
     The method getNumDaysInMonth() determines the number of days in a given
     month. The method accounts for leap years by using int variable
     daysPerYear as a parameter, which has a value of either 365 or 366.

     Pre-conditions: int month is an integer value (1-12)
     int daysPerYear is an integer value of 365 or 366

     Post-conditions: method returns an integer value equal to the number of
     days in the given month and year
     ****/


    public static int getNumDaysInMonth( int month, int daysPerYear )
    {
        int days = 0; // establish int variable to store day value

        // use switch to determine number of days based on given month
        switch ( month )
        {
            case 1:
                days = 31;
                break;
            case 2:
            { // if else accounts for leap years based on daysPerYear int
                if (daysPerYear == 366)
                {
                    days = 29;
                    break;
                }
                else
                {
                    days = 28;
                    break;
                }
            }
            case 3:
                days = 31;
                break;
            case 4:
                days = 30;
                break;
            case 5:
                days = 31;
                break;
            case 6:
                days = 30;
                break;
            case 7:
                days = 31;
                break;
            case 8:
                days = 31;
                break;
            case 9:
                days = 30;
                break;
            case 10:
                days = 31;
                break;
            case 11:
                days = 30;
                break;
            case 12:
                days = 31;
                break;
        }
        return days; // return int value determined by switch
    }


    /****
     The method printMonthHeader() prints the header of the calendar, including
     the month name & year on the first line, a line separator on the second
     line, and the days of the week in order on the third line.

     Pre-conditions: String monthName contains name of month.
     int variable year contains year in full form (e.g., 2012)

     Post-conditions: Header of calendar is displayed with correct month and
     year, as determined by method parameters
     ****/


    public static void printMonthHeader(  String monthName , int year )
    {
        // Display the month and year
        System.out.println( "\n" + "       " + monthName + " " + year );

        // Display line separator and days of week
        System.out.println( "---------------------------- " );
        System.out.println( "Sun Mon Tue Wed Thu Fri Sat " );
    }


    /****
     The method printMonthBody() prints the body of the calendar with the days
     of the month correctly corresponding to the day of the week by using for
     loops and while loop to position day numbers. This method adjusts dayNum
     value for Sunday from 7 to 0 in order to prevent calendar from printing
     extra blank first line.

     Pre-conditions: Variable daysInMonth is in integer form and has been
     correctly calculated to reflect the number of days in the given month.
     Variable dayNum is in integer form and has been correctly calculated.

     Post-conditions: The body of the calendar will be printed and will
     correctly display the days of the calendar associated with the
     corresponding days of the week.
     ****/


    public static void printMonthBody( int daysInMonth, int dayNum )
    {

        int daysMonth = 1; // variable for counting total days in month

        int daysWeek = 1; // variable for counting days in given week

        // dayNum = 7 = 0 for Sunday. Resetting to 0 prevents extra line jump
        if ( dayNum == 7 )
        {
            dayNum = 0;
        }

        // use for loop to set start point based on dayNum value
        for ( int i = 0; i < dayNum; i++ )
        {
            System.out.print("    ");
        }

        // use while loop for first week of calendar body
        while ( (daysWeek + dayNum) <= 7 ) // execute loop until 7 days reached
        {
                System.out.print( daysMonth + "   " );
                daysMonth++;
                daysWeek++;
        }

        System.out.println();

        // use for loop for remainder of calendar body
        // execute until daysInMonth total has been reached
        for ( ; daysMonth <= daysInMonth; daysMonth++ )
        {
            for ( daysWeek = 0; daysWeek < 7; daysWeek++ )
            {
                if(daysMonth<10)
                {
                    System.out.print( daysMonth + "   " );
                } else
                {
                    System.out.print( daysMonth + "  " );
                }

                daysMonth++;
                if( daysWeek == 6 )
                {
                    daysMonth--;
                }

                if( daysMonth > daysInMonth )
                {
                    break;
                }
            }
            System.out.print( "\n" );
        }
    }


    /****
     The method printMonthCalendar() displays the full calendar by calling
     methods printMonthHeader() and printMonthBody().

     Pre-conditions: Variable monthName is in string form and correctly
     corresponds to the given month number.
     Variable daysInMonth is in integer form and has been correctly calculated
     to reflect the number of days in the given month.
     Variable year in in int form and contains year in full form (e.g., 2012).
     Variable dayNum is in int form and has been correctly calculated to
     reflect the day of the week of the first day of the month.

     Post-conditions: The full calendar will be displayed and will accurately
     reflect the real calendar for the given month and year.
     ****/


    public static void printMonthCalendar( String monthName, int daysInMonth,
                                           int year, int dayNum )
    {
        // Display header using printMonthHeader method
        printMonthHeader( monthName, year);

        // Display body of calendar
        printMonthBody( daysInMonth, dayNum );
    }
}

