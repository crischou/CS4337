import java.io.InputStream;
import java.io.PrintStream;


class Lexer {
		public Lexer(InputStream in) {
			_input = in;
			_error = false;
			_line = 1;
			_eof = false;
			_eatWS();
			_readNext();
		}

		// You can add a new keyword by appending the string to the end of KEYWORDS, and a enum id to the end of KW_ID
        public final String KEYWORDS[]={"print", "get", "if", "then", "else", "end", "while", "do", "and", "or", "not"};
        public enum KW_ID {PRINT, GET, IF, THEN, ELSE, END, WHILE, DO, AND, OR, NOT};

		public enum TokenId {
			ID,
			STRING,
			SEMICOLON,
			KEYWORD,
			ASSIGN,
			PLUS,
			MINUS,
			MULT,
			DIV,
			MOD,
			GREATER,
			GREATER_EQ,
			LESS,
			LESS_EQ,
			EQUAL,
			NOT_EQUAL,
			OPEN_PAREN,
			CLOSE_PAREN,
			INT,
			END_OF_INPUT,
		};

		public class Token {
			public TokenId id;
			public int line;
			public int intVal;
			public String strVal;
			public KW_ID kwVal;
		};

		public Token newToken() {
			return new Token();
		}

		// Get the next token and store it int tok
		// Returns true if an error happens and
		// false otherwise
		public boolean lex(Token tok){

	if(_error) return true;

	if(_eof) {
		// Return END_OF_INPUT TOKEN When at eof
		tok.id = TokenId.END_OF_INPUT;
		return false;
	}

	tok.line = _line;
	// Have a character to process
	switch(_buf){
		case ';': tok.id = TokenId.SEMICOLON;
				  _readNext();
				  break;
		case '(': tok.id = TokenId.OPEN_PAREN;
				  _readNext();
				  break;
		case ')': tok.id = TokenId.CLOSE_PAREN;
				  _readNext();
				  break;
		case '+': tok.id = TokenId.PLUS;
				  _readNext();
				  break;
		case '-': tok.id = TokenId.MINUS;
				  _readNext();
				  break;
		case '*': tok.id = TokenId.MULT;
				  _readNext();
				  break;
		case '/': tok.id = TokenId.DIV;
				  _readNext();
				  break;
		case '%': tok.id = TokenId.MOD;
				  _readNext();
				  break;
		case '=': tok.id = _extendOp('=', TokenId.ASSIGN, TokenId.EQUAL);
				  break;
		case '>': tok.id = _extendOp('=', TokenId.GREATER, TokenId.GREATER_EQ);
				  break;
		case '<': tok.id = _extendOp('=', TokenId.LESS, TokenId.LESS_EQ);
				  break;
		case '!': if(!_readNext() || _buf != '=') {
					  _error = true;
					  _errStr = "Lex Error at line " + String.valueOf(_line) + ": Expected '=' after '!'";
				  } else {
					  tok.id = TokenId.NOT_EQUAL;
				      _readNext();
				  }
				  break;
		case '"': _lexString(tok);
				  break;
		default: if( _buf >= '0' && _buf <= '9') {
					 _lexInt(tok);
				 } else if (_isIdStart(_buf)) {
					 _lexId(tok);
				 } else {
					 _error = true;
					 _errStr = "Lex Error at line " + String.valueOf(_line) + ": Unexpected character '" + _buf + "'";
				 }
	}

	_eatWS();
	return _error;
		}

		public boolean hasError() {
		   	return _error; 
		}
		public void printErr(PrintStream out) {
			out.println(_errStr); 
		}
		
		private InputStream _input;
		private char _buf;
		private boolean _eof;

		private int _line;

		private boolean _error;
		private String _errStr;

		private void _eatWS() {
			char prev = 0;
			if(_buf == ' ' || _buf == '\t' || _buf == '\r' || _buf == 10) {
				if(_buf == '\r' || _buf == 10) _line++;
				prev = _buf;
				while(_readNext() && (_buf == ' ' || _buf == '\t' || _buf == '\n')) {
					if(_buf == '\r') _line++;
					if(_buf == 10 && prev != '\r') _line++;
					prev = _buf;
				}
			}
		}

		private void _lexString(Token tok) {
			tok.strVal = "";
			tok.id = TokenId.STRING;

			while(_readNext() && _buf != '"') {
				if(_buf == '\\') {
					if(!_readNext()) {
						_error = true;
				        _errStr = "Lex Error at line " + String.valueOf(_line) + ": Unexpected end of string";
						return;
					}
				switch(_buf) {
					case 't': tok.strVal += '\t';
							  break;
					case 'n': tok.strVal += '\n';
							  break;
					default: tok.strVal += _buf;
				}
				} else {
					tok.strVal += _buf;
				}
			}

		    if(!_readNext()) { // Each closing quote
				_error = true;
				_errStr = "Lex Error at line " + String.valueOf(_line) + ": Unexpected end of string";
			}
		}

		private void _lexInt(Token tok) {
	String tmp = "";
	tmp += _buf;
	while(_readNext() && _buf >= '0' && _buf <= '9') {
		tmp += _buf;
	}

	tok.id = TokenId.INT;
	tok.intVal = Integer.parseInt(tmp);
		}

		private void _lexId(Token tok) {
	tok.strVal = "";
	tok.strVal += _buf;
	while(_readNext() && (_isIdStart(_buf) || (_buf >= '0' && _buf <= '9'))) {
		tok.strVal += _buf;
	}

	_checkKeyword(tok);
		}

		private void _checkKeyword(Token tok) {
	tok.id = TokenId.ID;
	for(KW_ID i: KW_ID.values()) {
		if(KEYWORDS[i.ordinal()].equals(tok.strVal)) {
			tok.id = TokenId.KEYWORD;
			tok.kwVal = i;
			break;
		}
	}
		}

		private boolean _isIdStart(char c) {
	return c >= 'a' && c <= 'z' ||
		   c >= 'A' && c <= 'Z' ||
		   c == '_';
		}

		private TokenId _extendOp(char next, TokenId without, TokenId with) {
	if(!_readNext() || _buf != next) return without;
	else return with;
		}

		private boolean _readNext() {
			try {
			int tmp = _input.read();
			if(tmp == -1) {
				_eof = true;
				return false;
			} else {
				_buf = (char)tmp;
				return true;
			}
			} catch( Exception e) {
				_error = true;
				_errStr = "Exception: " + e;
				return false;
			}
		}
}
