package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingThread extends Thread {

	private String url;

	public PingThread() {
		super();
	}

	public PingThread(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {
			String name = urlToName();
			BufferedReader reader = execCommand("ping -4 -c 10 " + url);
			System.out.println("Pinging \u001B[33m" + url + "\u001B[0m...");
			String line = reader.readLine();
			while (!line.contains("rtt")) {
				line = reader.readLine();
				if (line.contains("bytes")) {
					double time = Double.parseDouble(line.split(" ")[7].replaceAll("(time=)|( ms)", ""));
					System.out.println(name + ": " + time + " ms");
				}
			}
			double averageTime = Double.parseDouble(line.split("/")[4]);
			System.out.println("\u001B[33m" + name + ": Average: " + averageTime + " ms\u001B[0m");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private BufferedReader execCommand(String command) throws IOException {
		return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
	}

	private String urlToName() {
		switch (url) {
		case "www.google.com.br":
			return "Google";
		case "www.uol.com.br":
			return "UOL";
		case "www.terra.com.br":
			return "Terra";
		default:
			return "Unknow";
		}
	}

}
