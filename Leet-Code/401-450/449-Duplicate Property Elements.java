// You're given a list of elements. Each element has a unique id and 3 properties. Two elements are considered as duplicates if they share any
// of the 3 properties. Please write a function that takes the input and returns all the duplicates.

// Input:
// E1: id1, p1, p2, p3
// E2: id2, p1, p4, p5
// E3: id3, p6, p7, p8

// Output: {{id1, id2}, {id3}}

// Input:
// E1: id1, p1, p2, p3
// E2: id2, p1, p4, p5
// E3: id3, p5, p7, p8

// Output: {{id1, id3, id3}}
// Does anyone know how to solve this?

import java.util.Map;
import java.util.List;

class Entity {
    int id;
    List<String> properties;
    
    public Entity(int id, List<String> properties) {
        this.id = id;
        this.properties = properties;
    }
}

public class Main {
    public static void main (String[] args) {
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity(1, Arrays.asList("p1", "p2", "p3")));
        entities.add(new Entity(2, Arrays.asList("p1", "p4", "p5")));
        entities.add(new Entity(3, Arrays.asList("p6", "p7", "p8")));
        entities.add(new Entity(4, Arrays.asList("p5", "p9", "p10")));
        
        Solution solution = new Solution();
        List<Set<Integer>> duplicates = solution.findDuplicates(entities);
        
        System.out.println(duplicates);
    }
}

class Solution {
    public List<Set<Integer>> findDuplicates(List<Entity> entities) {
        Map<String, List<Integer>> propertyMap = new HashMap<>();
        
        for (Entity entity: entities) {
            for (String property: entity.properties) {
                propertyMap.computeIfAbsent(property, k -> new ArrayList<>()).add(entity.id);
            }
        }
        
        Set<Set<Integer>> duplicates = new HashSet<>();
        for(List<Integer> idList: propertyMap.values()) {
            if (idList.size() > 1) {
                duplicates.add(new HashSet<>(idList));
            }
        }
        
        return new ArrayList<>(duplicates);
    }
}