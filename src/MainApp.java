import java.util.*;
public class MainApp {
        static ArrayList<Integer> playerPositions=new ArrayList<>();
        static ArrayList<Integer> cpuPositions=new ArrayList<>();

        public static void main(String[] args) {
            char[][] gameBoard={{' ','|',' ','|',' '},
                    {'-','+','-','+','-'},
                    {' ','|',' ','|',' '},
                    {'-','+','-','+','-'},
                    {' ','|',' ','|',' '}
            };
            Scanner scanner=new Scanner(System.in);

            String result;

            while (true){
                System.out.println("ENTER YOUR POSITION (1-9)");
                int playerPos= scanner.nextInt();

                while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                    System.out.println("POSITION TAKEN PLEASE ENTER CORRECT POSITION");
                    playerPos= scanner.nextInt();
                }
                placePieces(gameBoard,playerPos,"PLAYER");
                result=checkWinner();

                Random rand=new Random();
                int cpuPos= rand.nextInt(9)+1;

                while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                    cpuPos= rand.nextInt(9)+1;
                }

                placePieces(gameBoard,cpuPos,"CPU");
                printBoard(gameBoard);

                result=checkWinner();
                if(result.length()>0){
                    System.out.println(result);
                    break;
                }
            }
        }
        private static String checkWinner(){
            List topRow=Arrays.asList(1,2,3);
            List midRow=Arrays.asList(4,5,6);
            List botRow=Arrays.asList(7,8,9);

            List leftCol=Arrays.asList(1,4,7);
            List midCol=Arrays.asList(2,5,8);
            List rightCol=Arrays.asList(3,6,9);

            List cross1=Arrays.asList(1,5,9);
            List cross2=Arrays.asList(7,5,3);

            List<List> winning=new ArrayList<>();
            winning.add(topRow);
            winning.add(midRow);
            winning.add(botRow);
            winning.add(leftCol);
            winning.add(midCol);
            winning.add(rightCol);
            winning.add(cross1);
            winning.add(cross2);

            for(List l:winning){
                if(playerPositions.containsAll(l)){
                    return "CONGRATULATIONS YOU WON";
                }else if(cpuPositions.containsAll(l)){
                    return "CPU WIN: SORRY:(";
                }else if(playerPositions.size()+cpuPositions.size()==9){
                    return "DRAW !";
                }
            }
            return "";
        }
        private static void placePieces(char[][] gameBoard,int pos,String user){
            char symbol=' ';
            if(user.equals("PLAYER")){
                symbol='X';
                playerPositions.add(pos);
            }else if(user.equals("CPU")){
                symbol='0';
                cpuPositions.add(pos);
            }
            switch (pos){
                case 1:
                    gameBoard[0][0]=symbol;
                    break;
                case 2:
                    gameBoard[0][2]=symbol;
                    break;
                case 3:
                    gameBoard[0][4]=symbol;
                    break;
                case 4:
                    gameBoard[2][0]=symbol;
                    break;
                case 5:
                    gameBoard[2][2]=symbol;
                    break;
                case 6:
                    gameBoard[2][4]=symbol;
                    break;
                case 7:
                    gameBoard[4][0]=symbol;
                    break;
                case 8:
                    gameBoard[4][2]=symbol;
                    break;
                case 9:
                    gameBoard[4][4]=symbol;
                    break;
                default:
                    break;
            }
        }
        private static void printBoard(char[][] gameBoard){
            for(char[] row:gameBoard){
                for(char c:row){
                    System.out.print(c);
                }
                System.out.println();
            }
        }

}
