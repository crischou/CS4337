import java.io.*;


// This is an example driver program
// You will only need lexer.h and lexer.cpp
// in your Part 2.

class Main {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: lexer <file>");
			return;
		}

		try {
			File progFile = new File(args[0]);
			FileInputStream prog = new FileInputStream(progFile);

			Lexer lex = new Lexer(prog);
			// my parser
			Parser parser = new Parser();

			Lexer.Token tok = lex.newToken();
			
			while (!lex.lex(tok) && tok.id != Lexer.TokenId.END_OF_INPUT) {
				Lexer.Token newTok = lex.newToken();
				
				newTok.id = tok.id;
				newTok.line = tok.line;
				newTok.intVal = tok.intVal;
				newTok.strVal = tok.strVal;
				newTok.kwVal = tok.kwVal;

				switch (tok.id) {
				case ID:
					System.out.println("ID: " + tok.strVal);
					break;
				case STRING:
					System.out.println("String: \"" + tok.strVal + "\"");
					break;
				case SEMICOLON:
					System.out.println("SEMICOLON");
					break;
				case KEYWORD:
					System.out.println("Keyword: " + tok.strVal);
					break;
				case ASSIGN:
					System.out.println("ASSIGN");
					break;
				case PLUS:
					System.out.println("PLUS");
					break;
				case MINUS:
					System.out.println("MINUS");
					break;
				case MULT:
					System.out.println("MULT");
					break;
				case DIV:
					System.out.println("DIV");
					break;
				case MOD:
					System.out.println("MOD");
					break;
				case GREATER:
					System.out.println("GREATER");
					break;
				case GREATER_EQ:
					System.out.println("GREATER_EQ");
					break;
				case LESS:
					System.out.println("LESS");
					break;
				case LESS_EQ:
					System.out.println("LESS_EQ");
					break;
				case EQUAL:
					System.out.println("EQUAL");
					break;
				case NOT_EQUAL:
					System.out.println("NOT_EQUAL");
					break;
				case OPEN_PAREN:
					System.out.println("OPEN_PAREN");
					break;
				case CLOSE_PAREN:
					System.out.println("CLOSE_PAREN");
					break;
				case INT:
					System.out.println("Int: " + String.valueOf(tok.intVal));
					break;

				default:
					System.out.println("Error");
					break;
				}
				parser.add(newTok);

			}

			System.out.println("\nParser output starts here:");
			//
			//parse.print();
			for(int i = 0; i < tok.line; i++)
			parser.parse();
			

			if (lex.hasError()) {
				lex.printErr(System.err);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
