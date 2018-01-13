/**
 * 
 */
package com.github.exahexa.yukebox.data;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;



/**
 * @author exahexa
 * 
 */
public class FileVisitor extends SimpleFileVisitor<Path> {
	
	private ArrayList<Path> fileArray;
	
	public FileVisitor(ArrayList<Path> fileArray) {
		this.fileArray = fileArray;
	}
	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#postVisitDirectory(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult postVisitDirectory(Path arg0, IOException arg1) throws IOException {
		// TODO Auto-generated method stub
		return super.postVisitDirectory(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#preVisitDirectory(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult preVisitDirectory(Path arg0, BasicFileAttributes arg1) throws IOException {
		// TODO Auto-generated method stub
		return FileVisitResult.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFile(java.lang.Object, java.nio.file.attribute.BasicFileAttributes)
	 */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
		if (file.toString().endsWith(".mp3")) {
			fileArray.add(file);
		}
		return FileVisitResult.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see java.nio.file.SimpleFileVisitor#visitFileFailed(java.lang.Object, java.io.IOException)
	 */
	@Override
	public FileVisitResult visitFileFailed(Path arg0, IOException arg1) throws IOException {
		// TODO Auto-generated method stub
		return super.visitFileFailed(arg0, arg1);
	}
	
	

}
