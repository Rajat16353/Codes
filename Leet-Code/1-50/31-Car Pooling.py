# There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

# You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

# Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

 

# Example 1:

# Input: trips = [[2,1,5],[3,3,7]], capacity = 4
# Output: false
# Example 2:

# Input: trips = [[2,1,5],[3,3,7]], capacity = 5
# Output: true
 

# Constraints:

# 1 <= trips.length <= 1000
# trips[i].length == 3
# 1 <= numPassengersi <= 100
# 0 <= fromi < toi <= 1000
# 1 <= capacity <= 105

#First approach
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        cap_sum = 0
        trips.sort(key=lambda x: x[1])
        d = {}
        s = set()
        for i in range(len(trips)):
            cap_sum += trips[i][0]
            s.add(trips[i][2])
            if trips[i][2] not in d.keys():
                d[trips[i][2]] = trips[i][0]
            else:
                d[trips[i][2]] += trips[i][0]
            temp = trips[i][1]
            while(temp >= 0):
                if temp in s:
                    cap_sum -= d[temp]
                    d[temp] = 0
                temp -= 1
            if cap_sum > capacity:
                return False
        
        return True

#Second approach using dictionary and sorted
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        total_passengers = {}
        for trip in trips:
            if trip[1] not in total_passengers.keys():
                total_passengers[trip[1]] = +trip[0]
            else:
                total_passengers[trip[1]] += +trip[0]
            if trip[2] not in total_passengers.keys():
                total_passengers[trip[2]] = (-trip[0])
            else:
                total_passengers[trip[2]] += (-trip[0])
            
        sum_cap = 0
        for i in sorted (total_passengers):
            sum_cap += total_passengers[i]
            if sum_cap > capacity:
                return False
            
        return True
                
#Third Approach using list and set
class Solution:
    def carPooling(self, trips: List[List[int]], capacity: int) -> bool:
        total_passengers = [0]*1001
        s = set()
        for trip in trips:
            total_passengers[trip[1]] += +trip[0]
            total_passengers[trip[2]] += (-trip[0])
            s.add(trip[1])
            s.add(trip[2])
        
        print(s)
        sum_cap = 0
        for i in sorted(s):
            sum_cap += total_passengers[i]
            if sum_cap > capacity:
                return False
            
        return True
