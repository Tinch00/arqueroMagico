package arqueroMagico;

public class mainArquero {
	
	public static void main(String[] args) {
		int disparosAEfectuar = 100;
		
		Target objetivo = new Target(4000,20);
		Arquero arquero = new Arquero(disparosAEfectuar);
		Flecha[] flechas = new Flecha[disparosAEfectuar];		
		
		for (int i=0;i<disparosAEfectuar;i++) {
			flechas[i] = new Flecha();
		}
		
		arquero.setearDisparos(flechas);
		
		System.out.println("Disparo A Mansalva");
		arquero.dispararAMansalva(objetivo, 15, 25);
		
		System.out.println("Disparos Apuntando");
		arquero.dispararApuntando(objetivo, 20, 15);
		
		System.out.println("Cantidad de disparos acertados" + arquero.getImpactosPositivos());
		
		//arquero.disparar(target);
		
		
		
	}

}
