package arqueroMagico;

public class mainArquero {
	
	public static void main(String[] args) {
		int disparosAEfectuar = 100;
		
		Target target = new Target(4000,20);
		Arquero arquero = new Arquero(disparosAEfectuar);
		Flecha[] flechas = new Flecha[disparosAEfectuar];		
		
		for (int i=0;i<disparosAEfectuar;i++) {
			flechas[i] = new Flecha();
		}
		
		arquero.setearDisparos(flechas);
		
		System.out.println("Disparo A Mansalva");
		arquero.dispararAMansalvaMismaVelocidad(target, 15, 25);
		
		System.out.println("Disparos Apuntando");
		arquero.dispararApuntando(target, 10, 20);
		
		System.out.println("Cantidad de disparos acertados" + arquero.getImpactosPositivos());
		
		//arquero.disparar(target);
		
		
		
	}

}
