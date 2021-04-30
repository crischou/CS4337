//Cris CHou
//CYC180001

public class token {

	public String whatType(String args[]) {

		if (args[0].equals("+")) {

			if (args[1].equals("+")) {

				return "INCREMENT";

			} else if (args[1].equals(" ")) {

				return "ADD";

			} else {

				return "ERROR";

			}

		} else if (args[0].equals("-")) {

			if (args[1].equals("-")) {

				return "DECREMENT";

			} else if (args[1].equals(" ")) {

				return "SUBTRACT";

			} else {

				return "ERROR";

			}
		} else if (args[0].equals("*")) {

			return "MULTIPLY";

		} else if (args[0].equals("/")) {

			return "DIVIDE";

		} else if (args[0].equals(">")) {

			if (args[1] == "=") {

				return "GREATEREQUAL";

			} else if (args[1].equals(" ")) {

				return "GREATER";

			} else {

				return "ERROR";
			}

		} else if (args[0].equals("<")) {

			if (args[1].equals("=")) {

				return "LESSEQUAL";

			} else if (args[1].equals(" ")) {

				return "LESS";

			} else {

				return "ERROR";
			}

		} else if (args[0].equals("=")) {

			if (args[1].equals("=")) {

				return "EQUAL";

			} else if (args[1].equals(" ")) {

				return "ASSIGN";

			} else {

				return "ERROR";

			}
		} else if (args[0].equals("!")) {

			if (args[1].equals("=")) {

				return "NOTEQUAL";

			} else {

				return "ERROR";

			}
		} else if (args[0].equals("(")) {

			return "OPENPAREN";

		} else if (args[0].equals(")")) {

			return "CLOSEPAREN";

		} else if (args[0].equals(";")) {

			return "SEMICOLON";

		}

		return "ERROR";
	}

}
