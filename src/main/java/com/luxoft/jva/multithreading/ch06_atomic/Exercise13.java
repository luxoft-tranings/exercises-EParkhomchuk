package com.luxoft.jva.multithreading.ch06_atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * In this exercise we will play atomic ping-pong again:
 * <ul>
 * <li>Create classes {@link Ping} and {@link Pong} that implements {@link Runnable}.</li>
 * <li>Create class {@link Ball} that has two {@link AtomicInteger} fields ping and pong.</li>
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
public class Exercise13 {

	static class Ball
	{
		public volatile AtomicInteger ping = new AtomicInteger(0);
		public volatile AtomicInteger pong = new AtomicInteger(0);
	}

	static class Ping
	{
		void Run(Ball ball)
		{
			if(ball.ping.get() == ball.pong.get())
			{
				System.out.println("Ping = " + ball.ping.get() );
				ball.ping.getAndIncrement();
			}
		}
	}

	static class Pong
	{
		void Run(Ball ball)
		{
			if(ball.pong.get() < ball.ping.get())
			{
				System.out.println("Pong = " + ball.pong.get() );
				ball.pong.getAndIncrement();
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
					Thread.sleep(2000);
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
					Thread.sleep(2000);
				}
				catch (Exception e) {

				}
			}
		}).start();

		// your code goes here
	}

}

