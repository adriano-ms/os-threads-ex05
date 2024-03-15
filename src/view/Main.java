package view;

import threads.PingThread;

public class Main {

	public static void main(String[] args) {
		if(System.getProperty("os.name").contains("Linux")) {
			PingThread pingGoogle = new PingThread("www.google.com.br");
			PingThread pingUol = new PingThread("www.uol.com.br");
			PingThread pingTerra = new PingThread("www.terra.com.br");
			
			pingGoogle.start();
			pingUol.start();
			pingTerra.start();
			
		} else {
			System.out.println("This is not a Linux OS");
			
		}

	}

}
