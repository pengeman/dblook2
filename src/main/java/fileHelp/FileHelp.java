package fileHelp;

import java.io.*;

public class FileHelp {
    org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
    static final int TEXTFileModel = 1;  // 文本文件模式
    static final int BINFileModel = 2;   // 二进制文件模式

    /**
     * 打开文件,以指定的模式,有两种模式,文本文件和二进制文件
     *
     * @param filename
     * @param filemodel
     * @return 如果打开成功, 返回文件对象, 如果失败, 返回null.
     */
    public File open(String filename, int filemodel) {
        return null;
    }

    /**
     * @param file
     * @return
     */
    public StringBuffer read(File file) {
        StringBuffer stb = new StringBuffer();
        String st = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                stb.append(st + "\n");
            }
        } catch (FileNotFoundException e1) {
            LOG.error(e1.getMessage());
        } catch (IOException e2) {
            LOG.error(e2.getMessage());
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
        return stb;
    }

    public void write(File file, StringBuffer stringBuffer) {
        BufferedWriter bw = null;
        try {
             bw = new BufferedWriter(new FileWriter(file));
            bw.write(stringBuffer.toString());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
