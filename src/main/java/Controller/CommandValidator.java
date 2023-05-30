package Controller;

import Model.Data.ShapeType;
import Model.Exceptions.WrongShapeTypeException;

public class CommandValidator {
    public boolean validateAddNewShapeCommand(String[] params) throws WrongShapeTypeException {
        if(params.length != 5)
            return false;
        if(!ShapeType.contains(params[0]))
            throw new WrongShapeTypeException("Incorrect shape type : " + params[0]);
        for(int i = 1; i < 5; i++)
        {
           try
           {
               Double.valueOf(params[i]);
           }
           catch (Exception e)
           {
               return false;
           }
        }
        return true;
    }
}
