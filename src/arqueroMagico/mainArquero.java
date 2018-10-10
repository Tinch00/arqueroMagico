package arqueroMagico;

public class mainArquero {
	
	public static void main(String[] args) {
		int disparosAEfectuar = 100;
		
		Target objetivo = new Target(4000,20);
		
		Arquero arquero1 = new Arquero(disparosAEfectuar);
		Arquero arquero2 = new Arquero(disparosAEfectuar);
		
		Flecha[] flechas1 = new Flecha[disparosAEfectuar];
		Flecha[] flechas2 = new Flecha[disparosAEfectuar];
		
		for (int i=0;i<disparosAEfectuar;i++) {
			flechas1[i] = new Flecha();
			flechas2[i] = new Flecha();
		}
		
		arquero1.setearDisparos(flechas1);
		arquero2.setearDisparos(flechas2);
		
		System.out.println("Disparo A Mansalva aumentando");
		arquero1.dispararAMansalva(objetivo, 20	, 25);
		
		
//		System.out.println("Disparos Apuntando");
//		arquero2.dispararApuntando(objetivo, 20, 15);
//		
		System.out.println("Arquero1 disparos acertados: " + arquero1.getImpactosPositivos());
//		System.out.println("Arquero2 disparos acertados: " + arquero2.getImpactosPositivos());	
		
	}

}
