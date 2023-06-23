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
                "1. Skapa Task",
                "2. Visa Task via ID",
                "3. Visa alla task",
                "4. Uppdatera task status",
                "5. Uppdatera task text",
                "6. Ta Bort task",
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



    private void createTodoItem() {
        int id;
        Todo todo;

        do {
            System.out.print("Ange task ID: ");
            id = inputHandler.getIntInput();
            if (!Todo. isTodoIdDuplicate(id)) {
                System.out.println("Invalid ID: ID must be a positive non-zero value");
            } else if (dbFacade.getTodoItemById(id) != null) {
                System.out.println("Task with ID " + id + " already exists.");
            }
        } while (!Todo. isTodoIdDuplicate(id) || dbFacade.getTodoItemById(id) != null);

        System.out.print("Ange task Titel: ");
        String title = inputHandler.getStringInput();
        System.out.print("Är task Klart? (true/false): ");
        boolean isDone = inputHandler.getTaskStatusInput();

        todo = new Todo(id, title, isDone);
        dbFacade.createTodoItem(todo);

        System.out.println("Task skapat.");
    }



    private void viewTodoItemById() { //metod som visar en task via ID
        System.out.print("Ange task ID: ");
        int id = inputHandler.getIntInput();

        Todo todo = dbFacade.getTodoItemById(id);

        if (todo != null) {
            System.out.println("task hittat:");
            System.out.println(todo);
        } else {
            System.out.println("Task hittades inte.");
        }
    }

    private void viewAllTodoItems() { //metod som visar alla task
        System.out.println("Alla task:");
        List<Todo> todos = dbFacade.getAllTodoItems();

        if (todos.isEmpty()) {
            System.out.println("Inga task hittades.");
        } else {
            for (Todo todo : todos) {
                System.out.println(todo);
            }
        }
    }

    private void updateTodoTaskStatus() { //metod som uppdaterar en task
        viewAllTodoItems();
        System.out.print("Ange task ID: ");
        Integer id = inputHandler.getIntInput();
        System.out.print("Är task Klart? (true/false): ");
        boolean isDone = inputHandler.getTaskStatusInput();

        dbFacade.updateTodoStatus(id, isDone);
        System.out.println("Task uppdaterat.");
    }
    private void updateTodoTaskText() {
        viewAllTodoItems();
        System.out.print("Ange task ID: ");
        Integer id = inputHandler.getIntInput();
        System.out.print("Ange ny text: ");
        String newText = inputHandler.getStringInput();

        dbFacade.updateTodoText(id, newText);
        System.out.println("Task text uppdaterad.");
    }

    private void deleteTodoItem() {
        viewAllTodoItems();
        System.out.print("Ange task ID: ");
        Integer id = inputHandler.getIntInput();

        dbFacade.deleteTodoItemById(id);
        System.out.println("Task borttaget.");
    }
}
