package Model.Exceptions;

public class WrongShapeTypeException extends Exception{
    public  WrongShapeTypeException(String errorMessage)
    {
        super(errorMessage);
    }

    public WrongShapeTypeException() {
        super("Incorrect shape type");
    }
}
