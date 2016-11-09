package com.ace.training.usecase;

import java.awt.FlowLayout;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class DownloadManager {
	private static String file = "https://docs.oracle.com/cd/E20111_01/otm/acrobat/scalability.pdf";

	public static void main(String[] args) {
		//file = "https://www.leadtools.com/whitepapers/2014/implementing-a-standardized-pdfa-document-storage-system-with-leadtools.pdf";
		JFrame frame = new JFrame("Download Manager");
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 5,
				TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
		Downloader downloader = null;
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		for (int i = 0; i < 10; i++) {
			downloader = new Downloader(file, new File(file).getName());
			panel.add(downloader);
			executor.submit(downloader);
		}
		frame.setContentPane(panel);
	    frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 400);
		frame.setVisible(true);

	}

	static class Downloader extends JProgressBar implements Runnable {
		private String downloadFilePath;
		private String downloadFileName;

		public Downloader(String downloadFilePath, String downloadFileName) {
			super(0, 100);
			this.downloadFilePath = downloadFilePath;
			this.downloadFileName = downloadFileName;
		}

		@Override
		public void run() {
			JProgressBar p = new JProgressBar();
			int pVal = 0;
			BufferedInputStream in = null;
			FileOutputStream fout = null;
			HttpURLConnection conn = null;
			int totalSize = 0;
			double sumCount =0.0;
			try {
				URL url = new URL(downloadFilePath);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("HEAD");
				totalSize = conn.getContentLength();

				in = new BufferedInputStream(url.openStream());
				fout = new FileOutputStream(downloadFileName);
				final byte data[] = new byte[1024];
				int count;
				while ((count = in.read(data, 0, 1024)) != -1) {
					fout.write(data, 0, count);
					sumCount += count;
					int prgVal= (int) Math.round(sumCount/totalSize*100.0);
					setValue(prgVal);
					setString(prgVal+"");
					setStringPainted(true);
					//setValue((int) ((readBytes/(totalSize*1.0))*100));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();	
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fout != null) {
					try {
						fout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			p.setValue(pVal);

		}
	}
}
