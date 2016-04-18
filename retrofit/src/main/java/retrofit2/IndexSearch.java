package retrofit2;

import java.util.NoSuchElementException;

public class IndexSearch {

	static int indexOf(Object[] array, Object toFind) {
	    for (int i = 0; i < array.length; i++) {
	      if (toFind.equals(array[i])) return i;
	    }
	    throw new NoSuchElementException();
	  }

}
