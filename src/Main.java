import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstCardDrawn = scanner.nextInt();
        int numberOfDifferentCardValues = scanner.nextInt();

        ArrayList<Integer> valuesAvailable = new ArrayList<>();
        for (int i = 0; i < numberOfDifferentCardValues; i++) {
            valuesAvailable.add(scanner.nextInt());
        }
        int target = firstCardDrawn* 21;

        System.out.println("target: " + target);
        System.out.println("valuesAvailable: " + valuesAvailable);

        //Dynamic programming coin change problem
        int[] dp = new int[target+1];
        //Base case is if we need to hit target 0, we can do so with 0 cards.
        dp[0] = 0;
        for (int currentTarget = 1; currentTarget <= target; currentTarget++) {
            //Initialize every current best solution to Max value, as that is clearly worse than any other solution.
            dp[currentTarget] = Integer.MAX_VALUE;
            for (int currentCardValue : valuesAvailable) {
                //If we don't overdraw.
                if (currentTarget-currentCardValue >= 0) {
                    //The best solution is the one with fewest amount of cards drawn.
                    //We are doing bottom-up, checking every possible solution from 1 all the way to our target.
                    //If our previous solution, plus drawing an extra card, is better than our current best solution, we update it.
                    dp[currentTarget] = min(dp[currentTarget], dp[currentTarget-currentCardValue]+1);
                }
            }
        }

        System.out.println("Minimum cards:" + dp[target]);
        //Print the different cards used
        System.out.println("Cards used:");
        int x = target;
        while (x > 0) {
            for (var c : valuesAvailable) {
                if (x-c >= 0 && dp[x] == dp[x-c]+1) {
                    System.out.println(c);
                    x -= c;
                    break;
                }
            }
        }

    }
}