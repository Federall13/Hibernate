import bl.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class UserService {
    private final String SIMPLE_DATE_FORMAT_PATTERN = "yyyy/MM/dd";

    public void addUser() throws ParseException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        System.out.println("Введите имя");
        user.setName(reader.readLine());
        System.out.println("Введите фамилию");
        user.setLname(reader.readLine());
        System.out.println("Введите возраст");
        user.setAge(Integer.parseInt(reader.readLine()));
        System.out.println("Введите дату рождения в формате: " + SIMPLE_DATE_FORMAT_PATTERN);
        String stringDate = reader.readLine();
        java.util.Date javaDate = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).parse(stringDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaDate);
        calendar.add(Calendar.DATE, 1);
        javaDate = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        user.setDate(sqlDate);
        session.save(user);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    public void updateUser() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        System.out.println("Введите номер пользователя которого хотите изменить");
        user.setId(Integer.parseInt(reader.readLine()));
        System.out.println("Введите новое имя");
        user.setName(reader.readLine());
        System.out.println("Введите новую фамилию");
        user.setLname(reader.readLine());
        System.out.println("Введите возраст");
        user.setAge(Integer.parseInt(reader.readLine()));
        System.out.println("Введите дату рождения в формате: " + SIMPLE_DATE_FORMAT_PATTERN);
        String stringDate = reader.readLine();
        java.util.Date javaDate = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN).parse(stringDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(javaDate);
        calendar.add(Calendar.DATE, 1);
        javaDate = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        user.setDate(sqlDate);
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    public void deleteUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            User user = new User();
            System.out.println("Введите номер пользователя которого хотите удалить");
            user.setId(Integer.parseInt(reader.readLine()));
            User user1 = (User) session.get(User.class, user.getId());
            session.delete(user1);
            session.getTransaction().commit();
            HibernateUtil.shutdown();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void printAllUser() throws SQLException, ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = criteria.list();
        for (User s : list)
            System.out.println(s);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}