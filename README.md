# a4-auth-server-givemeallyourmoneygmbh
a4-auth-server-givemeallyourmoneygmbh created by GitHub Classroom

Sie arbeiten wieder in den Gruppen, in denen Sie ShareIt Teil 1 bearbeitet haben.

Sie erarbeiten eine Umsetzung der von Ihnen im Rahmen des PBL ausgearbeiteten Aufgabenstellung. Das bedeutet, Sie haben bei dieser Aufgabe verhältnismäßig viel Freiraum zur Ausgestaltung.

Token-Austausch und -Validierung müssen auf jeden Fall funktionieren und getestet sein für "gut"- sowie für Fehler-Fälle. Wo immer sinnvoll, dürfen Sie vorgefertigte Komponenten verwenden, Sie müssen jedoch mit Java umsetzen.

Was Sie nicht implementieren müssen:

User Interface für eine Anmeldeseite 
Registrierung neuer User - ein "hard coded" user reicht
Speichern von Password-Hashes (Passwort kann im Klartext gespeichert werden; was im realen Leben natürlich unmöglich wäre)
Idealerweise entsteht mit dem Authentisierungsservice ein neuer, eigenständiger Microservice. Sie können dazu ein neues Repository einrichten.



zum lokalen testen:

    Dummy User:
    User{userName='lisa', isAdmin=true, password='Hallo123', userId='1'}
    User{userName='peter', isAdmin=false, password='wert1234', userId='2'}

    Basic Auth (Beispiel: Authorization: Basic d2lraTpwZWRpYQ==
                            „d2lraTpwZWRpYQ==“ ist die Base64-Codierung von wiki:pedia und steht damit für Benutzername wiki, Passwort pedia.)

    //gibt einfach einen token zurück oder nix
	http://127.0.0.1:8082/services/oauth2/authorize

	//validiert den token übergabe im header Authorization
	http://127.0.0.1:8082/services/oauth2/token

    //erhalt alle oben gennanten user zurück
	http://127.0.0.1:8082/services/user/


	//den user mit der id 2 - falls erlaubt
	http://127.0.0.1:8082/services/user/2


