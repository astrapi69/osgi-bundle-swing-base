/**
 * The MIT License
 *
 * Copyright (C) 2024 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.osgi.bundle.swing.base;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import io.github.astrapi69.awt.screen.position.ComponentPositionStore;

public class SwingFrameActivator implements BundleActivator
{

	@Override
	public void start(BundleContext context) throws Exception
	{
		// create a component for instance a JFrame...
		JFrame frame = new JFrame("JFrame from osgi");
		JPanel panel = new JPanel();
		JLabel label = new JLabel("bla");
		panel.add(label);
		panel.setPreferredSize(new Dimension(400, 300));
		frame.getContentPane().setSize(800, 400);
		frame.getContentPane().add(panel);
		// create a store for the component position
		final ComponentPositionStore componentPositionStore = new ComponentPositionStore(frame,
			SwingFrameActivator.class, 600, 500);
		// restore the component position...
		componentPositionStore.restorePosition();
		// add a shutdown hook...
		Runtime.getRuntime().addShutdownHook(new Thread(componentPositionStore::storePosition));
		// show the frame...
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		System.out.println("io.github.astrapi69.osgi.bundle.swing.base.Activator started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		System.out.println("io.github.astrapi69.osgi.bundle.swing.base.Activator stopped.");
	}

}