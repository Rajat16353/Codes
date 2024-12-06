// A transaction is possibly invalid if:

// the amount exceeds $1000, or;
// if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
// You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.

// Return a list of transactions that are possibly invalid. You may return the answer in any order.

 

// Example 1:

// Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
// Output: ["alice,20,800,mtv","alice,50,100,beijing"]
// Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
// Example 2:

// Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
// Output: ["alice,50,1200,mtv"]
// Example 3:

// Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
// Output: ["bob,50,1200,mtv"]
 

// Constraints:

// transactions.length <= 1000
// Each transactions[i] takes the form "{name},{time},{amount},{city}"
// Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
// Each {time} consist of digits, and represent an integer between 0 and 1000.
// Each {amount} consist of digits, and represent an integer between 0 and 2000.

class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        Transaction[] transaction = parseTransactions(transactions);

        Arrays.sort(transaction, new TransactionComparator());
        
        for(int i = 0; i < transaction.length; i++) {
            if (transaction[i].valid && transaction[i].amount > 1000) {
                result.add(transaction[i].toString());
                transaction[i].valid = false;
            } 

            for (int j = i + 1; j < transaction.length; j++) {
                if (!transaction[i].name.equals(transaction[j].name)) {
                    break;
                } else {
                    if (transaction[j].time - transaction[i].time <= 60 && !transaction[j].city.equals(transaction[i].city)) {
                        if (transaction[i].valid) {
                            result.add(transaction[i].toString());
                            transaction[i].valid = false;
                        }
                        if (transaction[j].valid) {
                            result.add(transaction[j].toString());
                            transaction[j].valid = false;
                        }
                    }
                }
            }
        }

        return result;
    }

    private Transaction[] parseTransactions(String[] transactions) {
        Transaction[] transaction = new Transaction[transactions.length];
        int i = 0;

        for(String s: transactions) {
            String[] transactionArr = s.split(",");
            transaction[i++] = new Transaction(transactionArr[0], Integer.parseInt(transactionArr[1]), Integer.parseInt(transactionArr[2]), transactionArr[3]);
        }

        return transaction;
    }

    class Transaction {
        public String name;
        public int time;
        public int amount;
        public String city;
        public boolean valid;

        public Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
            this.valid = true;
        }

        @Override
        public String toString() {
            return String.format("%s,%d,%d,%s", this.name, this.time, this.amount, this.city);
        }
    }

    class TransactionComparator implements Comparator<Transaction> {

        @Override
        public int compare(Transaction a, Transaction b) {
            if (a.name.equals(b.name)) {
                return a.time - b.time;
            } else {
                return a.name.compareTo(b.name);
            }
        }
    }
}