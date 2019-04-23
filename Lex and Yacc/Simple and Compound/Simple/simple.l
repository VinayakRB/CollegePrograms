%{
  #include "y.tab.h"
%}
%%

be|have|do|say|get|make|go|know|take|see { printf("\nVERB : %s",yytext); return Verb; }
it|I|you|he|they|we|she|who|them|me|him|her|us { printf("\nPronoun : %s",yytext); return Pronoun; }
[A-z]+ { printf("\nNoun : %s",yytext); return Noun; }
[\n] { return 0; }
%%

int yywrap()
{
  return 0;
}
