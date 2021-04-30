//Cris Chou
//CYC180001

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in;
		
		if (args.length != 1) {
			System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
			System.exit(0);

		}
		lexer lexer = new lexer();
		try {
			
			File input_file = new File(args[0]);
			//File input_file = new File("input.txt");
			in = new Scanner(input_file);
			
			String token [] = new String[2];
			String op = "";
			while(in.hasNext()) {
				
				op = in.next();
				if(op.length() == 2) {
					
					token[0] = op.substring(0,1);
					token[1] = op.substring(1,2);
					
					System.out.println(lexer.getTokens(token));
					
					
				}else if(op.length() == 1) {
					
					token[0] = op;
					token[1] = " ";
					
					System.out.println(lexer.getTokens(token));
					
				}else {
					
					System.out.println("ERROR");
					
				}
				
				
			}
			
			
		
			
		}catch(Exception e) {
			
			
			System.out.println("Exception: " + e.getMessage());
			
		}
		

	}

}
