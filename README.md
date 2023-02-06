# Kalkulator
original
Upute za pocetak (ono sto sam uspjela pohvatati): 
1) skinuti GIT SCM https://git-scm.com/downloads
2) pratiti upute ovdje https://docs.github.com/en/get-started/using-git/about-git za "Example: Contribute to an existing repository" - tu se radi osobni branch my-branch, moze se i drukcije zvati npr. Dorote-branch jer se moj zove Ivana-branch
3) pratiti upute gore za "Example: contribute to an existing branch on GitHub" svaki put kad se edita neki dio koda
4) svaki put kad se nesto promijeni, unutar GitBash sucelja se radi nesto sto se zove "push request" sto salje promjene GitHubu, a onda se valjda na GitHubu radi "pull request" kako bi se te promjene prekontrolirale i prihvatile

NAPOMENA:
Budući da sam dosta toga izmijenila, napravi si novi folder negdje i u njega iznova kloniraj repozitorij kao što sam ti pokazala neki dan - mislim da će ti se na taj način možda biti lakše snaći.
Progress:
- Standardni kalkulator sad radi sve operacije vidljive na tipkama. 
- Omogućen je i unos putem tipkovnice te korištenje tipke backspace za brisanje unosa. Funkcionalnost tipke enter ne radi kako treba.
- Uspjela sam implementirati sučelje za crtanje grafova (paket Grapher)
- pokušala sam navedeno sučelje integrirati u klasu GraphingGUI, uspjela sam postići da se unos u tab-u "unos" prikazuje u textboxu na dnu sučelja za crtanje grafa u tab-u "Graf" 

Problems:
- u originalnom sučelju za crtanje grafa, crtanje se pokreće tipkom enter - kako mi u našem kalkulatoru želimo imati unos preko gumbi, pokušala sam implementirat tako da pritisak gumba jednakosti igra ulogu pritiska tipke enter, ali iz nekog razloga ne funkcionira
      -ovo sada radi
      
5.2. Dorotea:

Napredak:
- uspjela sam implementirati gotovo sve funkcije da se crtaju
- mislila sam izbaciti tipku y(jer ju nigdje necemo koristiti, barem koliko ja viidm) i tipku x^2 jer imamo vec opcenitu x^y pa mislim da nam ne treba, sto mislis o tome? (time bismo rjesile onaj problem praznih gumba) -@Ivana ma moze 

Problem:
- kako je arcsin definirana na segmentu -1<=x<=1, onda bi se samo na njemu i trebalo crtati, ali vidjet ces problem kada pokrenes npr arcsin(x) imam dva pravca paralelna s y osi koji ne bi trebali biti tu. Probala sam ispraviti na nekoliko načina, ali nisam uspjela. Analogno i za arccos. Sve ostale funkcije rade super.

5.2. Ivana:
Updates:
1) u procesu sam popravljanja gumba D jer ne radi kako treba, mislim da je glavni problem mijesanje screen-a i ekran-a prilikom updateanja jednog ili drugog
2) obrisala sam gumbe x^2 i y
3) pocistila sam malo kod (obrisala pakete koje vise ne koristimo i varijable koje ne sluze nicem)

6.2. Ivana:
Updates:
1) popravila sam gumbe za brisanje, obrisala sam gumb CE (jer mislim da on tu nema smisla - možda ga krivo razumijem, ali lako ga je vratiti nazad ako ga ipak trebamo) i zamijenila sam ga gumbom "eval" koji ce sluziti za evaluaciju funkcije i funckionirat ce kao gumb "=" u standardnom kalkulatoru
2) jos sam pocistila kod, npr. u metodi za listener-a za unarne operacije su se radile provjere hoce li operacija biti =, -, + ..., a to je nemoguce, jer se pritiskom gumbi za dobivanje tih operacija aktiviraju listeneri za binarne operacije pa unutar listener-a za unarne operacije, unarnaOperacija nikad nece biti neka od binarnih
3) ostavila sam zakomentirano hrpu System.out.println() jer sam ih koristila za debugging, ako ti bas budu smetali mozes ih obrisat, ali sam ih ostavila da ih ne moram opet pisat ak cu morat nes debuggat
4) pokusala sam malo uskladit varijable screen, ekran, textBox - sve je radilo na primjerima koje sam probavala pa se nadam da je to sad OK
