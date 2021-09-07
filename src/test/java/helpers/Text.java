package helpers;

import com.google.gson.*;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

import static com.google.gson.JsonParser.parseString;

@SuppressWarnings("unused")
public class Text {
    /**
     * @param input - Something that you hope is JSON
     * @return - A string that has line-breaks and indentations
     * <p>
     * If the input can not be parsed, you will get back whatever you put in
     */
    public static String indentJson(String input) {
        try {
            if (input != null) {
                String json;
//                JsonParser parser = new JsonParser();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement el = parseString(input);
                json = gson.toJson(el);

                return json;
            }
        } catch (JsonSyntaxException ignore) {
            // This is being fairly robust in that, if it can't be parsed, then the input is thrown back out
        }
        return input;
    }

    /**
     * @param input - A string that (hopefully) contains good XML
     * @return - a String that has line-breaks ind indentations, OR just an error message
     */
    public static String indentXML(String input) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            // Could be one of a couple. Don't send back the XML as output, it may not be nice enough to print out
            e.printStackTrace();
            return "Not XML or could not be parsed";
        }
    }

    /**
     * XML may not show nicely, even if you have indented it, so this makes it work nicely when displayed on a web page (report)
     *
     * @param xmlToConvert - - A string that (hopefully) contains good XML, possibly from indentXML
     * @return - a string that should show well in an HTML report
     */
    public static String convertXMLtoHTMLFriendly(String xmlToConvert) {
        xmlToConvert = indentXML(xmlToConvert);
        xmlToConvert = xmlToConvert.replace("<", "&lt;");
        xmlToConvert = xmlToConvert.replace(">", "&gt;");
        return xmlToConvert;
    }
}
