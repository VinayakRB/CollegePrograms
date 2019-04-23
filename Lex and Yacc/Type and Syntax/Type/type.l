%{
  #include "y.tab.h"
%}
%%

int { return(INT); }
float { return(FLOAT); }
char { return(CHAR); }
string { return(STRING); }
bool { return(BOOL); }
[a-z]* { return(VARIABLE); }
" " { return(SPACE); }
= { return(ASSIGNMENT); }
; { return(SEMICOLON); }
[0-9]* { return(VALUE_INT); }
"\'"[a-zA-Z]"\'" { return(VALUE_CHAR); }
-?[0-9]*["."[0-9]*]?? { return(VALUE_FLOAT); }
"\""[a-zA-Z]+[A-Za-z0-9]*"\"" { return(VALUE_STRING); }
[\t] ;
[\n] {return 0;}
%%

int yywrap()
{
  return 0;
}
