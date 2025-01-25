// Given a grid of m boys and n girls, where grid[i][j] = 1 represents that ith boy can invite girl jth girl to party. Each boy can invite only one girl and each girl can accept only one invitation. Find the most invitations that can be accepted.

// Example:

// Input: grid = {{1,1,1},
//                {1,0,1},
//                {0,0,1}}
// Output: 3
// Explanation: The invitations can be sent as follows:
// => 1st boy invites the 2nd girl.
// => 2nd boy invites the 1st girl.
// => 3rd boy invites the 3rd girl.



// Input: grid = {{1,0,1,0},
//                {1,0,0,0},
//                {0,0,1,0},
//                {1,1,1,0}}
// Output: 3
// Explanation: The invitations can be sent as follows:
// => 1st boy invites the 3rd girl.
// => 2nd boy invites the 1st girl.
// => 3rd boy invites no one.
// => 4th boy invites the 2nd girl.

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Define the invitation matrix
        int[][] invitationMatrix = {{1, 1, 1}, {1, 0, 1}, {0, 0, 1}};

        // Call the maximumInvitations function and store the result
        int totalInvitations = maximumInvitations(invitationMatrix);

        // Print the result
        System.out.println("The maximum possible number of accepted invitations is: " + totalInvitations);
    }
    
    private static int maximumInvitations(int[][] invitationMatrix) {
        int totalBoys = invitationMatrix.length;
        int totalGirls = invitationMatrix[0].length;
        int totalInvitations = 0;
        
        int[] girlInvitedBy = new int[totalGirls];
        
        for (int boy = 0; boy < totalBoys; boy++) {
            if (canInviteGirl(boy, totalBoys, new boolean[totalBoys], girlInvitedBy, invitationMatrix)) {
                totalInvitations += 1;
            }
        }
        
        return totalInvitations;
    }
    
    private static boolean canInviteGirl(int currentBoy, int totalGirls, boolean[] boyVisited, int[] girlInvitedBy, int[][] invitationMatrix) {
        if (boyVisited[currentBoy]) return false;
        
        boyVisited[currentBoy] = true;
        
        for (int girl = 0; girl < totalGirls; girl++) {
            if (invitationMatrix[currentBoy][girl] == 1) {
                if (girlInvitedBy[girl] == 0 || canInviteGirl(girlInvitedBy[girl], totalGirls, boyVisited, girlInvitedBy, invitationMatrix)) {
                    girlInvitedBy[girl] = currentBoy;
                    return true;
                }
            }
        }
        
        return false;
    }
}