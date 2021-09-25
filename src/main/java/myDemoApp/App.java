/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package myDemoApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;

public class App {
  public String getGreeting() {
    return "Hello world.";
  }

  public static ArrayList<String> classPassers(ArrayList<String> students, ArrayList<Integer> midtermGrades,
      ArrayList<Integer> finalGrades, double midtermPercentage, double finalPercentage, double treshold) {
    ArrayList<String> passedStudents = new ArrayList<String>();
    double midtermPoint = 0;
    double finalPoint = 0;
    double totalPoint = 0;
    if (students == null) {
      return null;
    } else if (midtermGrades == null) {
      return null;
    } else if (finalGrades == null) {
      return null;
    }
    for (int i = 0; i < students.size(); i++) {
      midtermPoint = (midtermGrades.get(i) * midtermPercentage) / 100;
      finalPoint = (finalGrades.get(i) * finalPercentage) / 100;
      totalPoint = midtermPoint + finalPoint;
      if (totalPoint >= treshold) {
        passedStudents.add(students.get(i));
      }
    }
    if (passedStudents.isEmpty())
      passedStudents.add("no one passed");
    return passedStudents;
  }

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(App.class);
    logger.error("hello world");

    int port = Integer.parseInt(System.getenv("PORT"));
    port(port);
    logger.error("Current port number: "+ port);

    System.out.println(new App().getGreeting());

    get("/", (req, res) -> "Hello, World!!");

    get("/compute", (rq, rs) -> {
      Map<String, String> map = new HashMap<String, String>();
      map.put("result", "not computed yet!");
      return new ModelAndView(map, "compute.mustache");
    }, new MustacheTemplateEngine());

    post("/compute", (req, res) -> {
      // System.out.println(req.queryParams("input1"));
      // System.out.println(req.queryParams("input2"));

      String input1 = req.queryParams("input1");
      java.util.Scanner sc1 = new java.util.Scanner(input1);
      sc1.useDelimiter("[;\r\n]+");
      java.util.ArrayList<String> inputListStudents = new java.util.ArrayList<>();
      while (sc1.hasNext()) {
        inputListStudents.add(sc1.next().replaceAll("\\s", ""));
      }

      String input2 = req.queryParams("input2");
      java.util.Scanner sc2 = new java.util.Scanner(input2);
      sc2.useDelimiter("[;\r\n]+");
      java.util.ArrayList<Integer> inputListMidGrades = new java.util.ArrayList<>();
      while (sc2.hasNext()) {
        int value = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
        inputListMidGrades.add(value);
      }

      String input3 = req.queryParams("input3");
      java.util.Scanner sc3 = new java.util.Scanner(input3);
      sc3.useDelimiter("[;\r\n]+");
      java.util.ArrayList<Integer> inputListFinalGrades = new java.util.ArrayList<>();
      while (sc3.hasNext()) {
        int value = Integer.parseInt(sc3.next().replaceAll("\\s", ""));
        inputListFinalGrades.add(value);
      }

      String input4 = req.queryParams("input4").replaceAll("\\s", "");
      double input4AsDouble = Double.parseDouble(input4);

      String input5 = req.queryParams("input5").replaceAll("\\s", "");
      double input5AsDouble = Double.parseDouble(input5);

      String input6 = req.queryParams("input6").replaceAll("\\s", "");
      double input6AsDouble = Double.parseDouble(input6);

      ArrayList<String> result = App.classPassers(inputListStudents, inputListMidGrades, inputListFinalGrades,
          input4AsDouble, input5AsDouble, input6AsDouble);

      Map<String, String> map = new HashMap<String, String>();
      String sum = "";

      if (result.size() != 0 && result != null) {
        for (int i = 0; i < result.size(); i++)
          sum += result.get(i) + ", ";
        map.put("result", sum);
      } else {
        map.put("result",
            "uncomputable! form1:String, form2&3:Integer, form4&5&6:Double. All form's set size must be equal!");
      }
      return new ModelAndView(map, "compute.mustache");
    }, new MustacheTemplateEngine());

  }
}
