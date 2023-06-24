package org.campusmolndal.MainMeny;

import org.campusmolndal.Database.MongoDbFacade;
import org.campusmolndal.InputHanterare.InputHandler;
import org.campusmolndal.TodoList.Todo;

import java.util.List;

public class Menu {
    private final MongoDbFacade dbFacade;
    public InputHandler inputHandler;

    public Menu() {
        //instansiering av objekt
        this.dbFacade = new MongoDbFacade();
        this.inputHandler = new InputHandler();
    }

    private void displayMenu() { //skriver ut menyn
        String[] menuOptions = { //skapar en array med menyalternativ
                "1. Skapa uppgift",
                "2. Visa uppgift via ID",
                "3. Visa alla uppgifter",
                "4. Uppdatera uppgift status",
                "5. Uppdatera uppgift text",
                "6. Ta Bort uppgift",
                "0. Avsluta"
        };

        System.out.println("==== ATT GÖRA-LISTA MENY ====");
        for (String option : menuOptions) { //for loop som skriver ut menyalternativen
            System.out.println(option);
        }
        System.out.println("=============================");
    }

    public void run() { //metod som körs när programmet startas
        int choice = -1; //variabel som används för att välja menyalternativ

        while (choice != 0) { //while loop som körs så länge choice inte är 0
                displayMenu();
                System.out.print("Välj ett alternativ: ");
                choice = inputHandler.getIntInput();

                switch (choice) {
                    case 1 -> createTodoItem();
                    case 2 -> viewTodoItemById();
                    case 3 -> viewAllTodoItems();
                    case 4 -> updateTodoTaskStatus();
                    case 5 -> updateTodoTaskText();
                    case 6 -> deleteTodoItem();
                    case 0 -> System.out.println("Avslutar...");
                    default -> System.out.println("Ogiltigt val. Försök igen.");
                }
                System.out.println();

        }

        inputHandler.closeScanner();
    }



    private void createTodoItem() { //metod som skapar en task
        int id;
        Todo todo;

        do { //do-while loop som körs så länge id inte är ett unikt id
            //frågar användaren efter ett id och kollar om det är ett unikt id
            System.out.print("Ange uppgift ID: ");
            id = inputHandler.getIntInput();
            if (!Todo. isTodoIdDuplicate(id)) {
                System.out.println("Ogiltigt ID: ID måste vara ett positivt värde som inte är noll.");
            } else if (dbFacade.getTodoItemById(id) != null) {
                System.out.println("Uppgift ID " + id + " existerar redan.");
            }
        } while (!Todo. isTodoIdDuplicate(id) || dbFacade.getTodoItemById(id) != null);
        //frågar användaren efter titel och status
        System.out.print("Ange uppgift text: ");
        String title = inputHandler.getStringInput();
        System.out.print("Är uppgiften Klar? (true/false): ");
        boolean isDone = inputHandler.getTaskStatusInput();
    //skapar ett nytt todo objekt och lägger till det i databasen
        todo = new Todo(id, title, isDone);
        dbFacade.createTodoItem(todo);

        System.out.println("Task skapat.");
    }



    private void viewTodoItemById() { //metod som visar en task via ID
        System.out.print("Ange uppgift ID: ");
        int id = inputHandler.getIntInput();

        Todo todo = dbFacade.getTodoItemById(id);

        if (todo != null) { //kollar om tasken finns
            System.out.println("uppgiften hittat:");
            System.out.println(todo);
        } else {
            System.out.println("uppgiften hittades inte.");
        }
    }

    private void viewAllTodoItems() { //metod som visar alla task
        System.out.println("Alla uppgifter:");
        //hämtar alla task från databasen och lägger till dem i en lista
        List<Todo> todos = dbFacade.getAllTodoItems();

        if (todos.isEmpty()) { //kollar om listan är tom så skrivs det ut att det inte finns några task
            System.out.println("Inga uppgifter hittades.");
        } else {
            for (Todo todo : todos) { //for loop som skriver ut alla task
                System.out.println(todo);
            }
        }
    }

    private void updateTodoTaskStatus() { //metod som uppdaterar en task
        //frågar användaren efter id och status
        viewAllTodoItems();
        System.out.print("Ange uppgift ID: ");
        Integer id = inputHandler.getIntInput();
        System.out.print("Är uppgiften Klar? (true/false): ");
        boolean isDone = inputHandler.getTaskStatusInput();
        //uppdaterar tasken i databasen
        dbFacade.updateTodoStatus(id, isDone);
        System.out.println("uppgiften uppdaterat.");
    }
    private void updateTodoTaskText() { //metod som uppdaterar en tasks text
        viewAllTodoItems(); //visar alla task med deras id sen så frågar den efter id och ny text
        System.out.print("Ange uppgift ID: ");
        Integer id = inputHandler.getIntInput();
        System.out.print("Ange ny text: ");
        String newText = inputHandler.getStringInput();
        //uppdaterar tasken i databasen
        dbFacade.updateTodoText(id, newText);
        System.out.println("Uppgiftens text uppdaterad.");
    }

    private void deleteTodoItem() { //metod som tar bort en task
        //frågar användaren efter id och tar bort tasken från databasen
        viewAllTodoItems();
        System.out.print("Ange uppgift ID: ");
        Integer id = inputHandler.getIntInput();
        //tar bort tasken från databasen
        dbFacade.deleteTodoItemById(id);
        System.out.println("Uppgiften borttaget.");
    }
}