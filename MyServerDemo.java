import java.io.*;
import java.net.*;

public class MyServerDemo {

    //trong server sẽ bắt đầu bằng psvm
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket =  null;
        String line;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        try {
            serverSocket = new ServerSocket(8080);
        }
        //bắt lỗi và in nó ra
        catch (IOException e) {
            e.printStackTrace();
        }

        try{
            System.out.println("Server is started at port 8080");
            socket = serverSocket.accept();
            System.out.println("Connected with a client");

            //nhận input và output từ server
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //Nhận dữ liệu từ người dùng và gửi lại
            while(true){
                //đọc input
                line = bufferedReader.readLine();

                //đẩy output bằng 3 dòng này
                bufferedWriter.write(line);
                //kết thúc dòng
                bufferedWriter.newLine();
                //đẩy output ra
                bufferedWriter.flush();

                //khi có quit từ client thì đóng server
                if(line.equalsIgnoreCase("quit")){
                    bufferedWriter.write(">> OK");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is closed");
    }
}
