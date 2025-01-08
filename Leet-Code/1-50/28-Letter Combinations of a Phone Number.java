// # Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

// # A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



 

// # Example 1:

// # Input: digits = "23"
// # Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// # Example 2:

// # Input: digits = ""
// # Output: []
// # Example 3:

// # Input: digits = "2"
// # Output: ["a","b","c"]
 

// # Constraints:

// # 0 <= digits.length <= 4
// # digits[i] is a digit in the range ['2', '9'].

// # Solved using backtracking
class Solution {
    Map<Character, Character[]> map;
    List<String> result;
    private void backtrack(String digits, StringBuilder word, int index) {
        if (word.length() == digits.length()) {
            result.add(word.toString());
            return;
        }
        for(char c: map.get(digits.charAt(index))) {
            word.append(c);
            backtrack(digits, word, index + 1);
            word.setLength(word.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        map = new HashMap<>();
        result = new ArrayList<>();

        map.put('2', new Character[]{'a', 'b', 'c'});
        map.put('3', new Character[]{'d', 'e', 'f'});
        map.put('4', new Character[]{'g', 'h', 'i'});
        map.put('5', new Character[]{'j', 'k', 'l'});
        map.put('6', new Character[]{'m', 'n', 'o'});
        map.put('7', new Character[]{'p', 'q', 'r', 's'});
        map.put('8', new Character[]{'t', 'u', 'v'});
        map.put('9', new Character[]{'w', 'x', 'y', 'z'});

        if (digits.length() != 0) backtrack(digits, new StringBuilder(), 0);
        return result;
    }
}