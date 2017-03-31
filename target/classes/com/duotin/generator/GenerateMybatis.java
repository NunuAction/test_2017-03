/**
 *@author:lrh 
 *
 *@create:2014年12月19日上午12:04:10
 */
package com.duotin.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * @author lrh
 * @create 2014年12月19日上午12:04:10
 */
public class GenerateMybatis {

//	private static final String XML_NAME = "dt-wechat-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-ad-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-user-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-live-generatorConfig.xml";
	private static final String XML_NAME = "dt-www-open-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-generatorConfig.xml";
//	private static final String XML_NAME = "dt-ad-generatorConfig.xml";
//	private static final String XML_NAME = "dt-message-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-ads-RecordConfig.xml";
//	private static final String XML_NAME = "dt-www-coupon.xml";
//	private static final String XML_NAME = "dt-ub-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-robot-generatorConfig.xml";
//	private static final String XML_NAME = "dt-matrix-crawl-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-rank-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-publish-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-operating-generatorConfig.xml";
//	private static final String XML_NAME = "dt-user-behavior-statistics-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-podcast-generatorConfig.xml";
//	private static final String XML_NAME = "dt-www-publish-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-rank-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-auth-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-topic-generatorConfig.xml";

//	private static final String XML_NAME = "dt-core-tag-generatorConfig.xml";
//	private static final String XML_NAME = "dt-core-usertag-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-mall-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-podcast-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-generatorConfig.xml";
//  private static final String XML_NAME = "dt-ad-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-page-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-recommend-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-topic-generatorConfig.xml";
//  	private static final String XML_NAME = "dt-www-robot-generatorConfig.xml";
//  private static final String XML_NAME = "dt-real-statistics-generatorConfig.xml";
//  private static final String XML_NAME = "dt-user-behavior-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-crawler-generatorConfig.xml";
//  private static final String XML_NAME = "dt-www-starup-page-generatorConfig.xml";
//  private static final String XML_NAME = "dt-real-statistics-generatorConfig.xml";
//  private static final String XML_NAME = "dt-user-behavior-generatorConfig.xml";

//	private static final String XML_NAME = "dt-real-behavior-generatorConfig.xml";
//  private static final String XML_NAME = "dt-real-statistics-generatorConfig.xml";
//  private static final String XML_NAME = "dt-user-behavior-generatorConfig.xml";
//  private static final String XML_NAME = "dt-statistics-generatorConfig.xml";


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String configDir = GenerateMybatis.class.getClassLoader().getResource("generate").getFile();
		if(args!=null&&args.length>0){
            configDir=args[0];
		}

		List<String> warnings = new ArrayList<String>();
		Set<String> fullyqualifiedTables = new HashSet<String>();

		Set<String> contexts = new HashSet<String>();
		try {
			for (File configurationFile : new File(configDir).listFiles()) {
				if(configurationFile.getName().equals(XML_NAME)) {
					ConfigurationParser cp = new ConfigurationParser(warnings);
					Configuration config = cp.parseConfiguration(configurationFile);

					DefaultShellCallback shellCallback = new DefaultShellCallback(Boolean.TRUE);

					MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);

					ProgressCallback progressCallback = new VerboseProgressCallback();

					myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);
				}
			}
		} catch (XMLParserException e) {
			writeLine(getString("Progress.3")); //$NON-NLS-1$
			writeLine();
			for (String error : e.getErrors()) {
				writeLine(error);
			}

			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (InvalidConfigurationException e) {
			writeLine(getString("Progress.16")); //$NON-NLS-1$
			for (String error : e.getErrors()) {
				writeLine(error);
			}
			return;
		} catch (InterruptedException e) {
			// ignore (will never happen with the DefaultShellCallback)
			;
		}

		for (String warning : warnings) {
			writeLine(warning);
		}

		if (warnings.size() == 0) {
			writeLine(getString("Progress.4")); //$NON-NLS-1$
		} else {
			writeLine();
			writeLine(getString("Progress.5")); //$NON-NLS-1$
		}
	}

	private static void writeLine(String message) {
		System.out.println(message);
	}

	private static void writeLine() {
		System.out.println();
	}

}
