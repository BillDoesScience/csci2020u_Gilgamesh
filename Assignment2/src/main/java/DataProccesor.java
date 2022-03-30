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

public class DataProccesor {
    private static File CSVFile;
    private static String[] GraphData;

    public DataProccesor(String FilePath) throws IOException, ParserConfigurationException {
        CSVFile = new File(FilePath);
        LoadFile();
    }

    public static String[] getGraphData() {
        return GraphData;
    }

    public static void WriteXMlFile(Document doc, String XML) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream(XML)));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static Document XMLGen() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        return docBuilder.newDocument();
    }

    public static void LoadFile() throws IOException, ParserConfigurationException {
        String row;
        String [] Headers = new String [8];
        long[] Max = new long [11];
        long[] Min = new long [9];
        int Counter = 0,avg_85=0,avg_00=0;
        Arrays.fill(Min,0,8,-1);

        Document doc = XMLGen();
        Document doc_stats = XMLGen();

        Element rootElement = doc.createElement("Airline_Stats");
        doc.appendChild(rootElement);

        Element rootElement2 = doc_stats.createElement("Summary");
        doc_stats.appendChild(rootElement2);

        if (CSVFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(CSVFile));

            Headers = (csvReader.readLine()).split(",");
            String[] Headers_new = Arrays.copyOf(Headers,Headers.length+3);
            Headers_new[Headers.length] = "Total_Incidents_85_14";
            Headers_new[Headers.length+1] = "avg_85_99";
            Headers_new[Headers.length+2] = "avg_00_14";


            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                String[] data_new = Arrays.copyOf(data,data.length+1);
                data_new[data.length] = Integer.toString((Integer.parseInt(data[2]) + Integer.parseInt(data[5])));

                GraphData[Counter] = data[0] + "," + data[4] + "," + data[7];
                Counter++;
                data = null;

                Element airline = doc.createElement("Airline");
                rootElement.appendChild(airline);
                airline.setAttribute("name",data_new[0]);

                for (int i = 1; i < 9;i++) {
                    Element seats = doc.createElement(Headers_new[i]);
                    seats.setTextContent(data_new[i]);
                    airline.appendChild(seats);

                    if (Long.parseLong(data_new[i]) > Max[i]) {
                        if (Min[i] == -1) {
                            Min[i] = Max[i];
                        }
                        Max[i] = Long.parseLong(data_new[i]);
                    } else if (Long.parseLong(data_new[i]) < Min[i]) {
                        Min[i] = Long.parseLong(data_new[i]);
                    }
                }

                avg_85 += Integer.parseInt(data_new[2]);
                avg_00 += Integer.parseInt(data_new[5]);

            }
            csvReader.close();
            Max[Headers.length+1] = avg_85/Counter;
            Max[Headers.length+2] = avg_00/Counter;
            Headers = Headers_new;
        }



        for (int j = 1; j < 11; j++) {
            Element Stats = doc_stats.createElement("Stat");
            rootElement2.appendChild(Stats);
            System.out.println(Headers.length);
            if (j < 9) {
                Element name = doc_stats.createElement("Name");
                name.setTextContent(Headers[j]);
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
                Element name = doc_stats.createElement("Name");
                name.setTextContent(Headers[j]);
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

        WriteXMlFile(doc,"./src/main/resources/converted_airline_safety");
        WriteXMlFile(doc_stats,"./src/main/resources/airline_summary_statistic");
    }

}
