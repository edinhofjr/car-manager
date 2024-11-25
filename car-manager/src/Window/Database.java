package Window;

import Entities.Car;
import Entities.Country;
import Entities.Make;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database {
    private static Database instance;
    private Connection conn;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private Database() {
        String url = "jdbc:postgresql://localhost:5432/manager";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "2005");

        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCountry(Country c) {
        try {
            String insertCountry = "INSERT INTO pais (nome) VALUES (?)";
            PreparedStatement pStmt = conn.prepareStatement(insertCountry);
            pStmt.setString(1, c.getName().toUpperCase());
            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pais");

            while (rs.next()) {
                Country c = new Country(rs.getInt("id"), rs.getString("nome"));
                System.out.println(c);
                countries.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public void addMake(Make m) {
        String addMake = "INSERT INTO marca (nome, pais_origem) VALUES ( ? , ? );";

        try {
            PreparedStatement pStmt = conn.prepareStatement(addMake);
            pStmt.setString(1, m.getName());
            pStmt.setInt(2, m.getCountry().getId());
            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<>();

        try {
            String sql = "SELECT carro.*, marca.nome, pais.* FROM carro\n" +
                    "INNER JOIN marca  ON carro.id_marca = marca.id\n" +
                    "INNER JOIN pais ON marca.pais_origem = pais.id;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {

                    int cc = rsmd.getColumnCount();
                    for (int i = 1; i <= cc; i++) {
                        System.out.println(rsmd.getColumnName(i));
                    }
                Country c = new Country(rs.getInt(8), rs.getString(9));
                Make m = new Make(rs.getInt(6),rs.getString(7),c);
                Car car = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        m
                );

                cars.add(car);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return cars;
    }
}
