# Upute
Upute za pocetak (ono sto sam uspjela pohvatati): 
1) skinuti GIT SCM https://git-scm.com/downloads
2) pratiti upute ovdje https://docs.github.com/en/get-started/using-git/about-git za "Example: Contribute to an existing repository" - tu se radi osobni branch my-branch, moze se i drukcije zvati npr. Dorote-branch jer se moj zove Ivana-branch
3) pratiti upute gore za "Example: contribute to an existing branch on GitHub" svaki put kad se edita neki dio koda
4) svaki put kad se nesto promijeni, unutar GitBash sucelja se radi nesto sto se zove "push request" sto salje promjene GitHubu, a onda se valjda na GitHubu radi "pull request" kako bi se te promjene prekontrolirale i prihvatile

# Nove novosti :)
- obrađene su sve greške (ja mislim)
- riješen je problem krivo formatiranog inputa i outputa u polinomnom kalkulatoru (za polyAdd, polyMulti) - sad sve radi "prirodno" i output je sortiran po potencijama (funkcija combineLikeTerms i klasa PolySorter)
- smješten rad s bazom podataka unutar swing worker dretve
- napravljeno je prepoznavanje polinoma i bez unosa zvjezdica

# Mogući problemi
- polinomni kalkulator ne zna sta bi sa polinomom tipa x+((x-3)+2*x^(3/2))

# Što još treba napraviti?
- treba obraditi slučajeve na polinomnom kalkulatoru kad korisnik npr nista ne upise u jedan ekran, a pozove npr zbrajanje ili npr pozove bilo sta nad praznim ekranom
- testovi/Jenkins
- treba srediti kod (napraviti sučelja, napraviti funkcije za dijelove koda koji se ponavljaju, anotirati sve da bi mogli imati javadoc)
- pohvatat skroz kod za grapher
- na kraju proći detaljno sve zahtjeve za proejkt koje je prof stavio na web i provjeriti jesmo li ispunile obavezne zahtjeve

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

- implementirano deriviranje polinoma
- implementirano množenje, zbrajanje i oduzimanje polinoma 
- pri deriviranju se uvijek derivira prvi polinom
- maknula sam gumbe iz polinomnog kalkulatora
- uspjela sam napraviti da radi spremanje u bazu podataka i dohvacanje iz baze podataka, skuzila sam da ju ipak moramo imati i da nije dovoljna memorija kakva je bila do sad jer smo i na satu radili s bazama podataka (zadnje predavanje) - ovo sam uspjela nakon neka 4 sata rada pa sam odustala - trebalo bi počistiti bazu podataka od gluposti koje sam do sad ubacila u nju, premienovati prvi stupac u "ime" kako bi korisnik pri spremanju mogao dodijeliti ime funkciji/polinomu i kasnije dohvatiti funkciju/polinom iz baze na temelju imena - za ovo bi trebalo napraviti da ime ima distinct atribut kako korisnik ne bi mogao dvaput unesti u bazu nesto s istim imenom (ili varati i unutar jave pamtiti ubačena i obrisana imena - cini se puno lakse da ne moramo petljati sa sql naredbama); mislim da bi trebalo dodati i gumb za brisanje stvari iz memorije; gore u uputama ostavljam linkove koji su mi bili korisni; takoder, rad s citanjem baze bi trebalo ubaciti u worker dretvu (zadnje predavanje); fun fact - sa sqlite se radi unutar cmd-a :)))))))))))))
- očišćen i raščlanjen kod za polinomni kalkulator - javi jel sad bolje za testiranje
- osposobljena interakcija s bazom podataka u polinomnom kalkulatoru
- osposobljena interakcija s bazom podataka u grafičkom kalkulatoru

# Linkovi za baze podataka
- https://communities.actian.com/s/article/java-sql-SQLException-Query-or-procedure-does-not-return-a-result-set ->jedan error
- https://stackoverflow.com/questions/16725377/unable-to-connect-to-database-no-suitable-driver-found ->jos jedan error
- https://www.tutorialspoint.com/sqlite/sqlite_delete_query.htm#:~:text=SQLite%20DELETE%20Query%20is%20used,the%20records%20would%20be%20deleted. ->brisanje 
- https://javadoc.scijava.org/Java14/java.sql/java/sql/package-summary.html ->stvari koje ukljucuje java.sql paket jer iz nekog razloga ant nema automatski import za ove stvari pa treba rucno importat sta fali
- https://www.tutorialspoint.com/sqlite/sqlite_select_query.htm -> select
- https://stackoverflow.com/questions/947215/how-to-get-a-list-of-column-names-on-sqlite3-database
- https://www.guru99.com/sqlite-query-insert-update.html -> insert
- https://www.tutorialspoint.com/sqlite/sqlite_commands.htm -> lijepi ispis u cmd
- https://www.guru99.com/sqlite-database.html#:~:text=SQLite%20CREATE%20Database,-Unlike%20other%20database&text=In%20this%20SQLite%20tutorial%2C%20here,%E2%80%9Ccmd%E2%80%9D%20and%20open%20it.&text=From%20the%20Installation%20and%20packages,the%20sqlite3.exe%20on%20it. ->stvaranje tablice u sqlite u cmd
- http://www.java2s.com/Code/JarDownload/sqlite/sqlite-jdbc-3.7.2.jar.zip ->ovo ces trebat skinut i ubacit u library folder
