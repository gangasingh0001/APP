package Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging implements ILogging{
    private File file;
    private FileHandler fileHandler;

    public Logging(String fileName) {
        this.file = this.createFile(fileName);
        this.fileHandler = this.setFileHandler();
    }

    private File createFile(String fileName) {
        final String dir = System.getProperty("user.dir");
        this.file = new File(dir+"/src/main/java/Log/"+fileName+".log");
        if(!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        return file;
    }

    private FileHandler setFileHandler() {
        try {
            this.fileHandler = new FileHandler(this.file.getAbsolutePath(),1024*10000,1,true);
            this.fileHandler.setLevel(Level.ALL);
            this.fileHandler.setFormatter(new CustomFormatter());
            return this.fileHandler;
        }catch (IOException ex) {
            ex.getStackTrace();
        }
        return this.fileHandler;
    }

    private FileHandler getFileHandlerObj() {
        return this.fileHandler;
    }

    public Logger attachFileHandlerToLogger(Logger logger) {
        logger.addHandler(getFileHandlerObj());
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        return logger;
    }

}