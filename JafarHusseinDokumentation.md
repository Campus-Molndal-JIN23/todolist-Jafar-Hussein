# Ditt namn
### Jafar Hussein

## Egna reflektioner
Detta projektet har varit en ganska rolig projekt, det har varit både bra och dåligt men till slut så blev projekten klar och jag blev nöjd med
resultaten.
## Projektet
### Todo lista
### Beskrivning av projektet
Detta projektet är en program som låter användaren skapa en lista på massa uppgifter som användaren ska göra.
Programmet låter användaren skapa en lista med uppgifter och spara den till databas sedan låter den hen att ändra
på uppgiften och status på uppgiften samt kan hen ta bort eller skriva ut alla uppgifter.
### Vad du har gjort
Detta projektet var en enskild uppgift som jag själv, för att hålla det kort så gjorde jag allting.
## Planering
Innan jag började skapa kod så använde jag mig av scrum för att förstå vad användaren vill ha ut av programmet.
Jag började med en produkt backlog med user stories där jag använde mig av en användarens perspektiv för att se vad som ska skapas.
När jag blev klar så skapade jag två sprint för uppgifterna. Därefter
skapade jag en uml diagram för att visualisera vad jag ville ha för metoder.
### Lösningsförslag innan uppgiften påbörjas

### UML Diagram
Som sagt så använde jag mig av uml diagram för att visualisera vad jag ville ha för metoder.
Sedan implementerade jag dessa metoder i min kod, detta hjälpte mig att förstå vad jag behöver för metoder och vad jag behöver för klasser.

### Jira/Trello/Github Project och projekthantering enligt Scrum/Kanban
### GitHub Project
Jag använde mig av Github Project där jag la min backlogg och mina sprints. Detta hjälpte mig
att hålla koll på vad jag behöver göra och vad jag har gjort, med product backlogg så använde jag mig av det för att
förstå vad funktionerna ska göra. Genom att använda mig av user stories så fick jag en lättare förståelse för vad jag behöver göra.

## Arbetet och dess genomförande
Först så började jag med att planera därefter började jag skapa alla klasser som jag behövde så att det sparar på tid
och inte bara skapar en och en. Sedan började jag med att skapa enkla klasserna först, först skapades metoderna men inget logisk kod fanns inne
i metoderna på grund av att jag skulle skapa unit tester först.

Jag skapade grundläggande klasser som jag behövde för att kunna skapa en todo lista och funktionerna som behövdes,
därefter så bestämde om vissa klasser behövde en t.ex. Facade klass för att kunna göra det enklare för användaren att använda programmet och
förstå vad funktionerna gör. För att hjälpa mig göra det enkel för en användare att
förstå vad dessa funktioner gör så gav jag dem namn som är lätt att förstå.

Innan logiken gick in i metoden så behövdes dem testas först, jag skapade unit tester för att testa om metoderna fungerar som de ska och använde
mig av mockito för externa klasser som t.ex. Databasen. När jag blev klar med unit testerna så började jag med att skapa logiken i metoderna.

### Vad som varit svårt
Det som var svår var testerna, jag hade svårt att testa meny klassen där testen höll bara på att ladda och tog aldrig slut. Det var 
något jag var tvungen att tänka igenom. Jag hade också små buggar men det löste sig till slut.
### Beskriv lite olika lösningar du gjort
Lösningar som jag har gjort var att skapa olika sorters felsökningar för att stoppa programmet från att krascha på grund av användaren. Jag såg till så att
programmet förklarar till användaren vad som går fel så att användaren vet had hen gör för fel.
### Beskriv något som var besvärligt att få till
Som sagt så var testerna jobbiga att få till men, jag hade massa med problem där testerna misslyckades men till sluta så fixade jag det och det gick bra i slutet.
### Beskriv om du fått byta lösning och varför i sådana fall
Jag fick byta produktionskod som jag redan har skrivit, och det var för att unit testerna skulle bli godkända vilket till slut blev klar. Det var många klasser som jag fick byta lösningar på för att få testerna att bli godkända.
## Reflektion & Slutsatser
### Vad gick bra
Det som gick bra var att jag fick hjälp från Fredrik som kollade på min kod för att verifiera på att projektet har grundläggande funktioner, när han kollade på koden så fick jag feedback på att det var många buggar, det hjälpte mig att undvika problem samt hitta på lösningar för att fixa det.
### Vad gick dåligt
Det var massa med bugs som sagt, t.ex. Dålig implementerad produktions kod som gjorde så att testen in blev godkänd, detta tog tid att fixa men gick bra när jag faktiskt gjorde det. Men detta visar mig hur bra att testa sin kod verkligen är.
### Vad har du lärt dig
Som sagt så har detta projektet hur det ser ut att göra unit testing för ett större projekt samt hur viktigt det är att testa sin kod för att stoppa buggar. Även om det tar tid att fixa testerna först så sparar den tid i framtiden, då slipper vi sitta timmar för att fixa buggar.
### Vad hade ni gjort annorlunda om ni gjort om projektet
I helhet så ser jag inget som jag skulle gjort annorlunda, jag är nöjd med resultatet och jag har lärt mig mycket från detta projektet gällande unit testing.
### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Möjligheterna jag ser är att jag kan skapa större projekt med unit testing, detta hjälper mig att förstå hur jag ska skriva min kod och hur jag ska testa den.
Det hjälper mig också skriva en robust kod som inte kraschar på grund av användaren eller på grund av buggar samt att jag kan skapa en kod som är lätt att förstå för andra utvecklare.