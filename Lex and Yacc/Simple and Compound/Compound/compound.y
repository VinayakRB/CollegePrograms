%{
  int yylex();
  void yyerror(const char *s);
  #include<stdio.h>
%}
%token Con Comma Verb Pronoun Noun
%%

statement:Subject Verb Object Comma Con Subject Verb Object { printf("\nCorrect Sentence!\n"); };
Subject:Noun|Pronoun;
Object:Noun|Pronoun;
%%

int main()
{
  printf("Enter Sentence :- \n");
  yyparse();
  return 0;
}
void yyerror(const char *s)
{
  printf("\nInvalid Sentence!\n");
}
