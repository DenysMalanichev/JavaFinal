package Model.Services;

import Controller.CommandValidator;
import Model.Dao.ShapeDao;
import Model.Data.Shape;
import Model.Exceptions.WrongShapeTypeException;
import View.MainView;
import com.google.common.collect.ComparisonChain;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShapeService {
    static Logger logger = LogManager.getLogger(ShapeService.class);
    private ShapeDao shapeDao;
    private CommandValidator validator;

    public ShapeService() throws IOException {
        validator = new CommandValidator();
        shapeDao = new ShapeDao();
    }

    public boolean addShape(String[] metrics)
    {
        try{
            if(validator.validateAddNewShapeCommand(metrics))
            {
                var shape = new Shape(metrics[0], Double.valueOf(metrics[1]),
                        Double.valueOf(metrics[2]),
                        Double.valueOf(metrics[3]),
                        Double.valueOf(metrics[4]));
                shapeDao.addShape(shape);
                return true;
            }
        }
        catch (WrongShapeTypeException e)
        {
            logger.warn("Attempt to create new sha[e with wrong type : " + metrics[0]);
        }
        return false;
    }

    public List<Shape> getShapeByType(String type)
    {
        return shapeDao.getAllShapes().stream()
                .filter(sh -> sh.getShapeType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Shape> getShapeBySquare(double square)
    {
        return shapeDao.getAllShapes().stream()
                .filter(sh -> sh.getSquare() > square)
                .collect(Collectors.toList());
    }

    public List<Shape> getAllShapes()
    {
        return shapeDao.getAllShapes();
    }

    public List<Shape> getAllShapesOrdered()
    {
        var shapes = shapeDao.getAllShapes();
        Collections.sort(shapes, (o1, o2) -> ComparisonChain.start()
                .compare(o1.getShapeType(), o2.getShapeType())
                .compare(o1.getPerimeter(), o2.getPerimeter())
                .result());
        return shapes;
    }

    public boolean saveToFile(String fileName)
    {
        logger.info("Save to file.");
        String str = "";
        for (var shape: shapeDao.getAllShapes()) {
            str += shape.toString() + '\n';
        }
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            logger.warn("IOException while saving shapes to file: " + e);
            return false;
        }
        return true;
    }

    public boolean saveAndExit() {
        try
        {
            shapeDao.save();
        }catch (IOException e)
        {
            logger.error("Error saving changes while exiting" + e);
        }
        return true;
    }

    public List<Shape> getShapeByTypeAndPerimeter(String type, double p)
    {
        return shapeDao.getAllShapes().stream()
                .filter(sh -> sh.getPerimeter() < p)
                .filter(sh -> sh.getShapeType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}
