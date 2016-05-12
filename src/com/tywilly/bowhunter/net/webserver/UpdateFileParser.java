package com.tywilly.bowhunter.net.webserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateFileParser {

	File baseDir;
	File revisionFile;
	PrintWriter write;

	public UpdateFileParser() throws IOException {
		baseDir = new File("assets/");
		revisionFile = new File("assets/revision.rev");
		
	}

	public void generateRevisionFile() {
		
		try {
			write = new PrintWriter(new FileWriter(revisionFile));
			dropToBaseFolderAndPrint(baseDir);
			write.flush();
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void dropToBaseFolderAndPrint(File file) throws IOException {

		if (file.isDirectory()) {

			File[] children = file.listFiles();

			for (File child : children) {
				dropToBaseFolderAndPrint(child);
			}

		}else{
			//write.write(file.getPath().substring(7) + "\n");
			write.println(file.getPath().substring(7));
			//System.out.println(file.getPath());
		}

	}

}
