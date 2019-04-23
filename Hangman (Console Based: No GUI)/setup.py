from cx_Freeze import setup, Executable

setup(name = "hangman.py",
      version = "0.1",
      description = "",
      executables = [Executable("hangman.py")])
