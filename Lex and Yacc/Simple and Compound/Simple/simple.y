%{
  int yylex();
  void yyerror(const char *s);
  #include<stdio.h>
%}
%token Verb Pronoun Noun
%%

statement:Subject Verb Object { printf("\nSentence is correct!\n");};
Subject:Noun|Pronoun;
Object:Noun|Pronoun;
%%

int main()
{
  printf("Enter Statement:- \n");
  yyparse();
  return 0;
}
void yyerror(const char *s)
{
  printf("\nInvalid Statement!\n");
}
