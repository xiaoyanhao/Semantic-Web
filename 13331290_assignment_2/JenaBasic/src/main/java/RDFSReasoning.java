import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.query.*;


/**
 * Created by xiaoyanhao on 15/9/6.
 */
public class RDFSReasoning {
  public static void main(String[] args) {
    String inputFileName = "src/main/resources/13331290_assignment_3.rdf";

    Model data = FileManager.get().loadModel(inputFileName);
    InfModel infmodel = ModelFactory.createRDFSModel(data);

    String[] instance = {"Museion", "ChickenHut"};

    for (int i = 0; i < instance.length; i++) {
      String sparql = String.format(
        "SELECT * WHERE { <%s> ?X ?Y .}", "http://example.org/inst/" + instance[i]
      );

      System.out.println("SPARQL Query:");
      System.out.println(sparql);
      System.out.println();
      System.out.println("New RDFS-inferred triples about " + instance[i] + " :");

      Query query = QueryFactory.create(sparql);

      try (QueryExecution qe = QueryExecutionFactory.create(query, infmodel)) {
        ResultSet res = qe.execSelect();
        res.forEachRemaining(System.out::println);
      }
      System.out.println();
    }
  }
}

/*
Result:

  SPARQL Query:
  SELECT * WHERE { <http://example.org/inst/Museion> ?X ?Y .}

  New RDFS-inferred triples about Museion :
  ( ?Y = <http://example.org/term/modernArtMuseum> ) ( ?X = rdf:type ) -> [Root]
  ( ?Y = <http://example.org/term/museum> ) ( ?X = rdf:type ) -> [Root]
  ( ?Y = <http://example.org/term/artMuseum> ) ( ?X = rdf:type ) -> [Root]
  ( ?Y = <http://example.org/term/tourismAttraction> ) ( ?X = rdf:type ) -> [Root]

  SPARQL Query:
  SELECT * WHERE { <http://example.org/inst/ChickenHut> ?X ?Y .}

  New RDFS-inferred triples about ChickenHut :
  ( ?Y = 3.5 ) ( ?X = <http://example.org/term/hasTripAdvisorRating> ) -> [Root]
  ( ?Y = 3.5 ) ( ?X = <http://example.org/term/hasRating> ) -> [Root]
  ( ?Y = <http://example.org/term/tourismObject> ) ( ?X = rdf:type ) -> [Root]
  ( ?Y = rdfs:Resource ) ( ?X = rdf:type ) -> [Root]

*/