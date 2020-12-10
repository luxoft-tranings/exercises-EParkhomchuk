package com.luxoft.jva.multithreading.ch06_atomic;

/**
 * In this exercise we will play volatile ping-pong:
 * <ul>
 * <li>Create classes {@link Ping} and {@link Pong} that implements {@link Runnable}.</li>
 * <li>Create class {@link Ball} that has two volatile fields ping and pong.</li>
 * </ul>
 * <p>
 * <p>
 * In loop
 * {@link Ping}:
 * <ul>
 * <li>Increase ping value by 1</li>
 * <li>Do nothing while current step != pong</li>
 * </ul>
 * <p>
 * <p>
 * {@link Pong}:
 * <ul>
 * <li>Do nothing while ping != current step</li>
 * <li>Increase pong value by 1</li>
 * </ul>
 *
 * @author BKuczynski.
 */


public class Exercise12 {

	static class Ball
	{
		public volatile int ping = 0;
		public volatile int pong = 0;
	}

	static class Ping
	{
		void Run(Ball ball)
		{
			if(ball.ping == ball.pong)
			{
				System.out.println("Ping = " + ball.ping );
				ball.ping++;
			}
		}
	}

	static class Pong
	{
		void Run(Ball ball)
		{
			if(ball.pong < ball.ping)
			{
				System.out.println("Pong = " + ball.pong );
				ball.pong++;
			}
		}
	}

	public static void main(String[] args)
	{
		Ball ball = new Ball();
		Ping ping = new Ping();
		Pong pong = new Pong();

		new Thread(() ->
		{
			while(true)
			{
				ping.Run(ball);
				try
				{
					Thread.sleep(1000);
				}
				catch (Exception e) {

				}
			}
		}).start();
		new Thread(() ->
		{
			while(true)
			{
				pong.Run(ball);
				try
				{
					Thread.sleep(1000);
				}
				catch (Exception e) {

				}
			}
		}).start();

		// your code goes here
	}

}
