

package InterviewQuestions;

import java.util.Scanner;
import java.util.Stack;


public class InterviewQuestions {

  
    public static void main(String[] args) {
            
        boolean repeat = true;
        
        while(repeat)
        {
        
        System.out.println("Do you want to test these methods with your input, "
                + "or prepicked inputs? Type 'Yes' if you want to enter your own "
                + "input and any other button if not.");
        
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        
        if(input.equals("Yes") || input.equals("yes"))
        {
            System.out.println("Ok, now which of the following"
                    + " would you like to test your string input"
                    + " for? (Type in the corresponding number)");
                      
            System.out.println("1) Palindrome Test");
            System.out.println("2) Balanced Expression Test");
            System.out.println("3) Evaluation of Expression Test");
            
             Scanner user1 = new Scanner(System.in);
            int userInput1 = user1.nextInt(); //used int variable so we could just do == 
            //instead of .equals() because i like it better
            
            if(userInput1 == 1)
            {
                System.out.println("Ok, now I want you to enter the string you want to "
                        + " do the Palindrome Test on.");
                
                Scanner palScan = new Scanner(System.in);
                String palTest = palScan.nextLine();
                
                if(isPalindrome(palTest))
                    System.out.println("Yes, this is a palindrome");
                    
                else
                    System.out.println("No, this is not a palindrome");
            }
            
            else if(userInput1 == 2)
            {
                System.out.println("Ok, now I want you to enter the expression"
                        + " that you want to test for balance.");
                
                Scanner balScan = new Scanner(System.in);
                String balTest = balScan.nextLine();
                
                if(isBalancedExpression(balTest))
                    System.out.println("Yes this is a balanced expression.");
                
                else
                    System.out.println("No this is not a balanced expression");   
            }
            
            else if(userInput1 == 3)
            {
                System.out.println("Ok, now give me an expression to evaluate.");
                
                
                Scanner exprScan = new Scanner(System.in);
                String exprTest = exprScan.nextLine();     
                
                System.out.println("This is equal to: " + evaluateExpression(exprTest));
            }
            
            else
                System.out.println("Invalid Input, sorry.");
        }  
        
        else
        {    
            System.out.println("That is fine, we will now test our prepicked inputs");
    


            String space = "---------------";

            System.out.println(isPalindrome("RaceCar"));
            System.out.println(isPalindrome("HelloWorld"));
            System.out.println(isPalindrome("W"));
            System.out.println(space);

            System.out.println(isBalancedExpression("{()}(){()}"));
            System.out.println(isBalancedExpression("{(})"));
            System.out.println(isBalancedExpression("{(5 + 6) - (3 * 4)}"));
            System.out.println(space);

            System.out.println(evaluateExpression("45+34*-"));
            System.out.println(evaluateExpression("138*+"));
            System.out.println(evaluateExpression("6+38*"));
            System.out.println(evaluateExpression("65+38*7"));
        }

        System.out.println("Would you like to do another test? Type yes or no.");

        Scanner last = new Scanner(System.in);
        String yn = last.nextLine();

        if(yn.equals("no") || yn.equals("No"))
        {
            System.out.println("Ok have a nice day.");
            repeat = false;
        }

    }
       
}
    
    
    
    public static boolean isPalindrome(String input)
    {
        
            char[] inputChar = input.toCharArray(); //turning the string into an array of characters
            Stack<Character> myStack = new Stack<>();//creating a stack of characters named "myStack"
            //created an object called "myStack" of the data type "Stack"
            
            for(int i = 0; i < inputChar.length; i++)//this for loop cycles through
            {                                   //our inputChar array and places each into the stack
                myStack.push(inputChar[i]);
            }
            
            String reversed = "";
            
            for(int i = 0; i < inputChar.length; i++)//loops through the length of the char array
            {                   //pops the last element to be pushed in, out and deletes it
                reversed += myStack.pop(); 
            }
            
            if(reversed.compareToIgnoreCase(input) == 0) //this compares the variable
                //reversed to the input, the .compareToIgnoreCase(input) is saying that we 
                //want to compare reversed to the variable input and it evaluates each character
                //in the string and compares them based on their unicode value, if the two are
                //the same it returns 0, so then we do the expression, so if 0 == 0
                //then we return true because then it's a palindrome
                return true;
            
        return false;
    }
    
    public static boolean isBalancedExpression(String inputExp)
    {
        char[] expChars = inputExp.toCharArray(); //does same thing as last method
        Stack<Character> expStack = new Stack<>();
        
        char v; //we will use this character to compare it to the top of the stack
        
        for(int i = 0; i < expChars.length; i++) //loops through array
        {
            if(expChars[i] == '(' || expChars[i] == '{') //checking if the array has an opening 
                //expression
                expStack.push(expChars[i]); //pushing it into the stack if it does
            
            else if(expChars[i] == ')' || expChars[i] == '}') //if it doesn't we want to see if
            { //it's a closing expression. If it's anything else this allows us to ignore it
                v = expStack.pop(); //using the variable to represent the last element to be put in 
                //the stack
                if((v == '(' && expChars[i] != ')') || (v == '{' && expChars[i] != '}'))
                        return false; //if the last element put in the stack isn't the opening 
                //equivalent expression such as ( or { then it's not a balanced expression and return
                //false
            }
                
        }
        if(expStack.empty())
            return true; //if the stack is empty that means we cycled through the array and stack
        //and it also means that each beginning expression matched with a closing one so it is true
        
        return false; //if none of these conditions are met then it has to be false
     }
    
    public static int evaluateExpression(String Expr)
    {
        char[] charExpr = Expr.toCharArray();
        Stack<Integer> stackExpr = new Stack<>();
        
        for(int i = 0; i < charExpr.length; i++)
        {
            if(Character.isDigit(charExpr[i]))
                stackExpr.push(Character.getNumericValue(charExpr[i]));
            
            else if(charExpr[i] == '+' || charExpr[i] == '-' || charExpr[i] == '*' || charExpr[i] == '/')
            {
                if(stackExpr.size() < 2)
                {
                    System.out.println("Invalid Expression");
                    return 0;
                }
                
                
                
                int num1 = stackExpr.pop();
                int num2 = stackExpr.pop();
                
                if(charExpr[i] == '+')
                    stackExpr.push(num1 + num2);
                
                else if(charExpr[i] == '-')
                    stackExpr.push(num2 - num1);
                
                else if(charExpr[i] == '*')
                    stackExpr.push(num1 * num2);
                
                else if(charExpr[i] == '/')
                    stackExpr.push(num2 / num1);
       
            }
           
        }
         if(stackExpr.size() > 1)
                {
                    System.out.println("Invalid Expression");
                    return 0;
                }
    
    return stackExpr.pop(); 
    
    }
    
}
