package rec;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.CaptureDeviceInfo;
import javax.media.Control;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSinkException;
import javax.media.NoProcessorException;
import javax.media.Processor;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.protocol.FileTypeDescriptor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import CustomClass.Movie;
import CustomClass.User;
import CustomClass.Word;
import DataBase.DataBaseMovies;
import LookAndFeel.CustomDialog;
import LookAndFeel.MyButtonUI;
import LookAndFeel.SimpleMenu;

public class RecordNew extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;

	private camDataSource dataSource;

	private DataSource camSource;
	private DataSource recordCamSource;
	private DataSink dataSink;
	private Processor processor;
	private Processor recordProcessor;
	private camStateHelper playhelper;
	File file = null;
	private User userNow;
	private Word wordNow;
	private List<Integer> ListIdmovies;
	
	private Timer timer = new Timer();
	

	public RecordNew(camDataSource dataSource, User user, Word word) {
		this.dataSource = dataSource;
		this.dataSource.setParent(this);
		this.userNow = user;
		this.wordNow = word;

		
		
		camSource = dataSource.cloneCamSource();
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		initComponents();
		try {
			processor = Manager.createProcessor(camSource);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Exception creating processor: " + e.getMessage(), "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		} catch (NoProcessorException e) {
			JOptionPane.showMessageDialog(this,
					"Exception creating processor: " + e.getMessage(), "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		playhelper = new camStateHelper(processor);
		if (!playhelper.configure(10000)) {
			JOptionPane.showMessageDialog(this, "cannot configure processor",
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		processor.setContentDescriptor(null);
		if (!playhelper.realize(10000)) {
			JOptionPane.showMessageDialog(this, "cannot realize processor",
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// setAudQuality(processor, 1.0f);
		// Control control =
		// processor.getControl("javax.media.control.FrameRateControl");
		// if ( control != null && control instanceof
		// javax.media.control.FrameRateControl )
		// ((javax.media.control.FrameRateControl)control).setFrameRate(5f);
		processor.start();
		try {
			Thread.sleep(10000);
		} catch (java.lang.InterruptedException e) {
		}

		Control control = processor
				.getControl("javax.media.control.FrameRateControl");
		if (control != null
				&& control instanceof javax.media.control.FrameRateControl) {
			((javax.media.control.FrameRateControl) control)
					.setFrameRate(15.0f);
		} else {
			System.out.println("no frame control");
		}

		processor.getVisualComponent().setBackground(Color.gray);
		centerPanel.add(processor.getVisualComponent(), BorderLayout.CENTER);
		// centerPanel.add(processor.getControlPanelComponent(),
		// BorderLayout.SOUTH);
	}

	private void initComponents() {
		northPanel = new javax.swing.JPanel();
		messageLabel = new javax.swing.JLabel();
		southPanel = new javax.swing.JPanel();
		recordButton = new javax.swing.JButton();
		fileLabel = new javax.swing.JLabel();
		centerPanel = new javax.swing.JPanel();
		userLabel = new javax.swing.JLabel();
		timerLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("My Webcam");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				processor.close();
			}
		});

		northPanel.setLayout(new java.awt.BorderLayout());

		messageLabel.setText(wordNow.getWord());
		northPanel.add(messageLabel, java.awt.BorderLayout.WEST);
		messageLabel.addMouseListener(new MouseListener() {
		
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				CustomDialog.showTooltipWindow(messageLabel, 2, wordNow);	
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		SimpleMenu sm = new SimpleMenu(userNow);
		northPanel.add(sm, java.awt.BorderLayout.EAST);
		
		getContentPane().add(northPanel, java.awt.BorderLayout.NORTH);

		southPanel.setLayout(new java.awt.BorderLayout());

		recordButton.setText("Record");
		recordButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				recordButtonActionPerformed(evt);
			}
		});

		southPanel.add(recordButton, java.awt.BorderLayout.EAST);
		southPanel.add(timerLabel, java.awt.BorderLayout.WEST);
			
		
		getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

		MyButtonUI.setupButtonUI(recordButton, 0, 1);
		centerPanel.setLayout(new java.awt.BorderLayout());

		getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

		pack();
	}

	private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {

		if (recordButton.getText().equals("Record")) {
			fileLabel.setText("File:");
			DataBaseMovies dbmv = new DataBaseMovies();
			try {
				ListIdmovies = dbmv.getMoviesIds();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file = new File(ListIdmovies.size()+1 + "_" + wordNow.getWord() + ".mov");
			recordToFile(file);
			new Timer().schedule(new GameTimer(), 10);
			fileLabel.setText("File:" + file.toString());
			recordButton.setText("Stop");
		} else {
			stopAndSend();
		}
	}
	
	public void stopAndSend() {
		stopRecording();
		recordButton.setText("Record");
		Movie mv = new Movie();
		
		DataBaseMovies db = new DataBaseMovies();
		mv.setOwner(userNow.getId());
		mv.setName(file.toString());
		mv.setWord(wordNow.getId());
		try {
			db.addMovie(mv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientPart cl = new ClientPart();
		cl.sendFile(file);
		this.dispose();
	}

	public void recordToFile(File file) {
		URL movieUrl = null;
		MediaLocator dest = null;
		try {
			movieUrl = file.toURL();
			dest = new MediaLocator(movieUrl);
		} catch (MalformedURLException e) {

		}

		recordCamSource = dataSource.cloneCamSource();
		try {
			recordProcessor = Manager.createProcessor(recordCamSource);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Exception creating record processor: " + e.getMessage(),
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		} catch (NoProcessorException e) {
			JOptionPane.showMessageDialog(this,
					"Exception creating record processor: " + e.getMessage(),
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		playhelper = new camStateHelper(recordProcessor);
		if (!playhelper.configure(10000)) {
			JOptionPane.showMessageDialog(this,
					"cannot configure record processor", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Format[] formats = new Format[2];
		// formats[1] = new AudioFormat(AudioFormat.IMA4);
		VideoFormat vfmt = new VideoFormat(VideoFormat.CINEPAK);
		(recordProcessor.getTrackControls())[0].setFormat(vfmt);
		(recordProcessor.getTrackControls())[0].setEnabled(true);
		recordProcessor.setContentDescriptor(new FileTypeDescriptor(
				FileTypeDescriptor.QUICKTIME));
		// FileTypeDescriptor outputType =
		// new FileTypeDescriptor(FileTypeDescriptor.QUICKTIME);

		// recordProcessor = Manager.createRealizedProcessor(new
		// ProcessorModel(formats,
		// outputType));

		if (!playhelper.realize(10000)) {
			JOptionPane.showMessageDialog(this, "cannot realize processor",
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Control control =
		// recordProcessor.getControl("javax.media.control.FrameRateControl");
		// if ( control != null && control instanceof
		// javax.media.control.FrameRateControl )
		// ((javax.media.control.FrameRateControl)control).setFrameRate(0.7f);
		try {
			if (recordProcessor.getDataOutput() == null) {
				JOptionPane.showMessageDialog(this, "No Data Output", "Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			dataSink = Manager.createDataSink(recordProcessor.getDataOutput(),
					dest);
			recordProcessor.start();
			dataSink.open();
			dataSink.start();
		} catch (NoDataSinkException ex) {
			JOptionPane.showMessageDialog(this,
					"No DataSink " + ex.getMessage(), "Error",
					JOptionPane.WARNING_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this,
					"IOException " + ex.getMessage(), "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		
		
		
	}

	public void stopRecording() {
		try {

			recordProcessor.close();
			dataSink.stop();
			dataSink.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"cannot stop recording " + e.getMessage(), "Error",
					JOptionPane.WARNING_MESSAGE);
		}

	}
	
	public void showWord(Word word) {
		
	}

	private javax.swing.JPanel centerPanel;
	private javax.swing.JLabel fileLabel;
	private javax.swing.JLabel messageLabel;
	private javax.swing.JPanel northPanel;
	private javax.swing.JButton recordButton;
	private javax.swing.JPanel southPanel;
	private javax.swing.JLabel userLabel;
	 private JLabel       timerLabel;
	// End of variables declaration
	CaptureDeviceInfo videoDevice = null;
	
	public class GameTimer extends TimerTask {
        public void run() {
            int seconds = 0, minutes = 0;
            while (true) {
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e){}
              seconds++;
              //if (minutes != 0)
                  timerLabel.setText(Integer.toString(minutes) + " ì : " + Integer.toString(seconds) + " ñ");
              if (seconds == 59) {
                seconds = -1;
                minutes++;
            }
              if (minutes == 1 && seconds == 30) {
            	  stopAndSend();
            	  
            	  PlayOrCreate poc = new PlayOrCreate(userNow);
					poc.setSize(720, 720);
					poc.setLocationRelativeTo(null);
					poc.setVisible(true);
            	  break;
              }
          }
      }
}
}
