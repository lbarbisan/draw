@echo off
echo suppression de la clef si existante
"%PROGRAMFILES%\Java\jdk1.5.0_06\bin\keytool" -delete -alias eurilogic -keystore keys -storepass fr.eurilogic.mozart.net -dname "CN=Eurilogic SAS - BU Industrie, OU=Eurilogic, O=Eurilogic SAS, L=Chatenay Malabry, ST=92, C=fr"
echo cr�ation de la clef
REM 													nom du certificat     r�pertoire    Validit� jour            mot de passe pour fichier keys       
"%PROGRAMFILES%\Java\jdk1.5.0_06\bin\keytool" -genkey -alias eurilogic -keystore keys       -validity 9999 -storepass fr.eurilogic.mozart.net             -dname "CN=Eurilogic SAS - BU Industrie, OU=Eurilogic, O=Eurilogic SAS, L=Chatenay Malabry, ST=92, C=fr"
Pause