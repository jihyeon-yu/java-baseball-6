package baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {
	static Scanner sc;
	static int strike, ball, notMatch;
    public static void main(String[] args) {
    	System.out.println("숫자 야구 게임을 시작합니다.");
    	List<Integer> computer = createComputerNum();
    	try {
    		// 첫 게임 실행
    		doGame(computer);
    		// 첫 게임이 끝나고 옵션 출력
    		L : while(true) {
    			System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    			int choice = sc.nextInt();
    			switch(choice) {
    				case 1:
    					// 2(종료)를 입력할 때까지 무한 실행
    					computer = createComputerNum();
        				doGame(computer);
        				break;
    				case 2:
    					System.out.println("숫자 야구 게임을 종료합니다.");
        				break L;
        			default:
        				// 1, 2 이외의 값을 입력할 시 예외 처리
        				throw new IllegalArgumentException();
    			}
    		}
    	} catch(IllegalArgumentException e) {
    		System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
    	}
    }
    
    private static void doGame(List<Integer> computer) {
    	while(true) {
    		strike = 0;
        	ball = 0;
        	notMatch = 0;
    		game(computer);
    		if(strike == 3) {
    			System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    			break;
    		}
    	}
    }
    
    private static void game(List<Integer> computer) {
    	sc = new Scanner(System.in);
    	System.out.print("숫자를 입력해주세요 : ");
    	String num = sc.next();
    	// 입력받은 String의 길이가 3인지 확인
    	if(num.length() != 3) throw new IllegalArgumentException();
    	// 입력받은 String이 숫자로만 구성되어 있는지 확인
    	if(!isNumeric(num)) throw new IllegalArgumentException();
    	// 인덱스 번호와 값 일치 여부를 확인하기 위해 사용자의 입력을 Array로 생성
    	int[] userPick = new int[num.length()];
    	for (int i = 0; i < num.length(); i++) {
			userPick[i] = num.charAt(i) - '0';
		}
    	
    	// 사용자의 선택 값과 컴퓨터의 선택 값의 일치 여부와 위치 확인
    	for (int i = 0; i < userPick.length; i++) {
			if(computer.contains(userPick[i]) && i == computer.indexOf(userPick[i])) strike++;
			else if(computer.contains(userPick[i]) && i != computer.indexOf(userPick[i])) ball++;
			else notMatch++;
		}
    	
    	if(notMatch == 3) System.out.println("낫싱");
    	else if(ball > 0 && strike == 0) System.out.println(ball + "볼");
    	else if(ball > 0 && strike > 0) System.out.println(ball + "볼 " + strike + "스트라이크");
    	else if(ball == 0 && strike > 0) System.out.println(strike + "스트라이크");
    }
    
    private static List<Integer> createComputerNum() {
    	Random random = new Random();
    	List<Integer> computer = new ArrayList<>();
    	while (computer.size() < 3) {
    	    int randomNumber = random.nextInt(9)+1;
    	    if (!computer.contains(randomNumber)) computer.add(randomNumber);
    	}
    	return computer;
    }
    
    private static boolean isNumeric(String str) {
    	if(str == null) return false;
    	try {
    		double d = Double.parseDouble(str);
    	} catch(NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
}
