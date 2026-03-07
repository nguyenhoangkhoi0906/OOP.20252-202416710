import java.util.Scanner;

public class Exercise64{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int month;
        int year;

        while (true){

            System.out.print("Enter month (name, abbreviation, or number): ");
            String monthInput = sc.next().toLowerCase();

            month = parseMonth(monthInput);

            if (month == -1){
                System.out.println("Invalid month. Please enter again.\n");
                continue;
            }

            System.out.print("Enter year (non-negative integer): ");

            if (!sc.hasNextInt()){
                System.out.println("Invalid year. Please enter again.\n");
                sc.next();
                continue;
            }

            year = sc.nextInt();

            if (year < 0){
                System.out.println("Year cannot be negative.\n");
                continue;
            }

            break;
        }

        int days = getDaysInMonth(month, year);

        System.out.println("Month " + month + " of year " + year + " has " + days + " days.");

        sc.close();
    }

    //convert month input to month number
    public static int parseMonth(String input){

        switch (input){
            case "1": case "january": case "jan": case "jan.": return 1;
            case "2": case "february": case "feb": case "feb.": return 2;
            case "3": case "march": case "mar": case "mar.": return 3;
            case "4": case "april": case "apr": case "apr.": return 4;
            case "5": case "may": return 5;
            case "6": case "june": case "jun": case "jun.": return 6;
            case "7": case "july": case "jul": case "jul.": return 7;
            case "8": case "august": case "aug": case "aug.": return 8;
            case "9": case "september": case "sep": case "sep.": return 9;
            case "10": case "october": case "oct": case "oct.": return 10;
            case "11": case "november": case "nov": case "nov.": return 11;
            case "12": case "december": case "dec": case "dec.": return 12;
            default: return -1;
        }
    }

    //calculate days in month
    public static int getDaysInMonth(int month, int year){

        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: return 31;

            case 4: case 6: case 9: case 11: return 30;

            case 2: return isLeapYear(year) ? 29 : 28;

            default: return -1;
        }
    }

    //check leap year
    public static boolean isLeapYear(int year){
       return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}