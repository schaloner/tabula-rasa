package controllers;

import controllers.tabularasa.TableController;
import models.tabularasa.TableModel;
import play.mvc.*;

import java.util.*;

import models.*;
import utils.LedPropertyParser;

public class PersonController extends Controller {

    private static final String[] STEVE_COLUMNS = {"name", "location"};
    private static final String[] GREET_COLUMNS = {"location", "name"};

    private static final String VIEW_ID = "personView";

    private static final String TABLE_ID = "personTable";

    public static void index(String userName)
    {
        // in a real application, userName would come from the current user
        userName = (userName == null) ? "steve" : userName;

        ExampleUser user = ExampleUser.findByUserName(userName);
        TableModel tableModel = TableController.getTableModel(user.tableOwner,
                                                              VIEW_ID,
                                                              TABLE_ID,
                                                              "steve".equals(userName) ? STEVE_COLUMNS : GREET_COLUMNS);

        List<PersonalisedTablesPerson> people = PersonalisedTablesPerson.findAll();

        render(tableModel,
               people);
    }

    @Util
    public static Object getByColumnKey(PersonalisedTablesPerson person,
                                        String columnKey)
    {
        return LedPropertyParser.getPropertyValue(person,
                                                  columnKey);
    }
}