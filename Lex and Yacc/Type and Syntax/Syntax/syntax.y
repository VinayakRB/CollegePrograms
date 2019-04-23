%{
  int yylex();
  void yyerror(const char *s);
  #include "stdio.h"
%}
%token Datatype Variable Space Delimeter
%%

statement:Datatype Space Variable Delimeter {printf("\nValid Syantax!\n");}
%%

int main()
{
  printf("Enter Instruction :-\n");
  yyparse();
  return 0;
}
void yyerror(const char *s)
{
printf("\n Invalid Syantax!");
}
