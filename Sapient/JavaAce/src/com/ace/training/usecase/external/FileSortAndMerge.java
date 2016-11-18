package com.ace.training.usecase.external;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class FileSortAndMerge {
	private static final String DIR = "data/";
	private static final String DIR_SORT = "data_sort/";

	public void sortAllFile(String dir) {
		File dirFile = new File(dir);
		for (File file : dirFile.listFiles()) {
			sortFile(file, DIR_SORT);
		}

		try {
			mergeSortedFiles(DIR_SORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void mergeSortedFiles(String sortDir) throws IOException {
		File dirFile = new File(sortDir);
		File[] listFiles = dirFile.listFiles();
		BufferedReader[] brs = new BufferedReader[listFiles.length];
		int i = 0;
		PriorityQueue<LinePair> queue = new PriorityQueue<>();
		File outFile = new File("Sorted_file.txt");
		BufferedWriter outBW = new BufferedWriter(new FileWriter(outFile));
		try {
			for (File file : dirFile.listFiles()) {
				brs[i] = new BufferedReader(new FileReader(file));
				String line = brs[i].readLine();
				if (line != null && !line.isEmpty()) {
					queue.add(new LinePair(line, i));
					i++;
				}
			}
			LinePair linePair = null;
			String line = null;
			while (!queue.isEmpty()) {
				linePair = queue.poll();
				if (linePair == null || linePair.getLine() == null) {
					System.out.println(queue.size());
					System.out.println("Line Pair Empty " + linePair);
					continue;
				}
				outBW.write(linePair.getLine());
				outBW.newLine();
				if ((line = brs[linePair.index].readLine()) != null) {
					if (line.isEmpty()) {
						continue;
					}
					queue.add(new LinePair(line, linePair.index));
				}
			}
		} finally {

			if (outBW != null) {
				outBW.close();
			}
			for (BufferedReader br : brs) {
				if (br != null) {
					br.close();
				}
			}
		}

	}

	private void sortFile(File file, String sortDir) {
		File outFile = new File(sortDir + file.getName());
		outFile.getParentFile().mkdirs();
		try (BufferedReader br = new BufferedReader(new FileReader(file));

		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {

			br.lines().sorted().forEach(s -> {
				try {
					bw.write(s);
					bw.newLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static class LinePair implements Comparable<LinePair> {
		private int index;
		private String line;

		public LinePair(String line, int index) {
			this.line = line;
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public String getLine() {
			return line;
		}

		@Override
		public int compareTo(LinePair o) {
			return line.compareTo(o.getLine());
		}
	}

	public static void main(String[] args) throws IOException {
		generateFile();
		FileSortAndMerge fileSorting = new FileSortAndMerge();
		fileSorting.sortAllFile(DIR);
	}

	private static void generateFile() throws IOException {
		for (int i = 0; i < 10; i++) {
			File f = new File(DIR + "file" + i + ".txt");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			ArrayList<Integer> numbersToWrite = getNumbersToWrite();
			bw.write(numbersToWrite.get(0).toString());
			for (int j = 1; j < numbersToWrite.size(); j++) {
				bw.write(System.lineSeparator());
				bw.write(numbersToWrite.get(j).toString());
			}
			bw.close();
			fw.close();

		}
	}

	private static ArrayList<Integer> getNumbersToWrite() {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numList.add(ThreadLocalRandom.current().nextInt(100, 999));
		}

		return numList;
	}

}
