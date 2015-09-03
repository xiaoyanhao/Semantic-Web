import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;

import java.io.*;

/**
 * Created by xiaoyanhao on 15/9/2.
 */
public class JenaManipulation {
  public static void main(String[] args) {
    String inputFileName = "src/main/resources/13331290_assignment_1.rdf";
    String resourceURL = "http://xmlns.com/foaf/0.1/PersonalProfileDocument";

    Model model = ModelFactory.createDefaultModel();

    InputStream in = FileManager.get().open(inputFileName);
    if (in == null) {
      throw new IllegalArgumentException("File: " + inputFileName + " not found");
    }

    model.read(in, "");
    Resource myself = model.getResource(resourceURL);

    myself
      .addProperty(FOAF.knows, model.createResource(FOAF.Person)
        .addProperty(FOAF.depiction, model.createResource()
          .addProperty(FOAF.name, "Huang Jin")
          .addProperty(FOAF.gender, "male")
          .addProperty(FOAF.surname, "Azrael")
          .addProperty(FOAF.title, "Soldier")
          .addProperty(FOAF.mbox, "270411549@qq.com")
          .addProperty(FOAF.interest, "Basketball and computer games")
        )
      );

    model.write(System.out, "TURTLE");
    System.out.println();
    model.write(System.out, "RDF/XML-ABBREV");
  }
}
