package controllers.tabularasa;

import be.objectify.led.Property;

import java.lang.reflect.Field;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public class FallbackObjectValueMapper<T> extends AbstractObjectValueMapper<T>
{
    public Object getByName(T t,
                            String name)
    {
        Object value = getTargetObject(t, name);
        return value == null ? "" : value;
    }

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
