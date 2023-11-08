package Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
public class CustomFormatter extends Formatter{
    @Override
    public String format(LogRecord record) {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) +": "+record.getLongThreadID() +"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +record.getMessage()+"\n";
    }
}
