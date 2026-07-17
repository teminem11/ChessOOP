import java.util.Scanner;
public class Main {
 public static void main(String[] args){
  Board board=new Board(); System.out.println("Chess OOP — enter moves like e2 e4, or quit.");
  try(Scanner scanner=new Scanner(System.in)){while(true){
   board.printBoard(); String turn=board.getCurrentTurn();
   if(board.isCheckmate(turn)){System.out.println("Checkmate! "+opposite(turn)+" wins.");break;}
   if(board.isStalemate(turn)){System.out.println("Stalemate. Draw.");break;}
   System.out.print(turn+"> "); if(!scanner.hasNextLine())break; String line=scanner.nextLine().trim(); if(line.equalsIgnoreCase("quit"))break;
   String[] move=line.split("\\s+"); if(move.length!=2||!move[0].matches("[a-h][1-8]")||!move[1].matches("[a-h][1-8]")){System.out.println("Example: e2 e4");continue;}
   board.movePiece(move[0].charAt(0)-'a',move[0].charAt(1)-'1',move[1].charAt(0)-'a',move[1].charAt(1)-'1');
  }}
 }
 private static String opposite(String color){return color.equals("white")?"black":"white";}
}
