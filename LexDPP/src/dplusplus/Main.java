package dplusplus;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;


import dplusplus.parser.Parser;
import dplusplus.parser.ParserException;
import dplusplus.lexer.Lexer;
import dplusplus.lexer.LexerException;
import dplusplus.node.Start;

public class Main 
{
    public static void main(String[] args) 
    {
        try 
        {
            String arquivo = "teste.dpp"; 

            // Instancia o Lexer exatamente como você já fazia
            Lexer lexer = new Lexer(
                            new PushbackReader(
                                new FileReader(arquivo), 1024));

            // Instancia o Parser do SableCC passando o lexer
            Parser parser = new Parser(lexer);

            System.out.println("Iniciando analise sintatica de: " + arquivo);

            // O método parse() inicia a análise sintática e retorna o nó raiz da árvore (Start)
            Start ast = parser.parse();

            // Se chegou aqui sem dar exceção, o código é sintaticamente correto!
            System.out.println("\nSucesso! O codigo foi reconhecido corretamente.");
            System.out.println("Imprimindo a arvore sintatica gerada:");
            System.out.println("----------------------------------------");
            
            // Imprime a árvore sintática textual padrão do SableCC na tela
            System.out.println(ast.toString());

        } 
        // Captura especificamente erros léxicos
        catch (LexerException e) 
        {
            System.out.println("\n[Erro Lexico]: " + e.getMessage());
        }
        // Captura especificamente erros sintáticos 
        catch (ParserException e) 
        {
            System.out.println("\n[Erro Sintatico]: " + e.getMessage());
        }
        // Captura falhas de leitura de arquivo
        catch (IOException e) 
        {
            System.out.println("\n[Erro de Arquivo]: Nao foi possivel ler o arquivo de teste. " + e.getMessage());
        }
        // Captura qualquer outro erro inesperado
        catch (Exception e) 
        {
            System.out.println("\n[Erro Inesperado]: " + e.getMessage());
        }
    }
}