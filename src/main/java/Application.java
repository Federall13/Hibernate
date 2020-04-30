import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Application {
    public static void main(String[] args) throws ParseException, SQLException, IOException {
        Router.route();
    }
}