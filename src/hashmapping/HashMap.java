package hashmapping;
/**
 * 
 * This is an implementation of a fixed size hashMap that can be parameterized for any data type.
 * Some assumption were made when dealing with collisions. I chose to deal with it by linking entries to eachother
 * in each index of the map. I thought about trying out a balanced tree for efficiency but that would
 * only be useful for a large number of collisions which kind of defeats the purpose of a hashMap.
 * Assuming the user is aware of their data set and they use an appropriate sized hashMap, The lookUp and placement time for 
 * an element should be about constant time. If the user chooses a small size relative to their data it's
 * more O(n) speed.
 * @author Aaron Havens
 *
 * @param <T> Generic type T for HashMap Class
 */
public class HashMap<T>{
	/**
	 * Array of Entry objects to be used as the hashMap
	 */
	private Entry<T>[] map;
	/**
	 * size of the hashmap
	 */
	private final int size;
	/**
	 * Number of occupied indices of the map
	 */
	private int elements;
	/**
	 * Constructs a hashMap of size 'size', default 10 if size is 0 or negative
	 * @param size number of indices or elements in hashMap
	 */
	@SuppressWarnings("unchecked")
	public HashMap(int size){
		if(size <=0){
			this.size = 10;
		}
		else{
			this.size = size;
		}
		map = new Entry[this.size];
	}
	/**
	 * Adds value to hashMap. If a duplicate key is found (collision) already in the map, the entry is overridden.
	 * If the value to be set is null, this method fails and returns false. If set is successful it return true
	 * @param key given String key to look up
	 * @param value Arbitrary data reference type depending on parameterization of hashMap
	 * @return Returns true if set was successful. False if not.
	 */
	public boolean set(String key, T value){
		//check null input;
		if(value == null){
			return false;}
		int index;
		index = abs(key.hashCode()%(size));
		//Entry to be added
		Entry<T> newE = new Entry<T>(key, value);
		//Check if nothing is in the index already.
		if(map[index] == null){
			map[index] = newE;
			elements ++;
			return true;
		}
		else{
			//start from first entry. Sifter will sift through each linked element to find the end.
			Entry<T> sifter = map[index];
			Entry<T> previous = null;
			while(sifter != null){
				//check if key matches. Might be a duplicate
				if(key.equals(map[index].getKey())){
					//Maybe the first entry of this index. Protocol becomes a little different
					if(previous == null){
						newE.next = sifter.next;
						map[index] = newE;
						return true;
					}
					//Found a duplicate key. Replace entry with new entry. Have previous next point to
					//new Entry and its next to the sequential entry.
					else{
						previous = newE;
						newE.next = sifter.next();
						return true;
					}
				}
				//keep siftin'
				previous = sifter;
				sifter = sifter.next();
			}
			//after all that sifting. We've reached the end. Pop that Entry on the end.
			previous.next = newE;
			return true;
		}		
	}
	/**
	 * Return the Value associated with the given key. If the key doesn't exist, it returns null
	 * @param key given key to hash and check
	 * @return The desired entry value or null if not found
	 */
	public T get(String key){
		int index;
		index = abs(key.hashCode())%(size);
		Entry<T> sifter = map[index];
		//if the index is null, safe to say its not there
		if(sifter == null){
			return null;
		}
		//sifting as usual
		else{
			while(sifter != null){
				if(sifter.getKey().equals(key)){
					return sifter.getValue();
				}
				sifter = sifter.next();
			}
		}
		//nothing found
		return null;
	}
	/**
	 * Very similar to Set(key, value). Given a key, find the associated entry, if it exists, and removes it.
	 * also returns the deleted value. return null if the key isn't found.
	 * @param key given key to hash and check
	 * @return Removed entry or null if not found.
	 */
	public T delete(String key){
		int index;
		index = abs(key.hashCode()%(size));
		Entry<T> out;
		if(map[index] == null){
			return null;
		}
		else{
			Entry<T> sifter = map[index];
			Entry<T> previous = null;
			while(sifter != null){
				if(sifter.getKey().equals(key)){
					if(previous == null){
						out = sifter;
						map[index] = sifter.next();
						//if we remove the last entry in an index, must decrement element count.
						if(map[index] == null){
							elements --;
						}
						return out.getValue();
					}
					else{
						out = sifter;
						previous.next = sifter.next();
						if(map[index] == null){
							elements--;
						}
						return out.getValue();
					}
				}
				previous = sifter;
				sifter = sifter.next;
			}
		}
		return null;
	}
	/**
	 * Returns the quotient of the number of occupied indices and the max size.
	 * @return occupied indices / size
	 */
	public float load(){
		return ((float)elements)/size;
	}
	/**
	 * This method takes care of any negative hashs that hashCode produces. A simple
	 * absolute value function.
	 * @param hash hashed value
	 * @return absolute value of  hashed value.
	 */
	private int abs(int hash){
		if(hash < 0){
			return hash*-1;
		}
		return hash;
	}
	/**
	 * 
	 * A nested class Entry for a given Value and key to be placed the hashMap.
	 * This is also setup to be a linked data structure. Although this may infringe
	 * upon the one-class rule, I think this makes the code easier to read.
	 *
	 * @param <V> Using the generic type for value allows us to parameterize any hashMap we instantiate.
	 */
	private static class Entry<V>{
		/**
		 * given key identity
		 */
		private String key;
		/**
		 * Generic data reference for Value
		 */
		private V value;
		/**
		 * Reference to the next Entry in the linked list
		 */
		private Entry<V> next;
		/**
		 * Constructs an Entry object to placed in the hashMap
		 * @param key given key to hash
		 * @param value generic type value
		 */
		public Entry(String key, V value){
			this.key = key;
			this.value = value;
			next = null;
		}
		/**
		 * Returns the key of this entry
		 * @return the String key for this entry
		 */
		public String getKey(){
			return key;
		}
		/**
		 * Return the generic value for this entry
		 * @return generic type value for this entry.
		 */
		public V getValue(){
			return value;
		}
		/**
		 * Returns the next Entry in the link.
		 * @return Entry that is referred to by this Entry's next pointer.
		 */
		public Entry<V> next(){
			return next;
		}
	}
}


