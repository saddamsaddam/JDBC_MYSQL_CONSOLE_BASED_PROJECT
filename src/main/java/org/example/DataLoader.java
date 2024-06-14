package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
   public static boolean result;
    public static void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Connection conn = DatabaseConnection.getConnection()) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Personer:")) {
                    loadPersons(br, conn);
                   //
                } else if (line.startsWith("Museer:")) {
                    loadMuseums(br, conn);
                } else if (line.startsWith("Funn:")) {
                   loadFindings(br, conn);
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadPersons(BufferedReader br, Connection conn) throws IOException, SQLException {
        int count = Integer.parseInt(br.readLine());
        String sql = "INSERT INTO Person (Id, Navn, Tlf, E_post) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < count; i++) {
                int id = Integer.parseInt(br.readLine());
                String name = br.readLine();
                String phone = br.readLine();
                String email = br.readLine();

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, phone);
                pstmt.setString(4, email);
                // check if id exist or not
                if(!personIdCheck(id)){
                    pstmt.executeUpdate();
                }
                else{
                    System.out.println(id+" id of person already exist");
                }

            }
        }
    }

    public static boolean personIdCheck(int id){
       List<Person> dataList= personList();
            result=false;
       dataList.forEach(e->{
           if(e.getId()==id){
               result=true;
           }

       });
      return  result;
    }

    public static boolean museumIdCheck(int id){
        List<Museum> dataList= museumList();
        result=false;
        dataList.forEach(e->{
            if(e.getId()==id){
                result=true;
            }

        });
        return  result;
    }
    private static void loadMuseums(BufferedReader br, Connection conn) throws IOException, SQLException {
        int count = Integer.parseInt(br.readLine());
        String sql = "INSERT INTO Museum (Id, Navn, Sted) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < count; i++) {
                int id = Integer.parseInt(br.readLine());
                String name = br.readLine();
                String place = br.readLine();

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, place);
                if(!museumIdCheck(id)){
                    pstmt.executeUpdate();
                }
                else{
                    System.out.println(id+" id of Museum already exist");
                }

            }
        }
    }

    private static void loadFindings(BufferedReader br, Connection conn) throws IOException, SQLException {
        String sqlVaapen = "INSERT INTO Vaapen (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlSmykke = "INSERT INTO Smykke (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Verdiestimat, filnavn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlMynt = "INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Diameter, Metall) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String line;
        while ((line = br.readLine()) != null && !line.equals("")) {
            int id = Integer.parseInt(line);
            String location = br.readLine();
            int personId = Integer.parseInt(br.readLine());
            String foundDate = br.readLine();
            int year = Integer.parseInt(br.readLine());
            String museumIdLine = br.readLine();
            Integer museumId = museumIdLine.isEmpty() ? null : Integer.parseInt(museumIdLine);
            String type = br.readLine();

            switch (type) {
                case "Våpen":
                    String weaponType = br.readLine();
                    String material = br.readLine();
                    int weight = Integer.parseInt(br.readLine());
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlVaapen)) {
                        pstmt.setInt(1, id);
                        pstmt.setString(2, location);
                        pstmt.setInt(3, personId);
                        pstmt.setString(4, foundDate);
                        pstmt.setInt(5, year);
                        if (museumId != null) {
                            pstmt.setInt(6, museumId);
                        } else {
                            pstmt.setNull(6, java.sql.Types.INTEGER);
                        }
                        pstmt.setString(7, weaponType);
                        pstmt.setString(8, material);
                        pstmt.setInt(9, weight);

                        if(!vaapenIdCheck(id)){
                            pstmt.executeUpdate();
                        }
                        else{
                            System.out.println(id+" id of vaapen already exist");
                        }

                    }
                    break;
                case "Smykke":
                    String jewelryType = br.readLine();
                    int value = Integer.parseInt(br.readLine());
                    String fileName = br.readLine();
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlSmykke)) {
                        pstmt.setInt(1, id);
                        pstmt.setString(2, location);
                        pstmt.setInt(3, personId);
                        pstmt.setString(4, foundDate);
                        pstmt.setInt(5, year);
                        if (museumId != null) {
                            pstmt.setInt(6, museumId);
                        } else {
                            pstmt.setNull(6, java.sql.Types.INTEGER);
                        }
                        pstmt.setString(7, jewelryType);
                        pstmt.setInt(8, value);
                        pstmt.setString(9, fileName);

                        if(!smykkeIdCheck(id)){
                            pstmt.executeUpdate();
                        }
                        else{
                            System.out.println(id+" id of smykke already exist");
                        }
                    }
                    break;
                case "Mynt":
                    int diameter = Integer.parseInt(br.readLine());
                    String metal = br.readLine();
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlMynt)) {
                        pstmt.setInt(1, id);
                        pstmt.setString(2, location);
                        pstmt.setInt(3, personId);
                        pstmt.setString(4, foundDate);
                        pstmt.setInt(5, year);
                        if (museumId != null) {
                            pstmt.setInt(6, museumId);
                        } else {
                            pstmt.setNull(6, java.sql.Types.INTEGER);
                        }
                        pstmt.setInt(7, diameter);
                        pstmt.setString(8, metal);
                        if(!myntIdCheck(id)){
                            pstmt.executeUpdate();
                        }
                        else{
                            System.out.println(id+" id of mynt already exist");
                        }
                    }
                    break;
            }
            br.readLine(); // Skip the separator line
        }
    }


    public static List<Vaapen>  vaapenList(){
        List<Vaapen> dataList=new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Vaapen");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vaapen data=new Vaapen();
                data.setId(rs.getInt("Id"));
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public static boolean vaapenIdCheck(int id){
        List<Vaapen> dataList= vaapenList();
        result=false;
        dataList.forEach(e->{
            if(e.getId()==id){
                result=true;
            }

        });
        return  result;
    }
    public static List<Smykke>  smykkeList(){
        List<Smykke> dataList=new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Smykke");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Smykke data=new Smykke();
                data.setId(rs.getInt("Id"));
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public static boolean smykkeIdCheck(int id){
        List<Smykke> dataList= smykkeList();
        result=false;
        dataList.forEach(e->{
            if(e.getId()==id){
                result=true;
            }

        });
        return  result;
    }
    public static List<Mynt>  myntList(){
        List<Mynt> dataList=new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Mynt");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Mynt data=new Mynt();
                data.setId(rs.getInt("Id"));
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    public static boolean myntIdCheck(int id){
        List<Mynt> dataList= myntList();
        result=false;
        dataList.forEach(e->{
            if(e.getId()==id){
                result=true;
            }

        });
        return  result;
    }
    public static List<Person>  personList(){
        List<Person> dataList=new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Person");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Person data=new Person(rs.getInt("Id"),rs.getString("Navn"), rs.getString("Tlf"), rs.getString("E_post"));
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static List<Museum> museumList(){
        List<Museum> dataList=new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Museum");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Museum data=new Museum(rs.getInt("Id"),rs.getString("Navn"),rs.getString("Sted"));
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  dataList;
    }

    public static void listFindByYear(int year){
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt, NULL AS Verdiestimat, NULL AS Diameter, NULL AS Metall, 'Vaapen' AS Category FROM Vaapen " +
                             "UNION ALL " +
                             "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, NULL AS Materiale, NULL AS Vekt, Verdiestimat, NULL AS Diameter, NULL AS Metall, 'Smykke' AS Category FROM Smykke " +
                             "UNION ALL " +
                             "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, NULL AS Type, NULL AS Materiale, NULL AS Vekt, NULL AS Verdiestimat, Diameter, Metall, 'Mynt' AS Category FROM Mynt"
             );
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                if(rs.getInt("Antatt_årstall")<year){
                    System.out.println("Id: " + rs.getInt("Id"));
                    System.out.println("Location: " + rs.getString("Funnsted"));
                    System.out.println("Finder Id: " + rs.getInt("Finner_id"));
                    System.out.println("Found Date: " + rs.getString("Funntidspunkt"));
                    System.out.println("Year: " + rs.getInt("Antatt_årstall"));
                    System.out.println("Museum Id: " + rs.getInt("Museum_id"));
                    System.out.println("Type: " + rs.getString("Type"));
                    System.out.println("Material: " + rs.getString("Materiale"));
                    System.out.println("Weight: " + rs.getInt("Vekt"));
                    System.out.println("Estimated Value: " + rs.getInt("Verdiestimat"));
                    System.out.println("Diameter: " + rs.getInt("Diameter"));
                    System.out.println("Metal: " + rs.getString("Metall"));
                    System.out.println("Category: " + rs.getString("Category"));
                    System.out.println("----------------------------------");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void listFindings() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, Materiale, Vekt, NULL AS Verdiestimat, NULL AS Diameter, NULL AS Metall, 'Vaapen' AS Category FROM Vaapen " +
                             "UNION ALL " +
                             "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, Type, NULL AS Materiale, NULL AS Vekt, Verdiestimat, NULL AS Diameter, NULL AS Metall, 'Smykke' AS Category FROM Smykke " +
                             "UNION ALL " +
                             "SELECT Id, Funnsted, Finner_id, Funntidspunkt, Antatt_årstall, Museum_id, NULL AS Type, NULL AS Materiale, NULL AS Vekt, NULL AS Verdiestimat, Diameter, Metall, 'Mynt' AS Category FROM Mynt"
             );
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("Id"));
                System.out.println("Location: " + rs.getString("Funnsted"));
                System.out.println("Finder Id: " + rs.getInt("Finner_id"));
                System.out.println("Found Date: " + rs.getString("Funntidspunkt"));
                System.out.println("Year: " + rs.getInt("Antatt_årstall"));
                System.out.println("Museum Id: " + rs.getInt("Museum_id"));
                System.out.println("Type: " + rs.getString("Type"));
                System.out.println("Material: " + rs.getString("Materiale"));
                System.out.println("Weight: " + rs.getInt("Vekt"));
                System.out.println("Estimated Value: " + rs.getInt("Verdiestimat"));
                System.out.println("Diameter: " + rs.getInt("Diameter"));
                System.out.println("Metal: " + rs.getString("Metall"));
                System.out.println("Category: " + rs.getString("Category"));
                System.out.println("----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
