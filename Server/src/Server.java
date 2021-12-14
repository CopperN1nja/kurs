import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class Server extends Thread {
    private DatabaseHandler myDataBase;
    private Socket clientSocket;
    private ObjectInputStream sois;
    private ObjectOutputStream soos;
    private String[] tmp;
    private String action;



    public Server(Socket socket) throws IOException {
        clientSocket = socket;
        sois = new ObjectInputStream(clientSocket.getInputStream());
        soos = new ObjectOutputStream(clientSocket.getOutputStream());

        start();
    }

    public void run() {
        myDataBase = new DatabaseHandler();
        try {
            System.out.println("Соединение установлено..");

            while (true)    {
                action = (String) sois.readObject();
                tmp = action.split(" ");

                switch (tmp[0]) {
                    case "registration" :
                        myDataBase.registration(tmp,soos);
                        break;
                    case "authorization" :
                        myDataBase.authorization(tmp,soos);
                        break;
                    case "createBusinessman" :
                        myDataBase.createBusinessman(tmp,soos);
                        break;
                    case "showAllAcc":
                        myDataBase.showAllAcc(tmp,soos);
                        break;
                    case "editAcc":
                        myDataBase.editAcc(tmp,soos);
                        break;
                    case "deleteAc":
                        myDataBase.deleteAc(tmp,soos);
                        break;
                    case "srchAc":
                        myDataBase.srchAc(tmp,soos);
                        break;
                    case "showAllBusinessman":
                        myDataBase.showAllBusinessman(tmp,soos);
                        break;
                    case "showAllTax":
                        myDataBase.showAllTax(tmp,soos);
                        break;
                    case "showAllBusinessBranch":
                        myDataBase.showAllBusinessBranch(tmp,soos);
                        break;
                    case "showAllTaxBranch" :
                        myDataBase.showAllTaxBranch(tmp,soos);
                        break;
                    case "addTax" :
                        myDataBase.addTax(tmp,soos);
                        break;
                    case "deleteTax" :
                        myDataBase.deleteTax(tmp,soos);
                        break;
                    case "addBuisBranch":
                        myDataBase.addBuisBranch(tmp,soos);
                        break;
                    case "deleteBuisBranch":
                        myDataBase.deleteBuisBranch(tmp,soos);
                        break;
                    case "addTaxBranch":
                        myDataBase.addTaxBranch(tmp,soos);
                        break;
                    case "deleteTaxBranch":
                        myDataBase.deleteTaxBranch(tmp,soos);
                        break;
                    case "editTax":
                        myDataBase.editTax(tmp,soos);
                        break;
                    case "showNewBusinessman":
                        myDataBase.showNewBusinessman(tmp,soos);
                        break;
                    case "showBusinessmanForRecalculation":
                        myDataBase.showBusinessmanForRecalculation(tmp,soos);
                        break;
                    case "deleteBusinessman":
                        myDataBase.deleteBusinessman(tmp,soos);
                        break;
                    case "calculateBusinesTax":
                        myDataBase.calculateBusinesTax(tmp,soos);
                        break;
                    case "showBusinessmanCalculated":
                        myDataBase.showBusinessmanCalculated(tmp,soos);
                        break;
                    case "sendRecalculate":
                        myDataBase.sendRecalculate(tmp,soos);
                        break;
                    case "recalculateTax":
                        myDataBase.recalculateTax(tmp,soos);
                        break;
                    case "allProfit" :
                        myDataBase.allProfit(tmp,soos);
                        break;
                    case "allIncome" :
                        myDataBase.allIncome(tmp,soos);
                        break;
                    case "taxAtBranch":
                        myDataBase.taxAtBranch(tmp,soos);
                        break;
                    case "sumAllTax":
                        myDataBase.sumAllTax(tmp,soos);
                        break;
                    case "sumNewTax":
                        myDataBase.sumNewTax(tmp,soos);
                        break;
                    case "showBranchOfTax":
                        myDataBase.showBranchOfTax(tmp,soos);
                        break;
                    case "showFirmProfHigther":
                        myDataBase.showFirmProfHigther(tmp,soos);
                        break;
                    case "showFirmProfLower":
                        myDataBase.showFirmProfLower(tmp,soos);
                        break;
                    case "showAllTaxOfFitrm":
                        myDataBase.showAllTaxOfFitrm(tmp,soos);
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("Клиент отключился.");
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                System.err.println("Сокет не закрыт!!!");
            }
        }

    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(2525);
        System.out.println("Сервер запущен...");

        try {
            while (true) {
                Socket newSocket = serverSocket.accept();
                System.out.println("Новый клиент подключился");

                try {
                    new Server(newSocket);
                } catch (Exception e) {
                    newSocket.close();
                }
            }

        } finally {
            serverSocket.close();
        }
    }
}



