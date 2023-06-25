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
        // variabler som används för att skapa ett nytt todo objekt
        int id = getUniqueTodoId();
        String title = getValidTodoTitle();
        boolean isDone = getTaskStatus();

        Todo todo = new Todo(id, title, isDone);

        dbFacade.createTodoItem(todo);

        System.out.println("Uppgift skapad.");
    }

    private int getUniqueTodoId() {//metod som frågar efter ett unikt id
        int id;
        boolean isInvalidId;
        do { //do-while loop som körs så länge id inte är ett unikt id
            //frågar användaren efter ett id och kollar om det är ett unikt id
            System.out.print("Ange uppgift ID: ");
            id = inputHandler.getIntInput();
            isInvalidId = !Todo.isTodoIdDuplicate(id) || dbFacade.getTodoItemById(id) != null;
            if (isInvalidId) {
                System.out.println("Ogiltigt ID: ID måste vara ett positivt värde som inte redan används.");
            }
        } while (isInvalidId);
        return id;
    }

    private String getValidTodoTitle() { //metod som frågar efter en titel
        //frågar användaren efter en titel och kollar om den är tom
        String title;
        boolean isInvalidTitle;
        do {
            System.out.print("Ange uppgift text: ");
            title = inputHandler.getStringInput();
            // isInvalid title är true om titeln är tom
            isInvalidTitle = title.isEmpty();
            // om titeln är tom skrivs det ut att den är ogiltig
            if (isInvalidTitle) {
                System.out.println("Ogiltig text: Texten får inte vara tom.");
            }
        } while (isInvalidTitle);
        return title;
    }

    private boolean getTaskStatus() { // Metod som frågar efter uppgiftens status
        boolean isDone;
        boolean isInvalidStatus;
        do {
            System.out.print("Är uppgiften klar? (true/false): "); // Fråga användaren efter uppgiftens status
            isDone = inputHandler.getTaskStatusInput(); // Hämta användarens inmatning för uppgiftens status
            isInvalidStatus = false; // Antag att statusen är giltig från början (kommer ändras om ogiltig)
            if (isInvalidStatus) {
                System.out.println("Ogiltig status: Ange antingen 'true' eller 'false'."); // Visa felmeddelande om statusen är ogiltig (Denna kodblock kommer aldrig att köras eftersom isInvalidStatus alltid är falsk)
            }
        } while (isInvalidStatus); // Upprepa loopen så länge statusen är ogiltig (Denna villkor kommer alltid vara falskt)
        return isDone; // Returnera den giltiga uppgiftens status
    }



    private void viewTodoItemById() { //metod som visar en uppdrag via ID
        System.out.print("Ange uppgift ID: ");
        int id = inputHandler.getIntInput();

        Todo todo = dbFacade.getTodoItemById(id);

        if (todo != null) { //kollar om uppdraget finns
            System.out.println("uppgiften hittat:");
            System.out.println(todo);
        } else {
            System.out.println("uppgiften hittades inte.");
        }
    }

    private void viewAllTodoItems() { //metod som visar alla uppdrag
        System.out.println("Alla uppgifter:");
        //hämtar alla uppdrag från databasen och lägger till dem i en lista
        List<Todo> todos = dbFacade.getAllTodoItems();

        if (todos.isEmpty()) { //kollar om listan är tom så skrivs det ut att det inte finns några uppdrag
            System.out.println("Inga uppgifter hittades.");
        } else {
            for (Todo todo : todos) { //for loop som skriver ut alla uppdrag
                System.out.println(todo);
            }
        }
    }

    private void updateTodoTaskStatus() { //metod som uppdaterar en uppdrag
        //frågar användaren efter id och status
        viewAllTodoItems();
        System.out.print("Ange uppgift ID: ");
        Integer id = inputHandler.getIntInput();
        System.out.print("Är uppgiften Klar? (true/false): ");
        boolean isDone = inputHandler.getTaskStatusInput();
        //uppdaterar uppdrag i databasen
        dbFacade.updateTodoStatus(id, isDone);
        System.out.println("uppgiften uppdaterat.");
    }
    private void updateTodoTaskText() { //metod som uppdaterar en uppdragets text
        viewAllTodoItems(); //visar alla uppdrag med deras id sen så frågar den efter id och ny text
        System.out.print("Ange uppgift ID: ");
        Integer id = inputHandler.getIntInput();
        System.out.print("Ange ny text: ");
        String newText = inputHandler.getStringInput();
        //uppdaterar uppdrag i databasen
        dbFacade.updateTodoText(id, newText);
        System.out.println("Uppgiftens text uppdaterad.");
    }

    private void deleteTodoItem() { //metod som tar bort ett uppdrag
        //frågar användaren efter id och tar bort uppdrag från databasen
        viewAllTodoItems();
        System.out.print("Ange uppgift ID: ");
        Integer id = inputHandler.getIntInput();
        //tar bort uppdrag från databasen
        dbFacade.deleteTodoItemById(id);
        System.out.println("Uppgiften borttaget.");
    }
}
