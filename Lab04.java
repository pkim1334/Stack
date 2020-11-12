import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab04{

  static String pattern = "([+-\\/*%])";
  static Pattern pt = Pattern.compile(pattern);
  static Matcher match;
  static String[] symbol = {"(",")","+","-","*","/","%","eos"};   //operators
  static int[] lib1 = {5,20,10,10,15,15,15,5};    //operator library(translator)
  static int[] lib2 = {25,20,10,10,15,15,15,5};   //operator library(translator)

  static Stack<String> s1 = new Stack<String>();

  public static void main(String[] args){

    Scanner input = new Scanner(System.in);
    boolean flag = true;
    String temp, output, expression;

    System.out.println("1) Infix to Postfix \n2) Postfix to Infix \n3) Exit");

    while(flag){
      temp = input.nextLine();

      if(temp.equals("1")){
        System.out.println("\nEnter expression");
        expression = input.nextLine();
        output = inf_to_postf(expression);    //function converting to postfix

        System.out.println(output);

        System.out.println("1) Infix to Postfix \n2) Postfix to Infix \n3) Exit");

      }else if(temp.equals("2")){
        System.out.println("\nEnter expression");
        expression = input.nextLine();
        output = postf_to_inf(expression);    //function converting to infix

        System.out.println(output);

        System.out.println("1) Infix to Postfix \n2) Postfix to Infix \n3) Exit");

      }else if(temp.equals("3")){
        flag = false;
      }

    }

  }

  public static String inf_to_postf(String expression){//start of inf_to_postf
    char char1;
    String addOn;
    String newExpression = "";

    int n;
    int inx1 = 7;
    int inx2;

    s1.push("eos");

    for(int i = 0; i < expression.length(); i++){
      char1 = expression.charAt(i);
      addOn = char1 + "";
      match = pt.matcher(addOn);

      if(!match.matches() && !addOn.equals("(") && !addOn.equals(")")){
        newExpression += addOn;
      }else {
        n = 0;

        while (!addOn.equals(symbol[n])){
          n++;
        }

        inx2 = n;

        if (inx2 != 1){
          while (lib1[inx1] >= lib2[inx2]){   //comparing precedents of operands
            newExpression += s1.pop();
            n = 0;

            while (!s1.peek().equals(symbol[n])) {
              n++;
            }
            inx1 = n;
          }
          s1.push(addOn);
          inx1 = inx2;
        }else {
          while(!s1.peek().equals("(")){
            newExpression += s1.pop();
          }
          s1.pop();

          while (!s1.peek().equals(symbol[n])) {
            n++;
          }
          inx1 = n;
        }
      }
    }
    while (!s1.peek().equals("eos")) {    //end of string(finished converting)
      newExpression += s1.pop();
    }
    return newExpression;
  }   //end of inf_to_postf

  public static String postf_to_inf(String expression){   //start of postf_to_inf
    char char1;
    String addOn;
    String str1;
    String str2;

    for (int i = 0;i < expression.length(); i++){
      char1 = expression.charAt(i);
      addOn = "" + char1;
      match = pt.matcher(addOn);

      if (!match.matches()){    //seeing of it is an operand or not
        s1.push(addOn);
      }else {
        str2 = s1.pop();
        str1 = s1.pop();
        s1.push(str1+addOn+str2);
      }
    }
    expression = s1.pop();
    return expression;
  }   //end of postf_to_inf
}
