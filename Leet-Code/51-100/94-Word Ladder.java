// A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

// Every adjacent pair of words differs by a single letter.
// Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
// sk == endWord
// Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

// Example 1:

// Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
// Output: 5
// Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
// Example 2:

// Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
// Output: 0
// Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

// Constraints:

// 1 <= beginWord.length <= 10
// endWord.length == beginWord.length
// 1 <= wordList.length <= 5000
// wordList[i].length == beginWord.length
// beginWord, endWord, and wordList[i] consist of lowercase English letters.
// beginWord != endWord
// All the words in wordList are unique.

class Solution {
    private String findPattern(String word, int index, int length) {
        return word.substring(0,index) + "*"+word.substring(index+1, length);
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        Map<String, List<String>> adjList = new HashMap<>();
        
        wordList.add(beginWord);
        //Word Length
        int l = beginWord.length();
        
        wordList.forEach(word -> {
            for (int i = 0; i<l; i++) {
                adjList.computeIfAbsent(findPattern(word, i, l), k -> new ArrayList<>()).add(word);
            }
        });
        
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.offer(beginWord);
        visited.add(beginWord);
        
        int level = 1;
        
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i<qSize; i++) {
                String wordFromQ = queue.poll();

                if (wordFromQ.equals(endWord)) {
                    return level;
                }
                for (int j = 0; j<l; j++) {
                    String pattern = findPattern(wordFromQ, j, l);
                    adjList.get(pattern).forEach (adjWord -> {
                        if(!visited.contains(adjWord)) {
                            queue.offer(adjWord);
                            visited.add(adjWord);
                        }
                    });
                }
            }
            level++;
        }
        return 0;
    }
        
}