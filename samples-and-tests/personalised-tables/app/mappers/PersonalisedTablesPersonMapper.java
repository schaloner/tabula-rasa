package mappers;

import controllers.tabularasa.AbstractObjectValueMapper;
import models.PersonalisedTablesPerson;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
public class PersonalisedTablesPersonMapper extends AbstractObjectValueMapper<PersonalisedTablesPerson>
{
    public Object getByName(PersonalisedTablesPerson personalisedTablesPerson,
                            String name)
    {
        Object value = null;
        if ("name".equals(name))
        {
            value = personalisedTablesPerson.name;
        }
        else if ("location".equals(name))
        {
            value = personalisedTablesPerson.location;
        }
        return value;
    }
}
