package controllers.tabularasa;

import be.objectify.led.Property;

import java.lang.reflect.Field;

/**
 * objectify-led based implementation of a {@link ObjectValueMapper}.  The inspected object must use
 * {@link be.objectify.led.Property} annotations to get the benefit of automated mapping.
 *
 * @author Steve Chaloner (steve@objectify.be).
 */
public class FallbackObjectValueMapper<T> extends AbstractObjectValueMapper<T>
{
    /**
     * {@inheritDoc}
     */
    public Object getByName(T t,
                            String name)
    {
        Object value = getTargetObject(t, name);
        return value == null ? "" : value;
    }

    /**
     * Gets the value of the field marked as property <i>name</i>, or null if it can't be found.
     *
     * @param t the object containing the information
     * @param name the identifier for that information
     * @return the value of the property
     */
    private Object getTargetObject(T t,
                                   String name)
    {
        Object target = null;

        if (t != null)
        {
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0, fieldsLength = fields.length; target == null && i < fieldsLength; i++)
            {
                Field field = fields[i];
                if (field.isAnnotationPresent(Property.class) &&
                    field.getAnnotation(Property.class).value().equals(name))
                {
                    try
                    {
                        target = field.get(t);
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        return target;
    }
}
