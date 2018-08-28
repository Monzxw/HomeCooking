package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUrlConnectionNetworkTask extends NetworkTask{

    public HttpUrlConnectionNetworkTask(String method) {
        super(method);
    }

    @Override
    public String doGet(String httpUrl) {
        String result;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestProperty("Charset", "UTF-8");
            if (urlConnection.getResponseCode() == 200) {
                InputStream is = urlConnection.getInputStream();
                result = readFromStream(is);
            } else {
                isSuccess = false;
                result = "Network Access Error: " + urlConnection.getResponseCode();
            }
        } catch(IOException e) {
            isSuccess = false;
            result = "Network Access Error: " + e.getMessage();
        }

        return result;
    }

    @Override
    public String doPost(String httpUrl, Map<String, String> paramMap) {
        String result;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 配置连接的Content-type
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setDoOutput(true);    // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true);     // 发送POST请求必须设置允许输入

            String data = "";
            boolean firstParam = true;
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                if(firstParam) {
                    data += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");
                    firstParam = false;
                } else {
                    data += "&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");
                }
            }
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            if (urlConnection.getResponseCode() == 200) {
                InputStream is = urlConnection.getInputStream();
                result = readFromStream(is);
            } else {
                isSuccess = false;
                result = "Network Access Error: " + urlConnection.getResponseCode();
            }
        } catch (IOException e) {
            isSuccess = false;
            result = "Network Access Error: " + e.getMessage();
        }

        return result;
    }

    /**
     * 输入流获取字符串
     *
     * @param is 输入流
     * @return String 返回的字符串
     * @throws IOException
     */
    public String readFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        is.close();
        String result = baos.toString();
        baos.close();
        return result;
    }
}
