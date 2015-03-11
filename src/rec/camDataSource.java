package rec;

import java.io.*;
import java.util.*;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.format.VideoFormat;

import LookAndFeel.SplashScreen;

public class camDataSource {

	private Component parent;

	private DataSource mainCamSource;
	private MediaLocator ml;
	private Processor processor;
	private boolean processing;
	
	public camDataSource() {}

	public camDataSource(Component parent) {
		this.parent = parent;
		setProcessing(false);
	}

	public void setMainSource() {
		
		setProcessing(false);
		VideoFormat vidformat = new VideoFormat(VideoFormat.YUV);
		Vector devices = CaptureDeviceManager.getDeviceList(vidformat);
		
		CaptureDeviceInfo di = null;

		if (devices.size() > 0)
			di = (CaptureDeviceInfo) devices.elementAt(0);
		else {
			JOptionPane.showMessageDialog(parent,
					"Your camera is not connected" , "No webcam found",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			ml = di.getLocator();
			setMainCamSource(Manager.createDataSource(ml));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(parent, "Exception locating media: "
					+ e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	}

	public void makeDataSourceCloneable() {

		setMainCamSource(Manager.createCloneableDataSource(getMainCamSource())); // turn
																					// our
																					// data
																					// source
																					// to
																					// a
																					// cloneable
																					// data
																					// source

	}

	public void startProcessing() {

		try {
			processor = Manager.createProcessor(getMainCamSource());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parent,
					"IO Exception creating processor: " + e.getMessage(),
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		} catch (NoProcessorException e) {
			JOptionPane.showMessageDialog(parent,
					"Exception creating processor: " + e.getMessage(), "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		camStateHelper playhelper = new camStateHelper(processor);
		if (!playhelper.configure(10000)) {
			JOptionPane.showMessageDialog(parent, "cannot configure processor",
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		processor.setContentDescriptor(null);

		if (!playhelper.realize(10000)) {
			JOptionPane.showMessageDialog(parent, "cannot realize processor",
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		processor.start(); // In order for or your clones to start, you must
							// start the original source
		/*
		 * try{ Thread.sleep(10000); }catch(java.lang.InterruptedException e){}
		 * 
		 * Control control =
		 * processor.getControl("javax.media.control.FrameRateControl"); if (
		 * control != null && control instanceof
		 * javax.media.control.FrameRateControl ){
		 * ((javax.media.control.FrameRateControl)control).setFrameRate(0.2f); }
		 * else{ System.out.println("no frame control"); }
		 */

		setProcessing(true);
	}

	public DataSource cloneCamSource() {
		if (!getProcessing())
			setMainSource();
		return ((SourceCloneable) getMainCamSource()).createClone();
		// return processor.getDataOutput();
	}

	public DataSource getMainCamSource() {
		return mainCamSource;
	}

	public void setMainCamSource(DataSource mainCamSource) {
		this.mainCamSource = mainCamSource;
	}

	public void setMl(MediaLocator ml) {
		this.ml = ml;
	}

	public MediaLocator getMl() {
		return ml;
	}

	public boolean getProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;

	}

	public void setParent(Component parent) {
		this.parent = parent;
	}

	public Component getParent() {
		return parent;
	}
}