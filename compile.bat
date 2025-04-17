@echo off
setlocal

:: Définir les chemins d'accès pour les dossiers source et de destination des fichiers compilés
set "WORK_DIR=D:\itu\s4\Optimisation\simplexe"
set "SRC_DIR=%WORK_DIR%\src"
set "BIN_DIR=%WORK_DIR%\bin"

:: Si nécessaire, modifie ces variables pour refléter les packages et les chemins exacts

:: Effacer les fichiers .class existants dans le répertoire de destination (bin)
echo Nettoyage du répertoire %BIN_DIR%...
rd /s /q %BIN_DIR%
mkdir %BIN_DIR%

:: Collecte des fichiers Java dans le dossier source et ses sous-dossiers
echo Collecte des fichiers Java...
del /q "%WORK_DIR%\sources.txt"
for /r %SRC_DIR% %%f in (*.java) do echo %%f >> "%WORK_DIR%\sources.txt"

:: Compiler les fichiers Java en utilisant le fichier sources.txt
echo Compilation des fichiers Java...
javac -d %BIN_DIR% @%WORK_DIR%\sources.txt

:: Vérifier si la compilation a réussi
if %errorlevel% neq 0 (
    echo Erreur de compilation. Veuillez vérifier les fichiers Java.
    exit /b %errorlevel%
)

del sources.txt

:: Fin du script
endlocal
