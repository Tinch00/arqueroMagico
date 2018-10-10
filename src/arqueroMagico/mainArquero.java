package arqueroMagico;

public class mainArquero {
	
	public static void main(String[] args) {
		int disparosAEfectuar = 100;
		
		Target objetivo = new Target(4000,20); //40metros. 20cm ancho (target pequeño)
		
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
		
		//Para verificar movimiento del pendulo.
//		for (double i=0;i<300;i+=0.5) {
//			System.out.println("Tiempo: " + i + "Posicion: " + objetivo.centroTargetEnFuncionDelTiempo(i));
//			
//		}
		
		System.out.println("Disparo A Mansalva aumentando");
		arquero1.dispararAMansalva(objetivo, 20, 20);
		
		System.out.println("Arquero1 disparos acertados: " + arquero1.getImpactosPositivos());
		if (arquero1.getImpactosPositivos() > 0) {
			Flecha fImp = arquero1.getFlechaImpacto();
			System.out.println("Coordenada de la flecha: X: " + fImp.getCoordenadaFinal().x 
					+ "Y: " + Math.round(fImp.getCoordenadaFinal().y * 100.0) / 100.0
					+ "Z: " + Math.round(fImp.getCoordenadaFinal().z));
		}else
			System.out.println("Sin aciertos");
		
		
		System.out.println("Disparos Apuntando");
		arquero2.dispararApuntando(objetivo, 20, 20);
		if (arquero2.getImpactosPositivos() > 0) {
			Flecha fImp = arquero2.getFlechaImpacto();
			System.out.println("Coordenada de la flecha: X: " + fImp.getCoordenadaFinal().x 
					+ "Y: " + Math.round(fImp.getCoordenadaFinal().y * 100.0) / 100.0
					+ "Z: " + Math.round(fImp.getCoordenadaFinal().z));
		}
		
		
		
		System.out.println("Arquero2 disparos acertados: " + arquero2.getImpactosPositivos());	
		
	}

}
