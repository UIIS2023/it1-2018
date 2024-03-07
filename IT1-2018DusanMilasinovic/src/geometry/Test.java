package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 
		 * 
		Tacka t1 = new Tacka();
		System.out.println("x koordinata: "+t1.getX());
		t1.setX(10);
		t1.setY(20);
		System.out.println("x koordinata: "+t1.getX());
		Linija l1 = new Linija();
		l1.settPocetna(t1);
		Tacka t2 = new Tacka();
		l1.settKrajnja(t2);
		System.out.println("X koordinatu pocetne tacke linije: "+l1.gettPocetna().getX());
		System.out.println("Udaljenost t1 od t2 je: "+t1.udaljenost(t2));
		System.out.println("Duzina l1: "+l1.duzina());
		//postaviti vrednost y koordinate pocetne tacke l1 na vrednost
		// x koordinate krajnje tacke linije l1
		l1.gettPocetna().setY(l1.gettKrajnja().getX());
		System.out.println("Y koordinata pocetne tacke linije l1: "+l1.gettPocetna().getY());
		//postaviti x koordinatu krajnje tacke l1 na vrednost duzine linije l1 umanjene za 
		//vrednost zbira x i y koordinate pocetne tacke linije.
		l1.gettKrajnja().setX((int) l1.duzina()-(l1.gettPocetna().getX()+l1.gettPocetna().getY()));
		System.out.println("X krajnje tacke l1: "+l1.gettKrajnja().getX());
		Pravougaonik p1 = new Pravougaonik();
		p1.setGoreLevo(t1);
		System.out.println("X koordinata gore levo p1: "+p1.getGoreLevo().getX());
		p1.getGoreLevo().setX(50);
		System.out.println("X t1: "+t1.getX());
		Krug kr1 = new Krug();
		kr1.setCentar(t1);
		System.out.println("Y centra kr1: "+kr1.getCentar().getY());
		//postaviti x koordinatu centra kruga
		//na vrednost zbira x koordinata pocetne i krajnje tacke l1
		kr1.getCentar().setX(l1.gettPocetna().getX()+l1.gettKrajnja().getX());
		System.out.println("X centra kr1: "+kr1.getCentar().getX());
		Tacka t3 = new Tacka(23, 32); 
		System.out.println("X t3: "+t3.getX());
		t2 = t3;
		Tacka t4 = new Tacka(t3.getX(),t3.getY());
		System.out.println("Y t4: "+t4.getY());
		Linija l2 = new Linija(new Tacka(4, 4), new Tacka(7, 7));
		System.out.println("duzina l2: "+l2.duzina());
		System.out.println("X krajnje tacke l2: "+l2.gettKrajnja().getX());
		l2.gettKrajnja().pomeriZa(5, 10);
		System.out.println("duzina l2: "+l2.duzina());
		//Zbir udaljenosti pocetne tacke linije l1 
		//od tacke gore levo p1 i duzine linije l2
		System.out.println("Zbir: "+ (l1.gettPocetna().udaljenost(p1.getGoreLevo())+ l2.duzina()));
		Pravougaonik p2 = new Pravougaonik(l2.gettPocetna(), 100, 50);
		System.out.println("povrsina p2: "+p2.povrsina());
		//kreirati kvadrat kv2
		//gore levo --> ista kao pocetna tacka linije l2, pomerena za po x 15 po y 20
		//stranica = duzina l2 uvecana za vrednost povrsine p1
		Tacka tp = new Tacka(l2.gettPocetna().getX(), l2.gettPocetna().getY());
		tp.pomeriZa(15, 20);
		Kvadrat kv2 = new Kvadrat(tp, (int)(l2.duzina() + p1.povrsina()));
		System.out.println("Obim kv2: "+kv2.obim());
		*/
		/*Tacka t1 = new Tacka(15,10);
		System.out.println(t1);
		Linija l1 = new Linija(t1, new Tacka(20, 20));
		System.out.println(l1);
		Pravougaonik p1 = new Pravougaonik(t1, 100, 50);
		System.out.println(p1);
		Kvadrat kv1 = new Kvadrat(t1, 100);
		System.out.println(kv1);
		Krug kr1 = new Krug(t1, 55);
		System.out.println(kr1);
		
		Tacka t2 = new Tacka(25, 25);
		Tacka t3 = t2;
		Tacka t4 = new Tacka(25, 25);
		if(t2 == t3)
			System.out.println("Tacke t2 i t3 su iste!");
		if(t2 == t4)
			System.out.println("Tacke t2 i t4 su iste!");
		if(t2.equals(t3))
			System.out.println("Tacke t2 i t3 su iste! equals");
		if(t2.equals(t4))
			System.out.println("Tacke t2 i t4 su iste! equals");
		
		Linija l2 = new Linija(t1, new Tacka(20, 20));
		System.out.println("Linije l1 i l2 su iste! "+l1.equals(l2));
		
		
		Pravougaonik p2 = new Pravougaonik(new Tacka(100, 100), 45, 54);
		Pravougaonik p3 = new Pravougaonik(new Tacka(100, 100), 45, 54);
		System.out.println("p2 = p3? "+p2.equals(p3));
		Kvadrat kv2 = new Kvadrat(new Tacka(50, 50), 75);
		Kvadrat kv3 = new Kvadrat(new Tacka(50, 50), 75);
		System.out.println("kv2 = kv3? "+kv2.equals(kv3));
		Krug kr2 = new Krug(new Tacka(80, 80),99);
		Krug kr3 = new Krug(new Tacka(80, 80),99);
		System.out.println("kr2 = kr3? "+kr2.equals(kr3));
		// postaviti y koordinatu centra pravougaonika p2 na vrednost
		// y koordinate sredine linije dijagonale pravougaonika p2
		p2.centar().setY(p2.dijagonala().sredinaLinije().getY());
		kv2.centar().setY(kv2.dijagonala().sredinaLinije().getY());
		
		Pravougaonik p4 = new Pravougaonik(new Tacka(10, 10), 100, 50);
		Kvadrat kv4 = new Kvadrat(new Tacka(10, 10), 50);
		System.out.println("Centar pravougaonika p4: "+p4.centar());
		System.out.println("Centar kvadrata kv4: "+kv4.centar());
		System.out.println("Dijagonala p4: "+p4.dijagonala());
		System.out.println("Dijagonala kv4: "+kv4.dijagonala());*/
		
		
		Point t1 = new Point(10,10);
		Point t2 = new Point(20,20);
		Point t3 = new Point(30,30);
		Point t4 = new Point(40,40);
		
		Point[] nizTacaka = {t3,t1,t4,t2};
		for(int i=0;i<nizTacaka.length;i++)
			System.out.println(nizTacaka[i]);
		
		Arrays.sort(nizTacaka);
		
		System.out.println("sortiran niz");
		for(int i=0;i<nizTacaka.length;i++)
			System.out.println(nizTacaka[i]);
		
		Line l2 = new Line(t1, new Point(20, 20));
		Line l3 = new Line(t1, new Point(30, 30));
		Line l4 = new Line(t1, new Point(40, 40));
		Line l5 = new Line(t1, new Point(50, 50));
		
		Line[] nizLinija = {l3,l2,l4,l5};
		
		for(int i=0;i<nizLinija.length;i++)
			System.out.println(nizLinija[i]);
		
		Arrays.sort(nizLinija);
		
		System.out.println("sortiran niz linija:");
		for(int i=0;i<nizLinija.length;i++)
			System.out.println(nizLinija[i]);
		
		Circle k1 = new Circle(t1, 10);
		Circle k2 = new Circle(t1, 20);
		Circle k3 = new Circle(t1, 30);
		Circle k4 = new Circle(t1, 40);
	
		
		Circle[] nizKrugova = {k3,k2,k4,k1};
		
		for(int i=0;i<nizKrugova.length;i++)
			System.out.println(nizKrugova[i]);
		
		Arrays.sort(nizKrugova);
		
		System.out.println("sortiran niz krugova:");
		for(int i=0;i<nizKrugova.length;i++)
			System.out.println(nizKrugova[i]);
		
		RectangleDraw kv1 = new RectangleDraw(t1, 10);
		RectangleDraw kv2 = new RectangleDraw(t1, 20);
		RectangleDraw kv3 = new RectangleDraw(t1, 30);
		RectangleDraw kv4 = new RectangleDraw(t1, 40);
	
		
		RectangleDraw[] nizKvadrata = {kv3,kv2,kv4,kv1};
		
		for(int i=0;i<nizKvadrata.length;i++)
			System.out.println(nizKvadrata[i]);
		
		Arrays.sort(nizKvadrata);
		
		System.out.println("sortiran niz kvadrata:");
		for(int i=0;i<nizKvadrata.length;i++)
			System.out.println(nizKvadrata[i]);
		
		Rectangle p1 = new Rectangle(t1, 100, 10);
		Rectangle p2 = new Rectangle(t1, 100, 20);
		Rectangle p3 = new Rectangle(t1, 100, 30);
		Rectangle p4 = new Rectangle(t1, 100, 40);
		
		Rectangle[] nizPravougaonika = {p3,p2,p4,p1};
		
		for(int i=0;i<nizPravougaonika.length;i++)
			System.out.println(nizPravougaonika[i]);
		
		Arrays.sort(nizPravougaonika);
		
		System.out.println("sortiran niz pravougaonika:");
		for(int i=0;i<nizPravougaonika.length;i++)
			System.out.println(nizPravougaonika[i]);
		
		ArrayList<Point> lista = new ArrayList<Point>();
		
		lista.add(t1);
		lista.add(t2);
		lista.add(t3);
		lista.add(t4);
		
		lista.remove(1);
		lista.add(1, t2);
		
		Iterator it = lista.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		System.out.println(lista.size());
		
		HashMap<String, Shape> hm = new HashMap<String, Shape>();
		hm.put("tacka", t1);
		hm.put("linija", l2);
		hm.put("krug", k1);
		hm.put("tacka", kv1);
		
		hm.remove("tacka");
		
		System.out.println(hm.get("tacka"));
		System.out.println(hm.values());
		
	}

}
