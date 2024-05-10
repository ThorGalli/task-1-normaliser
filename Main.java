import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    Normaliser normaliser = new Normaliser();
   
    ArrayList<String> inputs = new ArrayList<>();
    inputs.add("Java engineer");
    inputs.add("C# engineer");
    inputs.add("Accountant");
    inputs.add("Chief Accountant");

    while (true) {
      if(inputs.size() > 0) {
        System.out.println("Normalised job titles:");
        System.out.println(inputs.toString());
      }
      String newInput = System.console().readLine("Enter a job title (or leave it empty to finish): ");
      if (newInput.equals("")) {
        break;
      }
      inputs.add(newInput);
    }

    for (String input : inputs) {
      // Use the normalise method to normalise the input, or normaliseWithData to return the normalised input and the data used to normalise it
      // System.out.println(normaliser.normalise(input));
      System.out.println(normaliser.normaliseWithData(input));
    }

  }
}
