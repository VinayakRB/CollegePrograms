%{
  #include<stdio.h>
  int characters = 0;
  int blanks = 0;
  int lines = 0;
  int words = 0;
%}
%%

[\n] {lines++;words++;}
[\t] {blanks++;words++;}
[" "] {blanks++;words++;}
[^ \n\t] {characters++;}
%%

int main()
{
  char op[10];
  printf("Enter name of file : ");
  scanf("%s",op);
  yyin = fopen(op,"r");
  yylex();
  printf("Characters : %d\nBlanks : %d\nLines : %d\nWords : %d\n",characters,blanks,lines,words);
  return 0;
}

int yywrap()
{
  return(1);
}
