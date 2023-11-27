package LinkedList;

import java.util.Scanner;

public class PhonebookManager
{
    // main phonebook method
    public static void runPhonebook(Scanner s)
    {
        PbList seattle = new PbList(null, "Seattle");
        PbList bellingham = new PbList(null, "Bellingham");
        PbList currentList = null;

        boolean start = true;

        String listSelection = "";

        System.out.println("Welcome to the Washington Phone Book!");

        while(start == true)
        {
            runPhonebookOptions();
            
            listSelection = s.next();
            listSelection.toLowerCase();

            if (listSelection.charAt(0) == 's')
            {
                currentList = seattle;
                menu(s, currentList);
            }
            else if (listSelection.charAt(0) == 'b')
            {
                currentList = bellingham;
                menu(s, currentList);
            }
            else if (listSelection.charAt(0) == 'q')
            {
                start = false;
            }
        }
    }

    // option text for runPhonebook
    private static void runPhonebookOptions()
    {
        System.out.println("Please select an option: ");
        System.out.println("Select Seattle (s)");
        System.out.println("Select Bellingham (b)");
        System.out.println("Exit (q)");
    }

    // menu for selected city
    private static boolean menu(Scanner s, PbList currentList)
    {
        boolean start = true;

        String selection = "";

        while (start == true)
        {
            menuOptions();

            selection = s.next();
            selection = selection.toLowerCase();

            if (selection.charAt(0) == 'v')
            {
                printList(currentList);
            }
            else if (selection.charAt(0) == 'a')
            {
                menuAddNode(s, currentList);
            }
            else if (selection.charAt(0) == 'r')
            {
                menuRemoveNode(s, currentList);
            }
            else if (selection.charAt(0) == 'm')
            {
                menuModifyNode(s, currentList);
            }
            else if (selection.charAt(0) == 'b')
            {
                start = false;
            }
            else
            {
                System.out.println("Please enter a valid input.");
            }
        }

        return false;
    }

    // options for menu method
    private static void menuOptions()
    {
        System.out.printf(
        "%n%s%n%s%n%s%n%s%n%s%n%s",
        "Please select an option:",
                "View phone book (v)",
                "Add new entry (a)",
                "Remove entry (r)",
                "Modify entry (m)",
                "Back (b)");
        System.out.println();
    }


    // removeNode implemented for menu use
    private static void menuRemoveNode(Scanner s, PbList list)
    {
        if (list.getFront() == null)
        {
            System.out.println("This phone book has no entries to remove.");
        }
        else
        {
            printList(list);
            removeNode(list, getUserIndex(s, list));
        }
    }

    // addNode implemented for menu use
    public static void menuAddNode(Scanner s, PbList list)
    {
        PbNode newNode = new PbNode();

        newNode = createNode(s);
        newNode.setCity(list.getName());
        addNode(list, newNode, getUserIndex(s, list));
    }

    // creates node based on user specs
    public static PbNode createNode(Scanner s)
    {
        String name = nameFromUser(s);

        PbNode newNode = new PbNode(name, null);
        newNode.setAddress(addressFromUser(s));
        newNode.setPhoneNumber(phoneNumberFromUser(s));

        return newNode;
    }

    // modifyNode for menu use
    public static void menuModifyNode(Scanner s, PbList list)
    {
        PbNode current = list.getFront();

        int count = 0;
        int index = getUserIndex(s, list);

        while(count < index)
        {
            current = current.getNext();
            count++;
        } 

        System.out.println(current.toString());
        modifyNodeText();
        modifyNode(s, current);
    }

    // text for modifyNode
    private static void modifyNodeText()
    {
        System.out.printf(
         "%n%s%n%s%n%s%n%s", 
        "Which field would you like to modify?", 
                "Name (n)", 
                "Address (a)", 
                "Phone Number (p)");
        System.out.println();
    }

    // modifies nodes according to user specs 
    private static void modifyNode(Scanner s, PbNode entry)
    {
        boolean validInput = false;

        String selection = s.next();
        selection = selection.toLowerCase();

        while (validInput == false)
        {
            if (selection.charAt(0) == 'n')
            {
                entry.setName(nameFromUser(s));
                validInput = true;
            }
            else if (selection.charAt(0) == 'a')
            {
                entry.setAddress(addressFromUser(s));
                validInput = true;
            }
            else if (selection.charAt(0) == 'p')
            {
                entry.setPhoneNumber(phoneNumberFromUser(s));
                validInput = true;
            }
            else 
            {
                System.out.println("Please enter a valid input.");
                System.out.println();
            }
        }
    }

