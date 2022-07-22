# interview Java 
## Please implement TimeAwareMapImpl.java to give timeaware feature to hashmap.

#### This time aware hashmap look like the normal java hashmap but the user need to provide additional time input whenever get or put action is performed.

For example, if we put below data

|  | Key | Time       | Value      |
|-----|------|------------|------------|
| 1   | Key1 | 2022-01-01 | Key1Value1 |
| 2   | Key1 | 2022-01-05 | Key1Value2 |
| 3   | Key2 | 2022-01-05 | Key2Value1 |
| 4   | Key1 | 2021-01-05 | Key1Value3 |

### 1. When I call the get(Key1, 2021-01-05), The method should return *Key1Value3*
#### Explanation :
Since the hash table contain exact same key and time as inserted in 4, the method will just return Key1Value3  

### 2. When I call the get(Key1, 2022-01-02), The method should return *Key1Value1*
#### Explanation :
The TimeAwareMapImpl will first work out all the records with key *Key1*. From those records, look at the one just before the time 2022-01-02. From the sample data, it should be Key1Value1 as the one just before 2022-01-02

### 3. When I call the get(Key2, 2022-01-02), The method should return null
#### Explanation :
Again, the TimeAwareMapImpl will first work out all the records with key *Key2*, However, there is not any record which is before 2022-01-02, so null will be returned 


