package com.duotin.util;

import com.duotin.util.beans.DuotinMp3Bean;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.datatype.TextEncodedStringNullTerminated;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class FileUtils {

	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	public static final int BUFSIZE = 1024 * 8;

	static {
		AudioFileIO.logger.setLevel(Level.OFF);
		ID3v23Frame.logger.setLevel(Level.OFF);
		ID3v23Tag.logger.setLevel(Level.OFF);
		TextEncodedStringNullTerminated.logger.setLevel(Level.OFF);
	}

	public static void mergeFile(String targetPath, List<File> files) {
		if (StringUtils.isNotEmpty(targetPath) && CollectionUtils.isNotEmpty(files)) {
			FileChannel outChannel = null;
			FileChannel fc = null;
			FileInputStream in = null;
			try {
				outChannel = new FileOutputStream(targetPath).getChannel();
				for (File file : files) {
					in = new FileInputStream(file);
					fc = in.getChannel();
					ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
					while (fc.read(bb) != -1) {
						bb.flip();
						outChannel.write(bb);
						bb.clear();
					}
					fc.close();
					in.close();
				}
			} catch (Throwable e) {
				File file = new File(targetPath);
				if (file.exists()) {
					file.delete();
				}
				e.printStackTrace();
			} finally {
				try {
					if (outChannel != null) {
						outChannel.close();
					}
					if (fc != null) {
						fc.close();
					}
					if (in != null) {
						in.close();
					}
				} catch (IOException ignore) {
				}
			}

		}
	}

	public static Boolean flvTransMp3(String flvPath, String targetPath, String ffmpegPath) {
		if (StringUtils.isNotEmpty(ffmpegPath, targetPath, flvPath)) {
			File file = new File(flvPath);
			if (file.exists()) {
				try {
					Runtime runtime = Runtime.getRuntime();
					StringBuilder command = new StringBuilder();
					command.append(ffmpegPath);
					command.append("ffmpeg -i ");
					command.append(flvPath);
					command.append(" -acoder mp3 -vcodec copy -ab 128k -y ");
					command.append(targetPath);
					Process process = runtime.exec(command.toString());
					process.waitFor();
				} catch (Throwable e) {
					e.printStackTrace();
				}

				if (new File(targetPath).exists()) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	public static boolean fileMerge(String path, List<String> filePaths, String filename, String ffmpegPath) {
		if (StringUtils.isNotEmpty(path) && CollectionUtils.isNotEmpty(filePaths)) {
			createFilePath(path);
			String tp = path + filename;
			Runtime runtime = Runtime.getRuntime();
			Process process = null;
			try {
				for (String filepath : filePaths) {
					if (StringUtils.isNotEmpty(filepath)) {

						StringBuilder command = new StringBuilder();
						command.append(ffmpegPath);
						command.append("ffmpeg -i ");
						command.append(filepath);
						command.append(" -f mpeg -y -r 29.97 -ab 128k ");
						command.append(filepath);
						command.append(".actemp");
						process = runtime.exec(command.toString());
						process.waitFor();
					}
				}

				String targetPath = tp + ".actemp";
				List<File> files = new ArrayList<File>();
				for (String filepath : filePaths) {
					if (StringUtils.isNotEmpty(filepath)) {
						String fp = filepath + ".actemp";
						File file = new File(fp);
						if (file.exists()) {
							files.add(file);
						} else {
							break;
						}
					}
				}

				if (files.size() == filePaths.size()) {
					FileUtils.mergeFile(targetPath, files);
					File file = new File(targetPath);
					if (file.exists()) {
						StringBuilder cmd = new StringBuilder();
						cmd.append(ffmpegPath);
						cmd.append("ffmpeg -i ");
						cmd.append(file.getPath());
						cmd.append(" -f mp3 -y -ab 128k ");
						cmd.append(tp);
						process = runtime.exec(cmd.toString());
						process.waitFor();
					}

					File merge = new File(tp);
					if (merge.exists()) {
						return Boolean.TRUE;
					}
				}

			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				for (String filepath : filePaths) {
					if (StringUtils.isNotEmpty(filepath)) {
						File file = new File(filepath + ".actemp");
						if (file.exists()) {
							file.delete();
						}
					}
				}

				File file = new File(tp + ".actemp");
				if (file.exists()) {
					file.delete();
				}
			}

		}
		return Boolean.FALSE;
	}

	public static synchronized void createFilePath(String path) {
		if (StringUtils.isNotEmpty(path)) {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	/**
	 * 获取mp3文件播放时长
	 *
	 * @param file
	 *            待处理文件
	 * @return
	 */
	public static int getMp3RunTime(File file) {
		if (file.exists()) {
			try {
				AudioFile audioFile = (AudioFile) AudioFileIO.read(file);
				if (audioFile != null) {
					AudioHeader header = audioFile.getAudioHeader();
					if (header != null) {
						return header.getTrackLength();
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static String readFile(File file) {

		StringBuilder builder = new StringBuilder();

		try {

			InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (Throwable e) {
		}

		return filter(builder.toString());

	}

	public static boolean uploadFile(InputStream is, String filePathName) {
		if (StringUtils.isNotEmpty(filePathName)) {
			FileOutputStream fos = null;
			File file = null;
			try {
				file = new File(filePathName);
				fos = new FileOutputStream(file);
				IOUtils.copy(is, fos);
				return Boolean.TRUE;
			} catch (Throwable e) {
				log.error("upload file to duotin has a error !" , e);
			} finally {
				IOUtils.closeQuietly(is);
				IOUtils.closeQuietly(fos);
			}
		}
		return Boolean.FALSE;
	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	public static String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}

	public static void main(String[] args) {
		System.out.println(getMp3RunTime(new File("/Users/jared/Documents/project/3.mp3")));
	}

	/**
	 * 获取项目下资源文件路径
	 *
	 * @param fileName
	 * @return
	 */
	public static String getRsourceFilePath(String fileName) {
		try {
			if (StringUtils.isNotEmpty(fileName)) {
				Resource resource = new ClassPathResource(fileName);
				if (resource.exists()) {
					return resource.getFile().getPath();
				}
			}
		} catch (Throwable e) {
			log.error("resource load has an error: " , e);
		}
		return ConstStrings.EMPTY;
	}

	/**
	 * 上传mp3文件到多听服务器
	 *
	 * @param filePath
	 * @param uploadUrl
	 * @return
	 */
	public static int uploadMp3ToDuotin(final String filePath, String uploadUrl) {
		if (StringUtils.isNotEmpty(filePath)) {
			return uploadMp3ToDuotin(new PartSource(){
				private File file = new File(filePath);
				@Override
				public long getLength() {
					return file.length();
				}
				@Override
				public String getFileName() {
					return file.getName();
				}
				@Override
				public InputStream createInputStream() throws IOException {
					return new FileInputStream(file);
				}

			},uploadUrl);
		}
		return 0;
	}

	public static int uploadMp3ToDuotin(final String filePath,String proxyHost, int proxyPort, String uploadUrl) {
		if (StringUtils.isNotEmpty(filePath)) {
			return uploadMp3ToDuotin(new PartSource(){
				private File file = new File(filePath);
				@Override
				public long getLength() {
					return file.length();
				}
				@Override
				public String getFileName() {
					return file.getName();
				}
				@Override
				public InputStream createInputStream() throws IOException {
					return new FileInputStream(file);
				}

			},proxyHost,proxyPort,uploadUrl);
		}
		return 0;
	}

	public static int uploadMp3ToDuotin(PartSource mp3File, String uploadUrl) {
		if ( mp3File != null ) {
			try {
				FilePart fp = new FilePart("uf", mp3File);
				String json = HttpUtils.fileUpload(uploadUrl, new Part[]{fp});
				if (StringUtils.isNotEmpty(json)) {
					json = json.replaceAll("\r", "").replaceAll("\n", "");
					log.error("mp3 upload api return json:" + json);
					Map<String, Object> map = JsonUtils.toMap(json);
					if (CollectionUtils.isNotEmpty(map)) {
						String id = (String) map.get("id");
						if (StringUtils.isNotEmpty(id)) {
							return Integer.valueOf(id);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static int uploadMp3ToDuotin(PartSource mp3File,String proxyHost, int proxyPort, String uploadUrl) {
		if ( mp3File != null ) {
			try {
				FilePart fp = new FilePart("uf", mp3File);
				String json = HttpUtils.fileUpload(uploadUrl, proxyHost,  proxyPort, new Part[]{fp});
				if (StringUtils.isNotEmpty(json)) {
					json = json.replaceAll("\r", "").replaceAll("\n", "");
					log.error("mp3 upload api return json:" + json);
					Map<String, Object> map = JsonUtils.toMap(json);
					if (CollectionUtils.isNotEmpty(map)) {
						String id = (String) map.get("id");
						if (StringUtils.isNotEmpty(id)) {
							return Integer.valueOf(id);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static DuotinMp3Bean getMp3StatusOfDuotin(int id, String getUrl) {
		DuotinMp3Bean bean = new DuotinMp3Bean();
		bean.setStatus(DuotinMp3Bean.STATUS_FAILURE);
		if (id > 0) {
			String json = HttpUtils.getHtmlGet(getUrl + id);
			if (StringUtils.isNotEmpty(json)) {
				log.info("duotin mp3 upload return status:" + json);
				json = json.replaceAll("\r", "").replaceAll("\n", "");
				Map<String, Object> map = JsonUtils.toMap(json);
				if (CollectionUtils.isNotEmpty(map)) {
					if ("FINISHED".equals((String) map.get("status"))) {
						bean.setStatus(DuotinMp3Bean.STATUS_SUCCESS);
						bean.setFileUrl16((String) map.get("f16_url"));
						bean.setFileSize16(NumberUtils.valueOf((String) map.get("f16_size")));
						bean.setFileUrl32((String) map.get("f32_url"));
						bean.setFileSize32(NumberUtils.valueOf((String) map.get("f32_size")));
						bean.setFileUrl64((String) map.get("f64_url"));
						bean.setFileSize64(NumberUtils.valueOf((String) map.get("f64_size")));
						bean.setPlaytime(TimeUtils.secToTime(TimeUtils.timeStrToSec((String) map.get("playtime_string"))));
					} else if ("FAIL".equals((String) map.get("status"))) {
						bean.setStatus(DuotinMp3Bean.STATUS_FAILURE);
					} else if ("CONVERTING".equals((String)map.get("status"))) {
						bean.setStatus(DuotinMp3Bean.STATUS_PROCESSING);
					} else{
                        bean.setStatus(DuotinMp3Bean.STATUS_WAITING);
                    }
				}

			}
		}
		return bean;
	}
     //删除文件
    public static boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public static boolean rdFile(String filePath){
        File file = new File(filePath);
        if(!file.isDirectory()){
          log.info("file name:",file.getName());
        }else if(file.isDirectory()){
            String[] list = file.list();
            for(int i =0;i<list.length;i++){
                File files = new File(filePath+"\\"+list[i]);
                if(!files.isDirectory()){
                    log.info("file name:",file.getName());
                }else if(files.isDirectory()){
                    rdFile(filePath+"\\"+list[i]);
                }
            }
        }
        return true;
    }

    public static List<File> listFiles(File source, String filter){
        File[] fileList = source.listFiles();
        List<File> list = new ArrayList<File>();
        filter = filter == null ? null : filter.toLowerCase();
        if(fileList != null && fileList.length > 0){
            for(File file : fileList){
                if(file.isFile()){
                    add(list, file, filter);
                }else if(file.isDirectory()){
                    list.addAll(listFiles(file, filter));
                }
            }
        }else if(source.isFile()){
            add(list, source, filter);
        }
        return list;
    }
    private static void add(List<File> list, File file, String filter){
        if(filter == null){
            list.add(file);
        }else if(file.getAbsolutePath().toLowerCase().endsWith(filter)){
            list.add(file);
        }
    }

    /**
     * 上传song文件到多听服务器
     *
     * @param filePath
     * @param uploadUrl
     * @return
     */
    public static String uploadSongMp3ToDuotin(final String filePath, String uploadUrl) {
        if (StringUtils.isNotEmpty(filePath)) {
            return uploadSongMap3ToDuotin(new PartSource(){
                private File file = new File(filePath);
                @Override
                public long getLength() {
                    return file.length();
                }
                @Override
                public String getFileName() {
                    return file.getName();
                }
                @Override
                public InputStream createInputStream() throws IOException {
                    return new FileInputStream(file);
                }

            },uploadUrl);
        }
        return "error";
    }

    public static String uploadSongMap3ToDuotin(PartSource mp3File, String uploadUrl) {
        if ( mp3File != null ) {
            try {
                FilePart fp = new FilePart("uf", mp3File);
                String json = HttpUtils.fileUpload(uploadUrl, new Part[]{fp});
                if (StringUtils.isNotEmpty(json)) {
                    json = json.replaceAll("\r", "").replaceAll("\n", "");
                    log.error("song upload api return json:" + json);
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "error";
    }
    private static  void copyFile()
    {

    }
}
