package Model.Dao;

import Model.Data.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeDao implements Dao<Shape>{
    private static Logger logger;
    private ObjectMapper mapper;
    private static ArrayList<Shape> shapesList;
    public ShapeDao() throws IOException {
        logger = Logger.getLogger(ShapeDao.class.getName());
        mapper = new ObjectMapper();
        try
        {
            Shape[] objects = mapper.readerFor(Shape[].class).readValue(new File("shapes.json"));
            shapesList = new ArrayList<>(List.of(objects));
        }catch (FileNotFoundException e)
        {
            shapesList = new ArrayList<>();
        }
    }
    @Override
    public void addShape(Shape shape){
        shapesList.add(shape);
    }

    @Override
    public ArrayList<Shape> getAllShapes() {
        return shapesList;
    }

    @Override
    public void save() throws IOException {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("shapes.json"), shapesList);
    }
}
