package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUrlConnectionNetworkTaskReturnBytes extends NetworkTaskReturnBytes{

    public HttpUrlConnectionNetworkTaskReturnBytes(String method) {
        super(method);
    }

    @Override
    public byte[] doGet(String httpUrl) {
        byte[] result;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestProperty("Charset", "UTF-8");
            if (urlConnection.getResponseCode() == 200) {
                InputStream is = urlConnection.getInputStream();
                result = readBytesFromStream(is);
            } else {
                isSuccess = false;
                result = ("Network Access Error: " + urlConnection.getResponseCode()).getBytes();
            }
        } catch(IOException e) {
            isSuccess = false;
            result = ("Network Access Error: " + e.getMessage()).getBytes();
        }

        return result;
    }

    @Override
    public byte[] doPost(String httpUrl, Map<String, String> paramMap) {
        byte[] result;
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
                result = readBytesFromStream(is);
            } else {
                isSuccess = false;
                result = ("Network Access Error: " + urlConnection.getResponseCode()).getBytes();
            }
        } catch (IOException e) {
            isSuccess = false;
            result = ("Network Access Error: " + e.getMessage()).getBytes();
        }

        return result;
    }

    public byte[] readBytesFromStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = is.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
