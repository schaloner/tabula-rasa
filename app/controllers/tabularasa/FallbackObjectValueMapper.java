package controllers.tabularasa;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import be.objectify.led.Property;
import play.templates.JavaExtensions;

/**
 * objectify-led based implementation of a {@link ObjectValueMapper}.  The inspected object must use
 * {@link be.objectify.led.Property} annotations to get the benefit of automated mapping.
 * If no annotation is found, an attempt to navigate the object graph using the field names is made.
 *
 * @author Steve Chaloner (steve@objectify.be).
 * @author Manuel Bernhardt (bernhardt.manuel@gmail.com)
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
        if (value == null)
        {
            // try object graph
            value = getTargetObjectByPath(t, name);
        }
        return value == null ? "" : value;
    }

    /**
     * Gets the value of the field marked as property <i>name</i>, or null if it can't be found.
     *
     * @param t    the object containing the information
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

    private Object getTargetObjectByPath(T t,
                                         String name)
    {
        String[] path = name.split("\\.");
        Object obj = t;
        for (int i = 0; i < path.length; i++)
        {
            String s = path[i];
            try
            {
                Field f = obj.getClass().getField(s);
                if (i == (path.length - 1))
                {
                    try
                    {
                        Method getter = obj.getClass().getMethod("get" + JavaExtensions.capFirst(f.getName()));
                        return getter.invoke(obj, new Object[0]);
                    }
                    catch (NoSuchMethodException e)
                    {
                        return f.get(obj).toString();
                    }
                }
                else
                {
                    obj = f.get(obj);
                }
            }
            catch (Exception e)
            {
                // maybe we should throw a qualified exception of some sort here
                throw new RuntimeException(String.format("Field [%s] of class [%s] in path [%s] does not exist",
                                                         s,
                                                         obj.getClass().getName(),
                                                         name));
            }

        }

        return null;
    }
}
