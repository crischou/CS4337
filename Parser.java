
import java.util.ArrayList;
import java.util.List;
import java.util.*;

//class for variables
class vars {

	String type;
	String name;
	String strVal;
	int intVal;

}

class Parser {

	// check if variable exists
	public boolean exists(String name) {

		for (vars vars : varList) {
			if (vars.name.equals(name)) {
				// System.out.println("true");
				return true;
			}
		}
		return false;
	}

	// find variable name index
	public int find(String name) {

		for (vars var : varList) {

			if ((var.name).equals(name)) {

				return varList.indexOf(var);
			}
		}
		return -1;

	}

	List<Lexer.Token> tokens = new ArrayList<Lexer.Token>();

	List<vars> varList = new ArrayList<vars>();

	int i = 0;

	public Parser() {

	}

	// add
	public void add(Lexer.Token tok) {

		tokens.add(tok);

	}

	public void print() {

		for (Lexer.Token tok : tokens) {

			System.out.println(tok.id);

		}

	}

	public void parse() {

		if (i < tokens.size() - 1) {

			if (tokens.get(i).id == Lexer.TokenId.SEMICOLON) {

				// System.out.println("Stopping");
				i++;
			} else {

				switch (tokens.get(i).id) {

				case ID:

					switch (tokens.get(i).strVal) {

					case "String":
						if (tokens.get(++i).id == Lexer.TokenId.ID) {

							if (tokens.get(++i).id == Lexer.TokenId.ASSIGN) {

								if (tokens.get(++i).id == Lexer.TokenId.STRING) {

									vars newVar = new vars();
									newVar.type = "String";
									newVar.name = tokens.get(i - 2).strVal;
									newVar.strVal = tokens.get(i).strVal;
									varList.add(newVar);

								} else {
									System.out.println("Wrong type at " + tokens.get(i).line);
								}

							} else {
								System.out.println("Wrong syntax at " + tokens.get(i).line);
							}

						}

						break;
					case "int":
						if (tokens.get(++i).id == Lexer.TokenId.ID) {

							if (tokens.get(++i).id == Lexer.TokenId.ASSIGN) {

								if (tokens.get(++i).id == Lexer.TokenId.INT) {

									vars newVar = new vars();
									newVar.type = "int";
									newVar.name = tokens.get(i - 2).strVal;
									newVar.intVal = tokens.get(i).intVal;
									varList.add(newVar);

								} else {
									System.out.println("Wrong type at " + tokens.get(i).line);
								}

							} else {
								System.out.println("Wrong syntax at " + tokens.get(i).line);
							}

						}
						break;

					default:
						// check if variable exists
						// System.out.println("here");
						if (exists(tokens.get(i).strVal)) {

							switch (tokens.get(++i).id) {

							case ASSIGN:

								if (tokens.get(++i).id == Lexer.TokenId.STRING) {

									int index = varList.indexOf(tokens.get(i - 2).strVal);

									varList.get(index).strVal = tokens.get(i).strVal;
									// System.out.println("done");

								} else if (tokens.get(i).id == Lexer.TokenId.INT) {

									int index = find(tokens.get(i - 2).strVal);

									varList.get(index).intVal = tokens.get(i).intVal;
									// System.out.println("done");

								} else {
									// System.out.println(tokens.get(i).id);
									System.out.println("Not valid type");
								}
								break;
							// math operations with variables
							case PLUS:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									System.out.println(varList.get(index).intVal + tokens.get(i).intVal);

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										System.out.println((varList.get(index).intVal) + (varList.get(index2).intVal));

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case MINUS:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									System.out.println(varList.get(index).intVal - tokens.get(i).intVal);

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										System.out.println((varList.get(index).intVal) - (varList.get(index2).intVal));

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case MULT:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									System.out.println(varList.get(index).intVal * tokens.get(i).intVal);

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										System.out.println((varList.get(index).intVal) * (varList.get(index2).intVal));

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case DIV:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									System.out.println(varList.get(index).intVal / tokens.get(i).intVal);

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										System.out.println((varList.get(index).intVal) / (varList.get(index2).intVal));

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case MOD:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									System.out.println(varList.get(index).intVal % tokens.get(i).intVal);

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										System.out.println((varList.get(index).intVal) % (varList.get(index2).intVal));

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case GREATER:

								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									if (varList.get(index).intVal > tokens.get(i).intVal) {

										System.out.println("True");
									} else {

										System.out.println("False");

									}

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										if ((varList.get(index).intVal) > (varList.get(index2).intVal)) {

											System.out.println("True");

										} else {

											System.out.println("False");

										}

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}

								break;
							case GREATER_EQ:
								if (tokens.get(++i).id == Lexer.TokenId.ASSIGN) {
									if (tokens.get(++i).id == Lexer.TokenId.INT) {
										int index = find(tokens.get(i - 2).strVal);

										if (varList.get(index).intVal >= tokens.get(i).intVal) {

											System.out.println("True");
										} else {

											System.out.println("False");

										}

									} else if (tokens.get(i).id == Lexer.TokenId.ID) {

										// check if value exists
										if (find(tokens.get(i).strVal) != -1) {
											int index = find(tokens.get(i - 2).strVal);
											int index2 = find(tokens.get(i).strVal);

											if ((varList.get(index).intVal) >= (varList.get(index2).intVal)) {

												System.out.println("True");

											} else {

												System.out.println("False");

											}

										}

									} else {

										System.out.println("Error at line " + tokens.get(++i).line);

									}
								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case LESS:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									if (varList.get(index).intVal < tokens.get(i).intVal) {

										System.out.println("True");
									} else {

										System.out.println("False");

									}

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										if ((varList.get(index).intVal) < (varList.get(index2).intVal)) {

											System.out.println("True");

										} else {

											System.out.println("False");

										}

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case LESS_EQ:
								if (tokens.get(++i).id == Lexer.TokenId.ASSIGN) {
									if (tokens.get(++i).id == Lexer.TokenId.INT) {
										int index = find(tokens.get(i - 2).strVal);

										if (varList.get(index).intVal <= tokens.get(i).intVal) {

											System.out.println("True");
										} else {

											System.out.println("False");

										}

									} else if (tokens.get(i).id == Lexer.TokenId.ID) {

										// check if value exists
										if (find(tokens.get(i).strVal) != -1) {
											int index = find(tokens.get(i - 2).strVal);
											int index2 = find(tokens.get(i).strVal);

											if ((varList.get(index).intVal) <= (varList.get(index2).intVal)) {

												System.out.println("True");

											} else {

												System.out.println("False");

											}

										}

									} else {

										System.out.println("Error at line " + tokens.get(++i).line);

									}
								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case EQUAL:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									if (varList.get(index).intVal == tokens.get(i).intVal) {

										System.out.println("True");
									} else {

										System.out.println("False");

									}

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										if ((varList.get(index).intVal) == (varList.get(index2).intVal)) {

											System.out.println("True");

										} else {

											System.out.println("False");

										}

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
								break;
							case NOT_EQUAL:
								if (tokens.get(++i).id == Lexer.TokenId.INT) {
									int index = find(tokens.get(i - 2).strVal);

									if (varList.get(index).intVal != tokens.get(i).intVal) {

										System.out.println("True");
									} else {

										System.out.println("False");

									}

								} else if (tokens.get(i).id == Lexer.TokenId.ID) {

									// check if value exists
									if (find(tokens.get(i).strVal) != -1) {
										int index = find(tokens.get(i - 2).strVal);
										int index2 = find(tokens.get(i).strVal);

										if ((varList.get(index).intVal) != (varList.get(index2).intVal)) {

											System.out.println("True");

										} else {

											System.out.println("False");

										}

									}

								} else {

									System.out.println("Error at line " + tokens.get(++i).line);

								}
							}
						} else {
							System.out.println("Variable doesn't exist/uninitialized");
						}
					}
					break;
				case STRING:
					System.out.println("String: \"" + tokens.get(i).strVal + "\"");
					break;
				case SEMICOLON:
					System.out.println("SEMICOLON");
					break;

				case KEYWORD:

					// System.out.println("Keyword: " + tokens.get(i).strVal);

					switch (tokens.get(i).kwVal) {

					// for print keyword
					case PRINT:
						if (tokens.get(++i).id == Lexer.TokenId.STRING) {

							System.out.print(tokens.get(i).strVal);

						} else if (tokens.get(i).id == Lexer.TokenId.ID) {

							for (vars var : varList) {
								if (var.name.equals(tokens.get(i).strVal)) {

									if (var.type.equals("String")) {

										System.out.print(var.strVal);

									} else if (var.type.equals("int")) {

										System.out.print(var.intVal);

									} else {

										System.out.println("Unknown type error");
									}
								}
							}

						} else {

							// System.out.println("Here");
							System.out.println("Error at line  " + tokens.get(i).line);

						}
						break;
					// for get keyword
					case GET:

						if (tokens.get(++i).id == Lexer.TokenId.ID) {

							// MODIFICAITON 1 check if user input is string or int
							if (tokens.get(i).strVal.equals("String") || tokens.get(i).strVal.equals("int")) {

								if (tokens.get(++i).id == Lexer.TokenId.ID) {

									Scanner input = new Scanner(System.in);
									vars newVars = new vars();
									switch (tokens.get(i - 1).strVal) {

									case "String":

										String inp = input.nextLine();
										newVars.type = "String";
										newVars.name = tokens.get(i).strVal;
										newVars.strVal = inp;
										varList.add(newVars);
										break;

									case "int":

										int in = input.nextInt();
										newVars.type = "int";
										newVars.name = tokens.get(i).strVal;
										newVars.intVal = in;
										varList.add(newVars);
										break;

									default:

										System.out.println("Error at line  " + tokens.get(i).line);
										break;
									}
								}
							} else {
								// check if can find variable
								if (find(tokens.get(i).strVal) != -1) {
									Scanner input = new Scanner(System.in);
									int index = find(tokens.get(i).strVal);

									if (varList.get(index).type.equals("String")) {
										String newStr = input.nextLine();
										varList.get(index).strVal = newStr;

									} else if (varList.get(index).type.equals("int")) {
										int newInt = input.nextInt();
										varList.get(index).intVal = newInt;
									}

								}

							}

						} else {

							System.out.println("Error at line  " + tokens.get(i).line);

						}
						break;
					// if statement
					case IF:
						if (tokens.get(++i).id != Lexer.TokenId.OPEN_PAREN) {

							System.out.println("Error no open paren at line " + tokens.get(i).line);

						}
						break;

					default:
						System.out.println("Error");
						break;

					}

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
					// System.out.println("Int: " + String.valueOf(tokens.get(i).intVal));

					switch (tokens.get(++i).id) {
					case PLUS:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							System.out.println(tokens.get(i - 2).intVal + tokens.get(i).intVal);

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case MINUS:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							System.out.println(tokens.get(i - 2).intVal - tokens.get(i).intVal);

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case MULT:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							System.out.println(tokens.get(i - 2).intVal * tokens.get(i).intVal);

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case DIV:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							System.out.println(tokens.get(i - 2).intVal / tokens.get(i).intVal);

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case MOD:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							System.out.println(tokens.get(i - 2).intVal % tokens.get(i).intVal);

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case GREATER:

						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							if (tokens.get(i - 2).intVal > tokens.get(i).intVal) {

								System.out.println("True");

							} else {

								System.out.println("False");

							}

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}

						break;
					case GREATER_EQ:
						if (tokens.get(++i).id == Lexer.TokenId.ASSIGN) {
							if (tokens.get(++i).id == Lexer.TokenId.INT) {

								if (tokens.get(i - 2).intVal >= tokens.get(i).intVal) {

									System.out.println("True");

								} else {

									System.out.println("False");

								}

							} else {

								System.out.println("Error at line " + tokens.get(++i).line);

							}
						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case LESS:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							if (tokens.get(i - 2).intVal < tokens.get(i).intVal) {

								System.out.println("True");

							} else {

								System.out.println("False");

							}

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case LESS_EQ:
						if (tokens.get(++i).id == Lexer.TokenId.ASSIGN) {
							if (tokens.get(++i).id == Lexer.TokenId.INT) {

								if (tokens.get(i - 2).intVal <= tokens.get(i).intVal) {

									System.out.println("True");

								} else {

									System.out.println("False");

								}

							} else {

								System.out.println("Error at line " + tokens.get(++i).line);

							}
						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case EQUAL:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							if (tokens.get(i - 2).intVal == tokens.get(i).intVal) {

								System.out.println("True");

							} else {

								System.out.println("False");

							}

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;
					case NOT_EQUAL:
						if (tokens.get(++i).id == Lexer.TokenId.INT) {

							if (tokens.get(i - 2).intVal != tokens.get(i).intVal) {

								System.out.println("True");

							} else {

								System.out.println("False");

							}

						} else {

							System.out.println("Error at line " + tokens.get(++i).line);

						}
						break;

					default:
						System.out.println("Error at line " + tokens.get(i).line);
						break;
					}
					break;

				default:
					System.out.println("Error");
					break;
				}
				i++;
				parse();
			}

		}
	}

}