# KBS

## Wat te doen als je aan het werk gaat

Stappen plan voor het werken aan de applicatie.

> 💡 In elke directory staat in de `README.md` van die  directory wat voor bestanden daar in moeten

### Issue aanmaken
Als eerst maak je een issue aan met informatie over wat je gaat doen. Bijvoorbeeld of je een bug gaat fixen of een feature toevoegen. Je kan ook aangeven wie de issue behandeld in het menu aan de rechter kant.

Vervolgens maak je voor die issue een nieuwe branch aan maken en ga je daar in aan het werk. Zie uitleg hieronder over hoe je een nieuwe branch aanmaakt.

### Branch aanmaken
1. Haal verandering op `git pull`
2. Maak een nieuwe branch aan met je feature naam `git checkout -b "feature_naam"`
3. Als je klaar bent
    - `git add .`
    - `git commit -m "wat je hebt veranderd"`
4. Push je branch naar github `git push -u origin "branch_naam"`
5. Nadat je het bovenstaande command hebt uitgevoerd krijg je in je terminal een link om een 'pull request' te maken. Ga naar die link
6. Klik op 'create pull request'
7. Klik op 'merge pull request', 'confirm merge'

### Programmeren
Als je java componenten wil toevoegen aan de GUI dan is dat het handigst op het op deze manier te doen:
```java
public class Gui extends JFrame {
    // Initialiseer de variabele als property
    private JButton jbButton;

    public Gui() {
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Monitor | ICTm2n2");

        // Allocate de waarde van de variable hier
        jbButton = new JButton("Button");

        // Voeg hem toe
        add(jbButton);

        setVisible(true);
    }
}
```
