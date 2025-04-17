@echo off
setlocal

:: Définir les chemins d'accès pour les dossiers source et de destination des fichiers compilés
set "WORK_DIR=D:\itu\s4\Optimisation\simplexe"
set "BIN_DIR=%WORK_DIR%\bin"
set "MAIN_CLASS=main.Main"

:: Vérifier si le fichier Main.class existe dans le répertoire bin/oodb/main
if not exist "%BIN_DIR%\main\Main.class" (
    echo La classe %MAIN_CLASS% n'a pas été trouvée. Assurez-vous que la compilation a été effectuée.
    exit /b 1
)

:: Afficher le classpath pour le débogage
echo Classpath: %BIN_DIR%

:: Exécuter le programme Java (Main) en utilisant le répertoire bin comme chemin de classe
echo Exécution du programme Java...
java -cp "%BIN_DIR%" %MAIN_CLASS%

:: Fin du script
endlocal
