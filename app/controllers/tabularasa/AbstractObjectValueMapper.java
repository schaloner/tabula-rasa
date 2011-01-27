package controllers.tabularasa;

import com.google.gson.GsonBuilder;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public abstract class AbstractObjectValueMapper<T> implements ObjectValueMapper<T>
{
    public String getAsString(T t,
                              String name)
    {
        Object o = getByName(t,
                             name);
        return o == null ? "" : o.toString();
    }
}
