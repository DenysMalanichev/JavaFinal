import View.MainView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        MainView view;
        try
        {
            view = new MainView();
        }catch (IOException e)
        {
            System.out.println("Error connecting to data.");
            logger.error("Error creating new ShapeDao. " + e);
            return;
        }
        logger.info("Startup!");
        view.initView();
    }
}