    // gets contact name from user
    public static String nameFromUser(Scanner s)
    {
        String name = "";

        System.out.println("Please input a first name for this contact:");
        name = s.next();
        System.out.println("Please input a last name for this contact:");
        name = s.next() + ", " + name;

        return name;
    }

    // gets address from user
    public static String addressFromUser(Scanner s)
    {
        String address = "";

        System.out.println("Please input a address for this contact:");
        s = new Scanner(System.in);
        address = s.nextLine();

        return address;
    }

    // gets phone number from user
    public static String phoneNumberFromUser(Scanner s)
    {
        long selection = 0;

        boolean validInput = false;

        while (validInput == false)
        {
            try
            {
                s = new Scanner(System.in);
                System.out.println("Please enter a phone number: ");
                selection = s.nextLong();
                validInput = true;
            }
            catch (Exception NumberformatException)
            {
                System.out.printf("%n%s%n%s", 
                "Please enter a valid ten digit phone number ", 
                "without spaces or special characters.");
                System.out.println("");
            }
        }

        return numberFormatter(selection);
    }

    // turns phone number into proper format
    private static String numberFormatter(Long num)
    {
        String number = Long.toString(num);

        number = "(" + number.substring(0, 3) + ") " 
                + number.substring(3, 6) 
                + "-" + number.substring(6, 10);

        return number;
    }

    // gets index for entry from user
    public static int getUserIndex(Scanner s, PbList list)
    {
        int index = 0;

        if (list.getFront() == null)
        {
            index = 0;
        }
        else
        {
            index = getUserIndexLoop(s, list);
        }

        if (index > getListSize(list) - 1 && index != 0)
        {
            index = getListSize(list) - 1;
        }

        return index;
    }

    // try-catch for getting user index
    public static int getUserIndexLoop(Scanner s, PbList list)
    {
        boolean validInput = false;

        int index = 0;

        System.out.println("Please indicate the index for this entry: ");

        while (validInput == false)
        {
            try 
            {
                s = new Scanner(System.in);
                index = s.nextInt();
                validInput = true;
            }
            catch (Exception NumberformatException)
            {
                System.out.println("Please input an integer between 0 and " 
                    + (getListSize(list)) + ".");
            }
        }

        System.out.println();

        return index;
    }

    // basic addNode method that does not include user input
    public static void addNode(PbList list, PbNode newNode, int index)
    {
        PbNode prevNode;
        PbNode nextNode;

        if (index == 0)
        {
            newNode.setNext(list.getFront());
            list.setFront(newNode);
        }
        else
        {
            prevNode = getIndexedNode(list, index - 1);
            nextNode = getIndexedNode(list, index - 1).getNext(); 
            prevNode.setNext(newNode); // transitivity if x -> y && y - z then x -> z (x -> y -> z)
            newNode.setNext(nextNode);
        }
    }

    // basic removeNode without user input
    public static void removeNode(PbList list, int index)
    {
        PbNode current;

        if (index == 0)
        {
            current = list.getFront();
            list.setFront(current.getNext());
            current.setNext(null);
        }
        else
        {
            current = getIndexedNode(list, index);
            getIndexedNode(list, index - 1).setNext(current.getNext());
            current.setNext(null);
        }
    }

    // prints out a list
    public static void printList(PbList list)
    {
        PbNode current = list.getFront();

        int count = 0;

        if (current == null) 
        {
            System.out.print("This phone book has no contacts.");
            System.out.println();
        }
        else
        {
            while (current != null)
            {
                System.out.println();
                System.out.println("Contact " + count);
                System.out.println(current.toString());
                current = current.getNext();
                count++;
            }
        }
    }

    // gets the size of a list
    public static int getListSize(PbList list)
    {
        PbNode current = list.getFront();

        int count = 0;

        while (current != null)
        {
            current = current.getNext();
            count++;
        }

        return count;
    }

    // gets the index of a node
    public static int indexOf(PbNode front, PbNode entry)
    {
        int count = 0;

        PbNode current = front;
        
        if (current == null)
        {
            return 0;
        }
        else
        {
            while (current.getNext() != null && current != entry)
            {
                count++;
                current = current.getNext();
            }

            return count;
        }
    }

    // gets a node by an index
    public static PbNode getIndexedNode(PbList list, int index)
    {
        PbNode front = list.getFront();
        PbNode current = front;

        int count = 0;

        while (current.getNext() != null 
            && count != index)
        {
            count++;
            current = current.getNext();
        }

        return current;
    }
}