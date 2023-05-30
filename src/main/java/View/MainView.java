package View;

import Controller.ShapeController;
import Model.Dao.ShapeDao;
import Model.Data.Shape;
import Model.Exceptions.WrongShapeTypeException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class MainView {
    static Logger logger = LogManager.getLogger(MainView.class);
    private static ShapeController shapeController;

    public MainView() throws IOException{
        shapeController = new ShapeController();
    }

    public static void initView()
    {
        System.out.println("Welcome to Shape program! Enter your command or 'help' to see all commands:");
        var sc = new Scanner(System.in);
        while(true) {
            System.out.print(": ");
            String[] command = sc.nextLine().split(" ");
            switch (command[0]) {
                case "help":
                    System.out.println("'add' than 'shape type' 'metrics' - adds new shape record with given metrics");
                    System.out.println("'all' - gets all shapes");
                    System.out.println("'all-ordered' - gets all shapes ordered by type and perimeter");
                    System.out.println("'save' than 'file name' - saves current data to file");
                    System.out.println("'get-type' than 'shape type' - gets all shapes of given type;");
                    System.out.println("'get-square' than 'double square' - gets all shapes with square greater than given;");
                    System.out.println("'get-type-perimeter' than 'shape type' 'integer perimeter' - gets all shapes of given type and perimeter less than given;");

                    System.out.println("exit - saves changes and exit.");
                    break;
                case "all":
                {
                    System.out.println(shapeController.getAllShapes());
                    logger.info("User got all shapes");
                    break;
                }
                case "all-ordered":
                {
                    System.out.println(shapeController.getAllShapesOrdered());
                    logger.info("User got all shapes ordered");
                    break;
                }
                case "add":
                    System.out.println(shapeController.addNewShape(command));
                    logger.info("User added new shape of type " + command[1]);
                    break;
                case "get-type":
                {
                    System.out.println(shapeController.getShapeByType(command[1]));
                    logger.info("User queried shapes of type " + command[1]);
                    break;
                }
                case "get-square":
                {
                    System.out.println(shapeController.getShapeBySquare(command[1]));
                    logger.info("User queried shapes of square " + command[1]);
                    break;
                }
                case "get-type-perimeter":
                {
                    System.out.println(shapeController.getShapeByTypeAndPerimeter(command[1], command[2]));
                    logger.info("User queried shapes of type " + command[1] + " and square greater than " + command[2]);
                    break;
                }
                case "save":
                {
                    System.out.println(shapeController.saveToFile(command[1]));
                    break;
                }
                case "exit":
                    System.out.println(shapeController.saveAndExit());
                    return;
                default:
                    logger.info("Unexisting command: " + command);
                    System.out.println("Oops, wrong command. Try again...");
                    break;
            }
        }
    }
}
