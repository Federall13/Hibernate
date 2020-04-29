import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Router {
    public static ArrayList<String> strings = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));

    public static void route() throws IOException, SQLException, ParseException {
        UserService userService = new UserService();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        do {
            System.out.println(" Здравствуйте! Выберите действие, которое хотите совершить:");
            System.out.println(" 1. Добавить пользователя - введите 1");
            System.out.println(" 2. Изменить пользователя - введите 2");
            System.out.println(" 3. Удалить пользователя - введите 3");
            System.out.println(" 4. Вывести список всех пользователей - введите 4");
            System.out.println(" 5. Выйти - введите 5");
            choice = reader.readLine();
        } while (!strings.contains(choice));

        switch (choice) {
            case "1":
                userService.addUser();
                break;
            case "2":
                userService.updateUser();
                break;
            case "3":
                userService.deleteUser();
                break;
            case "4":
                userService.printAllUser();
                break;
            case "5":
                System.exit(0);
                break;
        }
    }
}