package Controller;

import Model.Dao.ShapeDao;
import Model.Data.Shape;
import Model.Data.ShapeType;
import Model.Exceptions.WrongShapeTypeException;
import Model.Services.ShapeService;
import com.google.common.collect.ComparisonChain;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ShapeController {
    static Logger logger = LogManager.getLogger(ShapeController.class);
    private ShapeDao shapeDao;
    private CommandValidator validator;
    private ShapeService shapeService;
    public ShapeController() throws IOException
    {
        validator = new CommandValidator();
        shapeDao = new ShapeDao();
        shapeService = new ShapeService();
    }

    public String addNewShape(String[] command)
    {
        if(shapeService.addShape(Arrays.copyOfRange(command, 1, command.length)))
        {
            return "Successfully added";
        }
        logger.info("User tried to add new shape of type " + command[1]);
        return "Error while adding. Describe your shape like: " +
                    "\n 'shape type' 'square' 'perimeter' 'centerX' 'centerY'";
    }

    public String getShapeByType(String type)
    {
        var shapes = shapeService.getShapeByType(type);
        if (shapes.isEmpty())
            return "Looks like there is no shapes of type " + type;
        return getStringByShapesList(shapes);
    }

    public String getShapeBySquare(String squareStr)
    {
        double square;
        try {
            square = Double.valueOf(squareStr);
        }catch (Exception e)
        {
            return "Wrong value of square";
        }
        var shapes = shapeService.getShapeBySquare(square);
        if(shapes.isEmpty())
           return "Looks like there is no shapes with square greater than " + squareStr;
        return getStringByShapesList(shapes);
    }

    public String getShapeByTypeAndPerimeter(String type, String p)
    {
        double perim;
        try {
            perim = Double.valueOf(p);
        }catch (Exception e)
        {
            return "Wrong value of perimeter";
        }
        var shapes = shapeService.getShapeByTypeAndPerimeter(type, perim);
        if(shapes.isEmpty())
           return "Looks like there is no shapes of type " + type + " and perimeter less than " + p;
        return getStringByShapesList(shapes);
    }

    public String getAllShapes()
    {
        String allShapes = "";
        for (var shape : shapeService.getAllShapes())
        {
            allShapes += shape.toString() + '\n';
        }
        if(allShapes.isEmpty())
            allShapes = "No shapes found";
        return allShapes;
    }

    public String getAllShapesOrdered()
    {
        String allShapes = "";
        for (var shape : shapeService.getAllShapesOrdered())
        {
            allShapes += shape.toString() + '\n';
        }
        if(allShapes.isEmpty())
            allShapes = "No shapes found";
        return allShapes;
    }

    public String saveToFile(String fileName)
    {
        if(shapeService.saveToFile(fileName))
        {
            return "Saved.";
        }
        return "Wrong path. Try again";
    }

    public String saveAndExit(){
        logger.info("Exiting");
        if(!shapeService.saveAndExit())
        {
            return "Error saving changes while exiting. Nothing was saved";
        }
        logger.info("All changes saved.");
        return "All changes saved.";
    }

    private String getStringByShapesList(List<Shape> shapes)
    {
        String shapesStr = " ";
        for (var shape : shapes) {
            shapesStr += shape.toString() + '\n';
        }
        return shapesStr;
    }
}
