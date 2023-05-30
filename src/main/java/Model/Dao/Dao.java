package Model.Dao;

import Model.Data.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {
    void addShape(T t);
    void save() throws IOException;
    ArrayList<Shape> getAllShapes();
}
