package com.tywilly.bowhunter.update;

import java.util.ArrayList;

import com.tywilly.bowhunter.entity.Entity;

public class UpdateThread extends Thread implements Runnable {

	public static ArrayList<Entity> ents = new ArrayList<Entity>();

	private long tickStartTime = 0;

	private long tickCounterStartTime = System.currentTimeMillis();

	private int currentTick = 1;

	public static int TPS = 20;

	public boolean isRunning = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (this.isRunning) {

			tickStartTime = System.currentTimeMillis();

			for (int i = 0; i < ents.size(); i++) {

				ents.get(i).update();

			}

			try {

				long sleepTime = Math.min(1000, Math.max(
						16 - (System.currentTimeMillis() - tickStartTime), 0));

				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (System.currentTimeMillis() - tickCounterStartTime >= 1000) {
				TPS = currentTick;
				currentTick = 1;
				tickCounterStartTime = System.currentTimeMillis();
			} else {
				currentTick++;
			}

		}

	}

}
