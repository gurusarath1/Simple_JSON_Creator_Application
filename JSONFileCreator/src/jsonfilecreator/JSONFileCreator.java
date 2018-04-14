/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonfilecreator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author gsgur
 */
public class JSONFileCreator {

    /**
     * @param args the command line arguments
     */
    static JSONObject Json_Obj = new JSONObject();

    static String fileName;
    static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        try {
            // Splash screen for the application
            welcome();
            Thread.sleep(2000);

            System.out.print("\nEnter File name - ");
            fileName = inp.next();
            int userCommand = 0;

            System.out.println("\n\n1 - New Json element \n2 - New Json Object\n3 - New Json Array\n4 - View JSON\n5 - Create JSON file and Exit");
            userCommand = inp.nextInt();
            inp.nextLine();

            //5 Exits 
            while (userCommand != 5) {

                try {
                    switch (userCommand) {
                        case 1:
                            JSON_Item_Create_And_Put();
                            break;
                        case 2:
                            JSON_Object_Create_And_Put();
                            break;
                        case 3:
                            JSON_Array_Create_And_Put();
                            break;
                        case 4:
                            System.out.println(Json_Obj);
                            break;
                        case 5:
                            //Exit
                            break;
                        default:
                            System.out.println("\nInvalid Input\n");
                            break;
                    }

                    System.out.println("\n\n1 - New Json element \n2 - New Json Object\n3 - New Json Array\n4 - View JSON\n5 - Create JSON file and Exit");

                    userCommand = inp.nextInt();
                    inp.nextLine();

                } catch (IOException ex) {
                    System.out.println("\n\n COULD NOT EXECUTE OPERATION !!!! \n\n");
                }

            }

            // Exitting sequence
            System.out.println(Json_Obj);

            String JSON_String = new String(Json_Obj.toString());

            try {

                FileWriter fr = new FileWriter(fileName + ".json");

                fr.write(JSON_String);

                fr.close();

            } catch (IOException e) {

                System.out.println("\n\n COULD NOT CREATE JSON FILE !!!! \n\n");

            } finally {

                System.out.println("File Created at location - " + System.getProperty("user.dir") + "\\" + fileName + ".json");

            }

            Thread.sleep(5000);

        } catch (Exception e) {
            
            // Exit gracefully when an exception occurs
            System.out.println("\n\n       ERROR !!!! \n\n Application will exit in 10 seconds");
            Thread.sleep(10000);
        }

    }

    static void JSON_Item_Create_And_Put() throws IOException {

        System.out.print("\nKey: ");
        String keyX = inp.nextLine();

        System.out.print("\nType (1- String, 2 - long, 3 - double): ");
        int dataType = inp.nextInt();
        inp.nextLine();// This is to flush the extra \n character

        String ValueX = null;
        long ValueY = 0;
        double ValueZ = 0.0;

        System.out.print("Value: ");

        try {

            switch (dataType) {
                default: //Invalid input also defaults to string
                case 1: // String
                    ValueX = inp.nextLine();
                    Json_Obj.put(keyX, ValueX);
                    break;
                case 2: // Long
                    ValueY = inp.nextLong();
                    inp.nextLine();
                    Json_Obj.put(keyX, ValueY);
                    break;
                case 3: // Double
                    ValueZ = inp.nextDouble();
                    inp.nextLine();
                    Json_Obj.put(keyX, ValueZ);

                    break;

            }
        } catch (InputMismatchException e) {

            System.out.println("\n\n ####Wrong data type of input !!!\n\n ####Whole operation terminated !!! \n\n ####Start over again !!!");

        }
    }

    static JSONObject JSON_Object_Create(int NumberOfelemetns) throws IOException {

        String keys[] = new String[NumberOfelemetns];
        int dataTypes[] = new int[NumberOfelemetns];
        JSONObject JSON_Object_return = new JSONObject();

        for (int i = 0; i < NumberOfelemetns; i++) {
            System.out.print("Key of " + i + "th element - ");
            keys[i] = inp.nextLine();
        }

        System.out.print("\nType (1- String, 2 - long, 3 - double): ");

        for (int i = 0; i < NumberOfelemetns; i++) {
            System.out.print("\nData Type " + i + " :");
            dataTypes[i] = inp.nextInt();
            inp.nextLine(); // This is to flush the extra \n character
        }

        for (int i = 0; i < NumberOfelemetns; i++) {

            System.out.print(i + "th Key's Value [" + keys[i] + "] - ");

            try {

                switch (dataTypes[i]) {
                    default: //Invalid input also defaults to string
                    case 1:

                        JSON_Object_return.put(keys[i], inp.nextLine());
                        break;

                    case 2:
                        JSON_Object_return.put(keys[i], inp.nextLong());
                        inp.nextLine();
                        break;

                    case 3:
                        JSON_Object_return.put(keys[i], inp.nextDouble());
                        inp.nextLine();
                        break;
                }

            } catch (InputMismatchException e) {

                System.out.println("\n\n ####Wrong data type of input !!!\n\n ####Whole operation terminated !!!\n\n ####Start over again !!!");

            }

        }

        return JSON_Object_return;

    }

    static void JSON_Object_Create_And_Put() throws IOException {

        System.out.print("\nObject Key: ");
        String ObjectkeyX = inp.nextLine();

        System.out.print("\nNumber of elements in the object: ");
        int NumEleX = inp.nextInt();
        inp.nextLine();// This is to flush the extra \n character

        JSONObject jsonObj = JSON_Object_Create(NumEleX);

        Json_Obj.put(ObjectkeyX, jsonObj);
    }

    static void JSON_Array_Create_And_Put() throws IOException {
        JSONArray jAry = new JSONArray();

        System.out.print("\nArray Key: ");
        String ArraykeyX = inp.nextLine();

        System.out.print("\nNumber of objects in array: ");
        int numberOfArrayObjects = inp.nextInt();
        inp.nextLine();// This is to flush the extra \n character

        System.out.print("\nNumber of elements in each object (in array): ");
        int numberOfElementsInObject = inp.nextInt();
        inp.nextLine();// This is to flush the extra \n character

        String ElementKeys[] = new String[numberOfElementsInObject];
        int ElementDataTypes[] = new int[numberOfElementsInObject];

        for (int i = 0; i < numberOfElementsInObject; i++) {
            System.out.print(i + "th Element's key - ");
            ElementKeys[i] = inp.nextLine();
        }

        System.out.print("\nType (1- String, 2 - long, 3 - double): \n");

        for (int i = 0; i < numberOfElementsInObject; i++) {
            System.out.print(i + "th Element's DataType - ");
            ElementDataTypes[i] = inp.nextInt();
            inp.nextLine();// This is to flush the extra \n character
        }

        for (int i = 0; i < numberOfArrayObjects; i++) {

            System.out.print(i + "th Object -----------------------------------------------------\n");

            JSONObject sub_object = new JSONObject();

            for (int j = 0; j < numberOfElementsInObject; j++) {

                System.out.print(i + "th Object's " + j + "th Key Value [" + ElementKeys[j] + "]");

                try {

                    switch (ElementDataTypes[j]) {
                        default: //Invalid input also defaults to string
                        case 1: // String
                            sub_object.put(ElementKeys[j], inp.nextLine());
                            break;

                        case 2: // Long
                            sub_object.put(ElementKeys[j], inp.nextLong());
                            inp.nextLine();
                            break;

                        case 3: // Double
                            sub_object.put(ElementKeys[j], inp.nextDouble());
                            inp.nextLine();
                            break;
                    }

                } catch (InputMismatchException e) {

                    System.out.println("\n\n ####Wrong data type of input !!!\n\n ####Whole operation terminated !!!\n\n ####Start over again !!!");

                }

            }

            jAry.add(sub_object);

        }

        Json_Obj.put(ArraykeyX, jAry);
    }

    static void welcome() {

        // Splash screen
        System.out.println("----------------------------------------------");
        System.out.println("---------- JSON File Creator V1.1 ------------");
        System.out.println("----------------------------------------------");
        System.out.println("----------------------- Guru Sarath ----------");
        System.out.println("----------------------------------------------");

    }

}
