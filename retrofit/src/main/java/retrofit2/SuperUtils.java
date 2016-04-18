package retrofit2;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

public class SuperUtils {

	public static String typeToString(Type type) {
	    return type instanceof Class ? ((Class<?>) type).getName() : type.toString();
	  }

	/** Returns true if {@code a} and {@code b} are equal. */
	public static boolean equals(Type a, Type b) {
	    if (a == b) {
	      return true; // Also handles (a == null && b == null).
	
	    } else if (a instanceof Class) {
	      return a.equals(b); // Class already specifies equals().
	
	    } else if (a instanceof ParameterizedType) {
	      if (!(b instanceof ParameterizedType)) return false;
	      ParameterizedType pa = (ParameterizedType) a;
	      ParameterizedType pb = (ParameterizedType) b;
	      return equal(pa.getOwnerType(), pb.getOwnerType())
	          && pa.getRawType().equals(pb.getRawType())
	          && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments());
	
	    } else if (a instanceof GenericArrayType) {
	      if (!(b instanceof GenericArrayType)) return false;
	      GenericArrayType ga = (GenericArrayType) a;
	      GenericArrayType gb = (GenericArrayType) b;
	      return equals(ga.getGenericComponentType(), gb.getGenericComponentType());
	
	    } else if (a instanceof WildcardType) {
	      if (!(b instanceof WildcardType)) return false;
	      WildcardType wa = (WildcardType) a;
	      WildcardType wb = (WildcardType) b;
	      return Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds())
	          && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds());
	
	    } else if (a instanceof TypeVariable) {
	      if (!(b instanceof TypeVariable)) return false;
	      TypeVariable<?> va = (TypeVariable<?>) a;
	      TypeVariable<?> vb = (TypeVariable<?>) b;
	      return va.getGenericDeclaration() == vb.getGenericDeclaration()
	          && va.getName().equals(vb.getName());
	
	    } else {
	      return false; // This isn't a type we support!
	    }
	  }

	private static boolean equal(Object a, Object b) {
	    return a == b || (a != null && a.equals(b));
	  }

	public SuperUtils() {
		super();
	}

}