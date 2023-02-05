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

Napredak:
  -uspjela sam implementirati gotovo sve funkcije da se crtaju
  -mislila sam izbaciti tipku y(jer u nigdje necemo koristiti) i tipku x^2 jer imamo vec opcenitu x^y pa mislim da nam ne treba, sto mislis o tome?
      -ugl zato nisam ni implementirala da radi funkcija x^2

Problem:
  -kako je arcsin definirana na segmentu -1<=x<=1, onda bi se samo na njemu i trebalo crtati, ali vidjet ces problem kada pokrenes npr arcsin(x) imam dva pravca paralelna s y osi koji ne bi trebali biti tu. Probala sam ispraviti na nekoliko načina, ali nisam uspjela. Analogno i za arccos. Sve ostale funkcije rade super.


