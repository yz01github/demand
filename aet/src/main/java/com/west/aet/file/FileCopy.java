package com.west.aet.file;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

@Getter
public class FileCopy {
	
	// 源目录,或源文件
	private String sourceDir = "D:\\eclipse_workspace\\shxi\\library";
	// 目标目录
	private String targetDir = "D:\\eclipse_workspace\\shxi\\library\\all_jar_bycode";
	
	public static void main(String[] args) {
		FileCopy fileCfg = new FileCopy();
		
		File file = new File(fileCfg.getSourceDir());
		SuffixFileFilter filter = new SuffixFileFilter(".jar");
		File fileDir = new File(fileCfg.getTargetDir());
		Collection<File> listFiles = FileUtils.listFiles(file, filter, TrueFileFilter.INSTANCE);
		// 循环copy文件
		listFiles.forEach(f -> {
			try {
				String filePath = f.getPath() + f.getName();
				System.out.println(filePath + " copy begin...");
				FileUtils.copyFileToDirectory(f, fileDir);
				System.out.println(filePath + " copy end...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		System.out.println("copy over!");
	}
	
}
