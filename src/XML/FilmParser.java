package XML;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class FilmParser {
    public static Document readFile(File filmFile) {
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader(filmFile);
            Document rDoc = parser.build(fr);
            System.out.println(rDoc.getRootElement().getName());
            List<Element> temp = rDoc.getRootElement().getChildren();
            for (int i = 0; i < temp.size(); ++i) {
                System.out.println(temp.get(i).getName());
            }
            return rDoc;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
