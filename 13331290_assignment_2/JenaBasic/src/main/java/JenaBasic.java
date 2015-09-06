import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;


/**
 * Created by xiaoyanhao on 15/9/2.
 */
public class JenaBasic {
  public static void main(String[] args) {
    String url = "http://example.org/";

    Model model = ModelFactory.createDefaultModel();

    Property said = model.createProperty(url + "/property/said");
    Resource xiaoyanhao =
      model.createResource(url + "/resource/xiaoyanhao")
        .addProperty(said, "Hello World!", "en");

    // 方法一，根据TURTLE格式按标准直接输出
    model.write(System.out, "TURTLE");

    // 方法二，遍历按字符串输出
    StmtIterator iter = model.listStatements();

    while (iter.hasNext()) {
      Statement stmt = iter.nextStatement();
      Resource subject = stmt.getSubject();
      Property predicate = stmt.getPredicate();
      RDFNode object = stmt.getObject();

      System.out.print(subject.toString());
      System.out.print(" " + predicate.toString() + " ");
      if (object instanceof Resource) {
        System.out.print(object.toString());
      } else {
        System.out.print(" \"" + object.toString() + "\"");
      }
      System.out.println(" .");
    }
  }
}
