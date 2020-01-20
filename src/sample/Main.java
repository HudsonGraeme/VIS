package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Main.java
 * Author: Spencer Graham
 * Date: January 17 2020
 * School: Ancaster High School
 * Purpose: A Vehicle Information System (VIS). This program displays and sorts a variety of vehicle data loaded from a Comma Separated Values (CSV) file.
 */



public class Main extends Application {
    /**
     * Launch the Java application
     * @param args - Launch the application with arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the JavaFX application
     * @param primaryStage - the stage/window the app will use to show the generated scene
     */
    @Override
    public void start(Stage primaryStage) {

        // Create a new File Chooser
        // Add limitations so the only files this application can open are Comma Separated Value files
        // Set the initial directory to the current directory of this file

        // The purpose of setting the directory to here is to give the user easy access to a template CSV ("VehicleInformation.CSV")
        // which holds example Data

        // Open the File Chooser dialog and wait for the user to select a file

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Comma Separated Values Files", "*.csv")
        );
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(primaryStage);

        // Build the scene using the scene builder method and pass the selected file to the scene

        Scene scene = buildScene(file);

        // Set the stage scene
        // Add our stylesheet
        // Set enable fullscreen (UI Looks much better in Fullscreen)
        // Set the window Title and Icon
        // Show our window (stage)

        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add(Main.class.getResource("style.css").toString());
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Vehicle Information System");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        primaryStage.show();

    }


    /**
     * Build the scene which will occupy the stage
     * @param file - The loaded CSV File
     * @return - The scene containing all of the subviews
     */
    private static Scene buildScene(File file) {

        // After the user selects a valid CSV file, a list of VehicleData is generated from the readCSVData function

        List list = readCSVData(file);

        TableView<?> mainTable = generateMainTableView(list);
        TableView<?> colorTable = generateColorTableView(list);
        TableView<?> mfgTable = generateManufacturerTableView(list);
        TableView<?> modelTable = generateModelTableView(list);
        TableView<?> yearTable = generateYearTableView(list);

        // Create the lower Pane view as a HBox
        // The purpose of this HBox is to horizontally split auxiliary tables

        HBox lowerBox = new HBox();
        lowerBox.getChildren().add(colorTable);
        lowerBox.getChildren().add(mfgTable);
        lowerBox.getChildren().add(modelTable);
        lowerBox.getChildren().add(yearTable);

        // Create a vertical SplitPane
        // Pane 0 is our main Table
        // Pane 1 is a HBox consisting of sub-tables arranged horizontally

        SplitPane layout = new SplitPane();
        layout.orientationProperty().set(Orientation.VERTICAL);
        layout.getItems().add(mainTable);
        layout.getItems().add(lowerBox);

        // Return the scene, with our SplitPane of TableViews as it's layout with the specified dimensions

        return new Scene(layout, 300, 250);
    }

    /**
     * Generate a TableView of Model, Manufacturer and corresponding vehicle sales data
     * @param list - The list of VehicleData from the loaded CSV file
     * @return - A TableView containing Manufacturer, Model vs Sales columns
     */
    private static TableView<?> generateModelTableView (List<VehicleData> list) {
        // Create a new list of ModelSales objects
        List<ModelSales> modelSalesList = new ArrayList<>();

        // Filter through the list of VehicleData
        for (int i = 0;i<list.size();i++) {

            // Set the manufacturer and model properties and initialize the count
            // Later we will add these values to a list of vehicle sales ModelSales objects
            String manufacturer = list.get(i).getMake();
            String model = list.get(i).getModel();
            int count = 0;

            // Filter through the VehicleData list
            for (VehicleData vehicleData : list) {
                // If a vehicle sale exists for the same model and make then add it to the count
                if (list.get(i).getMake().equals(vehicleData.getMake()) && list.get(i).getModel().equals(vehicleData.getModel())) {
                    count++;
                }
            }
            // Create a new object of ModelSales with the given parameters
            ModelSales sales = new ModelSales(manufacturer,model,count);

            // Check if the sales object already exists in the context of the modelSalesList. Add it to the list if it does not exist
            boolean flag = true;
            for (ModelSales s : modelSalesList) {
                    if(sales.getModel().equals(s.getModel())) {
                        flag = false;
                    }
            }
            if(flag) {
                modelSalesList.add(sales);
            }


        }


        // This stores how many vehicles exist of each make
        // Initialize the Manufacturer Table

        TableView<Object> modelTable = new TableView<>();

        // Create a new TableColumn storing the Makes

        TableColumn<Object,Object> mfgTCol = new TableColumn<>("Make");
        mfgTCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        // Create a new TableColumn storing the Models

        TableColumn<Object,Object> modelTCol = new TableColumn<>("Model");
        modelTCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        // Create a new TableColumn storing the count of vehicles

        TableColumn<Object, Object> countTCol = new  TableColumn<>("Count");
        countTCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        // Set the column widths; each column should be half the width of the Model Table
        modelTCol.prefWidthProperty().bind(modelTable.widthProperty().multiply(0.333));
        mfgTCol.prefWidthProperty().bind(modelTable.widthProperty().multiply(0.333));
        countTCol.prefWidthProperty().bind(modelTable.widthProperty().multiply(0.333));

        // Add our columns to the Model Table (Make, Model, Count)
        // Add our List of Model Sales objects to the table (Make, Model, Count)

        modelTable.getColumns().addAll(mfgTCol,modelTCol,countTCol);
        modelTable.getItems().addAll(modelSalesList);
        return modelTable;
    }

    /**
     *
     * @param list - The list of VehicleData from the loaded CSV file
     * @return - A TableView containing Manufacturer vs Sales columns
     */
    private static TableView<?> generateManufacturerTableView (List<VehicleData> list) {
        Map<String,Integer> mfgSales = new HashMap<>();

        List<MfgSales> mfgSalesList = new ArrayList<>();

        // Filter through the list of vehicle data and check for multiple occurrences of each manufacturer.
        // If there is an occurrence add it to the total count for that manufacturer
        // Finally, add the manufacturer name and total vehicles matching that manufacturer to the mfgSalesList
        for (int i = 0;i<list.size();i++) {

            mfgSales.put(list.get(i).getMake(),0);
            for (VehicleData vehicleData : list) {

                if (list.get(i).getMake().equals(vehicleData.getMake())) {
                    int z = mfgSales.get(list.get(i).getMake());
                    z++;
                    mfgSales.put(list.get(i).getMake(), z);

                }
            }
        }
        // For each value in the Hash Map of Manufacturer, Count, add the value at n to a List
        mfgSales.forEach((key,value) -> {
            MfgSales sales = new MfgSales(key,value);
            mfgSalesList.add(sales);
        });


        // This stores how many vehicles exist of each make
        // Initialize the Manufacturer Table

        TableView<Object> mfgTable = new TableView<>();

        // Create a new TableColumn storing the Makes

        TableColumn<Object,Object> mfgTCol = new TableColumn<>("Vehicle Make");
        mfgTCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        // Create a new TableColumn storing the count of vehicles

        TableColumn<Object, Object> countTCol = new  TableColumn<>("Count");
        countTCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        // Set the column widths; each column should be half the width of the Manufacturer Table

        mfgTCol.prefWidthProperty().bind(mfgTable.widthProperty().multiply(0.5));
        countTCol.prefWidthProperty().bind(mfgTable.widthProperty().multiply(0.5));

        // Add our columns to the Manufacturer Table (Make, Count)
        // Add our List of Manufacturer Sales objects to the table (Make, Count)

        mfgTable.getColumns().addAll(mfgTCol,countTCol);
        mfgTable.getItems().addAll(mfgSalesList);
        return mfgTable;
    }

    /**
     * Finds the number of vehicles associated with each individual's name and attaches it to the list of VehicleData
     * @param list - The list of VehicleData from the loaded CSV file
     */
    private static void attachVehicleCounts (List<VehicleData> list) {
        // Initialize a new Hash Map containing ID keys and count values for each person in the data
        Map<Integer,Integer> vehicles = new HashMap<>();

        // Filter through the list of loaded vehicle data
        for (int i = 0;i<list.size();i++) {
            // Put a new value of 0 into the id key in the Hash Map
            vehicles.put(i,0);
            // Filter through the list and check if the name of the person with id = i matches any other values in the list
            // If there are more than occurrence of a name, that means the person has more than one vehicle; add one to the Hash Map
            for (VehicleData vehicleData : list) {
                if (list.get(i).getFirstName().equals(vehicleData.getFirstName()) && list.get(i).getLastName().equals(vehicleData.getLastName())) {
                    int y = vehicles.get(i);
                    y++;
                    // Update the Hash Map with an additional count
                    vehicles.put(i, y);
                }
            }

        }
        // For each id and count, add the contents of the list at n to the list as the count property indicating how many vehicles each owner owns
        vehicles.forEach((key, value) -> list.get(key).setCount(value));
    }

    /**
     * Generates a TableView of Colour vs Sales data
     * @param list - The list of VehicleData from the loaded CSV file
     * @return - A TableView containing Color vs Sales columns
     */
    private static TableView<?> generateColorTableView (List<VehicleData> list) {
        // Initialize a new Hash Map containing Color, Count values
        Map<String,Integer> colorSales = new HashMap<>();
        // Initialize a new list of ColorSales objects. This will be used for our Table Column Cell Factories later on
        List<ColourSales> colorSalesList = new ArrayList<>();
        // Filter through the list of VehicleData
        for (int i = 0;i<list.size();i++) {
            // If the colour has not already been initialized in the map, create a 0 as it's value
            if (!colorSales.containsKey(list.get(i).getColor())) {
                colorSales.put(list.get(i).getColor(), 0);
                 }
            // Filter through the list of VehicleData
            for (VehicleData vehicleData : list) {
                // If a vehicle's colour matches the color at list.get(i).getColor() then add one to the Hash Map for the colorKey
                if (list.get(i).getColor().equals(vehicleData.getColor())) {
                    int z = colorSales.get(list.get(i).getColor());
                    z++;
                    colorSales.put(list.get(i).getColor(), z);
                }
            }
        }
        // For each value in the Hash Map of Color, Count, add the value at n to a List
        colorSales.forEach((key,value) -> {
            ColourSales sales = new ColourSales(key,value);
            colorSalesList.add(sales);
        });

        // This stores how many vehicles exist of each colour
        // Initialize the Color Table

        TableView<Object> colorTable = new TableView();

        // Create a new TableColumn storing the Colours

        TableColumn<Object,Object> colorTCol = new TableColumn<>("Vehicle Colour");
        colorTCol.setCellValueFactory(new PropertyValueFactory<>("colour"));

        // Create a new TableColumn storing the count of vehicles

        TableColumn<Object, Object> countTCol = new  TableColumn<>("Count");
        countTCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        // Set the column widths; each column should be half the width of the Color Table

        colorTCol.prefWidthProperty().bind(colorTable.widthProperty().multiply(0.5));
        countTCol.prefWidthProperty().bind(colorTable.widthProperty().multiply(0.5));

        // Add our columns to the Color Table (Colour, Count)
        // Add our List of ColourSales objects to the table (Colour, Count)

        colorTable.getColumns().addAll(colorTCol,countTCol);
        colorTable.getItems().addAll(colorSalesList);
        return colorTable;
    }

    /**
     * Read the contents of the CSV file and parse it into a List of VehicleData
     * @param file - The CSV File containing the Vehicle Information System Data
     * @return - A List of VehicleData using the data from the file
     */

    private static List<VehicleData> readCSVData (File file) {

        // Create a new List of Vehicle Data to temporarily store our VehicleData coming from the CSV file

        List<VehicleData> infos = new ArrayList();

        // Try to create a new Buffered Reader for reading the file's contents

        try (BufferedReader br = Files.newBufferedReader(file.toPath(),
                StandardCharsets.UTF_8)) {
            // The first readLine is there so the first line in the file which contains strings of column headers is ignored
            // For example
            // Line 0 = ("first_name", "last_name", "id", "VIN", "year1") || br.readLine(); // Ignore this value
            // Line 1 = ("Spencer", "Graham", 100, "283JBUWSC8SFC2", 2018) || String line = br.readLine(); // Use this value

            br.readLine();
            String line = br.readLine();

            // While the line is not null then split the line by a comma (Values are Comma Separated) into an array of line attributes
            // Pass all of the attributes to the createVehicle method and store the result in an instance of VehicleData
            // Add the 'info' of type VehicleData to the List of VehicleData 'infos'
            // Read the next line
            while (line != null) {
                String[] attributes = line.split(",");
                VehicleData info = createVehicle(attributes);
                infos.add(info);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            // If the file cannot be read or is not in UTF8 encoding, throw an error and print the Stack Trace
            ioe.printStackTrace();
        }

        return infos;
    }

    /**
     * Generate one VehicleData object for each line in the CSV document; accept metadata as formatted input
     * @param metadata - An array of String values from the CSV document
     * @return - An instance of VehicleData with the metadata attributes as a class constructor
     */
    private static VehicleData createVehicle(String[] metadata) {

        // Parse the CSV file into parameters for initializing a VehicleData Class

        String firstName = metadata[1];
        String lastName = metadata[2];
        String email = metadata[3];
        String VIN = metadata[4];
        String make = metadata[5];
        String model = metadata[6];
        String color = metadata[8];
        String plateNumber = metadata[9];

        int id = Integer.parseInt(metadata[0]);
        int modelYear = Integer.parseInt(metadata[7]);
        int year1 = Integer.parseInt(metadata[10]);
        int odo1 = Integer.parseInt(metadata[11]);
        int year2 = Integer.parseInt(metadata[12]);
        int odo2 = Integer.parseInt(metadata[13]);
        int year3 = Integer.parseInt(metadata[14]);
        int odo3 = Integer.parseInt(metadata[15]);
        int year4 = Integer.parseInt(metadata[16]);
        int odo4 = Integer.parseInt(metadata[17]);
        int year5 = Integer.parseInt(metadata[18]);
        int odo5 = Integer.parseInt(metadata[19]);

        // year# - The year which the corresponding odo#

        // Create a new instance of VehicleData and return the newly made instance

        return new VehicleData(id, firstName,lastName,email,VIN,make,model,modelYear,color,plateNumber,year1,odo1,year2,odo2,year3,odo3,year4,odo4,year5,odo5);
    }

    /**
     * Generate the top table view containing all of the CSV data plus several generated values and excluding the odometer readings for five years
     * @param list - A list of VehicleData loaded from the CSV
     * @return - The main Table View which will be displayed at the top of the Scene
     */
    private static TableView<?> generateMainTableView(List list) {
        // Create a new Table which will store our loaded VehicleData

        TableView<?> mainTable = new TableView<>();

        // Create a new TableColumn storing ID values and set the column's width ratio in the Main Table

        TableColumn idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.03));

        // Create a new TableColumn storing First Name values and set the column's width ratio in the Main Table

        TableColumn firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.08));

        // Create a new TableColumn storing Last Name values and set the column's width ratio in the Main Table

        TableColumn lastNameCol = new  TableColumn<>("Last Name");
        lastNameCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.08));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        // Create a new TableColumn storing email values

        TableColumn emailCol = new  TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Create a new TableColumn storing Vehicle VIN values

        TableColumn VINCol = new  TableColumn<>("VIN");
        VINCol.setCellValueFactory(new PropertyValueFactory<>("VIN"));

        // Create a new TableColumn storing Vehicle Make values

        TableColumn makeCol = new  TableColumn<>("Make");
        makeCol.setCellValueFactory(new PropertyValueFactory<>("make"));

        // Create a new TableColumn storing Vehicle Model values

        TableColumn modelCol = new  TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));

        // Create a new TableColumn storing Model Year values and set the column's width ratio in the Main Table

        TableColumn modelYearCol = new  TableColumn<>("Model Year");
        modelYearCol.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
        modelYearCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.08));

        // Create a new TableColumn storing Color values and set the column's width ratio in the Main Table

        TableColumn colorCol = new  TableColumn<>("Color");
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        colorCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.05));

        // Create a new TableColumn storing Plate Number values and set the column's width ratio in the Main Table

        TableColumn plateNumberCol = new  TableColumn<>("Plate Number");
        plateNumberCol.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        plateNumberCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.1));

        /*

        // This code exists for the purpose of displaying auxiliary data.
        // Since the project's requirement document did not list displaying each year and corresponding odometer reading,
        // this feature has not been implemented

        List<TableColumn readingColumns = new ArrayList();
        for (int i = 0;i<5;i++) {
            TableColumn yCol = new  TableColumn<>(String.format("Year %d",i+1));
            yCol.setCellValueFactory(new PropertyValueFactory<>(String.format("year%d", i+1)));
            mainTable.getColumns().addAll(yCol);
            TableColumn oCol = new  TableColumn<>(String.format("Odometer Reading %d",i+1));
            oCol.setCellValueFactory(new PropertyValueFactory<>(String.format("odometer%d",i+1)));
            mainTable.getColumns().addAll(oCol);
        }
        mainTable.getColumns().addAll(readingColumns);


        */

        // Create a new TableColumn storing Sticker values and set the column's width ratio in the Main Table

        TableColumn mileageCol = new  TableColumn<>("5 Year Mileage");
        mileageCol.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        mileageCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.1));

        // Create a new TableColumn storing Vehicle Count values and set the column's width ratio in the Main Table
        // Call the method to attach the vehicle count property to the list
        attachVehicleCounts(list);
        TableColumn countCol = new TableColumn<>("Number of Vehicles");
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        countCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.13));

        // Create a new TableColumn storing Sticker values and set the column's width ratio in the Main Table

        TableColumn stickerValidCol = new  TableColumn<>("Sticker");
        stickerValidCol.setCellValueFactory(new PropertyValueFactory<>("stickerValid"));
        stickerValidCol.prefWidthProperty().bind(mainTable.widthProperty().multiply(0.05));

        // Add all of our generated columns to the Main Table
        // Add all of our list data (List of VehicleData) to the Main Table

        mainTable.getColumns().addAll(idCol,firstNameCol,lastNameCol,emailCol,VINCol,makeCol,modelCol,modelYearCol,colorCol,plateNumberCol,mileageCol,stickerValidCol,countCol);
        mainTable.getItems().addAll(list);
        return mainTable;
    }

    /**
     * Generates a TableView of Years and corresponding sales data for that year
     * @param list - The list of VehicleData loaded from the CSV file
     * @return - A TableView containing Year vs Sales Data
     */
    private static TableView<?> generateYearTableView(List<VehicleData> list) {
        Map<Integer,Integer> yearSales = new HashMap<>();

        List<YearSales> yearSalesList = new ArrayList<>();

        // Filter through the list of VehicleData and check to see how many vehicles math the modelYear property
        // Add this value to the count and finally add the year and count to the yearSalesList
        for (int i = 0;i<list.size();i++) {

            yearSales.put(list.get(i).getModelYear(),0);
            for (VehicleData vehicleData : list) {
                if (list.get(i).getModelYear() == vehicleData.getModelYear()) {
                    int z = yearSales.get(list.get(i).getModelYear());
                    z++;
                    yearSales.put(list.get(i).getModelYear(), z);
                }
            }
        }
        // For each value in the Hash Map of Year, Count, add the value at n to a List
        yearSales.forEach((key,value) -> {
            YearSales sales = new YearSales(key,value);
            yearSalesList.add(sales);
        });

        // This stores how many vehicles exist of each year
        // Initialize the Year Table

        TableView<Object> yearTable = new TableView();

        // Create a new TableColumn storing the Years

        TableColumn<Object,Object> yearTCol = new TableColumn<>("Year");
        yearTCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        // Create a new TableColumn storing the count of vehicles

        TableColumn<Object, Object> countTCol = new  TableColumn<>("Sales");
        countTCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        // Set the column widths; each column should be half the width of the Year Table

        yearTCol.prefWidthProperty().bind(yearTable.widthProperty().multiply(0.5));
        countTCol.prefWidthProperty().bind(yearTable.widthProperty().multiply(0.5));

        // Add our columns to the Color Table (Year, Count)
        // Add our List of ColourSales objects to the table (Year, Count)

        yearTable.getColumns().addAll(yearTCol,countTCol);
        yearTable.getItems().addAll(yearSalesList);
        return yearTable;
    }


}
