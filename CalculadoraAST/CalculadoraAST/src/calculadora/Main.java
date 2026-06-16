package calculadora;
import calculadora.parser.*;
import calculadora.lexer.*;
import calculadora.node.*;
import java.io.*;

public class Main
{
 public static void main(String[] args)
 {
  try
  {
   String arquivo = "teste/expressao.calc";
  
   Parser p =
    new Parser(
    new Lexer(
    new PushbackReader(  
    new FileReader(arquivo), 1024))); 
   
   Start tree = p.parse();

   tree.apply(new ASTPrinter());
  }
  catch(Exception e)
  {
   System.out.println(e.getMessage());
  }
 }
}