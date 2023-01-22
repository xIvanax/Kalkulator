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



