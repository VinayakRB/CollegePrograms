%{
#include<stdio.h>
#include "y.tab.h"
extern int yylval;
%}

%%

[0-9]+ {
          yylval = atoi(yytext);
          return (float)NUMBER;
       }
[\t];
[\n] return 0;
. return (float)yytext[0];

%%

int yywrap()
{
  return 1;
}
