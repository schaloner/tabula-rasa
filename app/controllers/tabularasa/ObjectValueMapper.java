package controllers.tabularasa;

/**
 * ObjectValueMappers are used to pull information out of objects, but do not necessarily need to return what they pull
 * out.  This is a useful layer for transforming information.
 *
 * @author Steve Chaloner (steve@objectify.be).
 */
public interface ObjectValueMapper<T>
{
    /**
     * Obtain the value denoted by <i>name</i> from <i>t</i>.  How you make the link between the target member and the
     * name is left up to the implementer.
     *
     * @param t the object containing the information
     * @param name the identifier for that information
     * @return the mapped value, or null if nothing valid is available
     */
    Object getByName(T t,
                     String name);

    /**
     * Obtain the value denoted by <i>name</i> from <i>t</i> as a String.  How you make the link between the target
     * member and the name is left up to the implementer.
     *
     * @param t the object containing the information
     * @param name the identifier for that information
     * @return the mapped value as a string, or null if nothing valid is available
     */
    String getAsString(T t,
                       String name);
}
