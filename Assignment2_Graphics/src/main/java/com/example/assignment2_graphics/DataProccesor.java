package com.example.assignment2_graphics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Arrays;

/**
 * <h1>Data Processor Class</h1>
 * Handles all the Data Proccessing and Creation of the XML Converion of the File
 * This Object also creates the summary data from the passed CSV File
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-03-22
 */

public class DataProccesor {
    /** The File that is being Processed
     */
    private final File CSVFile;
    /** Contains the String Data that has to be plotted
     */
    protected String[] GraphData = new String[1];

    /**
     * @param FilePath - String to the File That is to be Processed
     * @throws IOException - IOException from LoadFile(), Which throws the error if their is an error related to the file
     * @throws ParserConfigurationException - ParserConfigExcept. Passed from LoadFile if there is an error in the XML File Config
     */
    public DataProccesor(String FilePath) throws IOException, ParserConfigurationException {
        CSVFile = new File(FilePath);
        LoadFile();
    }

    /** Getter Function for The Graph Data
     * @return - String Array containing the relevant data to be graphed
     */
    public String[] getGraphData() {
        return GraphData;
    }

    /** Writes out the XML File to the specified location
     * @param doc - The XML Document File that is to be Written
     * @param XML - The String Location of where the file should output
     */
    public void WriteXMlFile(Document doc, String XML) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
            tr.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream(XML)));

        } catch (TransformerException | IOException te) {
            System.out.println(te.getMessage());
        }
    }

    /** Generates a Document Object and passes it back
     * @return - Empty Document Object
     * @throws ParserConfigurationException - Throws an Error if the XML File fails to build properly
     */
    public Document XMLGen() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        return docBuilder.newDocument();
    }

    /** Primary Driver File for the Object
     * @throws IOException - Error occurs from File Reading or Writing
     * @throws ParserConfigurationException - Error Occurs from XMLGen()
     */
    public void LoadFile() throws IOException, ParserConfigurationException {
        // Inital variables
        String row;
        String [] Headers;
        long[] Max = new long [11];
        long[] Min = new long [9];
        int Counter = 0,avg_85=0,avg_00=0;
        Arrays.fill(Min,0,8,-1);

        //Generate Required Objects
        Document doc = XMLGen();
        Document doc_stats = XMLGen();

        // Set the Root Element for the Stats and XML File
        Element rootElement = doc.createElement("Airline_Stats");
        doc.appendChild(rootElement);

        Element rootElement2 = doc_stats.createElement("Summary");
        doc_stats.appendChild(rootElement2);


        if (CSVFile.isFile()) {
            //Read the CSV File
            BufferedReader csvReader = new BufferedReader(new FileReader(CSVFile));
            //Create the Headers
            Headers = (csvReader.readLine()).split(",");
            //Increase Headers by 3 and Add the extra Headers at the end
            String[] Headers_new = Arrays.copyOf(Headers,Headers.length+3);
            Headers_new[Headers.length] = "Total_Incidents_85_14";
            Headers_new[Headers.length+1] = "avg_85_99";
            Headers_new[Headers.length+2] = "avg_00_14";


            while ((row = csvReader.readLine()) != null) {
                // Split the CSV Data into a String Array
                String[] data = row.split(",");
                String[] data_new = Arrays.copyOf(data,data.length+1);
                // Add a new column which is the total number of incidents (Not Accidents)
                data_new[data.length] = Integer.toString((Integer.parseInt(data[2]) + Integer.parseInt(data[5])));

                //Add the Relevant Data into the GraphData Variable
                this.GraphData = Arrays.copyOf(this.GraphData,this.GraphData.length+1);
                this.GraphData[Counter] = data[0] + "," + data[4] + "," + data[7];
                Counter++;

                // Add the Main Airline Element
                Element airline = doc.createElement("Airline");
                rootElement.appendChild(airline);
                airline.setAttribute("name",data_new[0]);

                //Loop through the Related Columns
                for (int i = 1; i < 9;i++) {
                    // Generate the sub data for the Airline
                    Element seats = doc.createElement(Headers_new[i]);
                    seats.setTextContent(data_new[i]);
                    airline.appendChild(seats);

                    // Find Min and Max now to use Later
                    if (Long.parseLong(data_new[i]) > Max[i]) {
                        if (Min[i] == -1) {
                            Min[i] = Max[i];
                        }
                        Max[i] = Long.parseLong(data_new[i]);
                    } else if (Long.parseLong(data_new[i]) < Min[i]) {
                        Min[i] = Long.parseLong(data_new[i]);
                    }
                }
                // Average Calculations
                // Based on Incidents only
                avg_85 += Integer.parseInt(data_new[2]);
                avg_00 += Integer.parseInt(data_new[5]);

            }
            //Close File and Store Avg data in Max (Since we Won't need another Array)
            csvReader.close();
            Max[Headers.length+1] = avg_85/Counter;
            Max[Headers.length+2] = avg_00/Counter;


            // Loop Through and Generate the Summary
            for (int j = 1; j < 11; j++) {
                Element Stats = doc_stats.createElement("Stat");
                rootElement2.appendChild(Stats);
                // First 8 Columns Summarized
                if (j < 9) {
                    Element name = doc_stats.createElement("Name");
                    name.setTextContent(Headers_new[j]);
                    Stats.appendChild(name);

                    Element Min_Val = doc_stats.createElement("Min");
                    Min_Val.setTextContent(Long.toString(Min[j]));
                    Stats.appendChild(Min_Val);

                    Element Max_Val = doc_stats.createElement("Max");
                    Max_Val.setTextContent(Long.toString(Max[j]));
                    Stats.appendChild(Max_Val);

                    Element Avg = doc_stats.createElement("Avg");
                    Stats.appendChild(Avg);
                } else {
                    // The Last 2 Avg Columns
                    Element name = doc_stats.createElement("Name");
                    name.setTextContent(Headers_new[j]);
                    Stats.appendChild(name);

                    Element Min_Val = doc_stats.createElement("Min");
                    Stats.appendChild(Min_Val);

                    Element Max_Val = doc_stats.createElement("Max");
                    Stats.appendChild(Max_Val);

                    Element Avg = doc_stats.createElement("Avg");
                    Avg.setTextContent(Long.toString(Max[j]));
                    Stats.appendChild(Avg);
                }
            }
        }
        // File Write
        WriteXMlFile(doc,"./src/main/resources/converted_airline_safety");
        WriteXMlFile(doc_stats,"./src/main/resources/airline_summary_statistic");
    }

}
