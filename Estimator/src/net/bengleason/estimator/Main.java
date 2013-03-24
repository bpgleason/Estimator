package net.bengleason.estimator;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class Main {
	public static void main(String[] args) {
		try {
			if (args.length == 0 ) {
			
			System.out.println(getTextFromPDF(getModelFile()));
			} else {
				System.out.println(getTextFromPDF(new File(args[0])));
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @param file
	 * @return String containing the entire text of the document
	 */
	private static String getTextFromPDF(File file) throws Exception {
		String text = "";

		PDDocument pddDocument = PDDocument.load(file);
		PDFTextStripper textStripper = new PDFTextStripper();
		text = textStripper.getText(pddDocument);
		pddDocument.close();

		return text;
	}

	/**
	 * Returns a File pointing to the model PDF.
	 * 
	 * @return File object representing the PDF of the model book
	 */
	public static File getModelFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF",
				"pdf");
		chooser.setFileFilter(filter);

		int result = chooser.showDialog(null, "Select PDF");
		if (result != JFileChooser.CANCEL_OPTION) {

			File theFile = chooser.getSelectedFile();
			while (!filter.accept(theFile)) {
				JOptionPane.showMessageDialog(null,
						"The file can only be a pdf!", "Error",
						JOptionPane.ERROR_MESSAGE);
				result = chooser.showDialog(null, "Select PDF");
			}
			return theFile;
		} else
			return null;
	}

	/**
	 * TODO Smart exception handling to catch non-pdf files.
	 * 
	 * @param pdfFile
	 * @return average characters per page in model pdf
	 * @throws IOException
	 */
	/*
	 * public double calculateCharactersPerPage(File pdfFile) throws IOException
	 * { PDDocument pddDocument = PDDocument.load(pdfFile); PDFTextStripper
	 * textStripper = new PDFTextStripper();
	 * 
	 * int maxChars1 = 0; int maxChars2 = 0; int maxChars3 = 0;
	 * 
	 * int chars = 0;
	 * 
	 * for (int i = 30; i < 35; i++) { chars = 0; textStripper.setStartPage(i);
	 * textStripper.setEndPage(i); chars =
	 * textStripper.getText(pddDocument).length(); maxChars1 = chars; } for (int
	 * i = 30; i < 35; i++) { chars = 0; textStripper.setStartPage(i);
	 * textStripper.setEndPage(i); chars =
	 * textStripper.getText(pddDocument).length(); if (chars > maxChars2) {
	 * maxChars2 = chars; } } for (int i = 30; i < 35; i++) { chars = 0;
	 * textStripper.setStartPage(i); textStripper.setEndPage(i); chars =
	 * textStripper.getText(pddDocument).length(); if (chars > maxChars3) {
	 * maxChars3 = chars; } }
	 * 
	 * textStripper.setStartPage(1); actualPages =
	 * pddDocument.getNumberOfPages(); textStripper.setEndPage(actualPages);
	 * textStripper.getTotalCharCnt(); charactersPerPage = ((double)(maxChars1 +
	 * maxChars2 + maxChars3))/3; // average pddDocument.close();
	 * 
	 * return charactersPerPage; }
	 */
}
