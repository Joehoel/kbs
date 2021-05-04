# Backtracking

1. Begin met lege configuratie
2. Voeg een pfsense en loadbalancer toe om mee te beginnen
3. Loop over alle componenten

   - Voldoet de oplossing?
   - Is de beschikbaarheid van de oplossing groter dan of gelijk aan het doel EN is de prijs goedkoper dan die van de vorige beste oplossing

   ### **Ja**

   - Configuratie leegmaken
   - Component toevoegen aan configuratie
   - Prijs berekenen

   ### **Nee** (dus: is de beschikbaarheid van de oplossing kleiner dan het minimale doel dat we willen?)

   - Kiezen of webserver / databaseserver wordt toegevoegd
   - Percentage van webservers / databaseservers berekenen

   Is het beschikbaarheidspercentage van de databaseservers hoger dan die van de webservers

   ### **Ja**

   - Webserver toevoegen aan webserver array

   ### **Nee**

   - Databaseserver toevoegen aan databaseserver array

Is de prijs hoger dan de prijs van de tot nu toe beste oplossing?

- Niets doen oplossing is te duur

Oplossing verwijderen
