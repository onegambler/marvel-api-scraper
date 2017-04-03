package com.magale.roberto.scraper;

import com.magale.roberto.scraper.config.AppConfig;
import com.magale.roberto.scraper.service.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Initialising application... please bear with me... ");
        System.out.println("\n");
        AppService appService = context.getBean(AppService.class);
        appService.initialise();

        while (true) {
            System.out.println("\n");
            System.out.println("************************************************");
            System.out.println("Please enter desired result: ");
            System.out.println("1. List of all characters in alphabetical order ");
            System.out.println("2. Top 10 most popular characters");
            System.out.println("3. Highest influence characters ");
            System.out.println("4. Who would I infect?");
            System.out.println("5. Exit");
            Scanner inputReader = new Scanner(System.in);

            int selection;
            try {
                selection = inputReader.nextInt();
            } catch (Exception e) {
                System.err.println("Only numbers please");
                continue;
            }
            System.out.println("\n");
            switch (selection) {
                case 1:
                    appService.printCharacters();
                    break;
                case 2:
                    appService.printMostPopular();
                    break;
                case 3:
                    appService.printHighestInfluence();
                    break;
                case 4:
                    appService.printToInfectList();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Not valid");
            }
        }
    }

}
