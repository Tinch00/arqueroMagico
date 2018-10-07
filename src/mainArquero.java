
public class mainArquero {
	
	public static void main(String[] args) {
		int totalDisparos = 100;
		
		Target target = new Target(4000,20);
		Arquero arquero = new Arquero(totalDisparos);
		Flecha[] flechas = new Flecha[totalDisparos];
		
		
		Coordenada coordenadaInicio = new Coordenada(4000,150,0);
		
		
		for (int i=0;i<totalDisparos;i++) {
			flechas[i] = new Flecha();
		}
		
		arquero.setearDisparos(flechas);
		arquero.dispararAMansalvaMismaVelocidad(target, 15, 25);
		
		System.out.println("Cantidad de disparos acertados" + arquero.getImpactosPositivos());
		
		//arquero.disparar(target);
		
		
		
	}

}
