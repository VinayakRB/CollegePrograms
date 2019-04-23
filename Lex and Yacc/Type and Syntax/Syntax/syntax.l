%option noinput nounput
%{
  #include "y.tab.h"
  extern int yyval;
%}
%%

String|void|int|float|bool { printf(" Datatype %s :",yytext) ; return Datatype;}
[A-Za-z]+ { printf("\n Variable : %s",yytext); return Variable; }
";" { printf("\n Delimeter : %s",yytext); return Delimeter;}
" " { printf("\n Space : %s",yytext); return Space;}
[\t] ;
[\n] {return 0;}
. { return yytext[0];}
%%

int yywrap()
{
  return 0;
}
