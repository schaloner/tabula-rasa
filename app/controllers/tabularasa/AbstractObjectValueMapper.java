package controllers.tabularasa;

import com.google.gson.GsonBuilder;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public abstract class AbstractObjectValueMapper<T> implements ObjectValueMapper<T>
{
    /**
     * Invokes toString() on non-null objects obtained from {@link #getByName(Object, String)}.
     *
     * @param t the object containing the information
     * @param name the identifier for that information
     * @return the toString() value of the mapped object, or an empty string if the mapped object is null
     */
    public String getAsString(T t,
                              String name)
    {
        Object o = getByName(t,
                             name);
        return o == null ? "" : o.toString();
    }
}
