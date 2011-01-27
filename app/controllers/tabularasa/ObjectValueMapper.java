package controllers.tabularasa;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public interface ObjectValueMapper<T>
{
    Object getByName(T t,
                     String name);

    String getAsString(T t,
                       String name);
}
