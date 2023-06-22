package org.campusmolndal;

import org.campusmolndal.Database.MongoDbFacade;

public class Main {
    public static void main(String[] args) {
        MongoDbFacade mongoDbFacade = new MongoDbFacade();
        mongoDbFacade.deleteTodoItemById(1);



        }
    }


