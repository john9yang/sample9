package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;

public class ClassDemo {

    public static void main(String[] args) {
        TypeVariable[] typeVariables = List.class.getTypeParameters();
        System.out.println(typeVariables.length);

        TypeVariable[] typeVariables1 = String.class.getTypeParameters();
        Method[] methods1 = String.class.getMethods();
        System.out.println(typeVariables1.length);

        TypeVariable[] typeVariables2 = HashMap.class.getTypeParameters();
        System.out.println(typeVariables2.length);
    }
}
