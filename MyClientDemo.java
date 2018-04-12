import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClientDemo {

    public static void main(String[] args) throws IOException {

        //địa chỉ máy chủ
        final String serverHost = "localhost";

        Socket clientSocket = null;
        BufferedWriter bufferedWriter =  null;
        BufferedReader bufferedReader = null;
        Scanner keyboardScanner = new Scanner(System.in);
        boolean serverIsClosed = false;

        try {
            //gửi yêu cầu tới localhost ở port 8080
            clientSocket = new Socket(serverHost, 8080);
            //tạo output ở client để gửi tới server
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            //tạo input ở client để nhận từ server
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{

            //đoạn này em muốn đọc người dùng nhập vào cái gì rồi in ra
            // nhưng chưa biết làm thế nào để nó lặp đúng cách

//            while(!serverIsClosed) {
//                System.out.println("Enter your input to the server");
//                String clientInput = keyboardScanner.next();
//                bufferedWriter.write(clientInput);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
            bufferedWriter.write("This is a message");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write("quit");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.write("Dòng này không được viết vì đã quit trước đó");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            String responseLine;
            while((responseLine = bufferedReader.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if(responseLine.indexOf("OK") != -1){
                    serverIsClosed = true;
                    break;
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
