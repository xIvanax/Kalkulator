# Kalkulator
original
Upute za pocetak (ono sto sam uspjela pohvatati): 
1) skinuti GIT SCM https://git-scm.com/downloads
2) pratiti upute ovdje https://docs.github.com/en/get-started/using-git/about-git za "Example: Contribute to an existing repository" - tu se radi osobni branch my-branch, moze se i drukcije zvati npr. Dorote-branch jer se moj zove Ivana-branch
3) pratiti upute gore za "Example: contribute to an existing branch on GitHub" svaki put kad se edita neki dio koda
4) svaki put kad se nesto promijeni, unutar GitBash sucelja se radi nesto sto se zove "push request" sto salje promjene GitHubu, a onda se valjda na GitHubu radi "pull request" kako bi se te promjene prekontrolirale i prihvatile

NAPOMENA:
Budući da sam dosta toga izmijenila, napravi si novi folder negdje i u njega iznova kloniraj repozitorij kao što sam ti pokazala neki dan - mislim da će ti se na taj način možda biti lakše snaći.

# Nove novosti :)
- implementirano množenje, zbrajanje i oduzimanje polinoma 
- pri deriviranju se uvijek derivira prvi polinom

# Što još treba napraviti?
- testovi/Jenkins
- korištenje vrijednosti funkcije u nekim zadanim izrazima
- spremanje polinoma u memoriju kalkulatora i njihovo dohvaćanje iz memorije
- treba maknuti gumbe iz polinomnog kalkulatora 
- treba srediti kod (napraviti sučelja, napraviti funkcije za dijelove koda koji se ponavljaju, anotirati sve da bi mogli imati javadoc)
- pohvatat skroz kod za grapher
- na kraju proći detaljno sve zahtjeve za proejkt koje je prof stavio na web i provjeriti jesmo li ispunile obavezne zahtjeve i ako jesmo možemo li nešto bolje napraviti t.d. dobijemo dodatne bodove
*optional: stvari za dizajn

# Outdated novosti (tu sam iskopirala ovo što nam više nije bitno t.d. i ti i ja lakše vidimo prave novosti)
Progress:
- Standardni kalkulator sad radi sve operacije vidljive na tipkama. 
- Omogućen je i unos putem tipkovnice te korištenje tipke backspace za brisanje unosa. Funkcionalnost tipke enter ne radi kako treba.
- Uspjela sam implementirati sučelje za crtanje grafova (paket Grapher)
      
5.2. Dorotea:

Napredak:
- uspjela sam implementirati gotovo sve funkcije da se crtaju
- mislila sam izbaciti tipku y(jer ju nigdje necemo koristiti, barem koliko ja viidm) i tipku x^2 jer imamo vec opcenitu x^y pa mislim da nam ne treba, sto mislis o tome? (time bismo rjesile onaj problem praznih gumba) -@Ivana ma moze 

5.2. Ivana:
Updates:
1) u procesu sam popravljanja gumba D jer ne radi kako treba, mislim da je glavni problem mijesanje screen-a i ekran-a prilikom updateanja jednog ili drugog #resolved
2) obrisala sam gumbe x^2 i y
3) pocistila sam malo kod (obrisala pakete koje vise ne koristimo i varijable koje ne sluze nicem)

6.2. Ivana:
Updates:
1) popravila sam gumbe za brisanje, obrisala sam gumb CE (jer mislim da on tu nema smisla - možda ga krivo razumijem, ali lako ga je vratiti nazad ako ga ipak trebamo) i zamijenila sam ga gumbom "eval" koji ce sluziti za evaluaciju funkcije i funckionirat ce kao gumb "=" u standardnom kalkulatoru
2) jos sam pocistila kod, npr. u metodi za listener-a za unarne operacije su se radile provjere hoce li operacija biti =, -, + ..., a to je nemoguce, jer se pritiskom gumbi za dobivanje tih operacija aktiviraju listeneri za binarne operacije pa unutar listener-a za unarne operacije, unarnaOperacija nikad nece biti neka od binarnih
3) ostavila sam zakomentirano hrpu System.out.println() jer sam ih koristila za debugging, ako ti bas budu smetali mozes ih obrisat, ali sam ih ostavila da ih ne moram opet pisat ak cu morat nes debuggat
4) pokusala sam malo uskladit varijable screen, ekran, textBox - sve je radilo na primjerima koje sam probavala pa se nadam da je to sad OK

Updates 2.0:
1) teoretski je graphing GUI zavrsen, trebalo bi dodatno za svaki gumb provjeriti radi li kako treba (za svaku funkciju)
