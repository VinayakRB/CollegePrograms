%{
  int yylex();
  void yyerror(const char *s);
  #include "stdio.h"
%}
%token INT FLOAT CHAR STRING BOOL SPACE VARIABLE SEMICOLON ASSIGNMENT VALUE_INT VALUE_CHAR VALUE_FLOAT VALUE_STRING
%%

statement:INT SPACE VARIABLE SEMICOLON {printf("Valid statement\n");}
|FLOAT SPACE VARIABLE SEMICOLON {printf("Valid statement\n");}
|CHAR SPACE VARIABLE SEMICOLON {printf("Valid statement\n");}
|STRING SPACE VARIABLE SEMICOLON {printf("Valid statement\n");}
|INT SPACE VARIABLE SPACE ASSIGNMENT SPACE VALUE_INT SEMICOLON {printf("Valid statement with valid integer declaration\n");}
|FLOAT SPACE VARIABLE ASSIGNMENT SPACE VALUE_FLOAT SEMICOLON {printf("Valid statement with valid float declaration\n");}
|CHAR SPACE VARIABLE ASSIGNMENT SPACE VALUE_CHAR SEMICOLON {printf("Valid statement with valid char declaration\n");}
|STRING SPACE VARIABLE ASSIGNMENT SPACE VALUE_STRING SEMICOLON {printf("Valid statement with valid string declaration\n");}
;
%%

int main()
{
  printf("Enter Instruction :-\n");
  yyparse();
  return 0;
}
void yyerror(const char *s)
{
  printf("Invalid Statement\n");
}
